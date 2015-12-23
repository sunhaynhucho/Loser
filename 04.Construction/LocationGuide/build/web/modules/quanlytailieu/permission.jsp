<%-- 
    Document   : permission
    Created on : Aug 15, 2015, 4:02:42 PM
    Author     : KiemPQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/jquanlytailieu/jpermission.js"></script>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<table>
    <tr>
        <td width="40%" style="vertical-align: top;">
            <table class="easyui-datagrid" title="Danh sách tài liệu" style="height:350px;" id="griDoc"
                   data-options="singleSelect:true,url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices'
                   ,toolbar:'#tb'
                   ,onSelect:function(index,row){
                        onClickSelecRow(index,row);
                   }">
                <thead>
                    <tr>
                        <th data-options="field:'did',align:'center'">Mã</th>
                        <th data-options="field:'dname',width:'180px',align:'left'">Tên tài liệu</th>
                        <th data-options="field:'dcatname',align:'left'">Tên danh mục</th>
                    </tr>
                </thead>
            </table>
        </td>
        <td>
            <table class="easyui-datagrid" title="Danh sách nhóm user" style="height:200px;" id="gridRole"
                   data-options="toolbar:'#tbRole'
                   ,url:'<%=request.getContextPath()%>/PermissionServices'
                   ,idField:'roleid'">
                <thead>
                    <tr>
                        <th data-options="field:'ck',checkbox:true"></th>
                        <th data-options="field:'rolename',width:'180px',align:'left'">Tên</th>
                        <th data-options="field:'descrition',width:'180px',align:'left'"></th>
                    </tr>
                </thead>
            </table>
            <table class="easyui-datagrid" title="Danh sách user" style="height:350px;" id="gridUser"
                   data-options="toolbar:'#tbUser'
                   ,url:'<%=request.getContextPath()%>/PermissionServices'
                   ,idField:'username'">
                <thead>
                    <tr>
                        <th data-options="field:'ck',checkbox:true"></th>
                        <th data-options="field:'username',width:'180px',align:'left'">Tên đăng nhập</th>
                        <th data-options="field:'fullname',align:'left'">Họ và tên</th>
                    </tr>
                </thead>
            </table>
        </td>
    </tr>
</table>


<div id="tb" style="padding:2px 5px;">
    <input class="easyui-textbox" id='txtName' data-options="prompt:'Nhập nội dung cần tìm...'" />
    <input class="easyui-combotree" id="ccbDanhMuc"
           data-options="prompt:'Chọn danh mục...',url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
           method:'get',loadFilter: function(rows){
           return convert(rows);
           }" 
           style="width:200px;">

    <a href="#" class="easyui-linkbutton" onclick="loadDuLieuDocument()" iconCls="icon-search">Tìm</a>
    
</div>
           
<div id="tbRole" style="padding:2px 5px;">
    <input class="easyui-textbox" id='txtNameGroup' data-options="prompt:'Nhập tên nhóm ...'" />
    <a href="#" class="easyui-linkbutton" onclick="loadDuLieuNhom()" iconCls="icon-search">Tìm</a>
    <a href="#" class="easyui-linkbutton" onclick="saveDocRole()" iconCls="icon-save">Ghi lại</a>
</div>
           
<div id="tbUser" style="padding:2px 5px;">
    <input class="easyui-textbox" id='txtUserName' data-options="prompt:'Nhập tên user ...'" />
    <a href="#" class="easyui-linkbutton" onclick="loadDuLieuUser()" iconCls="icon-search">Tìm</a>
    <a href="#" class="easyui-linkbutton" onclick="saveDocUser()" iconCls="icon-save">Ghi lại</a>
</div>