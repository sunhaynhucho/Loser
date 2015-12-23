/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.login;

import com.elcom.omap.common.Md5;
import com.elcom.omap.database.MySQLAccess;
import com.kiemanh.vn.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KiemPQ-PC
 */
public class LoginLogic {

    public LoginLogic() {
    }
    

    private String username = null;
    private String password = null;
    private DbConnection con = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;
//    private OmapUser omapUser = null;
/*
 * private long userId;
    private int defaultuser;
    private String phonenumber;
    private String emailaddress;
    private String firstname;
    private String middlename;
    private String lastname;
    private String address;
    private int status;
    private String screenName;
    private long parentId;
 */
    public boolean checkAccount(String user, String pass) {
        String sql = "SELECT a.userid, a.username, a.password_user, a.fullname, "
                + "a.centerid,a.email, a.ip, a.cellphone, a.last_login, "
                + "a.address, a.comments, a.server_type "
                + "from user_info a where a.username = ?";// and type =4";
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            pre.setString(1, user);
            rs = pre.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString(13) + "|" + rs.getString(12) + "|" + rs.getString(10));
//                if (rs.getString(13).equals("1")) {
                    if (rs.getString(2).equals(user) && rs.getString(3).equals(Md5.getMd5Digest(pass))) {
//                        omapUser = new OmapUser();
//                        setUsername(rs.getString(10));
//                        omapUser.setUserId(rs.getLong(1));
//                        omapUser.setDefaultuser(rs.getInt(2));
//                        omapUser.setPhonenumber(rs.getString(3));
//                        omapUser.setEmailaddress(rs.getString(4));
//                        omapUser.setFirstname(rs.getString(5));
//                        omapUser.setMiddlename(rs.getString(6));
//                        omapUser.setLastname(rs.getString(7));
//                        omapUser.setAddress(rs.getString(8));
//                        omapUser.setStatus(rs.getInt(9));
//                        omapUser.setScreenName(rs.getString(10));
//                        omapUser.setParentId(rs.getLong(11));
                        return true;
                    } else {
                        return false;
                    }
//                } else {
//                    if (rs.getString(10).equals(user) && rs.getString(12).equals(pass)) {
////                        omapUser = new OmapUser();
//                        setUsername(rs.getString(10));
////                        omapUser.setUserId(rs.getLong(1));
////                        omapUser.setDefaultuser(rs.getInt(2));
////                        omapUser.setPhonenumber(rs.getString(3));
////                        omapUser.setEmailaddress(rs.getString(4));
////                        omapUser.setFirstname(rs.getString(5));
////                        omapUser.setMiddlename(rs.getString(6));
////                        omapUser.setLastname(rs.getString(7));
////                        omapUser.setAddress(rs.getString(8));
////                        omapUser.setStatus(rs.getInt(9));
////                        omapUser.setScreenName(rs.getString(10));
////                        omapUser.setParentId(rs.getLong(11));
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);

        }
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;

    }

//    public OmapUser getOmapUser() {
//        return omapUser;
//    }

    boolean isExitAcount(String admin) {
        String sql = "SELECT * FROM user_info where username = ?";// and type =4";
        try {
            con = MySQLAccess.getInstance().getConn();
            pre = con.prepareStatement(sql);
            pre.setString(1, admin);
            rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySQLAccess.getInstance().closeResultSet(rs);
            MySQLAccess.getInstance().closePreparedStatement(pre);
            MySQLAccess.getInstance().closeConn(con);

        }
        return false;
    }

    
}
