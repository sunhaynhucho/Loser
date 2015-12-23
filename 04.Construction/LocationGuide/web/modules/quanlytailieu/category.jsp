<%-- 
    Document   : category
    Created on : Feb 7, 2015, 11:40:45 AM
    Author     : SonDV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/jquanlytailieu/jquanlycategory.js"></script>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<table class="easyui-datagrid" title="Danh sách tài liệu" style="height:350px" id="griDoc"
       data-options="singleSelect:true,url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices',toolbar:'#tb'">
    <thead>
        <tr>
            <th data-options="field:'did',align:'center'">Mã</th>
            <th data-options="field:'dname',width:'180px',align:'left'">Tên danh mục</th>
            <th data-options="field:'dparent',align:'left'">Tên danh mục cha</th>
            <th data-options="field:'ddesc',align:'left'">Miêu tả</th>
            <th data-options="field:'dedit',align:'center'">Sửa</th>
            <th data-options="field:'dremove',align:'center'">Xóa</th>
        </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <a href="#" class="easyui-linkbutton" onclick="showAddCategory()" iconCls="icon-add">Thêm</a>
    <input class="easyui-textbox" id='txtName' data-options="prompt:'Tên danh mục...'" />
    <a href="#" class="easyui-linkbutton" onclick="loadDuLieuCategory()" iconCls="icon-search">Tìm</a>
    <a href="#" class="easyui-linkbutton" onclick="clearSearchCat()" iconCls="icon-clear">Xóa</a>
</div>
<div id='divDialog'></div>
