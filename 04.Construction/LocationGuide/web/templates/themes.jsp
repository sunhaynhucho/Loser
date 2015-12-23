<%-- 
    Document   : themes
    Created on : Nov 2, 2015, 3:31:15 PM
    Author     : KiemPQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
//request.setAttribute("css", "../css/main.css");
request.setAttribute("header", "view-header.jsp");
request.setAttribute("menu", "view-menu.jsp");
request.setAttribute("footer", "view-footer.jsp");

%>

<jsp:include page="main-themes.jsp" />