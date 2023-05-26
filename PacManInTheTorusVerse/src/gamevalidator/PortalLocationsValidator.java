package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.awt.*;
import java.util.List;

public class PortalLocationsValidator extends Check {

    private String LOG_MESSAGE1 = " - portal White count is not 2: ";
    private String LOG_MESSAGE2 = " - portal Yellow count is not 2: ";
    private String LOG_MESSAGE3 = " - portal DarkGray count is not 2: ";
    private String LOG_MESSAGE4 = " - portal DarkGold count is not 2: ";

    private EditorLog log = EditorLog.getInstance();

    @Override
    protected boolean isValid(LevelFileReader level) {

        boolean validity = true;

        List<Point> whitePortals = level.getPortalWhiteLocations();
        List<Point> yellowPortals  = level.getPortalYellowLocations();
        List<Point> darkGrayPortals = level.getPortalDarkGrayLocations();
        List<Point> darkGoldPortals = level.getPortalDarkGoldLocations();

        if (!whitePortals.isEmpty() && whitePortals.size() != 2) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE1 + getLocationString(whitePortals));
            validity = false;
        }
        if (!yellowPortals.isEmpty() && yellowPortals.size() != 2) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE2 + getLocationString(yellowPortals));
            validity = false;
        }
        if (!darkGrayPortals.isEmpty() && darkGrayPortals.size() != 2) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE3 + getLocationString(darkGrayPortals));
            validity = false;
        }
        if (!darkGoldPortals.isEmpty() && darkGoldPortals.size() != 2) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE4 + getLocationString(darkGoldPortals));
            validity = false;
        }

        return validity;
    }

    private String getLocationString(List<Point> pointList) {
        String output = "";
        for (Point point: pointList) {
            output = output + point.toString() + ";";
        }
        return output;
    }
}
