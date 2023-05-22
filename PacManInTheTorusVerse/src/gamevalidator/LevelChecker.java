package src.gamevalidator;

import java.io.File;


public class LevelChecker implements CheckerStrategy {

    public boolean checkValidity(File file) {

        LevelFileReader level = new LevelFileReader(file);

        Check[] checkList = {
                new PacmanLocationValidator(),
                new PortalLocationsValidator(),
                new GoldPillCountsValidator(),
                new GoldPillLocationsValidator()
        };

        boolean validity= true;
        for (Check check: checkList) {
            if (!check.isValid(level)) {
                validity = false;
            }
        }

        return validity;

    }



}
