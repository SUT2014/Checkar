/*
 *
 *  *
 *  *  * Copyright (c) 2020.  Kumaran Devaneson
 *  *  * All rights reserved
 *  *
 *  Main Class
 *
 */

package com.github.SUT2014.Checkar.chekar;

import com.github.SUT2014.Checkar.properties.PropertiesLoad;
import com.github.SUT2014.Checkar.tests.Test1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Checkar {
    private static final Logger LOGGER = LogManager.getLogger(Checkar.class);

    public static void main(String[] args) {
        //LOGGER.debug("There is no spoon");
        PropertiesLoad.loadProp(LOGGER);
        Test1 t1 = new Test1(LOGGER);
        t1.runTest();
            LOGGER.error("Shut Down..Goodbye !!");
        }
}
