/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

import com.elcom.omap.common.Constant;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Pham Quang Kiem
 */
public class CurrencyUtil {

    private static String DEFAULT_PATTERN = "###,###.##";
    private static char DEFAULT_GROUP_SEPARATOR = '.';
    private static char DEFAULT_DECIMAL_SEPARATOR = ',';

    public static void reset() {
        Config cfg = new Config(Constant.CONFIG_FOLDER +  "currency.cfg") {
            @Override
            public void load() {
                super.load();
                DEFAULT_PATTERN = getKey(null, "DEFAULT_PATTERN", DEFAULT_PATTERN);
                try {
                    DEFAULT_GROUP_SEPARATOR = getKey(null, "DEFAULT_GROUP_SEPARATOR", ".").charAt(0);
                } catch (Exception e) {
                }
                try {
                    DEFAULT_DECIMAL_SEPARATOR = getKey(null, "DEFAULT_DECIMAL_SEPARATOR", ",").charAt(0);
                } catch (Exception e) {
                }
            }

            @Override
            public void store() {
                setKey(null, "DEFAULT_PATTERN", DEFAULT_PATTERN);
                setKey(null, "DEFAULT_GROUP_SEPARATOR", "" + DEFAULT_GROUP_SEPARATOR);
                setKey(null, "DEFAULT_DECIMAL_SEPARATOR", "" + DEFAULT_DECIMAL_SEPARATOR);
                super.store();
            }

            @Override
            protected void setDefault() {
                super.setDefault();
                setKey(null, "DEFAULT_PATTERN", DEFAULT_PATTERN);
                setKey(null, "DEFAULT_GROUP_SEPARATOR", ".");
                setKey(null, "DEFAULT_DECIMAL_SEPARATOR", ",");
            }
        };
        try {
            cfg.load();
        } catch (Exception e) {
        }
    }

    static {
        reset();
    }

    public static String format(long value) {
        return format(value, DEFAULT_PATTERN, DEFAULT_GROUP_SEPARATOR, DEFAULT_DECIMAL_SEPARATOR);
    }

    public static String format(long value, String pattern, char groupSeparator, char decimalSeparator) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(groupSeparator);
        symbols.setDecimalSeparator(decimalSeparator);
        DecimalFormat fmter = new DecimalFormat(pattern, symbols);
        return fmter.format(value);
    }

    public static String format(long value, String pattern) {
        return format(value, pattern, DEFAULT_GROUP_SEPARATOR, DEFAULT_DECIMAL_SEPARATOR);
    }

    public static String format(double value) {
        return format(value, DEFAULT_PATTERN, DEFAULT_GROUP_SEPARATOR, DEFAULT_DECIMAL_SEPARATOR);
    }

    public static String format(double value, String pattern, char groupSeparator, char decimalSeparator) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(groupSeparator);
        symbols.setDecimalSeparator(decimalSeparator);
        DecimalFormat fmter = new DecimalFormat(pattern, symbols);
        return fmter.format(value);
    }

    public static String format(double value, String pattern) {
        return format(value, pattern, DEFAULT_GROUP_SEPARATOR, DEFAULT_DECIMAL_SEPARATOR);
    }

    public static void main(String args[]) {
        System.out.println(CurrencyUtil.format(1000000)); //1,000,000
        System.out.println(CurrencyUtil.format(-1000000));//-1,000,000
        System.out.println(CurrencyUtil.format(1000000.17, "###,###.## 'VND'", '.', ','));//1,000,000.17 VND
    }
}
