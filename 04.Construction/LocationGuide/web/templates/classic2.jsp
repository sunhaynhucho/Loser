<%-- classic2 layout --%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${requestScope.css}" type="text/css" rel="stylesheet" />
        <title> ${requestScope.title} </title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default/easyui.css">  
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">  
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fastpay.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cssmenu.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>  
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui-lang-vn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/omapcommon.js"></script>
    </head>
    <body style="background: #fff no-repeat fixed;">
        <center>
            <table cellspacing="0" cellpadding="0" border="0" width="1000" >
                <tr>
                    <td ><jsp:include page="${requestScope.header}" /></td>
                </tr>
                <tr >
                    <td style="background: url(<%=request.getContextPath()%>/css/images/brancrup_bg.png);border-radius:0px 0px 4px 4px;"><jsp:include page="${requestScope.breadcrumb}" /></td>
                </tr>
                <tr>
                    <td style="height: 5px;"></td>
                </tr>
                <tr >
                    <td>
                        <div style="background: #fff;border-radius:10px 10px 10px 10px;">
                            <table cellspacing="0" cellpadding="0" border="0" width="1000">
                                <tr>
                                    <td style="width: 150px;" valign="top" height="300">
                                        <jsp:include page="${requestScope.menu}" /></td>
                                    <td><div style="height: 5px;"></div></td>
                                    <td style="width: 844px;" valign="top" align="left">
                                        <jsp:include page="${requestScope.body}" />
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td >
                        <div style="height: 5px;"></div>
                        <jsp:include page="${requestScope.footer}" />
                    </td>
                </tr>
            </table>
        </center>
    </body>
</html>
