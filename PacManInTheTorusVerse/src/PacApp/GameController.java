package src.PacApp;

import src.PacManGame.GameDriver;
import src.mapeditor.editor.Controller;

import java.io.File;

public class GameController {
    private PacAppState state;

    public GameController(String state) {

        if (state.equals("e")) {
            this.state = PacAppState.Edit;
        }
        else {
            this.state = PacAppState.Test;
        }

    }

    public void runApplication(File file) {

        Controller editor = null;
        GameDriver gameDriver = null;

        switch (state) {

            case Edit:
                if (editor == null) {
                    editor = new Controller();
                }

            case Test:

                if (gameDriver == null) {
                    gameDriver = new GameDriver();
                    File errorFile = gameDriver.runGame(file);
                }

        }


    }

}
