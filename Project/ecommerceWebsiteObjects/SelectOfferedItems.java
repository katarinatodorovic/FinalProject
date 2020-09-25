package ecommercewebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class SelectOfferedItems {

    private static String secondOfferedItemPath ="/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/div[1]/a[1]/i[1]";
    private static String addMoreItemsClassName ="icon-plus";
    private static String sizeItemId ="group_1";
    private static String colorItemName ="White";
    private static String addToCartPath ="//span[contains(text(),'Add to cart')]";
    private static String exitAddToChartPopUp ="//span[@class='cross']";

    public static WebElement setSecondOfferedItem(WebDriver wd){
        return wd.findElement(By.xpath(secondOfferedItemPath));
    }

    public static void clickOnSecondOfferedItem(WebDriver wd){
        WebElement secondDress = SelectOfferedItems.setSecondOfferedItem(wd);
        JavascriptExecutor executor = (JavascriptExecutor)wd;
        executor.executeScript("arguments[0].click();", secondDress);

    }
    public static void clickOnAddMoreItems(WebDriver wd){

        wd.findElement(By.className(addMoreItemsClassName)).click();
    }
    public static void selectItemSize(WebDriver wd,String size){
        Select controls = new Select(wd.findElement(By.id(sizeItemId)));
        controls.selectByVisibleText(size);
    }
    public static void selectColorOfItem(WebDriver wd){
        wd.findElement(By.name(colorItemName)).click();
    }
    public static void clickOnAddToCart(WebDriver wd) throws InterruptedException {
        wd.findElement(By.xpath(addToCartPath)).click();
        Thread.sleep(1000);
        wd.findElement(By.xpath(exitAddToChartPopUp)).click();
    }
}
