// PacMan.java
// Simple PacMan implementation

package src.PacManGame;

import ch.aplu.jgamegrid.*;
import src.PacManGame.utility.GameCallback;


import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class Game extends GameGrid
{
  private static int nbHorzCells = 20;
  private static int nbVertCells = 11;

  protected GameActorFactory grid;

  // References to monsters and pacActor
  protected PacActor pacActor;
  private ArrayList<Monster> Monsters =  new ArrayList<Monster>();

  // Game logic variables
  private GameCallback gameCallback;
  private Properties properties;
  private int seed = 30006;

  // score
  private int score = 0;
  private int numberGoalItemsConsumed = 0;
  private int maxPillsAndItems = 0;

  // background
  private GGBackground background = getBg();


  public static int getNbHCells() {
    return nbHorzCells;
  }


  public static int getNbVCells() {
    return nbVertCells;
  }

  public Game(GameCallback gameCallback, Properties properties)
  {
    //Setup game
    super(nbHorzCells, nbVertCells, 20, false);
    this.gameCallback = gameCallback;
    this.properties = properties;
    setSimulationPeriod(100);
    setTitle("[PacMan in the Multiverse]");


    nbHorzCells = Integer.parseInt(properties.getProperty("game.width"));
    nbVertCells = Integer.parseInt(properties.getProperty("game.height"));




    // create the LevelLoader
    grid = new GameActorFactory(this,  this.properties);
    grid.readAllActorsToGame();

    //Setup for auto test
    pacActor.setPropertyMoves(properties.getProperty("PacMan.move"));
    pacActor.setAuto(Boolean.parseBoolean(properties.getProperty("PacMan.isAuto")));

    drawGrid(background);

    //Setup Random seeds

    pacActor.setSeed(seed);
    pacActor.setSlowDown(3);

    addKeyRepeatListener(pacActor);
    setKeyRepeatPeriod(150);

    for(Monster monster: Monsters){
        monster.setSeed(seed);
        monster.setSlowDown(3);
        if(monster instanceof TX5){
          monster.stopMoving(5);
        }
    }



    //Run the game
    doRun();
    show();
    // Loop to look for collision in the application thread
    // This makes it improbable that we miss a hit
    boolean hasPacmanBeenHit;
    boolean hasPacmanEatAllPills;

    maxPillsAndItems = Item.getGoalItemCount();
    
    do {
      hasPacmanBeenHit = haspacmanbeenhit();
      hasPacmanEatAllPills = haspacmaneatinpills();

      delay(10);
    } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
    delay(120);


    // End game
    Location loc = pacActor.getLocation();
    for(Monster monster : Monsters){
      monster.setStopMoving(true);
    }
    pacActor.removeSelf();
    String title = "";
    if (hasPacmanBeenHit) {
      background.setPaintColor(Color.red);
      title = "GAME OVER";
      addActor(new Actor("sprites/explosion3.gif"), loc);
    } else if (hasPacmanEatAllPills) {
      background.setPaintColor(Color.yellow);
      title = "YOU WIN";
    }
    setTitle(title);
    gameCallback.endOfGame(title);

    doPause();
  }

  /**
   * Gets all locations of pills and items (excluding iceCubes)
   * @return Location ArrayList: all pills and item locations
   */

  public ArrayList<Location> getPillAndItemLocations() {

    ArrayList<Actor> pills = getActors(Pill.class);
    ArrayList<Actor> golds = getActors(Gold.class);

    ArrayList<Location> locations= new ArrayList<Location>();
    Location location;

    for (Actor item: pills) {
      location = item.getLocation();
      locations.add(location);
    }

    for (Actor item: golds) {
      location = item.getLocation();
      locations.add(location);
    }

    return locations;
  }

  /**
   * Finds and returns all gold locations
   * @return ArrayList of gold locations
   */
  public ArrayList<Location> getgoldlocation(){
    ArrayList<Actor> gold = getActors(Gold.class);

    ArrayList<Location> answer  = new ArrayList<>();
    Location location;
    for (Actor item: gold){
      location = item.getLocation();
      answer.add(location);
    }

    return answer;
  }


  private void drawGrid(GGBackground bg) {

    bg.clear(Color.gray);
    bg.setPaintColor(Color.white);
    for (int y = 0; y < nbVertCells; y++) {
      for (int x = 0; x < nbHorzCells; x++) {
        bg.setPaintColor(Color.white);
        Location location = new Location(x, y);

        bg.fillCell(location, Color.lightGray);
      }
    }
  }

  /**
   * Detects collisions between pacActor and monsters
   * @return boolean: True if a collision is detected, otherwise false
   */
  private boolean haspacmanbeenhit() {

    for(Monster monster: Monsters){
      if(monster.getLocation().equals(pacActor.getLocation())) {
        return true;
      }
    }
    return false;
  }


  /**
   * Checks if pacActor has eaten all pills and gold items
   * @return boolean: True if all goal items have been consumed, otherwise false
   */
  public boolean haspacmaneatinpills(){
    if(numberGoalItemsConsumed >= maxPillsAndItems){
      return true;
    }else{
      return false;
    }
  }

  public void increaseScore(int score) {
    this.score += score;
  }

  public void increaseNumberGoalItemsConsumed() {
    this.numberGoalItemsConsumed +=1;
  }

  public GameCallback getGameCallback() { return gameCallback; }

  public void setPacActor(PacActor pacActor) { this.pacActor = pacActor; }

  public int getNumberGoalItemsConsumed() { return this.numberGoalItemsConsumed; }

  public int getNumHorzCells () { return this.nbHorzCells; }
  public int getNumVertCells () { return this.nbVertCells; }




  public int getScore() { return this.score; }
  public ArrayList<Monster> getMonsters() {
    return Monsters;
  }

  /**
   * Checks if there is a gold piece at a location
   * @param location: a given location to see if gold is located at
   * @return boolean: True if a gold is at that location, otherwise false
   */
  public boolean isGoldAtLocation(Location location) {
    if (getActorsAt(location, Gold.class) != null) {
      return true;
    } else {
      return false;
    }
  }

  public GameActorFactory getGrid() {
    return grid;
  }
}

