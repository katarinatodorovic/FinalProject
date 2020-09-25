package ecommercewebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ContactUsForm {

    private static String contactUsLinkText = "Contact us";
    private static String subjectFieldId = "id_contact";
    private static String emailFieldId ="email";
    private static String orderFieldId ="id_order";
    private static String imgFieldId = "fileUpload";
    private static String messageField = "message";
    private static String sendBtn = "//span[contains(text(),'Send')]";

    public static void clickOnContactUs(WebDriver wd){
        wd.findElement(By.linkText(contactUsLinkText)).click();
    }
    public static void chooseSubject(WebDriver wd,String subjectOption){
        Select subjectHeading = new Select(wd.findElement(By.id(subjectFieldId)));
        subjectHeading.selectByVisibleText(subjectOption);
    }
    public static void setEmail(WebDriver wd,String email){
        WebElement eMail = wd.findElement(By.id(emailFieldId));
        eMail.sendKeys(email);
    }
    public static void setOrderReference(WebDriver wd,String orderReference){
        WebElement orderReferenceEl = wd.findElement(By.id(orderFieldId));
        orderReferenceEl.sendKeys(orderReference);
    }
    public static void uploadImg(WebDriver wd,String imgPath){
      WebElement addPhoto =  wd.findElement(By.id(imgFieldId));
      addPhoto.sendKeys(imgPath);
    }
    public static void sendMessage(WebDriver wd,String mesageContent){
        WebElement sendMessage = wd.findElement(By.id(messageField));
        sendMessage.sendKeys(mesageContent);
    }
    public static void send(WebDriver wd){
        wd.findElement(By.xpath(sendBtn)).click();
    }
}
