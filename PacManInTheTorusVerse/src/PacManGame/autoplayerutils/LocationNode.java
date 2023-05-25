package src.PacManGame.autoplayerutils;
import ch.aplu.jgamegrid.Location;


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
