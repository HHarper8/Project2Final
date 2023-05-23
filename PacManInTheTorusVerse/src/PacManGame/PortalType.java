
package src.PacManGame;

public enum PortalType {
    WHITE,
    YELLOW,
    DARK_GRAY,
    DARK_GOLD;

    public String getImageName() {
        switch (this) {
            case WHITE: return "i_portalWhiteTile.png";
            case YELLOW: return "j_portalYellowTile.png";

            case DARK_GRAY: return "k_portalDarkGoldTile.png";
            case DARK_GOLD: return "l_portalDarkGrayTile.png";
            default: {
                assert false;
            }
        }
        return null;
    }
}


