package src;
import src.PacApp.GameController;
import src.gamevalidator.LevelLoader;
import src.PacApp.utilities.GameCallback;
import java.io.File;
import java.util.Properties;

public class Driver {

    public static void main(String args[]) {

        // See if any input arguments were give, otherwise call edit on nothing
        File file = null;
        String state = "e";

        if (args.length > 0) {
            file = new File(args[0]);

            if (file.isDirectory()) {
                // Test game
                state = "t";
            }

        }

        // Create game controller in correct state
        GameController controller = new GameController(state);
        controller.runApplication(file);
        // now using the properties stored by the LevelLoader
        Properties levelProps = LevelLoader.getInstance().getNextLevel();

        // check if there exists a level
        if (levelProps != null && !levelProps.isEmpty()) {
            // now load this property into the PacMan game
            GameCallback gameCallback = new GameCallback();
            new Game(gameCallback, properties);

        }



    }

}