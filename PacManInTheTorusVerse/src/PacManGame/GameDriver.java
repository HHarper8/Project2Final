package src.PacManGame;

import src.PacManGame.utility.GameCallback;
import src.gamevalidator.LevelFileReader;
import src.gamevalidator.LevelLoader;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;

public class GameDriver {

    public File runGame(File file) {

        // Load maps in to levelLoader
        LevelLoader loader = LevelLoader.getInstance();
        File errorFile = loader.loadLevels(file);


        //
        if (errorFile != null) {
            System.out.println("###DEBUG (line 20, GameDriver)### errorFile exists");
            return errorFile;
        }

        // Iterate through all maps, running game
        Properties currLevel = loader.getNextLevel();

        while (currLevel != null) {
            // Call Game with currLevel
            GameCallback gameCallback = new GameCallback();
            System.out.println("###DEBUG (line28, GameDriver)### MAKING A NEW GAME");
            new Game(gameCallback, currLevel);
            currLevel = loader.getNextLevel();
        }




        return null;

    }

}
