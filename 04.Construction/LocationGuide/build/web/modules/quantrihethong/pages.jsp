<%-- 
    Document   : pages
    Created on : Apr 26, 2013, 3:02:01 PM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<%
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_PAGE, "0", userName, "Lấy danh sách chức năng", OmapUtils.getClientIpAddr(request));
%>
<script type="text/javascript" src="../js/jquantrihethong/jpages.js"></script>
<div class="easyui-panel" title="Danh sách các chức năng" data-options="toolbar:toolbar"> 
    <ul id="ttPage" data-options="animate: true,  
        onContextMenu: function(e,node){  
        e.preventDefault();  
        $(this).tree('select',node.target);  
        $('#mm').menu('show',{  
        left: e.pageX,  
        top: e.pageY  
        });  
        }"></ul>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">  
    <div onclick="showAddPage()" data-options="iconCls:'icon-add'">Thêm</div>  
    <div onclick="showEditPage()" data-options="iconCls:'icon-edit'">Sửa</div>  
    <div onclick="deletePage()" data-options="iconCls:'icon-remove'">Xóa</div>
    <div class="menu-sep"></div>  
    <div onclick="expand()">Mở ra</div>  
    <div onclick="collapse()">Thu lại</div>  
</div> 
<div id="dialog" title="Thêm trang"></div>
<div id="editlog" title="Chinh sửa trang"></div>



