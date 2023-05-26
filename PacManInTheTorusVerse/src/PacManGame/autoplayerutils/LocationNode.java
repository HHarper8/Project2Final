package src.PacManGame.autoplayerutils;
import ch.aplu.jgamegrid.Location;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class LocationNode {
    private Location location;
    private LocationNode parent;

    public LocationNode(Location location, LocationNode parent) {
        this.location = location;
        this.parent = parent;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationNode getParent() {
        return parent;
    }

    public void setParent(LocationNode parent) {
        this.parent = parent;
    }

}
