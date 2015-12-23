/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap;

import com.elcom.omap.common.Constant;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jconfig.Configuration;
import org.jconfig.ConfigurationManager;
import org.jconfig.ConfigurationManagerException;
import org.jconfig.handler.XMLFileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ
 */
public class AppConfig {
    private static Configuration jconfig = null;// ConfigurationManager.getConfiguration(RoutingConstant.CONFIG_NAME);
    public static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);

    public AppConfig() {
    }

    public synchronized static Configuration getConfig() {
        if (jconfig == null) {
            jconfig = ConfigurationManager.getConfiguration(Constant.CONFIG_NAME);
        }
        return jconfig;
    }

    public static void reload() {
        synchronized (jconfig) {
            jconfig = ConfigurationManager.getConfiguration(Constant.CONFIG_NAME);
        }
    }

    public synchronized void reloadConfig() {
        ConfigurationManager cm = ConfigurationManager.getInstance();
        try {
            String fileStr = Constant.CONFIG_FOLDER;// + "ais_config_Center_BK.xml";
            File file = new File(fileStr);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileStr += Constant.CONFIG_FILE_NAME;
            file = new File(fileStr);
            if (!file.exists()) {
                createDefaultConfigFile(file);
            }
            XMLFileHandler fileHandler = new XMLFileHandler();
            fileHandler.setFile(file);
            cm.load(fileHandler, Constant.CONFIG_NAME);
        } catch (ConfigurationManagerException cme1) {
            logger.error("reloadConfig()", cme1);
        }
    }

    private void createDefaultConfigFile(File fout) {
        BufferedWriter w;
        try {
            w = new BufferedWriter(new FileWriter(fout));
            w.write("<?xml version=\"1.0\" ?>");
            w.newLine();
            w.write("\t<properties>");
            w.newLine();
            w.write("\t\t<category name=\"SETTINGS\">");
            w.newLine();
            w.write("\t\t\t<property name=\"NUM_SEND_THREAD\" value=\"20\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"TYPE_REQ\" value=\"1\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"SPEED_CREATE_REQ\" value=\"1\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"WS_URL\" value=\"http://192.168.6.112:8080/AccManagerWS/AccManagerProcessPort?wsdl\"/>");
            w.newLine();
            w.write("\t\t</category>");
            w.newLine();
            w.write("\t\t<category name=\"MYSQL\">");
            w.newLine();
            w.write("\t\t\t<property name=\"URL\" value=\"jdbc:oracle:thin:@(description=(address=(host=192.168.11.121)(protocol=tcp)(port=1521))(connect_data=(SERVER = DEDICATED)(service_name=gstt)))\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"USERNAME\" value=\"AVMS\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"PASSWORD\" value=\"AVMS\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"MAX_LIMIT\" value=\"15\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"MIN_LIMIT\" value=\"5\"/>");
            w.newLine();
            w.write("\t\t\t<property name=\"AUTO_COMMIT\" value=\"false\"/>");
            w.newLine();
            w.write("\t\t</category>");
            w.newLine();
            w.write("\t</properties>");
            w.flush();
            w.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
