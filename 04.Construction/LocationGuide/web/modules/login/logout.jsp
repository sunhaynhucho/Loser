<%-- 
    Document   : logout
    Created on : Jan 24, 2013, 10:51:27 AM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userName = OmapSessionUtils.getUserName(request); 
//    request.getSession().removeAttribute(Constant.USER_NAME);
//    request.getSession().removeAttribute(Constant.USER_OBJECT);
    OmapSessionUtils.clearUser(request);
    if (userName != null) {
//        OmapHistoryLogic.updateStatusUserLogout(userName);
        OmapHistoryLogic.insertUserHistory(Constant.ACTION_LOGOUT, "0", userName, "Đăng xuất.", OmapUtils.getClientIpAddr(request));
    }
    //response.sendRedirect(request.getContextPath() + "/view/");
%>

<script type="text/javascript" >
    var url = "<%=request.getContextPath()%>/login/login";    
    $(location).attr('href',url);
</script>

