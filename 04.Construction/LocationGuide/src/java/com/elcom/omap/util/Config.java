/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Pham Quang Kiem
 */
public class Config {

    protected final String FILENAME;
    protected final Properties props = new Properties();
    private final String charset;

    public void load() {
        try {
            System.out.println("Config.load(): Loding " + FILENAME + "...");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME), charset));
            props.load(in);

        } catch (Exception e) {
            System.out.println("Config.load(): exception loading file " + FILENAME + "..." + e);
            e.printStackTrace();
            setDefault();
            store();
        }
    }

    public void loadXML() {
        try {
            System.out.println("Config.load(): Loding XML file " + FILENAME + "...");
            props.loadFromXML(new FileInputStream(FILENAME));

        } catch (Exception e) {
            System.out.println("Config.load(): exception loading file " + FILENAME + "..." + e);
            e.printStackTrace();
            setDefault();
            store();
        }
    }

    protected String getComment() {
        return "#" + new Date();
    }

    public void store() {
        try {
            System.out.println("Config.store(): Writing " + FILENAME + "...");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME, false), charset));
            props.store(out, getComment());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void storeXML() {
        try {
            System.out.println("Config.store(): Writing XML file " + FILENAME + "...");
            props.storeToXML(new FileOutputStream(FILENAME), getComment(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void setDefault() {
    }

    public final String getKey(String category, String valuename) {
        if (category != null) {
            return props.getProperty(category + "." + valuename);
        } else {
            return props.getProperty(valuename);
        }
    }

    public final String getKey(String category, String valuename, String defaultvalue) {
        if (category != null) {
            return props.getProperty(category + "." + valuename, defaultvalue);
        } else {
            return props.getProperty(valuename, defaultvalue);
        }
    }

    public final void setKey(String category, String valuename, String value) {
        if (category != null) {
            props.setProperty(category + "." + valuename, value);
        } else {
            props.setProperty(valuename, value);
        }
    }

    public final int getKeyInt(String category, String valuename) {
        return Integer.parseInt(getKey(category, valuename));
    }

    public final int getKeyInt(String category, String valuename, int defaultvalue) {
        try {
            return Integer.parseInt(getKey(category, valuename, Integer.toString(defaultvalue)));
        } catch (Exception e) {
            return defaultvalue;
        }
    }

    public final void setKeyInt(String category, String valuename, int value) {
        setKey(category, valuename, Integer.toString(value));
    }

    public final long getKeyLong(String category, String valuename) {
        return Long.parseLong(getKey(category, valuename));
    }

    public final long getKeyLong(String category, String valuename, long defaultvalue) {
        try {
            return Long.parseLong(getKey(category, valuename, Long.toString(defaultvalue)));
        } catch (Exception e) {
            return defaultvalue;
        }
    }

    public final void setKeyLong(String category, String valuename, long value) {
        setKey(category, valuename, Long.toString(value));
    }

    public final boolean getKeyBoolean(String category, String valuename) {
        return "TRUE".equalsIgnoreCase(getKey(category, valuename));
    }

    public final boolean getKeyBoolean(String category, String valuename, boolean defaultvalue) {
        try {
            return "TRUE".equalsIgnoreCase(getKey(category, valuename, Boolean.toString(defaultvalue)));
        } catch (Exception e) {
            return defaultvalue;
        }
    }

    public final void setKeyBoolean(String category, String valuename, boolean value) {
        setKey(category, valuename, Boolean.toString(value));
    }

    public Config(String filename) {
        this(filename, "US-ASCII");
    }

    public Config(String filename, String charset) {
        super();
        this.FILENAME = filename;
        this.charset = charset;
    }
}
