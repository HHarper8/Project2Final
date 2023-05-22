package src.PacManGame;

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
        while (currLevel != null) {
            // Call Game with currLevel
            String fillerVariable = "call game here";
            currLevel = loader.getNextLevel();
        }

        return null;

    }

}
