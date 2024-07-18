package org.mobile.testing.framework.utils;

import lombok.extern.log4j.Log4j2;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyLoader {

    private PropertyLoader() {
    }

    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            log.error("Error loading properties from file {}: {}", filePath, e.getMessage());
            return null;
        }
        return properties;
    }
}
