/*
 *
 *  *
 *  *  * Copyright (c) 2020.  Kumaran Devaneson
 *  *  * All rights reserved
 *  *
 *  Load Properties for the Project
 *
 */

package com.github.SUT2014.Checkar.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

public class PropertiesLoad {
    private static String WEBDRIVER_DIR;
    private static String INPUT_DIR;
    private static String OUTPUT_DIR;
    private static String CHECKAR_URL;
    private static String PATTERN;
    private static Long WEBDRIVER_DELAY;

    //Load Properties into variables
    public static boolean loadProp(Logger LOGGER){
        InputStream inStream = null;
        try {
            Properties prop = new Properties();
            inStream =  PropertiesLoad.class.getClassLoader().getResourceAsStream("config.properties");

            if (inStream == null){
                LOGGER.error("Unable to find config.properties");
                return false;
            }
            prop.load(inStream);
            WEBDRIVER_DIR = prop.getProperty("WEBDRIVER_DIR");
            INPUT_DIR = prop.getProperty("INPUT_DIR");
            OUTPUT_DIR = prop.getProperty("OUTPUT_DIR");
            CHECKAR_URL = prop.getProperty("CHECKAR_URL");
            PATTERN = prop.getProperty("PATTERN");
            WEBDRIVER_DELAY = Long.parseLong(prop.getProperty("WEBDRIVER_DELAY"));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return false;
        }
        finally {
            try {
                if(inStream != null)
                    inStream.close();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return true;
    }

// go getters
    public static String getWebdriverDir() {
        return WEBDRIVER_DIR;
    }

    public static String getInputDir() {
        return INPUT_DIR;
    }

    public static String getOutputDir() {
        return OUTPUT_DIR;
    }

    public static String getCheckarUrl() {
        return CHECKAR_URL;
    }

    public static Long getWebdriverDelay() {
        return WEBDRIVER_DELAY;
    }

    public static String getPATTERN() {
        return PATTERN;
    }
}
