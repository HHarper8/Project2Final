// PacGrid.java

package src.PacManGame;


import ch.aplu.jgamegrid.*;
import java.util.Properties;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class PacManGameGrid
{
  private int nbHorzCells;
  private int nbVertCells;

  private int[][] mazeArray;
  private Game game;
  private Properties properties;





  public PacManGameGrid(Game game, Properties properties) {
    this.game = game;
    this.properties = properties;
    this.nbHorzCells = game.getNumHorzCells();
    this.nbVertCells = game.getNumVertCells();

  }

  /**
   * Reads in all actors to the game
   */
  public void readAllActorsToGame() {


    readActorsToGame("Wall");
    readActorsToGame("Pills");
    readActorsToGame("Gold");
    readActorsToGame("IceCube");
    readPortalsToGame();

    readActorsToGame("Troll");
    readActorsToGame("PacMan");
    readActorsToGame("TX5");




  }

  /**
   * reads all the portals into the game, assuming that the level-checking has been done beforehand
   * and there are the correct amount of portals
   */
  private void readPortalsToGame() {
    readPortalPairToGame("WhitePortal");
    readPortalPairToGame("YellowPortal");
    readPortalPairToGame("DarkGrayPortal");
    readPortalPairToGame("DarkGoldPortal");
  }

  private void readPortalPairToGame(String name) {
    Location actorLocation;
    String rawLocationString = getLocationString(name);
    if (rawLocationString != null && !rawLocationString.equals("")) {
      System.out.println("###DEBUG (line 69, PacManGameGrid)### "+name+" is being added.");
      String[] locations = rawLocationString.split(";");


      Portal[] portalPair = new Portal[2];

      for (int i = 0; i < 2; i++) {


        String[] location = locations[i].split(",");
        int x = Integer.parseInt(location[0]);
        int y = Integer.parseInt(location[1]);
        if (x < 0 || y < 0 || x >= nbHorzCells || y >= nbVertCells) {
          System.out.println("###DEBUG (line 82, PacManGameGrid)### ("+x+","+y+") out of board.");
          continue;
          //out of the board
        }
        actorLocation = new Location(x, y);
        System.out.println("###DEBUG (line 87, PacManGameGrid)### "+actorLocation+" is being added.");
        portalPair[i]=(Portal) spawnActor(name, actorLocation);

      }
      // now make the portal pairs have each other as attributes
      System.out.println("###DEBUG (line 89, PacManGameGrid)### "+portalPair);
    }




    return;
  }



  private String getLocationString(String name) {
    String str = properties.getProperty(name+".location");
    return str;
  }

  /**
   * Adds the actors into the game as a GameActor
   * @param name: Actor name
   * @param location: Actor location
   */
  private GameActor spawnActor(String name, Location location) {
    GameActor actor = null;
    if (name.equals("PacMan")) {
      actor = new PacActor();
      game.setPacActor((PacActor) actor);
      //TODO: REMOVE THIS
      System.out.println("###DEBUG### PAC ACTOR SET, line 119 PMGG");
    } else if (name.equals("Troll")) {
      actor = new Troll();
      game.getMonsters().add((Monster) actor);
    } else if (name.equals("TX5")) {
      actor = new TX5();
      game.getMonsters().add((Monster) actor);
    } else if (name.equals("Pills")) {
      actor = new Pill();
    } else if (name.equals("Gold")) {
      actor = new Gold();
    } else if (name.equals("IceCube")) {
      actor = new IceCube();
    } else if (name.equals("Wall")){
      actor = new Wall();
    } else if (name.equals("WhitePortal")) {
      actor = new Portal(PortalType.WHITE);
    } else if (name.equals("YellowPortal")) {
      actor = new Portal(PortalType.YELLOW);
    } else if (name.equals("DarkGoldPortal")) {
      actor = new Portal(PortalType.DARK_GOLD);
    } else if (name.equals("DarkGrayPortal")) {
      actor = new Portal(PortalType.DARK_GRAY);
    } else {
      ;
    }


    if (actor == null) {
      return null;
    }
    if (actor instanceof Monster) {
      game.addActor(actor, location, Location.NORTH);
    } else {
      game.addActor(actor, location);
    }

    return null;
  }

  /**
   * Spawns a specific singular from properties
   */
  private void readActorsToGame(String name) {
    Location actorLocation;
    String rawLocationString = getLocationString(name);
    if (rawLocationString != null) {
      String[] locations = rawLocationString.split(";");
      for (String locationString: locations) {
        String[] location = locationString.split(",");
        int x = Integer.parseInt(location[0]);
        int y = Integer.parseInt(location[1]);
        if (x < 0 || y < 0 || x >= nbHorzCells || y >= nbVertCells) {
          continue;
          //out of the board
        }
        actorLocation = new Location(x, y);
        spawnActor(name, actorLocation);
      }
    }
    return;
  }



}
