/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.login;

import com.elcom.omap.common.Constant;
import com.elcom.omap.database.DataPeresitents;
import com.elcom.omap.util.OmapUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class LoginDAO {

    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);

    public static OmapUser getUserByUserName(String tenDangNhap) {
        String sql = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip,"
                + "a.phonenumber, a.address, a.checkip, b.pos_type, b.pos_name "
                + "FROM qtht_users a, qtht_positions b where a.pos_id = b.pos_id and a.username = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(tenDangNhap);
        List lUser = DataPeresitents.queryList(sql, params);
        if (lUser != null && lUser.size() > 0) {
            Object[] obj = (Object[]) lUser.get(0);
            OmapUser user = new OmapUser();
            String userId = String.valueOf(obj[0]);
            String userName = String.valueOf(obj[1]);
            String passWord = String.valueOf(obj[2]);
            String fullname = String.valueOf(obj[3]);
            String email = String.valueOf(obj[4]);
            String ip = String.valueOf(obj[5]);
            String phonenumber = String.valueOf(obj[6]);
            String address = String.valueOf(obj[7]);
            String checkip = String.valueOf(obj[8]);
            int posType = OmapUtils.parserInteger(obj[9]);
            String posName = OmapUtils.parserObjectToString(obj[10]);
            user.setUserId(userId);
            user.setUserName(userName);
            user.setUserPass(passWord);
            user.setFullName(fullname);
            user.setEmail(email);
            user.setIp(ip);
            user.setPhoneNumber(phonenumber);
            user.setAddress(address);
            user.setCheckIp(checkip);
            user.setPosType(posType);
            user.setPosName(posName);
            return user;
        }
        return null;
    }

    public static OmapUser getUserBySessionId(String sessionID) {
        String sql = "SELECT a.userid, a.username, a.password, a.subid, a.amount, a.tarrif,a.issub,a.msisdn,a.fullname,a.resever FROM tbl_users a where a.SESSIONID = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(sessionID);
        List lUser = DataPeresitents.queryList(sql, params);
        if (lUser != null && lUser.size() > 0) {
            Object[] obj = (Object[]) lUser.get(0);
            OmapUser user = new OmapUser();
            String userId = String.valueOf(obj[0]);
            String userName = String.valueOf(obj[1]);
            String passWord = String.valueOf(obj[2]);
            String subId = String.valueOf(obj[3]);
            String strAmount = String.valueOf(obj[4]);
            if(strAmount.equals("null")){
                strAmount = "0";
            }
            long amount = Long.parseLong(strAmount);
            String tarrif = String.valueOf(obj[5]);
            String isSub = String.valueOf(obj[6]);
            String msisdn = String.valueOf(obj[7]);
            String fullName = String.valueOf(obj[8]);
            String strReser = String.valueOf(obj[9]);
            if(strReser.equals("null")){
                strReser = "0";
            }
           /* long resever = Long.parseLong(strReser);
            user.setAmount(amount);
            user.setIsSub(isSub);
            user.setPassWord(passWord);
            user.setSubId(subId);
            user.setTarrif(tarrif);
            user.setUserId(userId);
            user.setUserName(userName);
            user.setFullName(fullName);
            user.setMsisdn(msisdn);
            user.setResever(resever);*/
            return user;
        }
        return null;
    }

    public static boolean setLoginSessionID(String id, String userName) {
        String sql_update = "update tbl_users set SESSIONOLD = (select SESSIONID from tbl_users where USERNAME = ?) where USERNAME = ?";
        String sql = "update tbl_users set SESSIONID = ? where USERNAME = ?";
        try {

            ArrayList<String> params = new ArrayList<String>();
            params.add(id);
            params.add(userName);
            ArrayList<String> params2 = new ArrayList<String>();
//            params2.add(id);
            params2.add(userName);
            params2.add(userName);
            DataPeresitents.updateData(sql_update, params2);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addUser()==>" + sql, ex);
        }
        return false;
    }

    public static boolean setLoginSessionIDNULL(String key) {
        String sql = "update tbl_users set SESSIONID = '',SESSIONOLD = '' where SESSIONID = ?";
        try {

            ArrayList<String> params = new ArrayList<String>();
            params.add(key);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addUser()==>" + sql, ex);
        }
        return false;
    }

    public static OmapUser getUserByOldSessionId(String sessionID) {
        String sql = "SELECT a.userid, a.username, a.password, a.subid, a.amount, a.tarrif,a.issub,a.msisdn,a.fullname,a.resever FROM tbl_users a where a.SESSIONOLD = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(sessionID);
        List lUser = DataPeresitents.queryList(sql, params);
        if (lUser != null && lUser.size() > 0) {
            Object[] obj = (Object[]) lUser.get(0);
            OmapUser user = new OmapUser();
            String userId = String.valueOf(obj[0]);
            String userName = String.valueOf(obj[1]);
            String passWord = String.valueOf(obj[2]);
            String subId = String.valueOf(obj[3]);
            String strAmount = String.valueOf(obj[4]);
            if(strAmount.equals("null")){
                strAmount = "0";
            }
            long amount = Long.parseLong(strAmount);
            String tarrif = String.valueOf(obj[5]);
            String isSub = String.valueOf(obj[6]);
            String msisdn = String.valueOf(obj[7]);
            String fullName = String.valueOf(obj[8]);
            String strReser = String.valueOf(obj[9]);
            if(strReser.equals("null")){
                strReser = "0";
            }
            long resever = Long.parseLong(strReser);
          /*  user.setAmount(amount);
            user.setIsSub(isSub);
            user.setPassWord(passWord);
            user.setSubId(subId);
            user.setTarrif(tarrif);
            user.setUserId(userId);
            user.setUserName(userName);
            user.setFullName(fullName);
            user.setMsisdn(msisdn);
            user.setResever(resever);*/
            return user;
        }
        return null;
    }
}
