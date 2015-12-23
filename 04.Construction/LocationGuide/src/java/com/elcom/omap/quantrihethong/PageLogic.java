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
public class PageLogic {
    private static Logger logger = LoggerFactory.getLogger(Constant.LOGGER_NAME);
    public static List getPages(){
        String sql = "SELECT "
                + "a.pageid, a.pagename,a.pagelink,a.description,a.parentid,a.pagetype "
                + "FROM qtht_pages a order by a.pagename,a.parentid";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }
    
    public static boolean addPage(String name, String frendlyurl,String parentId, String des,String pageType){
        String sql = "INSERT INTO qtht_pages (parentid,pagename,description,pagelink,pagetype) "
                    + "VALUES (?,?,?,?,?)";
        try {
            
            ArrayList<String> params = new ArrayList<String>();
            params.add(parentId);
            params.add(name);
            params.add(des);
            params.add(frendlyurl);
            params.add(pageType);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addPage()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean editPage(String pageId,String name, String frendlyurl,String parentId, String des,String pageType){
        String sql = "update "
                + "qtht_pages set parentid = ?,pagename =?,description=?,"
                + "pagelink=?,pagetype = ? where pageid = ?";
        try {
            
            ArrayList<String> params = new ArrayList<String>();
            params.add(parentId);
            params.add(name);
            params.add(des);
            params.add(frendlyurl);
            params.add(pageType);
            params.add(pageId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addPage()==>"+ sql,ex);
        }
        return false;
    }
    
    public static boolean deletePage(String pageId){
        String sql = "delete from qtht_pages where pageid = ?";
        try {
            
            ArrayList<String> params = new ArrayList<String>();
            params.add(pageId);
            int result = DataPeresitents.updateData(sql, params);
            return result > 0;
        } catch (SQLException ex) {
            logger.error("addPage()==>"+ sql,ex);
        }
        return false;
    }
    
    public static List getPagesByFriendlyUrl(String friendlyUrl){
        String sql = "SELECT "
                + "a.pageid, a.name,a.keywords,a.friendlyurl,a.description,a.parentpageid,a.PAGE_TYPE "
                + "FROM tbl_pages a order by a.name,a.parentpageid";
        ArrayList<String> params = new ArrayList<String>();
        List resutl = DataPeresitents.queryList(sql, params);
        return resutl;
    }
   
}
