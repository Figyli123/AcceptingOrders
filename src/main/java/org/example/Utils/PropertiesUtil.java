package org.example.Utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {

       var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");

        try {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key){
            return PROPERTIES.getProperty(key);
    }


}
