package src.gamevalidator;

import java.io.File;

public class LevelSaveChecker {

    public static void checkLevel(File levelFile) {

        CheckerStrategy strategy = CheckerStrategyFactory.getInstance().getCheckerStrategy(levelFile);
        strategy.checkValidity(levelFile);

    }

}
