package tests;

import ecommercewebsite.SettingsClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class CheckSocialMediaLinks {
    private WebDriver driver;
    private static String facebookDestinationUrl ="https://www.facebook.com/groups/525066904174158/";
    private static String twitterDestinationUrl ="https://twitter.com/seleniumfrmwrk";
    private static String youTubeDestinationUrl = "https://www.youtube.com/channel/UCHl59sI3SRjQ-qPcTrgt0tA";
    private static String googleDestinationUrl ="https://plus.google.com/111979135243110831526/posts";
    private static String facebookIconPath="//li[@class='facebook']//a";
    private static String twitterIconPath="//li[@class='twitter']//a";
    private static String youTubeIconPath="//li[@class='youtube']//a";
    private static String googleIconPath="//li[@class='google-plus']//a";


  @BeforeClass
    public void setProperties(){
        driver = SettingsClass.getDriver();
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void checkSocialIconsLinks(String socialIconPath,String destinationUrl){
        driver.findElement(By.xpath(socialIconPath)).click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String link=driver.switchTo().window(tabs.get(1)).getCurrentUrl();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(destinationUrl,link);
        driver.close();
        driver.switchTo().window(tabs.get(0));

    }
    @Test
    public void clickFaceBookSocialIcon(){
      checkSocialIconsLinks(facebookIconPath,facebookDestinationUrl);

    }

   @Test
    public void clickTwitterIcon(){
      checkSocialIconsLinks(twitterIconPath,twitterDestinationUrl);

    }
    @Test
    public void clickYouTubeSocialIcon()  {
        checkSocialIconsLinks(youTubeIconPath,youTubeDestinationUrl);


    }
    @Test
    public void clickGoogleSocialIcon(){
       checkSocialIconsLinks(googleIconPath,googleDestinationUrl);
    }
}
