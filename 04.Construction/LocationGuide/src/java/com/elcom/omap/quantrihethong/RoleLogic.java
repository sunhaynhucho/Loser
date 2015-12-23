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
public class RoleLogic {
    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
    public static boolean addRole(String roleName, String description){
        String sql = "INSERT INTO qtht_roles (rolename,description) VALUES (?,?)";
        try {
            
            ArrayList<String> params = new ArrayList<String>();
            params.add(roleName);
            params.add(description);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addRole()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean editRole(String roleId,String roleName, String description){
        String sql = "update qtht_roles set rolename = ?,description = ? where roleid = ?";
        try {
            
            ArrayList<String> params = new ArrayList<String>();
            params.add(roleName);
            params.add(description);
            params.add(roleId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("editRole()==>"+ sql,ex);
        }
        return false;
    }
    
    
    public static boolean deleteRole(String roleId){
        String sql = "delete from qtht_roles where roleid = ?";
        String sql_user = "delete from qtht_rolespages where roleid = ?";
        String sql_page = "delete from qtht_rolespages where roleid = ?";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(roleId);
            DataPeresitents.updateData(sql_user, params);
            DataPeresitents.updateData(sql_page, params);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("deleteRole()==>"+ sql,ex);
        }
        return false;
    }
    
    public static List getRoles(){
        String sql = "SELECT a.roleid, a.rolename, a.description FROM qtht_roles a order by a.rolename";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }
    
    
    public static List getRoles(String roleId){
        String sql = "SELECT a.roleid, a.rolename, a.description FROM qtht_roles a where a.roleid = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(roleId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }

    public static boolean isExistRoleByName(String name) {
        String sql = "SELECT a.roleid, a.rolename, a.description FROM qtht_roles a where a.rolename = ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(name);
        List resutl = DataPeresitents.queryList(sql, params);
        if(resutl == null || resutl.size() <= 0){
            return false;
        }else if(resutl.size() >= 1){
            return true;
        }
        return false;
    }

    public static boolean isExistRoleByName(String name, String pageId) {
        String sql = "SELECT a.roleid, a.rolename, a.description FROM qtht_roles a where a.rolename = ? and a.roleid <> ?";
        ArrayList<String> params = new ArrayList<String>();
        params.add(name);
        params.add(pageId);
        List resutl = DataPeresitents.queryList(sql, params);
        if(resutl == null || resutl.size() <= 0){
            return false;
        }else if(resutl.size() >= 1){
            return true;
        }
        return false;
    }
    
}
