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
public class RolePageLogic {
    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
    public static List getListPageByRoleId(String roleId) {
        String sql = "select a.pageid, a.parentid, a.pagename, a.description, a.pagelink,a.pagetype "
                + "FROM qtht_pages a where a.pageid in (SELECT b.pageid FROM qtht_rolespages b where  b.roleid = ?) order by a.pagename";
        ArrayList<String> params = new ArrayList<String>();
        params.add(roleId);
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }
    
    public static boolean insertRolePage(String pageId,String roleId){
        String sql = "insert into qtht_rolespages (pageid,roleid) values (?,?)";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(pageId);
            params.add(roleId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            //logger.error("insertRolePage()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean deleteRolePage(String roleId){
        String sql = "delete from qtht_rolespages where roleid = ?";
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(roleId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("deleteRolePage()==>"+ sql,ex);
        }
        return false;
    }
}
