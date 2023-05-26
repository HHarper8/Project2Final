//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
package src.PacManGame;
import ch.aplu.jgamegrid.Location;

public class Portal extends GameActor {
    private PortalType portalType;
    private static final String OBSTACLE_NAME = "Portal";
    private Portal otherPortal;

    public Portal(PortalType portalType) {
        super(new Sprite(portalType.getImageName()));
        this.portalType = portalType;
        this.setCurrentName(OBSTACLE_NAME);


    }

    public void setOtherPortal(Portal other) {
        this.otherPortal = other;
    }

    public Location getOtherPortalLocation() {
        return this.otherPortal.getLocation();
    }
}
