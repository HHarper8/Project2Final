package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
public class GoldPillCountsValidator extends Check {

    private final String LOG_MESSAGE1 = " - less than 2 Gold and Pill";

    private EditorLog log = EditorLog.getInstance();

    @Override
    protected boolean isValid(LevelFileReader level) {

        if ( (level.getGoldLocations().size() + level.getPillLocations().size()) > 1) {
            return true;
        }

        log.writeString("Level " + level.getFileName() + LOG_MESSAGE1);

        return false;
    }
}
