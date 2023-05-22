package src.gamevalidator;

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
