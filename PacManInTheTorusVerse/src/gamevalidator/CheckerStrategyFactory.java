package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.io.File;

public class CheckerStrategyFactory {

    private static CheckerStrategyFactory instance = null;

    protected static CheckerStrategyFactory getInstance() {
        if (instance == null) {
            instance = new CheckerStrategyFactory();
        }
        return instance;
    }

    protected CheckerStrategy getCheckerStrategy(File file) {

        if (file.isDirectory()) {
            return new GameChecker();
        }
        else {
            return new LevelChecker();
        }

    }



}
