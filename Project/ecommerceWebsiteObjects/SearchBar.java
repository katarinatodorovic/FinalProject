package ecommercewebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBar {
    private static String searchBarId ="search_query_top";
    private static String submitSearchName = "submit_search";
    public static void searchForItem(WebDriver wd,String searchFor){
        WebElement searchBar = wd.findElement(By.id(searchBarId));
        searchBar.sendKeys(searchFor);
       wd.findElement(By.name(submitSearchName)).click();
    }
}
