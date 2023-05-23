package src;

public class Portal extends Obstacle {
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

    public Portal getOtherPortalLocation() {
        return this.otherPortal;
    }
}
