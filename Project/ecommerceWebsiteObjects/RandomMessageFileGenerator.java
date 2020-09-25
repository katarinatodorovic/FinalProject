package ecommercewebsite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class RandomMessageFileGenerator {
    private static String excelFilepath;

    public static String getExcelFilepath() {
        return excelFilepath;
    }

    private static void writeMessage(Message message, Row row) {
        Cell cell = row.createCell(1);
        cell.setCellValue(message.getSubjectHeading());

        cell = row.createCell(2);
        cell.setCellValue(message.getEmailAddress());

        cell = row.createCell(3);
        cell.setCellValue(message.getOrderReference());

        cell = row.createCell(4);
        cell.setCellValue(message.getImgFilePath());

        cell = row.createCell(5);
        cell.setCellValue(message.getMessage());
    }

    public static void writeExcel(List<Message> messages, String fileName) throws IOException {

        excelFilepath = "C:\\Users\\kaca\\Desktop\\"+fileName+".xlsx" ;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row;
        int rowCount = 0;
        row = sheet.createRow(0);
        Cell cell = row.createCell(1);
        cell.setCellValue("SubjectHeading");

        cell = row.createCell(2);
        cell.setCellValue("EmailAddress");

        cell = row.createCell(3);
        cell.setCellValue("OrderReference");

        cell = row.createCell(4);
        cell.setCellValue("ImgFilePath");

        cell = row.createCell(5);
        cell.setCellValue("Message");

        for (Message message : messages) {
            row = sheet.createRow(++rowCount);
            writeMessage(message, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilepath)) {
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Your excel file has been generated!");
    }
}
