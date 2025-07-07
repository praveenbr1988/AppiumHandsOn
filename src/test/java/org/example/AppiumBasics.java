package org.example;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumBasics{

    @Test
    public void myFirstTest() throws MalformedURLException {


        //starting the Appium Server
        AppiumDriverLocalService service = new AppiumServiceBuilder()
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
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);

        //Actual Automation

        driver.quit();

        //Stoping Sever
        service.close();


    }
}