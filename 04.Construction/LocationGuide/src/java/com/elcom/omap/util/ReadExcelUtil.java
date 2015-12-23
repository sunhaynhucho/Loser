/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

/**
 *
 * @author Pham Quang Kiem
 */
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExcelUtil {

    private static final String fdate1 = "dd/mm/yyyy";
    private static final String fdate2 = "dd-mm-yyyy";

    public static String castDataXLS(HSSFWorkbook wb, HSSFCell c) {
        String value = "";
        if (c != null) {
            int cellType = c.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                value = "";
            } else if (cellType == HSSFCell.CELL_TYPE_BOOLEAN) {
                value = "" + c.getBooleanCellValue();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                value = "" + c.getStringCellValue().trim();
            } else if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                //value = "" + c.getCellFormula();
                value = castDataFormularXLS(evaluator.evaluate(c), c);
                //System.out.println("CELL FORMULAR");
            } else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(c)) {
//                    value = new SimpleDateFormat(fdate1).format(c.getDateCellValue());
                    String dGiaTri = c.getDateCellValue().toString();
                    DateFormat readFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

                    Date date = null;
                    try {
                        date = readFormat.parse(dGiaTri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (date != null) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        value = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);

                    }

                } else {
                    value = "" + new BigDecimal(c.getNumericCellValue());
                }
            } else if (cellType == HSSFCell.CELL_TYPE_ERROR) {
                value = "" + c.getErrorCellValue();
            } else {
                value = "";
            }
        } else {
            return "";
        }
        return value;
    }

    public static String castDataXLSX(Workbook wb, Cell c) {
        String value = "";
        if (c != null) {
            int cellType = c.getCellType();
            if (cellType == Cell.CELL_TYPE_BLANK) {
                value = "";
            } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
                value = "" + c.getBooleanCellValue();
            } else if (cellType == Cell.CELL_TYPE_STRING) {
                value = "" + c.getRichStringCellValue().getString().trim();
            } else if (cellType == Cell.CELL_TYPE_FORMULA) {
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                //value = "" + c.getCellFormula();
                value = castDataFormularXLSX(evaluator.evaluate(c), c);
                //System.out.println("CELL FORMULAR 2007");
            } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(c)) {
//                    value = new SimpleDateFormat(fdate1).format(c.getDateCellValue());
                    String dGiaTri = c.getDateCellValue().toString();
                    DateFormat readFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

                    Date date = null;
                    try {
                        date = readFormat.parse(dGiaTri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (date != null) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        value = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);

                    }

                } else {
                    value = "" + new BigDecimal(c.getNumericCellValue());
                }
            } else if (cellType == Cell.CELL_TYPE_ERROR) {
                value = "" + c.getErrorCellValue();
            } else {
                value = "";
            }
        } else {
            return "";
        }
        return value;
    }

    private static String castDataFormularXLS(CellValue cellValue, HSSFCell c) {
        String value = "";
        if (cellValue != null) {
            switch (cellValue.getCellType()) {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    //System.out.println(cellValue.getBooleanValue());
                    value = "" + cellValue.getBooleanValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(c)) {
                        value = new SimpleDateFormat(fdate1).format(c.getDateCellValue()).toString().trim();
                    } else {
                        value = "" + new BigDecimal(c.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    //System.out.println(cellValue.getStringValue());
                    value = cellValue.getStringValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    value = "";
                    break;

                // CELL_TYPE_FORMULA will never happen
                case Cell.CELL_TYPE_FORMULA:
                    break;
            }
        }
        return value;
    }

    private static String castDataFormularXLSX(CellValue cellValue, Cell c) {
        String value = "";
        if (cellValue != null) {
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    //System.out.println(cellValue.getBooleanValue());
                    value = "" + cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(c)) {
                        value = new SimpleDateFormat(fdate1).format(c.getDateCellValue()).toString().trim();
                    } else {
                        value = "" + new BigDecimal(c.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    //System.out.println(cellValue.getStringValue());
                    value = cellValue.getStringValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    value = "";
                    break;
                // CELL_TYPE_FORMULA will never happen
                case Cell.CELL_TYPE_FORMULA:
                    break;
            }
        }
        return value;
    }
}
