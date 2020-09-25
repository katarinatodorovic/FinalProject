package tests;

import ecommercewebsite.ContactUsForm;
import ecommercewebsite.Message;
import ecommercewebsite.RandomMessageFileGenerator;
import ecommercewebsite.SettingsClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestMessages {
    private WebDriver driver;
    @BeforeClass
    public void setProperties() throws IOException {
        driver = SettingsClass.getDriver();
        ContactUsForm.clickOnContactUs(driver);
        RandomMessageFileGenerator.
                writeExcel(Message.generateListOfRandomMessages
                        (30,7,
                                "hotmail.com",20),"MessagesData");
    }

    @Test
 public void testMessageSucessfulnes(){
        ArrayList<String>messageStatus= Message.sendMessages(30,driver);
        boolean messageSuccess = true;
        for (int i =0;i<messageStatus.size();i++){
            if (!messageStatus.get(i).equals("Your message has been successfully sent to our team.")){
                messageSuccess = false;
            }
        }
        Assert.assertEquals(messageSuccess,true);
    }
}
