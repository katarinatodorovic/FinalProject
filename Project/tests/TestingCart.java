package tests;


import ecommercewebsite.SelectOfferedItems;
import ecommercewebsite.SettingsClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestingCart {
    private WebDriver driver;
    private  String colorAndSizeInsideCartPath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/dl[1]/dt[1]/div[1]/div[2]/a[1]";
    private String quantityInCartPath="/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]/span[1]";
    private String cartPath="/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]";

    @BeforeClass
    public void setProperties() throws InterruptedException {

        driver = SettingsClass.getDriver();
        SelectOfferedItems.clickOnSecondOfferedItem(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SelectOfferedItems.clickOnAddMoreItems(driver);
        SelectOfferedItems.selectItemSize(driver,"M");
        SelectOfferedItems.selectColorOfItem(driver);
        SelectOfferedItems.clickOnAddToCart(driver);

    }

    @Test
    public void testingSoppingCart() {
        WebElement quantityInCart = driver.findElement(By.xpath(quantityInCartPath));
        String quantity = quantityInCart.getText();
        Assert.assertEquals(quantity,"2");
    }

    @Test
    public void testingSoppingCart2() throws InterruptedException {

        WebElement cart = driver.findElement(By.xpath(cartPath));
        Actions action = new Actions(driver);
        action.moveToElement(cart).clickAndHold().build().perform();
        WebElement colorAndSize = driver.findElement(By.xpath(colorAndSizeInsideCartPath));
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(colorAndSize));
        String sizeColor = colorAndSize.getText();
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(sizeColor,"White, M");

    }
}
