package tests;

import ecommercewebsite.SearchBar;
import ecommercewebsite.SettingsClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;


public class TestSearchBarCheckingSearchResults {

    private WebDriver driver;
    @BeforeClass
    public void setProperties() throws InterruptedException {

        driver = SettingsClass.getDriver();
        SearchBar.searchForItem(driver,"yellow");
        Thread.sleep(2000);

    }

    @Test
    public void  numOfDisplayedItems()  {
        WebElement howManyItemsAreDisplayed = driver.findElement(By.className("heading-counter"));
       String quantity = howManyItemsAreDisplayed.getText();
       Assert.assertEquals(quantity,"3 results have been found.");
    }
    @Test
    public void  colorOfDisplayedItems() {
        boolean haveKeyWord =false;
        List<WebElement> results = driver.findElements(By.className("color_to_pick_list"));
        for (int i = 0; i < results.size(); i++) {
            List<WebElement> resultsListColor = results.get(i).findElements(By.className("color_pick"));
            for (int j = 0; j < resultsListColor.size(); j++){
                if (resultsListColor.get(j).getAttribute("style").contains("background: rgb(241, 196, 15);")){
                    haveKeyWord=true;
                }
            }
        }
        Assert.assertEquals(haveKeyWord, true);
    }

}

