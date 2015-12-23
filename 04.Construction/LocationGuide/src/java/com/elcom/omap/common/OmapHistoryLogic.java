/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.common;

import com.elcom.omap.database.DataPeresitents;
import java.util.ArrayList;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class OmapHistoryLogic {

    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
    //InsertUserHistory("Khai thác","1", uname, "Tạo account EZPay không thành công +" + msisdn + " msisdnError", clientIp);

    public static boolean insertUserHistory(String action, String resul, String username,
            String actiondetail, String ip) {
        int result = 0;
        ArrayList<String> params = new ArrayList<String>();
        String strquery = "insert into qtht_userhistory ( actionname, actiondetail, username, actiontime,"
                + "actionstatus,ipaddress) values(?,?,?,CURRENT_DATE,?,?)";
        params.add(action);
        params.add(actiondetail);
        params.add(username);
        params.add(resul);
        params.add(ip);
        try {
            result = DataPeresitents.updateData(strquery, params);//
        } catch (SystemException e) {
            logger.error("insertUserHistory()=>", e);
        } catch (Exception e) {
            logger.error("insertUserHistory()=>", e);
        }
        return result > 0;
    }

    
}
