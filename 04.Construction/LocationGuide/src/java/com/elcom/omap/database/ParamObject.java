/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.database;

import com.elcom.omap.common.Constant;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Pham Quang Kiem
 */
public class ParamObject {
    
    public static final int TYPE_BYTE = 0;
    public static final int TYPE_SHOT = 1;
    public static final int TYPE_INT = 2;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_DOUBLE = 4;
    public static final int TYPE_LONG = 5;
    
    public static final int TYPE_BOOLEAN = 6;
    public static final int TYPE_STRING = 7;
    public static final int TYPE_DATE = 8;
    
    private String value;
    private int paramType;

    public ParamObject() {
        value = "";
        paramType = 0;
    }

    public ParamObject(String value, int paramType) {
        this.value = value;
        this.paramType = paramType;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getParamType() {
        return paramType;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
    }
    
    public static byte convertToByte(String value){
        byte result = 0;
        try{
            result = Byte.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static short convertToShort(String value){
        short result = 0;
        try{
            result = Short.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static int convertToInt(String value){
        int result = 0;
        try{
            result = Integer.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static float convertToFloat(String value){
        float result = 0;
        try{
            result = Float.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static double convertToDouble(String value){
        double result = 0;
        try{
            result = Double.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static long convertToLong(String value){
        long result = 0;
        try{
            result = Long.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static boolean convertToBoolean(String value){
        boolean result = false;
        try{
            result = Boolean.valueOf(value);
        }catch (Exception e){
            
        }
        return result;
    }
    public static String convertToString(String value){
        return value;
    }
    public static Date convertToDate(String value){
        Date result = new Date(0);
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        try{
            java.util.Date d = format.parse(value);
            result = new Date(d.getTime());
        }catch (Exception e){
            
        }
        return result;
    }
    
}
