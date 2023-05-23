package src.PacManGame;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class IceCube extends Item {
    private static final String ITEM_NAME = "ice";
    private static final int SCORE_VALUE = 0;

    private static final String IMAGE_NAME = "ice.png";
    private static final Sprite SPRITE = new Sprite(IMAGE_NAME);

    private static int count =0;

    public IceCube(){
        super(SPRITE);
        setCurrentName(ITEM_NAME);
        setScoreValue(SCORE_VALUE);
        count++;
    }

    @Override
    public void beConsumed() {
        super.beConsumed();
    }



}
