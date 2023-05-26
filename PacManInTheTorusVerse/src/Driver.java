//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

package src;
import src.PacApp.GameController;




import java.io.File;


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

    }

}