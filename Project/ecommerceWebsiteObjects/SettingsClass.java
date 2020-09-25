package ecommercewebsite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SettingsClass {
   private static WebDriver driver;
    public static void setSettings(){
        SettingsPaths settings = new SettingsPaths();
        System.setProperty("webdriver.chrome.driver",settings.getChromeDriverPath());
        driver = new ChromeDriver();
        driver.get(settings.getUrl());
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
