/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.quantrihethong;

import com.elcom.omap.common.Constant;
import com.elcom.omap.database.DataPeresitents;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KiemPQ-PC
 */
public class UserRoleLogic {
    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
    public static List getListRoleByUserId(String userId) {
        //SELECT a.description, a.rolename, a.roleid  FROM role a;
        String sql = "SELECT a.roleid, a.rolename, a.description FROM qtht_roles a where a.roleid in (SELECT b.roleid FROM qtht_usersroles b where  b.userid = ?) order by a.rolename";
        ArrayList<String> params = new ArrayList<String>();
        params.add(userId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }
    
    public static boolean deleteUserRoles(String userId){
        String sql = "delete from qtht_usersroles where userid = ?";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(userId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("deleteUserRoles()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean insertUserRole(String userId,String roleId){
        String sql = "insert into qtht_usersroles (userid,roleid) values (?,?)";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(userId);
            params.add(roleId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("insertUserRole()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean insertUserPermision(String userId,String roleId){
        String sql = "insert into qtht_permission (userid,manager_id) values (?,?)";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(roleId);
            params.add(userId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("insertUserRole()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean deleteUserPermision(String userId){
        String sql = "delete from qtht_permission where manager_id = ?";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(userId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("deleteUserPermision()==>"+ sql,ex);
        }
        return false;
    }
    
    
    
    public static boolean checkUserAccessPage(String userId,String userName,String catKey,String pageKey){
//        List listRole = getListRoleByUserId(userId);
//        List<Object> listPage =new ArrayList<Object>();
//        for(Object oRole:listRole){
//            Object[] obj = (Object[])oRole;
//            String roleId = String.valueOf(obj[0]);
//            List listRolePage = RolePageLogic.getListPageByRoleId(roleId);
//            for(Object oPage:listRolePage){
//                listPage.add(oPage);
//            }
//        }
//        
//        String key = "";
//        if(catKey == null || catKey.equals("")){
//            key = pageKey;
//        }else{
//            key = catKey + "/" + pageKey;
//        }
//        for(Object oPage:listPage){
//            Object[] obj = (Object[])oPage;
//            String keyPage = String.valueOf(obj[3]);
//            logger.debug(keyPage + "|" + key);
//            if(key.equals(keyPage)){
//                return true;
//            }
//        }
//        return false;
        return true;
    }
}
