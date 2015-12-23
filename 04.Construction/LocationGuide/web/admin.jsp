<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.quantrihethong.UserRoleLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%

            String userName = "khách";
            OmapUser omapUser = OmapSessionUtils.getOmapUserUserName(request);
            if (omapUser != null) {
                userName = omapUser.getUserName();
            }
            if (userName.equals("khách")) {
                OmapUser omapUserOld = OmapSessionUtils.getOmapUserOldUserName(request);
                if (omapUserOld != null) {
                    response.sendRedirect(request.getContextPath() + "/login/login?errorcode=1");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login/login");
                }
                return;
            }


        %>
        <%
            String funcName = "home";
            String folderName = "";
            if (request.getParameter("f") != null) {
                funcName = (String) request.getParameter("f");
            }
            if (request.getParameter("c") != null) {
                folderName = (String) request.getParameter("c");
            }
            request.setAttribute("title", "Hệ thống Quản lý công việc");

            boolean isAccess = UserRoleLogic.checkUserAccessPage(omapUser.getUserId() + "", userName, folderName, funcName);
            if (isAccess) {
                if (folderName.equals("")) {
                    request.setAttribute("body", "/modules/" + funcName + ".jsp");
                } else {
                    request.setAttribute("body", "/modules/" + folderName + "/" + funcName + ".jsp");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login/error");
                return;
            }
        %>

        <jsp:include page="templates/admin-template.jsp" />

    </body>
</html>