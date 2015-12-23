/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.quantrihethong;

import com.elcom.omap.common.Constant;
import com.elcom.omap.database.DataPeresitents;
import com.elcom.omap.database.MySQLAccess;
import com.elcom.omap.util.OmapUtils;
import com.kiemanh.vn.DbConnection;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class QuanTriHeThongDataLogic {

    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);

    public static List getListChucVu() {
        String sql = "select pos_id, pos_name, pos_type from qtht_positions order by pos_type";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListCenter() {
        String sql = "select a.centerid, a.centercode, a.centername, a.address, a.tel, a.fax,a.status from VMSCENTER a WHERE a.CENTERCODE<>'company' order by a.CENTERNAME";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListCenterByUserName(String userName) {
        String sql = "select centerid, centercode, centername, address, tel, fax, status from VMSCENTER where CENTERID = (select centerid from user_info where username=?)";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userName);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListCenterActive(String status) {
        String sql = "select a.centerid, a.centercode, a.centername, a.address, a.tel, a.fax,a.status from VMSCENTER a WHERE a.STATUS=? order by a.CENTERNAME";
        ArrayList<String> params = new ArrayList<String>();
        params.add(status);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListRoleByUserName(String userName) {
        String sql = "select r.roleid, r.rolename from qtht_roles r, qtht_usersroles ur where r.roleid=ur.roleid and ur.userid in(select userid from tbl_users where username=?)";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userName);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List listUserByUserName(String userName) {
        String sql = "a.userid, a.username, a.userpass, a.fullname, a.email, a.ip, "
                + "a.phonenumber, a.address, a.checkip FROM qtht_users a order by a.username";
        ArrayList<String> params = new ArrayList<String>();
//        params.add(userName);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List listUserByUserId(String userid) {
        String sql = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip, "
                + "a.phonenumber, a.address, a.checkip, a.pos_id, b.image_url "
                + "FROM qtht_users a, qtht_users b where a.userid = b.userid and a.userid=?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userid);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListUser() {
        String sql = "SELECT a.userid, a.username, a.password_user, a.fullname, "
                + "a.centerid,a.email, a.ip, a.cellphone, a.last_login, "
                + "a.address, a.comments, a.server_type "
                + "FROM user_info a order by a.fullname";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListUserOrigin() {
        String sql = "SELECT a.userid, a.username, a.password, a.subid, a.amount, a.tarrif,"
                + "a.issub, a.msisdn, a.fullname "
                + "FROM tbl_users a where a.issub = 1 order by a.fullname";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    static List listUserOfCenter(String ctId) {
        //select * from user_info where centerid=? order by FULLNAME
        String sql = "SELECT a.userid, a.username, a.password_user, a.fullname, "
                + "a.centerid,a.email, a.ip, a.cellphone, a.last_login, "
                + "a.address, a.comments, a.server_type "
                + "FROM user_info a where a.centerid = ? order by a.fullname";

        ArrayList<String> params = new ArrayList<String>();
        params.add(ctId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static boolean addUser(String userName, String passWord, String fullname, String email,
            String ip, String phonenumber, String address, String checkip, String posId, String imageUrl) {

        if (imageUrl.trim() == "") {
            String sql = "insert into qtht_users (username, userpass, fullname, email, ip, phonenumber, "
                    + "address, checkip, pos_id) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            try {

                ArrayList<String> params = new ArrayList<String>();
                params.add(userName);
                params.add(passWord);
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(posId);

                int result = DataPeresitents.updateData(sql, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("addUser()==>" + sql, ex);
            }
        } else {
            String sql = "insert into qtht_users (username, userpass, fullname, email, ip, phonenumber, "
                    + "address, checkip, pos_id, image_url) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            try {

                ArrayList<String> params = new ArrayList<String>();
                params.add(userName);
                params.add(passWord);
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(posId);
                params.add(imageUrl);

                int result = DataPeresitents.updateData(sql, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("addUser()==>" + sql, ex);
            }
        }

        return false;
    }

    static boolean checkUserNameExits(String tenDangNhap) {
        String sql = "SELECT * FROM qtht_users where username=?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(tenDangNhap);
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl != null && resutl.size() >= 1) {
            return true;
        }
        return false;
    }

    public static boolean editUser(String userId, String username, String passWord, String fullname,
            String email, String ip,
            String phonenumber, String address, String checkip, String posId, String imgUrl) {

        if (imgUrl.trim() != "") {

            String sql = "update qtht_users set userpass = ?, fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, pos_id = ?, image_url = ? "
                    + "where userid = ?";
            String sql_NoPass = "update qtht_users set fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, pos_id = ?, image_url = ? where userid = ?";
            String sqlQuery = sql_NoPass;
            try {

                ArrayList<String> params = new ArrayList<String>();
                if (passWord != null && passWord.equals("") == false) {
                    params.add(passWord);
                    sqlQuery = sql;
                }
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(posId);
                params.add(imgUrl);
                params.add(userId);

                int result = DataPeresitents.updateData(sqlQuery, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("editUser()==>" + sql, ex);
            }
        } else {
            String sql = "update qtht_users set userpass = ?, fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, pos_id = ? "
                    + "where userid = ?";
            String sql_NoPass = "update qtht_users set fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, pos_id = ? where userid = ?";
            String sqlQuery = sql_NoPass;
            try {

                ArrayList<String> params = new ArrayList<String>();
                if (passWord != null && passWord.equals("") == false) {
                    params.add(passWord);
                    sqlQuery = sql;
                }
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(posId);
                params.add(userId);

                int result = DataPeresitents.updateData(sqlQuery, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("editUser()==>" + sql, ex);
            }
        }
        return false;
    }

    public static boolean editUser(String userId, String username, String passWord, String fullname,
            String email, String ip,
            String phonenumber, String address, String checkip) {
        String sql = "update qtht_users set userpass = ?, fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ? "
                + "where userid = ?";
        String sql_NoPass = "update qtht_users set fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ? where userid = ?";
        String sqlQuery = sql_NoPass;
        try {

            ArrayList<String> params = new ArrayList<String>();
            if (passWord != null && passWord.equals("") == false) {
                params.add(passWord);
                sqlQuery = sql;
            }
            params.add(fullname);
            params.add(email);
            params.add(ip);
            params.add(phonenumber);
            params.add(address);
            params.add(checkip);
            params.add(userId);
            int result = DataPeresitents.updateData(sqlQuery, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("editUser()==>" + sql, ex);
        }
        return false;
    }

    public static boolean editUserImage(String userId, String username, String passWord, String fullname,
            String email, String ip,
            String phonenumber, String address, String checkip, String imageUrl) {

        if (imageUrl.trim() != "") {
            String sql = "update qtht_users set userpass = ?, fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, image_url = ? "
                    + "where userid = ?";
            String sql_NoPass = "update qtht_users set fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ?, image_url = ? where userid = ?";
            String sqlQuery = sql_NoPass;
            try {

                ArrayList<String> params = new ArrayList<String>();
                if (passWord != null && passWord.equals("") == false) {
                    params.add(passWord);
                    sqlQuery = sql;
                }
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(imageUrl);
                params.add(userId);

                int result = DataPeresitents.updateData(sqlQuery, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("editUser()==>" + sql, ex);
            }
        } else {
            String sql = "update qtht_users set userpass = ?, fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ? "
                    + "where userid = ?";
            String sql_NoPass = "update qtht_users set fullname = ?, email = ?, ip = ?, phonenumber = ?, address = ?, checkip = ? where userid = ?";
            String sqlQuery = sql_NoPass;
            try {

                ArrayList<String> params = new ArrayList<String>();
                if (passWord != null && passWord.equals("") == false) {
                    params.add(passWord);
                    sqlQuery = sql;
                }
                params.add(fullname);
                params.add(email);
                params.add(ip);
                params.add(phonenumber);
                params.add(address);
                params.add(checkip);
                params.add(userId);

                int result = DataPeresitents.updateData(sqlQuery, params);
                return result > 0;
            } catch (SQLException ex) {
                logger.error("editUser()==>" + sql, ex);
            }
        }

        return false;
    }

    static boolean deleteUser(String userId) {
        String sql = "delete from qtht_users where userid = ?";
        String sqlRoleUser = "delete from qtht_usersroles where userid = ?";

        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(userId);
            DataPeresitents.updateData(sqlRoleUser, params);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("deleteUser()==>" + sql, ex);
        }
        return false;
    }

    static List getTreePages() {
        String sql = "SELECT a.pageid, a.parentid, a.pagename, "
                + "a.description, a.pagelink, a.pagetype "
                + "FROM qtht_pages a ";

        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List getListPageByUserName(String userName) {
        String sql = "SELECT a.pageid, a.parentid, a.pagename,"
                + "a.description, a.pagelink, a.pagetype"
                + " FROM qtht_pages a where a.pageid in ( "
                + "select pageid from qtht_rolespages where roleid in ("
                + "select roleid from qtht_usersroles where userid in (SELECT userid from qtht_users where username = ?)))";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userName);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    static boolean editAddAmountUser(String userId, String tenDangNhap, int amount, String userName) {
        DbConnection conn = null;
        java.sql.CallableStatement cs = null;
        int result = 0;
        try {
            conn = MySQLAccess.getInstance().getConn();
            cs = conn.prepareCall("{?=call FUNC_ADD_AMOUNT(?,?,?)}");
            // Set the value for the IN OUT parameter
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, tenDangNhap);
            cs.setInt(3, amount);
            cs.setString(4, userName);

            // Execute the stored procedure
            cs.executeUpdate();
            // Call a procedure with one OUT parameter
            result = cs.getInt(1);
        } catch (Exception se) {
            logger.error("editAddAmountUser()==>", se);
        } finally {
            MySQLAccess.getInstance().closeCallableStatement(cs);
            MySQLAccess.getInstance().closeConn(conn);
        }
        return result == 1 ? true : false;
    }

    public static List listUsers() {
        String sql = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip,"
                + "a.phonenumber, a.address, a.checkip FROM qtht_users a order by a.username";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List listUsers(String userName) {
        String sql = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip,"
                + "a.phonenumber, a.address, a.checkip,a.pos_id, b.pos_name,b.pos_type "
                + "FROM qtht_users a left join qtht_positions b on a.pos_id = b.pos_id "
                + "order by a.username";
        String sql_NoUserName = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip,"
                + "a.phonenumber, a.address, a.checkip,a.pos_id, b.pos_name,b.pos_type "
                + "FROM qtht_users a left join qtht_positions b on a.pos_id = b.pos_id "
                + "where UPPER(a.username) like ? order by a.username";

        ArrayList<String> params = new ArrayList<String>();
        if (userName != null && !userName.equals("")) {
            sql = sql_NoUserName;
            params.add("%" + userName.toUpperCase() + "%");
        }
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List listUserByParentId(String userId) {
        String sql = "SELECT a.userid, a.username, a.userpass, a.fullname, a.email, a.ip,"
                + "a.phonenumber, a.address, a.checkip,a.pos_id, b.pos_name "
                + "FROM qtht_users a left join qtht_positions b on a.pos_id = b.pos_id "
                + "where a.userid in (select userid from qtht_permission where manager_id = ?)"
                + "order by a.username";

        ArrayList<String> params = new ArrayList<String>();
        params.add(userId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static List listUserBySubId(String userId) {
        String sql = "SELECT a.userid, a.username, a.password, a.subid, a.amount, a.tarrif,"
                + "a.issub, a.msisdn, a.fullname, b.username FROM tbl_users a,tbl_users b where a.subid = b.userid (+) and a.subid = ? order by a.username";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static boolean editUser(String userId, String tenDangNhap, String passWord, String fullName, String phoneNumber) {
        String sql = "update tbl_users set password = ?,msisdn = ?, fullname = ? "
                + "where userid = ?";
        String sql_NoPass = "update tbl_users set msisdn = ?,fullname = ? where userid = ?";
        String sqlQuery = sql_NoPass;
        try {

            ArrayList<String> params = new ArrayList<String>();
            if (passWord != null && passWord.equals("") == false) {
                params.add(passWord);
                sqlQuery = sql;
            }
            params.add(phoneNumber);
            params.add(fullName);
            params.add(userId);
            int result = DataPeresitents.updateData(sqlQuery, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("editUser()==>" + sql, ex);
        }
        return false;
    }

    static boolean isHaveSub(String userId) {
        String sql = "SELECT * from tbl_users where subid = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userId);
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl != null && resutl.size() > 0) {
            return true;
        }
        return false;
    }

    public static List listAction() {
        String sql = "select distinct actionname from qtht_userhistory order by actionname";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    static List giamSatTruyNhapNguoiSuDung(String tuNgay, String denNgay, String trungTam, String congViec, String tenDangNhap, String tBatDau, String tKetThuc) {
        String sql = "select actionname,actionstatus,DATE_FORMAT(actiontime,'%d/%m/%Y %H:%i:%s'),username,hisid,actiondetail,ipaddress\n"
                + "                 from qtht_userhistory where actiontime >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and"
                + " actiontime < STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        ArrayList<String> params = new ArrayList<String>();
        params.add(tuNgay + " 00:00:00");
        params.add(denNgay + " 23:59:59");
        if (!"All".equals(congViec)) {
            sql += " and actionname like ?";
            params.add("%" + congViec + "%");
        }
        if (tenDangNhap != null && tenDangNhap != "") {
            sql += " and username like ?";
            params.add("%" + tenDangNhap + "%");
        }
        //params.add(trungTam);
        sql += " limit " + tBatDau + "," + tKetThuc;
//        params.add(tBatDau);
//        params.add(tKetThuc);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    static int totalGiamSatTruyNhapNguoiSuDung(String tuNgay, String denNgay, String trungTam, String congViec, String tenDangNhap) {
        String sql = "select count(hisid) from qtht_userhistory where actiontime >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and"
                + " actiontime < STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        ArrayList<String> params = new ArrayList<String>();
        params.add(tuNgay + " 00:00:00");
        params.add(denNgay + " 23:59:59");
        if (!"All".equals(congViec)) {
            sql += " and actionname like ?";
            params.add("%" + congViec + "%");
        }
        if (tenDangNhap != null && tenDangNhap != "") {
            sql += " and username like ?";
            params.add("%" + tenDangNhap + "%");
        }
        //params.add(trungTam);
        //sql += " limit " + tBatDau + "," + tKetThuc;
//        params.add(tBatDau);
//        params.add(tKetThuc);
        List resutl = DataPeresitents.queryList(sql, params);
        if (resutl == null) {
            return 0;
        }
        Object os = resutl.get(0);
        Object[] o = (Object[]) os;
        int total = OmapUtils.parserInteger(o[0]);
        return total;
    }

    static String xuatBaocao(String userId, int key, String userName, String startTime, String endTime, String fileUpload, String fileDownload) {

        String urlDL = "";
        DbConnection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date dateTime = new Date();
            String currentTime = dateFormat.format(dateTime);
            String pathFile = fileUpload + userName + "_" + currentTime + ".xls";

            FileOutputStream fileOut = new FileOutputStream(pathFile);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            String sql = "";
            ArrayList<String> params = new ArrayList<String>();

            if (key == 0) {
                sql = "select sub.wcontent, sub.wprocess, sub.wcreate_time, sub.wupdate_time, sub.wdeadline, qu.FULLNAME from ( "
                        + "select wcontent, wprocess, wcreate_time, wupdate_time, wdeadline, quserid from "
                        + "(select qu.USERID as wuserId, tw.work_content as wcontent, tw.work_process as wprocess, "
                        + "if(DATE_FORMAT(tw.create_time,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(now(),'%d/%m/%Y'),DATE_FORMAT(tw.create_time,'%d/%m/%Y')) as wcreate_time, "
                        + "if(DATE_FORMAT(tw.update_time,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(tw.create_time,'%d/%m/%Y'),DATE_FORMAT(tw.update_time,'%d/%m/%Y')) as wupdate_time, "
                        + "if(DATE_FORMAT(tw.deadline,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(tw.create_time,'%d/%m/%Y'),DATE_FORMAT(tw.deadline,'%d/%m/%Y')) as wdeadline, "
                        + "tw.USERID as quserid "
                        + "from qtht_users qu, qtht_users_works qw, tbl_works tw "
                        + "where qu.USERID = qw.USERID and qw.work_id = tw.work_id "
                        + "and qu.USERID = ? "
                        + "and tw.create_time >= STR_TO_DATE(?, '%d/%m/%Y') "
                        + "and tw.deadline <= STR_TO_DATE(?, '%d/%m/%Y') "
                        + ") as subQuery "
                        + ") as sub, qtht_users qu "
                        + "where sub.quserid = qu.USERID ";

                params.add(userId);
                params.add(startTime);
                params.add(endTime);

            } else {
                sql = "select wcontent, wprocess, wcreate_time, wupdate_time, wdeadline, wfullname from "
                        + "( select qu.USERID as wuserId, tw.work_content as wcontent, tw.work_process as wprocess, "
                        + "if(DATE_FORMAT(tw.create_time,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(now(),'%d/%m/%Y'),DATE_FORMAT(tw.create_time,'%d/%m/%Y')) as wcreate_time, "
                        + "if(DATE_FORMAT(tw.update_time,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(tw.create_time,'%d/%m/%Y'),DATE_FORMAT(tw.update_time,'%d/%m/%Y')) as wupdate_time, "
                        + "if(DATE_FORMAT(tw.deadline,'%d/%m/%Y')='00/00/0000',DATE_FORMAT(tw.create_time,'%d/%m/%Y'),DATE_FORMAT(tw.deadline,'%d/%m/%Y')) as wdeadline, "
                        + "qu.FULLNAME as wfullname "
                        + "from tbl_works tw, qtht_users qu, qtht_users_works qw "
                        + "where  qw.work_id = tw.work_id and qw.userid = qu.USERID "
                        + "and tw.userid = ? "
                        + "and tw.create_time >= STR_TO_DATE(?, '%d/%m/%Y') "
                        + "and tw.deadline <= STR_TO_DATE(?, '%d/%m/%Y') "
                        + ") "
                        + "AS subquery where wuserId != ? ";

                params.add(userId);
                params.add(startTime);
                params.add(endTime);
                params.add(userId);
            }

            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            int index = 0;
            if (params.size() > 0) {
                for (String param : params) {
                    index++;
                    pre.setString(index, param);
                }
            }
            rs = pre.executeQuery();

            Row row2;
            row2 = (Row) worksheet.createRow((short) 0);

            row2.createCell(0).setCellValue("ID");
            row2.createCell(1).setCellValue("Nội dung");
            row2.createCell(2).setCellValue("Tiến độ");
            row2.createCell(3).setCellValue("Ngày bắt đầu");
            row2.createCell(4).setCellValue("Ngày thay đổi");
            row2.createCell(5).setCellValue("Ngày kết thúc");
            if (key == 0) {
                row2.createCell(6).setCellValue("Người giao việc");
            } else {
                row2.createCell(6).setCellValue("Người nhận việc");
            }

            int count = 0;
            while (rs.next()) {
                count++;
                int a = rs.getRow();
                row2 = (Row) worksheet.createRow((short) a);
                int tiendo = rs.getInt(2);
                String td = "";
                if (tiendo == 0) {
                    td = "Chưa thực hiện";
                } else if (tiendo == 1) {
                    td = "Đã hoàn thành";
                } else if (tiendo == 2) {
                    td = "Đang thực hiện";
                }
                row2.createCell(0).setCellValue(count);
                row2.createCell(1).setCellValue(rs.getString(1));
                row2.createCell(2).setCellValue(td);
                row2.createCell(3).setCellValue(rs.getString(3));
                row2.createCell(4).setCellValue(rs.getString(4));
                row2.createCell(5).setCellValue(rs.getString(5));
                row2.createCell(6).setCellValue(rs.getString(6));

            }

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            urlDL = fileDownload + userName + "_" + currentTime + ".xls";;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);
        }
        return urlDL;
    }
}
