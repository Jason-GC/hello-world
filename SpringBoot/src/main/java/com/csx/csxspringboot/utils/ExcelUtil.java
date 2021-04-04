package com.csx.csxspringboot.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExcelUtil
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/27 21:43
 */
@Log4j2
public class ExcelUtil {

    /**
     * poi方式解析Excel文件
     * @Author Cuisx
     * @Date 16:57 2021/3/28
     * @param excelFile
     * @return java.util.List<java.lang.Object> 返回结果
     */
    public static List<Object> parseExcelFile(String excelFile) throws IOException {

        InputStream fileInputStream = null;
        List<Object> list = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(excelFile);
            //创建工作簿
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取标题行
            Row rowTitle = sheet.getRow(0);
            if(rowTitle != null){
                //列数量
                int cellsNum = rowTitle.getPhysicalNumberOfCells();
                for (int i=0; i<cellsNum; i++){
                    Cell cell = rowTitle.getCell(i);
                    if(cell != null){
                        System.out.print(cell.getStringCellValue()+" | ");
                    }
                }
                System.out.println("\n");
            }

            //获取内容
            //得到行数
            int rowsNum = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowsNum; i++) {
                List<Object> obj = new ArrayList<>();
                //得到每一行数据
                Row rowData = sheet.getRow(i);
                if(rowData != null){
                    //与标题列对应的内容列
                    int cellNum = rowTitle.getPhysicalNumberOfCells();
                    System.out.print("[ ");
                    for (int j = 0; j <cellNum; j++) {
                        Cell cell = rowData.getCell(j);
                        int cellType = cell.getCellType();
                        String cellValue = "";
                        switch (cellType){
                            case Cell.CELL_TYPE_STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                //日期
                                if(HSSFDateUtil.isCellDateFormatted(cell)){
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                }else {
                                //数字
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cellValue = cell.getStringCellValue();
                                }
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                System.out.println("数据类型错误");
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                log.info("数据为空");
                                break;
                        }
                        System.out.print(cellValue+" | ");
                        obj.add(cellValue);
                    }
                    System.out.print(" ]");
                }
                System.out.println("\n");
                list.add(obj);
            }
        } catch (FileNotFoundException e) {
            log.info("文件未找到！！！");
            e.printStackTrace();
        } catch (IOException e) {
            log.info("解析Excel文件错误！！！");
            e.printStackTrace();
        }finally {
            fileInputStream.close();
        }

        return list;
    }
    
    /**
     * 生成Excel文件
     * @Author Cuisx
     * @Date 17:09 2021/3/28
     * @param excelFlieName
     * @param data 数据
     * @return void
     */
    public static void writeExcel(String excelFlieName,List<Object> data) throws Exception{

        //创建工作簿
//        Workbook workbook = new XSSFWorkbook();
        Workbook workbook = new SXSSFWorkbook();//大数据
        //创建工作表
        Sheet sheet = workbook.createSheet(excelFlieName);
        //创建行
        Row row1 = sheet.createRow(0);
        //创建列名
        String[] columnsName = new String[]{"userName","createTime"};
        for (int i=0; i<2; i++){
            Cell cell = row1.createCell(i);
            cell.setCellValue(columnsName[i]);
        }

        //创建数据
        List<String> nameList = new ArrayList<>();
        nameList.add("name1");
        nameList.add("name2");
        List<String> timeList = new ArrayList<>();
        timeList.add(new DateTime().toString("yyy-MM-dd HH:mm:ss"));
        timeList.add(new DateTime().toString("yyy-MM-dd HH:mm:ss"));

        Row row2 = sheet.createRow(1);
        for (int i=0; i<2; i++){
            Cell cell = row2.createCell(i);
            cell.setCellValue(nameList.get(i));
            cell.setCellValue(timeList.get(i));
        }

        //生成excel
        FileOutputStream fileOutputStream = new FileOutputStream("user01.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        //清除临时文件
        ((SXSSFWorkbook)workbook).dispose();
        workbook.close();
        log.info("生成excel文件结束");
    }

}
