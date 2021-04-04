package com.csx.csxspringboot.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: POIExcelUtil
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/31 21:33
 */
@Log4j2
@RestController
public class POIExcelUtil {

    /**
     * 将数据导出至excel文件
     * @Author Cuisx
     * @Date 0:29 2021/4/1
     * @return java.io.File
     */
    @PostMapping("/export")
    public static void PIOExportExcel() throws IOException, ParseException {

        //创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet();
        //创建行
        Row row1 = sheet.createRow(0);
        //创建单元格
        row1.createCell(0).setCellValue("标题一");
        row1.createCell(1).setCellValue("标题二");
        row1.createCell(2).setCellValue("标题三");
        row1.createCell(3).setCellValue("标题四");

        Row row2 = sheet.createRow(1);
        //创建单元格
        row2.createCell(0).setCellValue("Cuisx");
        row2.createCell(1).setCellValue("Cuisx");
        row2.createCell(2).setCellValue(20.06);
        row2.createCell(3).setCellValue("2021-04-01");
        OutputStream out = new FileOutputStream("0001.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
        log.info("success ");
    }



    /**
     * 解析excel文件：循环解析工作表（sheet1），将解析工作表的行数据放置Object[]中
     * @Author Cuisx
     * @Date 0:24 2021/4/1
     * @param excelFile
     * @param excelFilePath
     * @return java.util.List<java.lang.Object[]>
     */
    public static List<Object[]> PIOParseExcel(MultipartFile excelFile,String excelFilePath) throws IOException {

        List<Object[]> entitys = new ArrayList<>();
        //获取工作簿
        Workbook workbook = new XSSFWorkbook(excelFilePath);

        //获取工作表
        //获取工作表数量
        int Sheets = workbook.getNumberOfSheets();
        log.info("numberOfSheets: "+Sheets);
        //遍历工作表
        for (int i = 0; i <Sheets ; i++) {
            log.info("第 "+i+" 张工作表开始>>>>>>>>>> ");
            //得到工作表
            Sheet sheetAt = workbook.getSheetAt(i);
            //获取行数目
            int rows = sheetAt.getPhysicalNumberOfRows();
            for (int j=0; j<rows;j++) {
                //行数据
                Row row = sheetAt.getRow(j);
                //获取列数目
                int cells = row.getPhysicalNumberOfCells();
                Object[] objArray = new Object[cells];
                for (int k=0;k<cells;k++) {
                    //获取列数据
                    Cell cell = row.getCell(k);
                    CellType cellTypeEnum = cell.getCellTypeEnum();
                    switch (cellTypeEnum){
                        case _NONE:
                            objArray[k] = "";
                            log.info("_NONE");
                            break;
                        case STRING:
                            objArray[k] = cell.getStringCellValue();
                            log.info(cell.getStringCellValue());
                            break;
                        case ERROR:
                            objArray[k] = cell.getErrorCellValue();
                            log.info("ERROR");
                            break;
                        case BOOLEAN:
                            objArray[k] = cell.getBooleanCellValue();
                            log.info(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            objArray[k] = cell.getArrayFormulaRange();
                            log.info("FORMULA");
                            break;
                        case BLANK:
                            objArray[k] = "";
                            log.info("BLANK");
                            break;
                        case NUMERIC: //数字
                            //日期
                            String string;
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                Date dateCellValue = cell.getDateCellValue();
                                string = new DateTime(dateCellValue).toString("yyyy-MM-dd");
                                log.info(string);
                            }else {
                                cell.setCellType(CellType.STRING);
                                string = cell.getStringCellValue();
                                log.info(cell.getStringCellValue());
                            }
                            objArray[k] = string;
                            break;
                    }
                }
                entitys.add(objArray);
            }
            log.info("第 "+i+" 张工作表结束>>>>>>>>>> ");
        }
        workbook.close();
        return entitys;
    }

}
