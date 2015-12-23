<%-- 
    Document   : home
    Created on : Feb 7, 2015, 11:26:39 AM
    Author     : elcom154
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/jquanlytailieu/jquanlytailieu.js"></script>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<table class="easyui-datagrid" title="Danh sách tài liệu" style="height:350px" id="griDoc"
       data-options="singleSelect:true,url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices',toolbar:'#tb'">
    <thead>
        <tr>
            <th data-options="field:'did',align:'center'">Mã</th>
            <th data-options="field:'dname',width:'180px',align:'left'">Tên tài liệu</th>
            <th data-options="field:'dcatname',align:'left'">Tên danh mục</th>
            <th data-options="field:'ddownload',align:'center'">Tải về</th>
            <th data-options="field:'dview',align:'center'">Xem</th>
            <th data-options="field:'dedit',align:'center'">Sửa</th>
            <th data-options="field:'dremove',align:'center'">Xóa</th>
        </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <a href="#" class="easyui-linkbutton" onclick="showAddDoc()" iconCls="icon-add">Thêm</a>
    <input class="easyui-textbox" id='txtName' data-options="prompt:'Nhập nội dung cần tìm...'" />
    <input class="easyui-combotree" id="ccbDanhMuc"
           data-options="prompt:'Chọn danh mục...',url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
           method:'get',loadFilter: function(rows){
           return convert(rows);
           }" 
           style="width:200px;">

    <a href="#" class="easyui-linkbutton" onclick="loadDuLieuDocument()" iconCls="icon-search">Tìm</a>
    <a href="#" class="easyui-linkbutton" onclick="clearSearch()" iconCls="icon-clear">Xóa</a>
</div>

<div id ="divDialog"></div>
<div id ="dgABCDE"></div>
<input id="txtUrlFileShow" type="hidden"/>