/*
 *
 *  *
 *  *  * Copyright (c) 2020.  Kumaran Devaneson
 *  *  * All rights reserved
 *  *
 *  Customised Selenium API methods
 *
 */

package com.github.SUT2014.Checkar.selenium;

import com.github.SUT2014.Checkar.properties.PropertiesLoad;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class CustomSelenium {
    private final Logger LOGGER;
    private final Long DELAY;
    private WebDriver browser = null;

    public CustomSelenium(Logger logger, Long seconds) {
        LOGGER = logger;
        this.DELAY = seconds;
    }

    //load firefox driver
    private void loadFireFoxDriver(String str){
        System.setProperty("webdriver.gecko.driver", PropertiesLoad.getWebdriverDir()+"/"+str+"/geckodriver");
        browser = new FirefoxDriver();
    }

    // load chrome driver
    private void loadChromeDriver(String str) {
        System.setProperty("webdriver.chrome.driver", PropertiesLoad.getWebdriverDir()+"/"+str+"/chromedriver");
        browser = new ChromeDriver();
    }

    // Return webdriver handle, customise it by sending in browser type (firefox,chrome,ie..)
    public void setDriver(String str) {
        switch(str){
            case "firefox":
                loadFireFoxDriver(str);
                break;
            case "chrome":
                loadChromeDriver(str);
                break;
        }
    }

    // Navigate to page
    public void navigateTo(String URL){
        browser.get(URL);
    }

    // getTitle of a page
    public String getTitle(){
        return(browser.getTitle());
    }

    // timeout value
    public void setImplicitTimeout(Long timeout){
        browser.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    //get xpath element, ignore case
    public String getElementTextIgnoreCase(String str){
        WebElement ele = browser.findElement(By.xpath("//dt[contains(translate(., '" + str + "', '"
                            + str.toUpperCase() + "'), '"+ str.toUpperCase() + "')]/following-sibling::dd"));
        return(ele.getText());
    }

    //send keys by id
    public void sendById(String id, String key){
        browser.findElement(By.id(id)).sendKeys(key);
    }

    //find and click button by xpath
    public void findAndClick(String key){
        WebElement button = browser.findElement(By.xpath("//button[contains(.,'" + key + "')]"));
        button.click();
    }

    //send and click button
    public void findSendAndClick(String URL, String id, String input, String buttonStr) throws InterruptedException {
        navigateTo(URL);
        Thread.sleep(DELAY);
        sendById(id, input);
        findAndClick(buttonStr);
        Thread.sleep(DELAY);
    }
    // retrieve details by keys
    public String retrieveDetailsForKeys(String keys){
        String returnCSV = "", tmp = "";
        for(String name : keys.split(",")){
            tmp = getElementTextIgnoreCase(name.toLowerCase());
            if (tmp.isEmpty()){
                return tmp;
            }
            returnCSV +=  tmp + ",";
        }
        return returnCSV;

    }

    //close webdriver handle
    public void close(){
        if (browser!=null){
            browser.close();
        }
    }


}
