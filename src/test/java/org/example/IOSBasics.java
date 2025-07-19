package org.example;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class IOSBasics extends IOSBaseTest{

    public IOSBasics(){
//        super();
//        gs=new gestureUtilities(driver);

    }

    @Test(enabled = false)
    public void practice_1(){

        //Locators AccessibilityId, Id, xpath, classname, predicate strings

        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();

        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Text Entry\"`]")).click();
        driver.findElement(By.className("XCUIElementTypeTextField")).sendKeys("praveen");
        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"OK\"]")).click();

        driver.findElement(AppiumBy.iOSNsPredicateString("value == 'Confirm / Cancel' AND name BEGINSWITH 'Confirm / Cancel'")).click();

        String textPresent = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"A message should be a short, complete sentence.\"]")).getText();
        Assert.assertEquals(textPresent, "A message should be a short, complete sentence.");

        boolean cancelPresentOrNot = driver.findElement(AppiumBy.accessibilityId("Cancel")).isDisplayed();
        Assert.assertTrue(cancelPresentOrNot);

        if(textPresent.equalsIgnoreCase("A message should be a short, complete sentence.")){
            driver.findElement(AppiumBy.accessibilityId("Confirm")).click();
        }
        else {
            driver.findElement(AppiumBy.accessibilityId("Cancel")).click();
        }

        System.out.println("Test Completed");


    }
//gestures--https://appium.github.io/appium-xcuitest-driver/9.9/guides/gestures/
    //longpress
    @Test(enabled = false)
    public void practice_2(){
        driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
        WebElement stepperBtn = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Increment\"`][3]"));
        longPress(stepperBtn,5);
    }

//Scroll to a text
    @Test(enabled = false)
    public void practice_3() throws InterruptedException {

        String targetEle = driver.findElement(AppiumBy.accessibilityId("Web View")).getText();
        scrollToText("Web View","down" );
//        WebElement scrollableContainer = driver.findElement(AppiumBy.accessibilityId("Web View"));
//        scrollToElement(scrollableContainer,"down");

        driver.findElement(AppiumBy.accessibilityId("Web View")).click();

        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"UIKitCatalog\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

        //picker component works like text box- we can use sendkeys and get text..
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
        driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("105");


        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Red color component value")).getText(),"80");
        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Green color component value")).getText(),"220");
        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Blue color component value")).getText(),"105");

    }

    //Use Bundle ID for opening native apps..Here we are trying to open the photos..
    @Test(enabled = true)
    public void practice_4() throws InterruptedException {

        Map<String,String> params = new HashMap<>();
        params.put("bundleId", "com.apple.mobileslideshow");
        driver.executeScript("mobile:launchApp", params);
        driver.navigate().back();

    }

}