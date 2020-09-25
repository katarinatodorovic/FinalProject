
package ecommercewebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageNavigationMenu {

   private static String womanNavBarXPath = "//a[@class='sf-with-ul'][contains(text(),'Women')]";
   private static String summerDressPath = "//li[@class='sfHover']//a[contains(text(),'Summer Dresses')]";
   private static String dressNavBarPath = "//body[@id='index']/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]";
   private static String summerDressPath2 = "//li[@class='sfHover']//a[contains(text(),'Summer Dresses')]";


    public static WebElement setWomanNavBarButton(WebDriver wd){
        return wd.findElement(By.xpath(womanNavBarXPath));
    }

    public static void hoverOverWomanNavBar(WebDriver wd){
        hoverAction(wd,setWomanNavBarButton(wd),10);
    }
    public static void clickSummerDressViaWomanNav(WebDriver wd){
        wd.findElement(By.xpath(summerDressPath)).click();
    }

    public static WebElement setDressNavBarButton(WebDriver wd){
        return wd.findElement(By.xpath(dressNavBarPath));
    }
    public static void hoverOverDressNavBar(WebDriver wd){
        hoverAction(wd,setDressNavBarButton(wd),10);
    }
    public static void clickSummerDressViaDressNav(WebDriver wd){
        wd.findElement(By.xpath(summerDressPath2)).click();
    }

    public static void hoverAction(WebDriver wd,WebElement element, int timeToWaitInSecond){
        WebElement elementNew = element;
        Actions action = new Actions(wd);
        action.moveToElement(elementNew).clickAndHold().build().perform();
        WebDriverWait wait = new WebDriverWait(wd,timeToWaitInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(elementNew));
    }
}
