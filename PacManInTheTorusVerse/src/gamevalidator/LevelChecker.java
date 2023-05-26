package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
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
