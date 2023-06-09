package src.gamevalidator;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LevelLoader {

    private static LevelLoader instance = null;
    private List<Properties> allLevels;
    private int numLevels = 0;

    private LevelLoader() {}

    public static LevelLoader getInstance() {
        if (instance == null) {
            instance = new LevelLoader();
        }
        return instance;
    }

    public File loadLevels(File gameFile) {

        // Get validity checker
        CheckerStrategy strategy = CheckerStrategyFactory.getInstance().getCheckerStrategy(gameFile);

        // If it's a game folder and fails the game check, return game folder
        if (!strategy.checkValidity(gameFile)) {
            return gameFile;
        }

        // For each file in game folder, check validity and return level folder if invalid, otherwise read into properties
        List<File> levels = Arrays.asList(gameFile.listFiles());
        CheckerStrategy levelChecker = CheckerStrategyFactory.getInstance().getCheckerStrategy(levels.get(0));

        for (File level: levels) {
            if (!levelChecker.checkValidity(level)) {
                return level;
            }
            else {
                Properties levelProperties = createProperties(level);
                allLevels.add(numLevels, levelProperties);
                numLevels++;
            }
        }

        return null;
    }

    private Properties createProperties(File levelFile) {

        LevelFileReader level = new LevelFileReader(levelFile);
        Properties levelProperties = new Properties();

        levelProperties.setProperty("game.width", Integer.toString(level.getGameWidth()));
        levelProperties.setProperty("game.height", Integer.toString(level.getGameHeight()));

        levelProperties.setProperty("pacman.location", locationToProperties(level.getPacLocations()));
        levelProperties.setProperty("TX5.location", locationToProperties(level.getTX5Locations()));
        levelProperties.setProperty("Troll.location", locationToProperties(level.getTrollLocations()));
        levelProperties.setProperty("Pills.location", locationToProperties(level.getPillLocations()));
        levelProperties.setProperty("Gold.location", locationToProperties(level.getGoldLocations()));
        levelProperties.setProperty("Walls.location", locationToProperties(level.getWallLocations()));
        levelProperties.setProperty("Paths.location", locationToProperties(level.getPathLocations()));

        levelProperties.setProperty("WhitePortal.location", locationToProperties(level.getPortalWhiteLocations()));
        levelProperties.setProperty("YellowPortal.location", locationToProperties(level.getPortalYellowLocations()));
        levelProperties.setProperty("DarkGrayPortal.location", locationToProperties(level.getPortalDarkGrayLocations()));
        levelProperties.setProperty("DarkGoldPortal.location", locationToProperties(level.getPortalDarkGoldLocations()));

        return levelProperties;
    }

    private String locationToProperties(List<Point> locations) {

        String output = "";
        for (Point point: locations) {
            output = output + point.x + "," + point.y + ";";
        }

        // Remove trailing ';'
        output = output.substring(0,output.length()-1);

        return output;
    }

    public Properties getNextLevel() {

        if (allLevels.isEmpty()) {
            return null;
        }
        Properties currLevel = allLevels.remove(0);
        return currLevel;
    }


}
