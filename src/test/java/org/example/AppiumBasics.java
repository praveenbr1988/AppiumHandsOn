package org.example;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
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


public class AppiumBasics extends BaseTest{
    gestureUtilities gs;

    public AppiumBasics(){
//        super();
//        gs=new gestureUtilities(driver);

    }
    @Test(enabled = false)
    public void wifiSettingsName() throws InterruptedException {
        //Actual Automation
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();;
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        String alertTitle = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");

        driver.findElement(By.id("android:id/edit")).sendKeys("Praveen");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        System.out.println("Wifi settings completed successfully ");
        Thread.sleep(3000);


    }

    @Test(enabled = false)
    public void longPress() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();


        // Long Press gesture
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        //gs.longPressAction(ele,2000);
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                                    ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                                                    "duration",2000 ));


        String menuText = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuText,"Sample menu");

        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());

        System.out.println("Long Press completed successfully ");
        Thread.sleep(3000);

    }


    @Test(enabled = false)
    public void scrollView() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //Way1--UIAndroidAutomator(googleEngine)
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
//        Thread.sleep(3000);
//        System.out.println("Scrolling to a particular element is Completed");


        //way2- Using JavaScript Executor- Scrolling little bit without knowing the destination until the bottom of the page
        boolean canScrollMore;
        do{
            HashMap<Object,Object> args = new java.util.HashMap<>();
            args.put("left", 100);
            args.put("top", 100);
            args.put("width", 200);
            args.put("height", 200);
            args.put("direction", "down");
            args.put("percent", 3.0);
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", args);
       }while(canScrollMore);
        Thread.sleep(3000);
        System.out.println("Scrolling little is Completed");

    }

    @Test(enabled = false)
    public void swipeDemo() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"1. Photos\"]")).click();
        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));

        //Before Swipe, focus is on first photo
        Assert.assertEquals(firstImage.getAttribute("focusable"),"true");

        //swipe activity---Using JavaScript Executors
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstImage).getId(),
                "direction", "left",
                "percent", 0.10
        ));

        //After Swipe, focus moves to second photo, hence for first photo, focusable should be false
        firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(firstImage.getAttribute("focusable"),"false");

        Thread.sleep(3000);
        System.out.println("Swiping the first image is Completed");

    }

    @Test(enabled = false)
    public void dragAndDrop() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        //drag and drop activity---Using JavaScript Executors
        dragAndDrop(source,641,574);

        //After drag and drop, focus moves to second photo, hence for first photo, focusable should be false
        String dropStatus = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(dropStatus,"Dropped!");

        Thread.sleep(3000);
        System.out.println("Drag and Drop the first image is Completed");

    }

    @Test(enabled = false)
    public void miscActions() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();;
        driver.findElement(By.id("android:id/checkbox")).click();

        /// /changing portratit to landscape
        DeviceRotation ro = new DeviceRotation(0,0,90);
        driver.rotate(ro);

        WebElement ele1 = driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]"));
        ele1.click();
        String alertTitle = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");

        //Copy/Paste Using CLipboard
        driver.setClipboardText("Praveen");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        //Keyboard Press
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));


        System.out.println("misc actions completed successfully ");
        Thread.sleep(3000);

    }

    @Test(enabled = false)
    public void goToPage() throws InterruptedException {
        //Go to an activity(page/Screen)---Using JavaScript Executors, give packagename/ActivityName
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
        ));
        System.out.println("Directly went to a page completed successfully ");
        Thread.sleep(3000);

    }


}