
package src.PacManGame;



//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class Wall extends GameActor {
    private static final String WALL_NAME = "Wall";
    private static final String IMAGE_NAME = "b_wallTile.png";
    private static final Sprite SPRITE = new Sprite(IMAGE_NAME);
    public Wall() {
        super(SPRITE);
        this.setCurrentName(WALL_NAME);

    }

}