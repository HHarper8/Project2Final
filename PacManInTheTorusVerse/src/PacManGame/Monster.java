// Monster.java
// Used for PacMan

package src.PacManGame;



import ch.aplu.jgamegrid.*;
import java.util.*;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)


public class Monster extends GameActor {

  private ArrayList<Location> visitedList = new ArrayList<Location>();
  private final int listLength = 10;
  private boolean stopMoving = false;
  private int seed = 0;

  private Random randomiser = new Random(0);
  private int SECOND_TO_MILLISECONDS = 1000;



  public Monster(Sprite sprite) {
    super(sprite);
  }

  /**
   * Stops a monster moving for a number of seconds
   *
   * @param seconds: Number of seconds the Monster should stop moving by
   */
  public void stopMoving(int seconds) {
    this.stopMoving = true;
    Timer timer = new Timer(); // Instantiate Timer Object
    int SECOND_TO_MILLISECONDS = 1000;
    final Monster monster = this;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        monster.stopMoving = false;
      }
    }, seconds * SECOND_TO_MILLISECONDS);
  }

  /**
   * Sets seed for a monster
   *
   * @param seed: default or given seed
   */
  public void setSeed(int seed) {
    this.seed = seed;
    randomiser.setSeed(seed);
  }

  public void setStopMoving(boolean stopMoving) {
    this.stopMoving = stopMoving;
  }

  /**
   * Defines how the monster should move at each time step
   */
  public void act() {
    if (stopMoving) {
      return;
    }

      walkApproach();

    if (getDirection() > 150 && getDirection() < 210)
      setHorzMirror(false);
    else
      setHorzMirror(true);
  }


  public void walkApproach() {return;}


  public String getType() {
    return getCurrentName();
  }

  /**
   * Adds a location to an arraylist of visited locations to prevent monster backtracking
   *
   * @param location: A location on the game grid
   */
  protected void addVisitedList(Location location) {
    visitedList.add(location);
    if (visitedList.size() == listLength)
      visitedList.remove(0);
  }

  /**
   * Checks if a given location has been previously visited
   *
   * @param location: A given or current location
   * @return: Boolean: True if Location has been visited previously, otherwise false
   */
  protected boolean isVisited(Location location) {
    for (Location loc : visitedList)
      if (loc.equals(location))
        return true;
    return false;
  }

  /**
   * Determines if location is a valid move
   *
   * @param location: Desired location to move to
   * @return boolean: True if location is a valid move location, otherwise false
   */
  protected boolean canMove(Location location) {
    Actor wall = getGame().getOneActorAt(location, Wall.class);
    if (wall != null || location.getX() >= getGame().getNumHorzCells()
            || location.getX() < 0 || location.getY() >= getGame().getNumVertCells() || location.getY() < 0)
      return false;
    else
      return true;
  }

  public Random getRandomiser() {
    return randomiser;
  }



}
