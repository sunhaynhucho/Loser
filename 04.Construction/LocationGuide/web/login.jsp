<%-- 
    Document   : login
    Created on : Jan 20, 2013, 4:56:12 PM
    Author     : KiemPQ-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%
            String funcName = "login";
            String folderName = "login";
            if (request.getParameter("f") != null) {
                funcName = (String) request.getParameter("f");
            }
            if (request.getParameter("c") != null) {
                folderName = (String) request.getParameter("c");
            }
            // Check phan quyen tai day
            //End check phan quyen
            request.setAttribute("title", "Hệ thống Quản lý công việc");
            if (folderName.equals("")) {
                request.setAttribute("body", "/modules/" + funcName + ".jsp");
            } else {
                request.setAttribute("body", "/modules/" + folderName + "/" + funcName + ".jsp");
            }
            //request.setAttribute("title", "Welcome to MyCom");
            //request.setAttribute("body", "/modules/login.jsp");
        %>

        <jsp:include page="templates/admin-template.jsp" />
    </body>
</html>
