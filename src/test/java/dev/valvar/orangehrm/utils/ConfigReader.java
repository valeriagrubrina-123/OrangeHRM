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
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);
            PROPERTIES = new Properties();
            PROPERTIES.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти или прочитать файл конфигурации config.properties", e);
        }
    }

    /**
     * Получить значение свойства.
     *
     * @param key ключ.
     * @return значение.
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
