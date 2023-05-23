
package src.PacManGame;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class Item extends GameActor {
    private int scoreValue = 0;
    private static int count = 0;
    private static int goalItemCount = 0;

    // constructor using Sprite
    public Item(Sprite sprite) {
        super(sprite);
        count++;
    }
    // constructor without Sprite
    public Item() {
        super();
        count++;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    /**
     * Called when an item is consumed
     */
    protected void beConsumed() {
        getGame().increaseScore(this.scoreValue);
        if(getGame().getGrid().getVersion() == Version.MULTIVERSE) {
            affectMonsters();
        }
        removeSelf();
    }

    /**
     * Concrete implementation will affect monsters with the items effect
     */
    public void affectMonsters() {
        return;
    }

    /**
     * Get number of items which are part of pacman's goal count
     * @return count: number of goal items left on the board
     */
    public static int getGoalItemCount() {
        int count = Pill.getCount() + Gold.getCount();

        return count;
    }
}