package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GoldPillLocationsValidator extends Check {

    private final String LOG_MESSAGE1 = " - Pill not accessible: ";
    private final String LOG_MESSAGE2 = " - Gold not accessible: ";

    private EditorLog log = EditorLog.getInstance();

    @Override
    protected boolean isValid(LevelFileReader level) {

        int width = level.getGameWidth();
        int height = level.getGameHeight();

        List<Point> pacLocations = level.getPacLocations();
        List<Point> wallLocations = level.getWallLocations();
        List<Point> whitePortalLocations = level.getPortalWhiteLocations();
        List<Point> yellowPortalLocations = level.getPortalYellowLocations();
        List<Point> darkGoldPortalLocations = level.getPortalDarkGoldLocations();
        List<Point> darkGrayPortalLocations = level.getPortalDarkGrayLocations();
        List<Point> pillLocations = level.getPillLocations();
        List<Point> goldLocations = level.getGoldLocations();

        // Start at pacman location
        Point spawn = pacLocations.get(0);

        // Push 4 adjacent moves on to stack if not a wall or portal, store all moves which are reachable from pacman
        List<Point> validMoves = new ArrayList<>();
        List<Point> stack = new ArrayList<>();
        stack.add(0, spawn);

        while (!stack.isEmpty()) {

            Point curr = stack.remove(0);

            Point n = new Point(curr.x, curr.y+1);
            Point e = new Point(curr.x+1,curr.y);
            Point s = new Point(curr.x, curr.y-1);
            Point w = new Point(curr.x-1, curr.y);

            Point[] points = {n, e, s, w};

            // Add point to stack if within bounds and not a wall
            for (Point point: points) {
                if ((point.x >= 1 && point.x <= width) && (point.y >= 1 && point.y <= height) &&
                        !wallLocations.contains(point)) {

                    // If portal, add point of exit if we havnt already tried it
                    if (whitePortalLocations.contains(point)) {
                        for (Point portal: whitePortalLocations) {
                            if (!portal.equals(point)) {
                                if (!validMoves.contains(portal)) {
                                    stack.add(0, portal);
                                }
                            }
                        }
                    }
                    else if (yellowPortalLocations.contains(point)) {
                        for (Point portal: yellowPortalLocations) {
                            if (!portal.equals(point)) {
                                if (!validMoves.contains(portal)) {
                                    stack.add(0, portal);
                                }
                            }
                        }
                    }
                    else if (darkGoldPortalLocations.contains(point)) {
                        for (Point portal: darkGoldPortalLocations) {
                            if (!portal.equals(point)) {
                                if (!validMoves.contains(portal)) {
                                    stack.add(0, portal);
                                }
                            }
                        }
                    }
                    else if (darkGrayPortalLocations.contains(point)) {
                        for (Point portal: darkGrayPortalLocations) {
                            if (!portal.equals(point)) {
                                if (!validMoves.contains(portal)) {
                                    stack.add(0, portal);
                                }
                            }
                        }
                    }
                    if (!validMoves.contains(point)){
                        stack.add(0, point);
                    }
                }
            }
            validMoves.add(curr);

        }

        // Check pill locations to see if any not in pacman reach
        List<Point> invalidGold = new ArrayList<>();
        List<Point> invalidPills = new ArrayList<>();

        for (Point pill: pillLocations) {
            if (!validMoves.contains(pill)) {
                invalidPills.add(pill);
            }
        }
        for (Point gold: goldLocations) {
            if (!validMoves.contains(gold)) {
                invalidGold.add(gold);
            }
        }

        // Print any errors to log and return
        boolean validity = true;

        if (!invalidPills.isEmpty()) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE1 + getLocationString(invalidPills));
            validity = false;
        }
        if (!invalidGold.isEmpty()) {
            log.writeString("Level " + level.getFileName() + LOG_MESSAGE2 + getLocationString(invalidGold));
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
