<%-- 
    Document   : breadcrumb
    Created on : Jan 24, 2013, 9:15:25 AM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.util.CurrencyUtil"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/BreadCrumb.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jBreadCrumb.1.1.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function()
    {
        jQuery("#breadCrumb3").jBreadCrumb();
    })
</script>
<%
    String c = "";
    String f = "";
    String strCat = "";
    String strFunc = "";
    String strUrl = "";

    if (request.getParameter("c") != null) {
        c = request.getParameter("c");
    }
    if (request.getParameter("f") != null) {
        f = request.getParameter("f");
    }
    if (c.equals("khaithac")) {
        strCat = "Gửi SMS";


    } else if (c.equals("quanlycongviec")) {
        strCat = "Quản lý công việc";
        strUrl = request.getContextPath() + "/quanlycongviec/home";

    } else if (c.equals("quantrihethong")) {
        strCat = "Quản trị hệ thống";
        strUrl = request.getContextPath() + "/quantrihethong/home";

        if (f.equals("users")) {
            strFunc = "Danh sách người dùng";
        } else if (f.equals("user_roles")) {
            strFunc = "Phân quyền cho người dùng";
        } else if (f.equals("roles")) {
            strFunc = "Danh sách role";
        } else if (f.equals("role_pages")) {
            strFunc = "Phân chức năng cho nhóm người dùng";
        } else if (f.equals("pages")) {
            strFunc = "Danh sách các chức năng";
        } else if (f.equals("user_history")) {
            strFunc = "Lịch sử truy nhập web";
        }

    } else if (c.equals("login")) {
        strCat = "Tài khoản";
        strUrl = request.getContextPath() + "/login/login";
        if (f.equals("logout")) {
            strFunc = "Đăng xuất";
        } else if (f.equals("edituser")) {
            strFunc = "Thay đổi thông tin tài khoản";
        } else if (f.equals("khuyenmaichuky")) {
            strFunc = "Khuyến mãi theo hình thức cộng tiền";
        }
    } else {
        strCat = "Trang chủ";
        f = "home";
    }
    String userName = "khách";
    String urlLogin = request.getContextPath() + "/login/login";
    userName = OmapSessionUtils.getUserName(request);
    OmapUser omapUser = OmapSessionUtils.getOmapUserUserName(request);

    if (userName != null && userName != "khách") {
        urlLogin = "#";
        userName += " - " + omapUser.getFullName();
    } else {
        userName = "khách";
    }
    String urlWeb = request.getContextPath();
%>
<div id="mm2" style="width:200px;">  
    <% if (userName != "khách") {%>
    <a href="<%=urlWeb%>/login/edituser" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Thay đổi thông tin</a> 
    <a href="<%=urlWeb%>/login/logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">Thoát</a> 
    <% }%>
</div>  
<div id="breadCrumb3" class="breadCrumb module" style="border-radius:0px 0px 0px 4px;width: 725;float: left;">
    <ul>
        <li>
            <a href="<%=request.getContextPath()%>/view/home">Trang chủ</a>
        </li>
        <% if (!f.equals("home")) {%>
        <li>
            <a href="<%=strUrl%>"><%=strCat%></a>
        </li>

        <li>
            <%=strFunc%>
        </li>
        <% } else {%>
        <li>
            <%=strCat%>
        </li>
        <% }%>
    </ul>


</div> <div style="text-align: left;padding-top: 0px;">
    <a href="<%=urlLogin%>" target="_self" class="easyui-splitbutton right" data-options="menu:'#mm2'">Xin chào <%=userName%></a> 
</div>