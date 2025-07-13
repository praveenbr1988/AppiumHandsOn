package org.example;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    public IOSBaseTest(){

    }

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //starting the Appium Server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();

        //Setting up the Desired Capabilities
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 16");
        options.setPlatformName("iOS");
        options.setApp("/Users/naveenkumarvenkatesan/Documents/pr_workspace/AppiumHandsOn/src/test/resources/UIKitCatalog.app");
        options.setPlatformVersion("18.5");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));


        //Android Driver Initilization
        //Provide Appium server URL and Capabilities
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterClass
    public void tearDown(){
        //Closing the driver
        driver.quit();
        //Stoping Sever
        service.stop();
    }

    public void longPress(WebElement element, long seconds){

        RemoteWebElement remoteElement = (RemoteWebElement) element;
        Map<String,Object> params = new HashMap<>();
        params.put("elementId", remoteElement.getId());
        params.put("duration", seconds);
        driver.executeScript("mobile: touchAndHold", params);

    }


    public void scrollToText(String text ,String direction) throws InterruptedException {

        Map<String,Object> params = new HashMap<>();
        params.put("name", text);
        params.put("direction", direction);

        driver.executeScript("mobile: scroll", params);
        Thread.sleep(5000);

    }

    public void scrollToElement(WebElement scrollContainer, String direction) {
        RemoteWebElement container = (RemoteWebElement) scrollContainer;
        Map<String, Object> params = new HashMap<>();
        params.put("element", container.getId());
        params.put("direction", direction); // "down", "up", etc.

        driver.executeScript("mobile: scroll", params);
    }

}
