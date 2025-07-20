package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Ecommerce_TC_1 extends BaseTest {

    @Test(enabled = true)
    public void fillForm() throws InterruptedException {

        //dropdown
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();

        //TextBox
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("Praveen");
        //Hiding Keyboard
        driver.hideKeyboard();
        //Radio button
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        //Clicking Button
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        Thread.sleep(3000);

        String toolBarTitleText = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        Assert.assertEquals(toolBarTitleText,"Products");
        System.out.println("Successfully logged in");

    }

    @Test(enabled = true)
    public void verifyToastMsg() throws InterruptedException {

        //dropdown
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();

        driver.findElement(AppiumBy.className("android.widget.Button")).click();

        String toastMsg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMsg,"Please enter your name");
        System.out.println("Successfully verified the TOast msg");
        Thread.sleep(3000);

    }

    @Test(enabled = true)
    public void selectAProduct() throws InterruptedException {


        fillForm();
        //Navigate till the desired shoe is coming on the view
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
        int itemCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i=0;i<itemCount;i++){
            String productName= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equals("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                Thread.sleep(1000);
                String cartText = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).getText();
                Assert.assertEquals(cartText,"ADDED TO CART");
                break;
            }
        }

        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(),"1");
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);

        WebElement cartTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElement(cartTitle,"Cart"));


        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText(),"Jordan 6 Rings");
        System.out.println("Successfully added the product to the cart");
        Thread.sleep(3000);

    }

    @Test(enabled = true)
    public void verifyTotalInCart() throws InterruptedException {


        fillForm();

        //Navigate till the desired shoe is coming on the view
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
        int itemCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i=0;i<itemCount;i++){
            String productName= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            Thread.sleep(1000);
            String cartText = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).getText();
            Assert.assertEquals(cartText,"ADDED TO CART");
        }

        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText(),String.valueOf(itemCount));
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebElement cartTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.attributeContains(cartTitle,"text","Cart"));


        scrollTillBottomOfPage();

        int productcounts= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        Double sumOfprice=0.0;
        for(int i=0;i<productcounts;i++){
            sumOfprice = sumOfprice+ Double.parseDouble(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText().substring(1));

        }
        System.out.println("Calculated Price: "+sumOfprice);

        Double totalPrice = Double.valueOf(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1));
        System.out.println("Total Price displayed: "+totalPrice);
        Assert.assertEquals(sumOfprice,totalPrice);

        System.out.println("Total price is verified for the selected products");

        driver.findElement(By.className("android.widget.CheckBox")).click();

        WebElement terms= driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPress(terms);

        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText(),"Terms Of Conditions");
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);

        //Hybrid app- On clicking submit btn, it
        Set<String> contexts = driver.getContextHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
            driver.context(contextName);
        }

        driver.findElement(By.name("q")).sendKeys("Web Context"+ Keys.ENTER);
        Thread.sleep(3000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
        Thread.sleep(3000);

    }


}
