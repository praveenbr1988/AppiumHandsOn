package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileBrowserBaseTest {


    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    public MobileBrowserBaseTest(){

    }

    @BeforeClass
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
        options.setChromedriverExecutable("C:\\Users\\techi\\OneDrive\\Desktop\\Personal1\\Projects\\AppiumHandsOn\\src\\test\\resources\\drivers\\chromedriver.exe");
        options.setCapability("browserName","Chrome");

        //Android Driver Initilization
        //Provide Appium server URL and Capabilities
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterClass
    public void tearDown(){
        //Closing the driver
        driver.quit();
        //Stoping Sever
        service.stop();
    }


}
