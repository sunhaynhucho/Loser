/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

import com.elcom.omap.common.Constant;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class OmapUtils {

    private static OmapUtils instance = null;
    private static Logger logger = null;// LoggerFactory.getLogger(Constant.LOGGER_NAME);

    public static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
        }
        return logger;
    }

    public static String convertMoney(String tarrif) {
        long l = 0;
        try {
            l = Long.parseLong(tarrif);
        } catch (Exception e) {
        }
        String result = CurrencyUtil.format(l) + " VND";
        return result;
    }

    public static String parserPositionIcon(int ic) {
        String result = "icon-nv";
        switch (ic) {
            case 0:
                result = "icon-tp";
                break;
            case 1:
                result = "icon-tb";
                break;
            case 2:
                result = "icon-nv";
                break;
        }
        return result;
    }

    public static String convertTienDoCongViec(int wprocess) {
        String hoanthanh = "";
        switch (wprocess) {
            case 0:
                hoanthanh = "<span style=\"color: red\">Chưa thực hiện</span>";
                break;
            case 1:
                hoanthanh = "<span style=\"color: green\">Hoàn thành</span>";
                break;
            case 2:
                hoanthanh = "<span style=\"color: blue\">Đang thực hiện</span>";
                break;
            default:
                hoanthanh = "<span style=\"color: red\">Chưa thực hiện</span>";
                break;
        }
        return hoanthanh;
    }
    private int msgSeq = 0;

    public OmapUtils() {
    }

    public synchronized int getMsgSeq() {
        msgSeq++;
        return msgSeq;
    }

    public static OmapUtils getInstance() {
        if (instance == null) {
            instance = new OmapUtils();
        }
        return instance;
    }

    public static boolean isPhoneNumber(String phonenumber) {
        try {
            if (phonenumber.startsWith("+") || phonenumber.startsWith("0")) {
                Long.parseLong(phonenumber.substring(1));
            } else {
                Long.parseLong(phonenumber);
            }
        } catch (Exception e) {
            return false;
        }
        if (phonenumber.length() < 9) {
            return false;
        }
        int index = 0;
        switch (phonenumber.charAt(0)) {
            case '+':
                if (phonenumber.charAt(1) != '8' || phonenumber.charAt(2) != '4') {
                    return false;
                }
                index = 3;
                break;
            case '8':
                if (phonenumber.charAt(1) != '4') {
                    return false;
                }
                index = 2;
                break;
            case '0':
                index = 1;
                break;
            default:
                break;
        }
        if (phonenumber.charAt(index) == '9') {
            return (phonenumber.length() - index) == 9;
        } else if (phonenumber.charAt(index) == '1') {
            return (phonenumber.length() - index) == 10;
        }
        return false;
    }

    public static String convertPhoneNumber(String number) {
        if (!isPhoneNumber(number)) {
            return null;
        }
        String vinaNumber = number;
//        if (number.startsWith("0")) {
//            vinaNumber = number.substring(1);
//        }
        if (number.startsWith("+84")) {
            vinaNumber = "0" + number.substring(3);
        }
        if (number.startsWith("84")) {
            vinaNumber = "0" + number.substring(2);
        }
        if (vinaNumber.startsWith("+")) {
            return null;
        }
        return vinaNumber;
    }

    public static boolean isPhoneMobifone(String number) throws NumberFormatException {
        if (!isPhoneNumber(number)) {
            return false;
        }

        String vinaNumber = number;
        if (number.startsWith("0")) {
            vinaNumber = number.substring(1);
        }
        if (number.startsWith("+84")) {
            vinaNumber = number.substring(3);
        }
        if (number.startsWith("84")) {
            vinaNumber = number.substring(2);
        }
        if (vinaNumber.startsWith("+")) {
            return false;
        }

        // check for vina
        if (vinaNumber.charAt(0) == '9') {
            if (vinaNumber.charAt(1) == '0' || vinaNumber.charAt(1) == '3') {
                return true;
            }
        } else if (vinaNumber.charAt(1) == '2' && vinaNumber.charAt(0) == '1') {
            if (vinaNumber.charAt(2) == '0' || vinaNumber.charAt(2) == '1'
                    || vinaNumber.charAt(2) == '2'
                    || vinaNumber.charAt(2) == '6'
                    || vinaNumber.charAt(2) == '8') {
                return true;
            }
        }

        return false;
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
        return dateFormat.format(new Date());
    }

    public static String getCurrentDate(int number) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
        long n = number * 1000 * 60 * 60 * 24;
        return dateFormat.format(new Date(System.currentTimeMillis() + n));
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String convertStringDate(long longDate) {
        Date date = new Date(longDate);
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        return format.format(date);
    }

    public static long getDays(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        long diffDays = 0;
        long diffTime = 0;
        try {
            long startTime = format.parse(startDate).getTime();
            long endTime = format.parse(endDate).getTime();
            diffTime = endTime - startTime;
        } catch (Exception e) {
        }
        diffDays = diffTime / (1000 * 60 * 60 * 24);
        return diffDays;
    }

    public static void showErrSessionTimeOut(PrintWriter out) {
        JSONObject jObj = new JSONObject();
        jObj.put("code", "3");
        jObj.put("detail", "Session của bạn đã hết. Bạn hãy refesh lại web và thực hiện đăng nhập lại");
        out.print(jObj);
        out.flush();
    }

    public static void ExportExcel(HttpServletResponse response, String path) throws ServletException, IOException {
        try {
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(new File(path));
            if (in == null) {
                out.close();
            } else {
                byte[] buffer = new byte[1024];
                int len;

                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }

                out.flush();
                in.close();
                out.close();
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    public static int parserInteger(Object value) {
        int result = 0;
        try {
            result = Integer.parseInt(String.valueOf(value));
        } catch (Exception e) {
        }
        return result;
    }

    public static long parserLong(Object value) {
        long result = 0;
        try {
            result = Long.parseLong(String.valueOf(value));
        } catch (Exception e) {
        }
        return result;
    }

    public static String parserObjectToString(Object object) {
        String value = String.valueOf(object);
        if (value == null || "null".equals(value)) {
            return "";
        }
        return value;
    }
}
