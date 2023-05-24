// PacActor.java
// Used for PacMan

package src.PacManGame;

import ch.aplu.jgamegrid.*;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class PacActor extends GameActor implements GGKeyRepeatListener {
  private int idSprite = 0;
  private ArrayList<Location> visitedList = new ArrayList<Location>();
  private List<String> propertyMoves = new ArrayList<>();
  private int propertyMoveIndex = 0;
  private final int listLength = 10;
  private int seed;
  private Random randomiser = new Random();
  private static final boolean CAN_ROTATE = true;
  private static final String IMAGE_NAME = "pacpix.gif";
  private static final int NB_SPRITES = 4;
  private static final Sprite SPRITE = new Sprite(CAN_ROTATE, IMAGE_NAME, NB_SPRITES);
  private boolean teleported = false;

  public PacActor() {
    super(SPRITE);
    this.setCurrentName("PacMan");
  }
  private boolean isAuto = false;
  public void setAuto(boolean auto) {
    isAuto = auto;
  }
  public void setSeed(int seed) {
    this.seed = seed;
    randomiser.setSeed(seed);
  }
  public void setPropertyMoves(String propertyMoveString) {
    if (propertyMoveString != null) {
      this.propertyMoves = Arrays.asList(propertyMoveString.split(","));
    }
  }

  public void keyRepeated(int keyCode)
  {
    if (isAuto) {
      return;
    }
    if (isRemoved())
      return;
    Location next = null;
    switch (keyCode)
    {
      case KeyEvent.VK_LEFT:
        next = getLocation().getNeighbourLocation(Location.WEST);
        setDirection(Location.WEST);
        break;
      case KeyEvent.VK_UP:
        next = getLocation().getNeighbourLocation(Location.NORTH);
        setDirection(Location.NORTH);
        break;
      case KeyEvent.VK_RIGHT:
        next = getLocation().getNeighbourLocation(Location.EAST);
        setDirection(Location.EAST);
        break;
      case KeyEvent.VK_DOWN:
        next = getLocation().getNeighbourLocation(Location.SOUTH);
        setDirection(Location.SOUTH);
        break;
    }
    if (next != null && canMove(next))
    {
      setLocation(next);
      eatPill(next);
      handlePortalCollision(next);
    }
  }

  public void act() {
    show(idSprite);
    idSprite++;
    if (idSprite == NB_SPRITES)
      idSprite = 0;

    if (isAuto) {
      moveInAutoMode();
    }



    // Should we make up a new Log class that handles and redirects logging events to GameCallBack?
    //this is actually in the wrong place I believe as this is an error in our log file - Aryan
    getGame().getGameCallback().pacManLocationChanged(getLocation(), getGame().getScore(),
            getGame().getNumberGoalItemsConsumed());
  }
  private Location closestPillLocation() {
    int currentDistance = 1000;
    Location currentLocation = null;
    List<Location> pillAndItemLocations = getGame().getPillAndItemLocations();
    for (Location location: pillAndItemLocations) {
      int distanceToPill = location.getDistanceTo(getLocation());
      if (distanceToPill < currentDistance) {
        currentLocation = location;
        currentDistance = distanceToPill;
      }
    }
    return currentLocation;
  }
  private void followPropertyMoves() {
    String currentMove = propertyMoves.get(propertyMoveIndex);
    switch(currentMove) {
      case "R":
        turn(90);
        break;
      case "L":
        turn(-90);
        break;
      case "M":
        Location next = getNextMoveLocation();
        if (canMove(next)) {
          setLocation(next);
          eatPill(next);
          handlePortalCollision(next);
        }
        break;
    }
    propertyMoveIndex++;
  }

  private void moveInAutoMode() {
    if (propertyMoves.size() > propertyMoveIndex) {
      followPropertyMoves();
      return;
    }
    Location closestPill = closestPillLocation();
    double oldDirection = getDirection();

    Location.CompassDirection compassDir =
            getLocation().get4CompassDirectionTo(closestPill);
    Location next = getLocation().getNeighbourLocation(compassDir);
    setDirection(compassDir);
    if (!isVisited(next) && canMove(next)) {
      setLocation(next);
    } else {
      // normal movement
      int sign = randomiser.nextDouble() < 0.5 ? 1 : -1;
      setDirection(oldDirection);
      turn(sign * 90);  // Try to turn left/right
      next = getNextMoveLocation();
      if (canMove(next)) {
        setLocation(next);
      } else {
        setDirection(oldDirection);
        next = getNextMoveLocation();


        if (canMove(next)) // Try to move forward
        {
          setLocation(next);
        } else {
          setDirection(oldDirection);
          turn(-sign * 90);  // Try to turn right/left
          next = getNextMoveLocation();
          if (canMove(next)) {
            setLocation(next);
          } else {
            setDirection(oldDirection);
            turn(180);  // Turn backward
            next = getNextMoveLocation();
            setLocation(next);
          }
        }
      }
    }
    eatPill(next);
    addVisitedList(next);
  }
  private void addVisitedList(Location location)
  {
    visitedList.add(location);
    if (visitedList.size() == listLength)
      visitedList.remove(0);
  }
  private boolean isVisited(Location location)
  {
    for (Location loc : visitedList)
      if (loc.equals(location))
        return true;
    return false;
  }

  private boolean canMove(Location location)
  {
    Actor wall = getGame().getOneActorAt(location, Wall.class);
    if ( wall != null || location.getX() >= getGame().getNumHorzCells()
            || location.getX() < 0 || location.getY() >= getGame().getNumVertCells() || location.getY() < 0)
      return false;
    else
      return true;
  }

  private void handlePortalCollision(Location next) {
    Actor portalActor = getGame().getOneActorAt(next, Portal.class);
    if (portalActor != null && !teleported) {
      teleported = true;
      Portal portal = (Portal)portalActor;
      Location loc = portal.getOtherPortalLocation();

      setLocation(loc);
      System.out.println("###DEBUG (line 212, PacActor)####"+"PacMan Teleported to "+loc);

     return;
    }
    if (portalActor == null && teleported) {
      teleported = false;
    }

    return;
  }


  private void eatPill(Location location)
  {
    ArrayList<Actor> items = getGame().getActorsAt(location,Item.class);
    for(Actor actor: items) {
      if (actor == null) {
        continue;
      }
      Item item = (Item)actor;
      item.beConsumed();
      getGame().getGameCallback().pacManEatPillsAndItems(location, item.getCurrentName());
      String title = "[PacMan in the Multiverse] Current score: " + getGame().getScore();
      gameGrid.setTitle(title);
    }
  }

}
