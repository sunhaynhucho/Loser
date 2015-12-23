/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

/**
 *
 * @author KiemPQ-PC
 */
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import com.lzc.io.ExcelUtil;
import com.lzc.io.ExcelUtil.BorderStyle;
import com.lzc.io.ExcelUtil.FillStyle;
import com.lzc.io.ExcelUtil.FontStyle;
import com.lzc.io.ExcelUtil.TextStyle;

public class ExcelStyle {
	public static BorderStyle borderNone = ExcelUtil.createBorderStyle(HSSFCellStyle.BORDER_NONE,HSSFCellStyle.BORDER_THIN);
	public static BorderStyle borderStyle = ExcelUtil.createBorderStyle(ExcelUtil.BORDER_ALL,HSSFCellStyle.BORDER_THIN);
    
	public static FontStyle fontStyleHeader = ExcelUtil.createFontStyle(HSSFColor.BLACK.index,10,true,false,false,HSSFFont.U_NONE,"Arial");
	public static FontStyle fontStyleTitle = ExcelUtil.createFontStyle(HSSFColor.BLACK.index,14,true,false,false,HSSFFont.U_NONE,"Arial");
	public static FontStyle fontStyleNomal = ExcelUtil.createFontStyle(HSSFColor.BLACK.index,10,false,false,false,HSSFFont.U_NONE,"Arial");
	public static FontStyle fontStyleBold = ExcelUtil.createFontStyle(HSSFColor.BLACK.index,10,true,false,false,HSSFFont.U_NONE,"Arial");
	public static FontStyle fontStyleBoldColor = ExcelUtil.createFontStyle(HSSFColor.RED.index,10,true,false,false,HSSFFont.U_NONE,"Arial");
    
	public static TextStyle textStyleCenter = ExcelUtil.createTextStyle(true,HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER);
	public static TextStyle textStyleLeft = ExcelUtil.createTextStyle(true,HSSFCellStyle.ALIGN_LEFT,HSSFCellStyle.VERTICAL_CENTER);
	public static TextStyle textStyleRight = ExcelUtil.createTextStyle(true,HSSFCellStyle.ALIGN_RIGHT,HSSFCellStyle.VERTICAL_CENTER);
    
	public static FillStyle fillStyleNone = ExcelUtil.createFillStyle(HSSFColor.WHITE.index,(int)HSSFCellStyle.SOLID_FOREGROUND);
    
}