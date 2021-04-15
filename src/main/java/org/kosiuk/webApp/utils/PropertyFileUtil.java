package org.kosiuk.webApp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileUtil {

    public static Object getProperty(String fileName, String propertyName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(fileName);
            prop.load(input);
            return prop.getProperty(propertyName);

        } catch (IOException ioe) {

            throw new IOException("Something went wrong throughout trying to load property from property file.", ioe);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioe){
                    throw new IOException("Can't close the property file input stream.");
                }
            }
        }

    }

}
