package src.PacManGame;

import ch.aplu.jgamegrid.Location;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class TX5 extends Monster {
    private static final String MONSTER_NAME = "TX5";
    private static final String IMAGE_NAME = "m_tx5.gif";
    private static final Sprite SPRITE = new Sprite(IMAGE_NAME);
    public TX5() {
        super(SPRITE);
        this.setCurrentName(MONSTER_NAME);
    }

    /**
     * Defines walk behaviour for TX5. TX5 chooses the compass direction which is closest to pacman and moves
     * in that direction
     */
    @Override
    public void walkApproach(){

        Location pacLocation = getGame().pacActor.getLocation();
        double oldDirection = getDirection();
        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(pacLocation);
        Location next = getLocation().getNeighbourLocation(compassDir);
        setDirection(compassDir);

        if (!isVisited(next) && canMove(next)) {
            setLocation(next);
        }
        else {
            int sign = getRandomiser().nextDouble() < 0.5 ? 1 : -1;
            setDirection(oldDirection);
            turn(sign * 90);  // Try to turn left/right
            next = getNextMoveLocation();
            if (canMove(next)) {
                setLocation(next);
            }
            else {
                setDirection(oldDirection);
                next = getNextMoveLocation();
                if (canMove(next)) { // Try to move forward
                    setLocation(next);
                }
                else {
                    setDirection(oldDirection);
                    turn(-sign * 90);  // Try to turn right/left
                    next = getNextMoveLocation();
                    if (canMove(next)) {
                        setLocation(next);
                    }
                    else {
                        setDirection(oldDirection);
                        turn(180);  // Turn backward
                        next = getNextMoveLocation();
                        setLocation(next);
                    }
                }
            }
        }

        getGame().getGameCallback().monsterLocationChanged(this);
        addVisitedList(next);

    }



}