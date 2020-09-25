package ecommercewebsite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Message {
    private String subjectHeading;
    private String emailAddress;
    private  String orderReference;
    private static String imgFilePaths="C:\Users\kaca\Desktop\Projekat\ImgPaths.txt";
    private  String imgFilePath;
    private String message;
    private static String messageStatuspath ="//p[@class='alert alert-success']";
    private static String AlphaNumericString = "0123456789abcdefghijklmnopqrstuvxyz";

   public Message(int lengthOfAddress, String domain, int lengthOfMessage) throws IOException {
        this.subjectHeading = subjectHeadingRandomiser();
        this.emailAddress = generateEmail(lengthOfAddress,domain);
        this.orderReference = randomString(lengthOfAddress);
        this.imgFilePath=getRandomImg();
        this.message=randomString(lengthOfMessage);
    }
    public Message(String subjectHeading, String emailAddress, String orderReference, String imgFilePath,String message){
        this.subjectHeading = subjectHeading;
        this.emailAddress = emailAddress;
        this.orderReference = orderReference;
       this.imgFilePath =imgFilePath;
        this.message=message;
    }
    public Message(String subjectHeading, String emailAddress, String orderReference, String message){
        this.subjectHeading = subjectHeading;
        this.emailAddress = emailAddress;
        this.orderReference = orderReference;
        this.imgFilePath=null;
        this.message=message;
    }

    public String getSubjectHeading() {
        return subjectHeading;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public  String getImgFilePath() {
        return imgFilePath;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageStatuspath() {
        return messageStatuspath;
    }

    public String subjectHeadingRandomiser(){
        String[]subjectOptions={"Customer service","Webmaster"};
        int index = (int) ((Math.random() * 2));
        String subjOption=subjectOptions[index];
        return subjOption;

    }
    public static String getRandomImg() throws IOException {
        int count=0;
        File files;
        try{
            files = new File(imgFilePaths);
            count=0;
            Scanner scanner = new Scanner(files);
            while (scanner.hasNextLine()) {
                String path = scanner.nextLine();
                count++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("Problem with input file!");
        }
        int r = (int) ((Math.random() * (count - 1)) + 1);
        String line = Files.readAllLines(Paths.get(imgFilePaths)).get(r);
        return line;
    }
    public static String randomString(int n) {

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public static String generateEmail( int length,String domain) {
        return (randomString(length) + "@" + domain);
    }
    public static ArrayList generateListOfRandomMessages(int numOfMessages,int lengthOfAddress, String domain, int lengthOfMessage ) throws IOException {
        ArrayList<Message> messages=new ArrayList<>();
        for (int i =0;i<numOfMessages;i++){
            Message message = new Message(lengthOfAddress,domain,lengthOfMessage);
            messages.add(message);

        }
        return messages;
    }
    public static ArrayList sendMessages(int numOfMessages, WebDriver driver) {

        ArrayList<String> messageStatus = new ArrayList<>();
        for (int i = 1; i <= numOfMessages; i++) {
            try {
                FileInputStream fi = new FileInputStream(RandomMessageFileGenerator.getExcelFilepath());
                XSSFWorkbook wb = new XSSFWorkbook(fi);


                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(1);

                ContactUsForm.chooseSubject(driver, cell.toString());


                cell = row.getCell(2);
                ContactUsForm.setEmail(driver, cell.toString());

                cell = row.getCell(3);
                ContactUsForm.setOrderReference(driver, cell.toString());

                cell = row.getCell(4);
               ContactUsForm.uploadImg(driver,cell.toString());

                cell = row.getCell(5);
                ContactUsForm.sendMessage(driver, cell.toString());


                Thread.sleep(1500);

                ContactUsForm.send(driver);
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                String messageStatuss = driver.findElement(By.xpath(messageStatuspath)).getText();
                messageStatus.add(messageStatuss);
                driver.navigate().back();
                driver.navigate().refresh();

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return messageStatus;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSubjectHeading()).
                append("[ sent from: ").append(getEmailAddress()).
                append(", ").append(getOrderReference()).
                append(",(").append(getImgFilePath()).
                append(")").append(getMessage()).append("]");
        return String.valueOf(sb);
    }
}
