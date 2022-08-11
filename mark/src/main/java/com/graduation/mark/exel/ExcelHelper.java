package com.graduation.mark.exel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.graduation.mark.models.Result;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "exam";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Result> excelToResult(InputStream is,int exam_id) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);

            Iterator<Row> rows = sheet.iterator();
            List<Result> results = new ArrayList<Result>();
            System.out.println("ExcelToResult");
            int rowNumber = 0;
            if(!rows.hasNext()){
                System.err.println("null Row");
            }
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {

                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                int student_id = 0,mark=0;
                int cellIdx = 0;
                if(!cellsInRow.hasNext()){
                    System.err.println("nullcell");
                }
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            student_id=(int) currentCell.getNumericCellValue();
                            System.out.println(student_id);
                            break;
                        case 1:
                            mark=(int) currentCell.getNumericCellValue();
                            System.out.println(mark);
                            break;
                        default:
                            System.err.println("default");
                            break;
                    }
                    cellIdx++;
                }
                if(student_id!=0) {
                    Result result = new Result(student_id, mark, exam_id);
                    System.err.println(result.toString());
                    results.add(result);
                }
            }
            workbook.close();
            return results;
        } catch (IOException e) {
            System.err.println("fail to parse Excel file: " + e.getMessage());

            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}