package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.io.File;
import java.util.*;

public class GameChecker implements CheckerStrategy {

    private final String LOG_MESSAGE1 = " - no maps found";
    private final String LOG_MESSAGE2 = " - multiple maps at same level: ";

    private EditorLog log = EditorLog.getInstance();

    public boolean checkValidity(File gameFolder) {

        Map<String, String> levels = new HashMap<>();
        List<String> duplicateLevels = new ArrayList<>();

        File[] files = gameFolder.listFiles();
        for (File file: files) {
            String name = file.getName();
            // Find all numbers in string
            name = name.replaceAll("[^-?0-9]+", " ");
            List<String> numList = Arrays.asList(name.trim().split(" "));
            // Has to be 1 number exactly if level name
            if (numList.size() == 1) {
                String levelNum = numList.get(0);
                // If it's a duplicate, keep track of filename (unless already kept track of)
                if (levels.containsKey(levelNum)) {
                    if (duplicateLevels.contains(levels.get(levelNum))) {
                        duplicateLevels.add(name);
                    }
                    else {
                        duplicateLevels.add(levels.get(levelNum));
                        duplicateLevels.add(name);
                    }
                }
                else {
                    levels.put(name, numList.get(0));
                }
            }
        }

        if (levels.isEmpty()) {
            log.writeString("Game " + gameFolder.getName() + LOG_MESSAGE1);
            return false;
        }
        else if (!duplicateLevels.isEmpty()) {
            log.writeString("Game " + gameFolder.getName() + LOG_MESSAGE2 + getLocationString(duplicateLevels));
            return false;
        }

    return true;

    }

    private String getLocationString(List<String> fileList) {
        String output = "";
        for (String fileName: fileList) {
            output = output + fileName + "; ";
        }
        return output;
    }

}
