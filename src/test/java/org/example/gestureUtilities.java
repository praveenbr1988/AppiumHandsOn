package org.example;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;


public class gestureUtilities{

    AndroidDriver driver;
    public gestureUtilities(AndroidDriver driver){
        super();
        this.driver=driver;

    }

    // Long Press gesture
    public void longPressAction(WebElement element, int waitTime) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                                    ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                                                    "duration",waitTime ));
        Thread.sleep(3000);
    }





}