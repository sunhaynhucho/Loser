<%-- 
    Document   : user
    Created on : Jan 20, 2013, 12:49:36 PM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var requestUrl = "<%=request.getContextPath()%>";
    var userName = "<%=OmapSessionUtils.getUserName(request)%>";
</script>
<script type="text/javascript" src="../js/jquantrihethong/juser.js"></script>
<script type="text/javascript" >
    
</script>
<%
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USER, "0", userName, "Lấy danh sách user", OmapUtils.getClientIpAddr(request));
%>
<table id="dgUser" class="easyui-datagrid" title="Danh sách người dùng" 
       data-options="rownumbers:true,loadFilter: pagerFilter,singleSelect:true,pageSize:10,autoRowHeight:false,pagination:true,  
       toolbar:'#tb',url: '<%=request.getContextPath()%>/QuanTriHeThongServices'">  
    <thead>  
        <tr>  
            <th data-options="field:'username',align:'left'">Tên đăng nhập</th>
            <th data-options="field:'fullname',align:'left'">Họ và tên</th>
            <th data-options="field:'posname',align:'left'">Chức vụ</th>
            <th data-options="field:'phonenumber',align:'left'">Số điện thoại</th>
            <th data-options="field:'email',align:'left'">Email</th>
            <th data-options="field:'ip',align:'right'">Địa chỉ ip đăng ký</th>  
            <th data-options="field:'address',align:'left'">Địa chỉ</th>  
            <th data-options="field:'checkip',align:'left'">Kiểm tra Ip đăng nhập</th>  
        </tr>  
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">  
    <div style="margin-bottom:5px">  
        <a href="#" onclick="showAddUser()" class="easyui-linkbutton" iconCls="icon-add" plain="true">Thêm</a>  
        <a href="#" onclick="showEditUser()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">Sửa</a>  
        <a href="#" onclick="deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Xóa</a>  
    </div>
    <div>
        Tên đăng nhập: <input class="easyui-validatebox" maxlength="49" type="text" id="txtUserName" name="txtUserName" style="width:100px"></input>
        <a href="#" onclick="submitTimKiemUserByCenterId()" class="easyui-linkbutton" iconCls="icon-search">Tìm Kiếm</a>  
    </div>
</div>
<div id="dgAddUser" title="Basic dialog"></div>
<div id="dgEditUser" title="Chinh sửa trang"></div>      
<div id="dgAmountUser" title="Chinh sửa trang"></div>  