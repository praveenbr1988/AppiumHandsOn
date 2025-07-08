package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    public BaseTest(){

    }

    @BeforeSuite
    public void configureAppium() throws MalformedURLException {
        //starting the Appium Server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\techi\\AppData\\Roaming\\npm\\" +
                        "node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723).build();
        service.start();

        //Setting up the Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Praveenemulator");
        options.setApp("C:\\Users\\techi\\OneDrive\\Desktop\\Personal1\\Projects\\AppiumHandsOn\\src\\test\\resources\\ApiDemos-debug.apk");

        //Android Driver Initilization
        //Provide Appium server URL and Capabilities
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterSuite
    public void tearDown(){
        //Closing the driver
        driver.quit();
        //Stoping Sever
        service.close();
    }


    //LongPress- Using JavaScriptExecutor
    public void longPress(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                        "duration",2000 ));
    }


    //drag and drop activity---Using JavaScript Executors
    public void dragAndDrop(WebElement ele, int xPos, int yPos){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "endX", xPos,
                "endY", yPos
        ));
    }

}
