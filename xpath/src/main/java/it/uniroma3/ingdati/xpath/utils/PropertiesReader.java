package it.uniroma3.ingdati.xpath.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static  it.uniroma3.ingdati.xpath.utils.Utils.getResourceFullPath;

public class PropertiesReader {

    private static final Logger logger = Logger.getLogger(PropertiesReader.class.toString());

    private static final String PROPERTIES_FILE = "application.properties";

    public static String getProperty(String propertyName) {
        String property = null;
        try (InputStream input = new FileInputStream(getResourceFullPath(PROPERTIES_FILE))) {
            Properties prop = new Properties();
            prop.load(input);
            property = prop.getProperty(propertyName);
            logger.info("getProperty() - property="+ property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return property;
    }


}
