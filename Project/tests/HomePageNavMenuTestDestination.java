package tests;
import ecommercewebsite.HomePageNavigationMenu;
import ecommercewebsite.SettingsClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;


public class HomePageNavMenuTestDestination {

   private WebDriver driver;
   private static String dressDestinationUrl ="http://automationpractice.com/index.php?id_category=11&controller=category";

@BeforeSuite
public void setProperties(){
  SettingsClass.setSettings();
  driver = SettingsClass.getDriver();

}
    public String returnDressDestinationLinkViaWoman (){

        HomePageNavigationMenu.hoverOverWomanNavBar(driver);
        HomePageNavigationMenu.clickSummerDressViaWomanNav(driver);
        String url =driver.getCurrentUrl();
        return url;
    }
    @AfterSuite
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void summerDressDestinationViaWomanNav() {

        Assert.assertEquals(returnDressDestinationLinkViaWoman(),dressDestinationUrl);

    }

    public String returnDressDestinationLinkViaDress (){

        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        HomePageNavigationMenu.hoverOverDressNavBar(driver);
        HomePageNavigationMenu.clickSummerDressViaDressNav(driver);
        String url = driver.getCurrentUrl();
        return url;
    }
  @Test
    public void summerDressDestinationViaDressNav(){

       Assert.assertEquals(returnDressDestinationLinkViaDress(),dressDestinationUrl);
    }
  @Test
    public void isDestinationOfPreviousTwoTestTheSame(){
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(returnDressDestinationLinkViaWoman(),returnDressDestinationLinkViaDress());

}

}

