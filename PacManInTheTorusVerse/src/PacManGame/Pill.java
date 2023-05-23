
package src.PacManGame;;


//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class Pill extends Item {
    private static final String ITEM_NAME = "pills";
    private static final int SCORE_VALUE = 1;
    private static final String IMAGE_NAME = "pill.png";
    private static final Sprite SPRITE = new Sprite(IMAGE_NAME);
    private static int count = 0;
    public Pill() {
        super(SPRITE);
        setCurrentName(ITEM_NAME);
        setScoreValue(SCORE_VALUE);
        count++;
    }
    public static int getCount() {
        return count;
    }
    /**
     * Increments goal items consumed and destroys itself
     */
    @Override
    public void beConsumed() {
        getGame().increaseNumberGoalItemsConsumed();
        super.beConsumed();
    }

}
