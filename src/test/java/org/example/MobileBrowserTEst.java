package org.example;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MobileBrowserTEst extends MobileBrowserBaseTest {

    @Test
    public void browserTEst() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.className("navbar-toggler-icon")).click();
        driver.findElement(By.xpath("//a[@href='/angularAppdemo/products']")).click();

        WebElement productTobeSelected = driver.findElement(By.xpath("//a[@href='/angularAppdemo/products/3']"));
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productTobeSelected);
        Thread.sleep(3000);

//        while (driver.findElements(By.xpath("//a[@href='/angularAppdemo/products/3']")).isEmpty()) {
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
//            Thread.sleep(1000); // wait for loading
//        }

        productTobeSelected.click();

        Thread.sleep(3000);


    }





}
