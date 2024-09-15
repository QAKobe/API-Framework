package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigWriter {
    private static final String FILE_PATH = "C:\\Users\\Kubanych\\IdeaProjects\\APIB18\\configuration.properties";

    public static void updateConfiguration(String key, String value) {
        Properties prop = new Properties();

        try {
            // Load existing properties
            try (FileInputStream inputStream = new FileInputStream(FILE_PATH)) {
                prop.load(inputStream);
            }

            // Update the property
            prop.setProperty(key, value);

            // Save the properties back to the file
            try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
                prop.store(outputStream, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
