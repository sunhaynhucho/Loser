/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.common;

import com.elcom.omap.login.LoginDAO;
import com.elcom.omap.login.OmapUser;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Pham Quang Kiem
 */
public class OmapSessionUtils {

    /*private static ConcurrentHashMap<String, OmapUser> sessionUser = new ConcurrentHashMap<String, OmapUser>();

    public static String getUserName(HttpServletRequest request) {
        OmapUser omapUser = sessionUser.get(request.getSession().getId());
        if (omapUser != null) {
            return omapUser.getUserName();
        }
//        String userName = (String) request.getSession().getAttribute(Constant.USER_NAME);
        return null;
    }

    public static OmapUser getOmapUserUserName(HttpServletRequest request) {
        OmapUser omapUser = sessionUser.get(request.getSession().getId());
        if (omapUser != null) {
            return omapUser;
        }
//        OmapUser userName = (OmapUser) request.getSession().getAttribute(Constant.USER_OBJECT);
        return null;
    }

    public static void setOmapUserUserName(HttpServletRequest request, OmapUser omapUser) {
//        request.getSession().setAttribute(Constant.USER_NAME, omapUser.getUserName());
//        request.getSession().setAttribute(Constant.USER_OBJECT, omapUser);
        String keyOmap = "";
        if (sessionUser.containsValue(omapUser)) {
            for (String key : sessionUser.keySet()) {
                OmapUser item = sessionUser.get(key);
                if (item != null && item.getUserName().equals(omapUser.getUserName())) {
                    keyOmap = key;
                    break;
                }
            }
            if (!"".equals(keyOmap)) {
                sessionUser.remove(keyOmap);
            }
        }
        sessionUser.put(request.getSession().getId(), omapUser);
    }
    
    public static void setAmountUser(OmapUser omapUser, long amount) {
//        request.getSession().setAttribute(Constant.USER_NAME, omapUser.getUserName());
//        request.getSession().setAttribute(Constant.USER_OBJECT, omapUser);
        String keyOmap = "";
        if (sessionUser.containsValue(omapUser)) {
            for (String key : sessionUser.keySet()) {
                OmapUser item = sessionUser.get(key);
                if (item != null && item.getUserName().equals(omapUser.getUserName())) {
                    keyOmap = key;
                    break;
                }
            }
            if (!"".equals(keyOmap)) {
                sessionUser.replace(keyOmap, omapUser);
            }
        }
    }
    
    public static void clearUser(HttpServletRequest request){
        String key = request.getSession().getId();
        sessionUser.remove(key);
    }
    */
    
//    private static ConcurrentHashMap<String, OmapUser> sessionUser = new ConcurrentHashMap<String, OmapUser>();

    public static String getUserName(HttpServletRequest request) {
        OmapUser omapUser = (OmapUser)request.getSession().getAttribute(Constant.USER_OBJECT);
        if(omapUser != null){
            return omapUser.getUserName();
        }
        return  null;
    }
    
    public static String getUserId(HttpServletRequest request) {
        OmapUser omapUser = (OmapUser)request.getSession().getAttribute(Constant.USER_OBJECT);
        if(omapUser != null){
            return omapUser.getUserId();
        }
        return null;
    }

    public static OmapUser getOmapUserUserName(HttpServletRequest request) {
//        OmapUser omapUser = LoginDAO.getUserBySessionId(request.getSession().getId());
//        if (omapUser != null) {
//            return omapUser;
//        }
//        return null;
        OmapUser omapUser = (OmapUser)request.getSession().getAttribute(Constant.USER_OBJECT);
        return  omapUser;
    }

    public static void setOmapUserUserName(HttpServletRequest request, OmapUser omapUser) {
        //LoginDAO.setLoginSessionID(request.getSession().getId(),omapUser.getUserName());
        request.getSession().setAttribute(Constant.USER_NAME, omapUser.getUserName());
        request.getSession().setAttribute(Constant.USER_OBJECT, omapUser);
    }
    
    public static void setAmountUser(OmapUser omapUser, long amount) {
    }
    
    public static void clearUser(HttpServletRequest request){
        //String key = request.getSession().getId();
        //LoginDAO.setLoginSessionIDNULL(key);
        request.getSession().removeAttribute(Constant.USER_NAME);
        request.getSession().removeAttribute(Constant.USER_OBJECT);
    }
    
    public static OmapUser getOmapUserOldUserName(HttpServletRequest request){
        
//        request.getSession().setAttribute(Constant.USER_OBJECT, request);
//        OmapUser omapUser = LoginDAO.getUserByOldSessionId(request.getSession().getId());
//        if (omapUser != null) {
//            return omapUser;
//        }
        return null;
    }
    
    
}
