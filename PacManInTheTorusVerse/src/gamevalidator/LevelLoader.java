package src.gamevalidator;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LevelLoader {

    private static LevelLoader instance = null;
    private List<Properties> allLevels = new ArrayList<>();
    private int numLevels = 0;
    private int firstInvalidLevel = 0;
    private boolean foundFirstInvalidLevel = false;

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
        List<File> levels = null;
        if (gameFile.isDirectory()) {
            levels = Arrays.asList(gameFile.listFiles());
        }
        else {
            levels = Arrays.asList(gameFile);
        }
        CheckerStrategy levelChecker = CheckerStrategyFactory.getInstance().getCheckerStrategy(levels.get(0));

        for (File level: levels) {
            if (!levelChecker.checkValidity(level)) {
                foundFirstInvalidLevel = true;
                System.out.println("###DEBUG (line 41, LevelLoader)### level not valid");

                return level;
            }
            else {
                Properties levelProperties = createProperties(level);
                System.out.println("###DEBUG (line 45, LevelLoader)### adding a level");
                allLevels.add(numLevels, levelProperties);
                numLevels++;
                if (!foundFirstInvalidLevel) {
                    firstInvalidLevel++;
                }

            }
        }

        return null;
    }

    public int getFirstInvalidLevel() {
        return firstInvalidLevel;
    }

    private Properties createProperties(File levelFile) {

        LevelFileReader level = new LevelFileReader(levelFile);
        Properties levelProperties = new Properties();

        levelProperties.setProperty("game.width", Integer.toString(level.getGameWidth()));
        levelProperties.setProperty("game.height", Integer.toString(level.getGameHeight()));

        levelProperties.setProperty("PacMan.location", locationToProperties(level.getPacLocations()));
        levelProperties.setProperty("TX5.location", locationToProperties(level.getTX5Locations()));
        levelProperties.setProperty("Troll.location", locationToProperties(level.getTrollLocations()));
        levelProperties.setProperty("Pills.location", locationToProperties(level.getPillLocations()));
        levelProperties.setProperty("Gold.location", locationToProperties(level.getGoldLocations()));

        levelProperties.setProperty("Wall.location", locationToProperties(level.getWallLocations()));
        levelProperties.setProperty("Paths.location", locationToProperties(level.getPathLocations()));

        levelProperties.setProperty("WhitePortal.location", locationToProperties(level.getPortalWhiteLocations()));
        levelProperties.setProperty("YellowPortal.location", locationToProperties(level.getPortalYellowLocations()));
        levelProperties.setProperty("DarkGrayPortal.location", locationToProperties(level.getPortalDarkGrayLocations()));
        levelProperties.setProperty("DarkGoldPortal.location", locationToProperties(level.getPortalDarkGoldLocations()));

        levelProperties.setProperty("PacMan.isAuto", "true");

        return levelProperties;
    }

    private String locationToProperties(List<Point> locations) {

        String output = "";
        for (Point point: locations) {
            // x and y are being swapped such that PacManGameGrid works

            // TODO: CONFIRM WITH GROUP
            int new_x = point.x-1;
            int new_y = point.y-1;
            output = output +  new_x + "," + new_y + ";";
        }

        // Remove trailing ';'
        if (output.length() > 0) {
            output = output.substring(0,output.length()-1);
        }

        return output;
    }

    public Properties getNextLevel() {
        if (allLevels == null) {

            return null;
        }


        if (allLevels.isEmpty()) {

            return null;
        }
        Properties currLevel = allLevels.remove(0);
        return currLevel;
    }


}
