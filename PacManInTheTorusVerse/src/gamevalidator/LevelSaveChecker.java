package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.io.File;

public class LevelSaveChecker {

    public static void checkLevel(File levelFile) {

        CheckerStrategy strategy = CheckerStrategyFactory.getInstance().getCheckerStrategy(levelFile);
        strategy.checkValidity(levelFile);

    }

}
