/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loser.map.test;

import com.loser.map.commons.AppConfig;
import com.loser.map.commons.Constant;
import com.loser.map.model.Location;
import com.loser.map.sql.SqlMapView;
import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author VinhNV
 */
public class Test {

    public static void main(String[] args) {
        String fileLogConfig = Constant.CONFIG_FOLDER + "ConfigLog.log4j.xml";
        DOMConfigurator.configure(fileLogConfig);
        // Start config
        AppConfig conf = new AppConfig();
        conf.reloadConfig();
        AppConfig.getConfig();
        AppConfig.reload();
        List<Location> lst = SqlMapView.locationGetByAll();
        System.out.println("" + lst.get(0).toString());
    }

}
