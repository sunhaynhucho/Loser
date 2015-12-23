<%-- 
    Document   : user_position
    Created on : Feb 9, 2015, 11:20:14 AM
    Author     : elcom154
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="../js/jquantrihethong/juserpos.js"></script>
<table>
    <tr>
        <td style="vertical-align: top;">
            <table id="dgUser" class="easyui-datagrid" title="Danh sách người dùng" 
                   data-options="rownumbers:true,loadFilter: pagerFilter,singleSelect:true,pageSize:10,autoRowHeight:false,pagination:true,  
                   toolbar:'#tb',url: '<%=request.getContextPath()%>/QuanTriHeThongServices',onSelect: function(rowIndex, rowData){
                   reloadGridPage(rowData.userid,rowData.postype);
                   },idField:'userid'">  
                <thead>  
                    <tr>  
                        <th data-options="field:'username',align:'left'">Tên đăng nhập</th>
                        <th data-options="field:'fullname',align:'left'">Họ và tên</th>
                        <th data-options="field:'posname',align:'left'">Chức vụ</th>
                    </tr>  
                </thead>
            </table>

        <td style="width: 3px;"></td>
        <td width="47%" style="vertical-align: top;">
            <table id="dgUser2" class="easyui-datagrid" style="height: 430px;" title="Danh sách nhân viên" 
                   data-options="rownumbers:true,singleSelect:false,autoRowHeight:false, 
                   url: '<%=request.getContextPath()%>/QuanTriHeThongServices',
                   selectOnCheck:true,checkOnSelect:true,idField:'userid'">  
                <thead>  
                    <tr>  
                        <th data-options="field:'ck',checkbox:true,align:'left'"></th>
                        <th data-options="field:'username',align:'left'">Tên đăng nhập</th>
                        <th data-options="field:'fullname',align:'left'">Họ và tên</th>
                        <th data-options="field:'posname',align:'left'">Chức vụ</th>
                    </tr>  
                </thead>
            </table>
        </td>
    </tr></table>

<div id="tb" style="padding:5px;height:auto">  
    <div style="margin-bottom:5px">  
        <a href="#" onclick="submitManageUser()" class="easyui-linkbutton" iconCls="icon-save" plain="true">Ghi lại</a>  
    </div>
    <div>
        Tên đăng nhập: <input class="easyui-validatebox" maxlength="49" type="text" id="txtUserName" name="txtUserName" style="width:100px"></input>
        <a href="#" onclick="submitTimKiemUserByCenterId()" class="easyui-linkbutton" iconCls="icon-search">Tìm Kiếm</a>  
    </div>
</div>