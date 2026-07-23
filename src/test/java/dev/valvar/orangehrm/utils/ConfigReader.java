package dev.valvar.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Читальщик конфигов.
 */
public class ConfigReader {

    private static final Properties PROPERTIES;
    static {
        PROPERTIES = new Properties();
        String path = "src/test/resources/config.properties";
        try (FileInputStream input = new FileInputStream(path)) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти или прочитать файл конфигурации config.properties", e);
        }
    }

    /**
     * Получить значение свойства.
     * Порядок приоритета:
     * 1. System property (-Dkey=value)
     * 2. Environment variable (System.getenv)
     * 3. config.properties
     *
     * @param key ключ.
     * @return значение.
     */
    public static String getProperty(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            return systemProperty;
        }

        String envProperty = System.getenv(key);
        if (envProperty != null && !envProperty.trim().isEmpty()) {
            return envProperty;
        }

        return PROPERTIES.getProperty(key);
    }

}
