
package src.PacManGame;


import ch.aplu.jgamegrid.*;

//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class GameActor extends Actor {

    private String currentName = "";

    // constructor with sprite
    public GameActor(Sprite sprite){
        super(sprite.isRotatable(), sprite.getImageName(), sprite.getImgCount());
    }
    // constructor without sprite
    public GameActor() {
        super();
    }

    public void setCurrentName(String name) {
        this.currentName = name;
    }

    public String getCurrentName() {
        return this.currentName;
    }

    // gets the GameGrid in the Actor class
    public Game getGame() {return (Game) this.gameGrid;}

}
