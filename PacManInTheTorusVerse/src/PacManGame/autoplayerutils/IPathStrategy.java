package src.PacManGame.autoplayerutils;
import ch.aplu.jgamegrid.Location;
import src.PacManGame.PacActor;

import java.util.ArrayList;

public interface IPathStrategy {
    Location findNextMoveLocation(PacActor pacActor);
    ArrayList<Location> findPathToTarget(PacActor pacActor);
}
