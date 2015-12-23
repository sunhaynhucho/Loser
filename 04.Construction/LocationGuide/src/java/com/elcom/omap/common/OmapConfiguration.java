/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Pham Quang Kiem
 */
public class OmapConfiguration {
    private String configFilePath;
    private Properties properties = new Properties();
    private boolean isXML;

    public OmapConfiguration(String configFilePath, boolean isXML) throws IOException {
        this.configFilePath = configFilePath;
        this.isXML = isXML;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(configFilePath);
            if (isXML) {
                properties.loadFromXML(fis);
            } else {
                properties.load(fis);
            }
        } catch (FileNotFoundException ex) {
            // creates the configuration file and set default properties
            setDefaults();
            save();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    private void setDefaults() {
        properties.put("URL", "jdbc:oracle:thin:@(description=(address=(host=192.168.11.121)(protocol=tcp)(port=1521))(connect_data=(SERVER = DEDICATED)(service_name=gstt)))");
        properties.put("USERNAME", "msurvey");
        properties.put("PASSWORD", "msurvey");
        properties.put("AUTO_COMMIT", "false");
        properties.put("MAX_LIMIT", "PGS_ROUTING_ERROR");
    }

    public void save() throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(configFilePath);
            if (isXML) {
                properties.storeToXML(fos, "My Application Settings","UTF-8");
            } else {
                properties.store(fos, "My Application Settings");
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public int getIntProperty(String key, int defaultValue) {
        String strValue = properties.getProperty(key, defaultValue + "");
        int result = defaultValue;
        try {
            result = Integer.parseInt(strValue);
        } catch (Exception e) {
        }
        return result;
    }
    
    public long getLongProperty(String key, long defaultValue) {
        String strValue = properties.getProperty(key, String.valueOf(defaultValue));
        long result = defaultValue;
        try {
            result = Long.parseLong(strValue);
        } catch (Exception e) {
        }
        return result;
    }
    
    public float getFloatProperty(String key, float defaultValue) {
        String strValue = properties.getProperty(key, String.valueOf(defaultValue));
        float result = defaultValue;
        try {
            result = Float.parseFloat(strValue);
        } catch (Exception e) {
        }
        return result;
    }
    
    public double getDoubleProperty(String key, double defaultValue) {
        String strValue = properties.getProperty(key, String.valueOf(defaultValue));
        double result = defaultValue;
        try {
            result = Double.parseDouble(strValue);
        } catch (Exception e) {
        }
        return result;
    }
    
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String strValue = properties.getProperty(key, String.valueOf(defaultValue));
        boolean result = defaultValue; 
        try {
            result = Boolean.parseBoolean(strValue);
        } catch (Exception e) {
        }
        return result;
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
