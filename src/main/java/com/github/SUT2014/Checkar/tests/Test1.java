/*
 *
 *  *
 *  *  * Copyright (c) 2020.  Kumaran Devaneson
 *  *  * All rights reserved
 *  *
 *  Test Case - 1
 *  Steps:
 *    1. Read Output Text File
 *    2. Read Input Text File
 *    3. Retrieve Reg Numbers from Input Text
 *    4. Retrieve keys from Output File.
 *    5. Access website for each RegNumber, retrieve Keys information
 *    6. Verify it against Output CSV information.
 */


package com.github.SUT2014.Checkar.tests;

import com.github.SUT2014.Checkar.properties.PropertiesLoad;
import com.github.SUT2014.Checkar.selenium.CustomSelenium;
import com.github.SUT2014.Checkar.utils.FileUtils;
import com.github.SUT2014.Checkar.utils.StringUtils;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Test1 {
    private final Logger LOGGER;
    //some test specific customisation data
    private final Long DELAY = 5000l;
    private final String BROWSER_KIND = "firefox";
    private final String KEYS = "Registration,Make,Model,Colour,Year";
    private CustomSelenium cs;

    public Test1(Logger logger) {
        LOGGER = logger;
    }

    public void setupTest(){


    }
    public void runTest(){
        //cs = new CustomSelenium(LOGGER, PropertiesLoad.getWebdriverDelay());
        cs = new CustomSelenium(LOGGER, DELAY); //over ride webdriver DELAY with local Delay setting
        try {
            cs.setDriver(BROWSER_KIND);
            List<String> outputList = FileUtils.populateOutList(PropertiesLoad.getOutputDir());
            List<String> REGs = StringUtils.getAllREGs(FileUtils.readAllFiles(PropertiesLoad.getInputDir()),
                    PropertiesLoad.getPATTERN());
            LOGGER.debug("RegNos retrieved from Input File:" + REGs.toString());

            for(String RegNo : REGs){
                LOGGER.debug("***************************************************************************");
                //retrieve page information for each RegNo, keys are passed to retrieve elements
                cs.findSendAndClick(PropertiesLoad.getCheckarUrl(), "vrm-input", RegNo, "Free Car Check");
                String retrievedCSV = cs.retrieveDetailsForKeys(KEYS);
                if (retrievedCSV.isEmpty()){
                    LOGGER.error("Failed - RegNo:" + RegNo + " Car details not found");
                }
                else{
                    LOGGER.debug("Checking Car RegNo: " + RegNo);
                    if (StringUtils.findAndCompareCSV(outputList, retrievedCSV, LOGGER)){
                        LOGGER.debug("Passed - RegNo:" + RegNo + " Car Details Match");
                    }
                    else{
                        LOGGER.error("Failed - RegNo:" + RegNo + " Car details Dont Match");
                    }
                    LOGGER.debug(KEYS);
                    LOGGER.debug(retrievedCSV);

                }
                LOGGER.debug("***************************************************************************");
            }
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        finally {
            //clean up and quit
            cleanup();
        }
    }
    public void cleanup(){
        cs.close();
    }
}
