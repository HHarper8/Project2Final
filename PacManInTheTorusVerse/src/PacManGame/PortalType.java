//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
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


