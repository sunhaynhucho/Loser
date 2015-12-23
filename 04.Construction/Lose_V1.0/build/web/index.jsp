<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loser</title>
    </head>
    <body>
        <%
            String funcName = "index";
            String folderName = "";
            if (request.getParameter("f") != null) {
                funcName = (String) request.getParameter("f");
            }
            if (request.getParameter("c") != null) {
                folderName = (String) request.getParameter("c");
            }
            request.setAttribute("title", "Hệ thống Quản lý công việc");

            if (folderName.equals("")) {
                if (funcName.equals("")) {
                    request.setAttribute("body", "/views/mapview/" + "index.jsp");
                } else {
                    request.setAttribute("body", "/views/mapview/" + funcName + ".jsp");
                }
            } else {
                request.setAttribute("body", "/views/" + folderName + "/" + funcName + ".jsp");
            }
        %>

        <jsp:include page="templates/themes.jsp" />
    </body>
</html>
