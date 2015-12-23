<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<!DOCTYPE html>
<%
    String data = request.getParameter("data");
%>
<span id="txtDataFileResult"><%=data%></span>