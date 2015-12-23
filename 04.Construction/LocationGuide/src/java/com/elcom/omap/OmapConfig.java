/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap;

import com.elcom.omap.common.Constant;
import com.elcom.omap.util.OmapConfiguration;
import java.io.IOException;

/**
 *
 * @author KiemPQ-PC
 */
public class OmapConfig {

    private static OmapConfiguration omapConfig = null;
    public OmapConfig() {
    }
    
    public static OmapConfiguration getConfig() {
        if (omapConfig == null) {
            try {
                omapConfig = new OmapConfiguration(Constant.CONFIG_FOLDER+ "settings.xml", true);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return omapConfig;
    }
}
