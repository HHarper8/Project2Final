package src.PacManGame.autoplayerutils;
import ch.aplu.jgamegrid.Location;
import src.PacManGame.PacActor;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.util.ArrayList;

public interface IPathStrategy {
    Location findNextMoveLocation(PacActor pacActor);
    ArrayList<Location> findPathToTarget(PacActor pacActor);
}
