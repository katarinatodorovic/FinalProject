package tests;

import ecommercewebsite.ContactUsForm;
import ecommercewebsite.Message;
import ecommercewebsite.SettingsClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestOneMesage {
    private WebDriver driver;
    private static String imgPath = "C:\\Users\\kaca\\Desktop\\Kaca\\18-CONTACTUS-HEADER.jpg";
    @BeforeClass
    public void setProperties(){

        driver = SettingsClass.getDriver();
        ContactUsForm.clickOnContactUs(driver);
    }

     @Test
     public void testOneMessage() throws IOException {
        Message mesage = new Message(8,"mail.com",20);
         ContactUsForm.clickOnContactUs(driver);
         ContactUsForm.chooseSubject(driver, mesage.getSubjectHeading());
         ContactUsForm.setEmail(driver,mesage.getEmailAddress());
         ContactUsForm.setOrderReference(driver,"Dress");
         ContactUsForm.uploadImg(driver,imgPath);
         ContactUsForm.sendMessage(driver,mesage.getMessage());
         ContactUsForm.send(driver);
         driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
         String messageStatuss = driver.findElement(By.xpath(Message.getMessageStatuspath())).getText();
         Assert.assertEquals(messageStatuss,"Your message has been successfully sent to our team.");
     }
}
