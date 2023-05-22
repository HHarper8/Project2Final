package src.gamevalidator;

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
