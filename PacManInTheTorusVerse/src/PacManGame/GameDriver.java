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

        if (errorFile != null) {
            return errorFile;
        }



        // Iterate through all maps, running game
        Properties currLevel = loader.getNextLevel();
        int levelCounter = 0;
        while (currLevel != null) {


            // Call Game with currLevel


            GameCallback gameCallback = new GameCallback();


            Game game = new Game(gameCallback, currLevel);
            if(!game.isGameWon()) {
                break;
            }
            currLevel = loader.getNextLevel();
            levelCounter++;
        }




        return null;

    }

}
