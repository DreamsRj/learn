package com.dreams.rj.learn.poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class TestPoI {
    public static void main(String[] args) {
        FileOutputStream out = null;
        File file = new File("/dreamsrj/abc.xlsx");


        try {
            out = new FileOutputStream(file);

            XSSFWorkbook workbook = null;
            if(file.exists()){
                 workbook = new XSSFWorkbook(new FileInputStream(file));
            }else{
                file.createNewFile();
                workbook = new XSSFWorkbook();
                workbook.createSheet("abc");
            }


            test(workbook, 1);
            workbook.write(out);
            test(workbook, 2);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test(XSSFWorkbook work , int rowNum){
        XSSFSheet sheet = work.getSheetAt(0);
        XSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < 10; i++) {
            row.createCell(i).setCellValue("ABC"+i);
        }

    }
}
