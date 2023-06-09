package src.gamevalidator;

import java.awt.*;
import java.util.List;

public class PacmanLocationValidator extends Check {

    private final String LOG_MESSAGE1 = " - no start for PacMan";
    private final String LOG_MESSAGE2 = " - more than one start for PacMan: ";

    private EditorLog log = EditorLog.getInstance();

    @Override
    protected boolean isValid(LevelFileReader level) {

        List<Point> pacLocations = level.getPacLocations();

        if (pacLocations.isEmpty()) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE1);
            return false;
        }
        else if (pacLocations.size() > 1) {
            String output = "";
            for (Point point: pacLocations) {
                output = output + point.toString() + ";";
            }
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE2 + output);
            return false;
        }

        return true;
    }
}
