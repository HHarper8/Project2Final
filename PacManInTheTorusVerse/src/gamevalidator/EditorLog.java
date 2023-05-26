package src.gamevalidator;
//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorLog {

    private static EditorLog instance = null;
    private String logFilePath = "EditorLog.txt";
    private FileWriter fileWriter = null;

    private EditorLog() {
        try {
            fileWriter = new FileWriter(new File(logFilePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    protected static EditorLog getInstance() {

        if (instance == null) {
            instance = new EditorLog();
        }

        return instance;
    }

    protected void writeString(String str) {
        try {
            fileWriter.write(str);
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
