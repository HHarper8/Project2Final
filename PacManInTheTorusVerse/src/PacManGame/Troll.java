package src;

import ch.aplu.jgamegrid.*;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class Troll extends Monster {
    private static final String MONSTER_NAME = "Troll";
    private static final String IMAGE_NAME = "m_troll.gif";
    private static final Sprite SPRITE = new Sprite(IMAGE_NAME);
    public Troll() {
        super(SPRITE);
        this.setCurrentName(MONSTER_NAME);
    }

    /**
     * Defines walk behaviour for Troll. Troll randomly walks in any 4 compass direction.
     */
    @Override
    public void walkApproach(){
        Location pacLocation = getGame().pacActor.getLocation();
        double oldDirection = getDirection();
        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(pacLocation);
        setDirection(compassDir);
        int sign = getRandomiser().nextDouble() < 0.5 ? 1 : -1;
        setDirection(oldDirection);
        turn(sign * 90);  // Try to turn left/right
        //Location interum = getNextMoveLocation();
        Location next = getNextMoveLocation();

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
        getGame().getGameCallback().monsterLocationChanged(this);
        addVisitedList(next);

    }


    /**
     * Similar to walk approach but Troll moves 2 cells instead of 1
     */
    @Override
    public void angrywalkApproach(){
        Location pacLocation = getGame().pacActor.getLocation();
        double oldDirection = getDirection();
        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(pacLocation);
        setDirection(compassDir);
        int sign = getRandomiser().nextDouble() < 0.5 ? 1 : -1;
        setDirection(oldDirection);
        turn(sign * 90);  // Try to turn left/right
        Location interum = getNextMoveLocation();
        Location next = interum.getNeighbourLocation(getDirection());

        if (canMove(next) && canMove(interum)) {
            setLocation(next);
        }
        else {
            setDirection(oldDirection);
            interum = getNextMoveLocation();
            next = interum.getNeighbourLocation(getDirection());
            if (canMove(next) && canMove(interum)) { // Try to move forward
                setLocation(next);
            }
            else {
                setDirection(oldDirection);
                turn(-sign * 90);  // Try to turn right/left
                interum = getNextMoveLocation();
                next = interum.getNeighbourLocation(getDirection());
                if (canMove(next)&&canMove(interum)) {
                    setLocation(next);
                }
                else {

                    setDirection(oldDirection);
                    turn(180);  // Turn backward
                    interum = getNextMoveLocation();
                    next = interum.getNeighbourLocation(getDirection());
                    setLocation(next);
                }
            }
        }
        getGame().getGameCallback().monsterLocationChanged(this);
        addVisitedList(next);

    }



}



