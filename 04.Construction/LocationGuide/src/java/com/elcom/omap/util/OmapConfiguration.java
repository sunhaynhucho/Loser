/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.util;

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
        properties.put("URL", "jdbc:mysql://192.168.6.106:3306/db_assignment");
        properties.put("AUTO_COMMIT", "false");
        properties.put("MAX_LIMIT", "1");
        properties.put("USERNAME", "assignment");
        properties.put("PASSWORD", "assignment");
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

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
