<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.quantrihethong.RoleLogic"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" >
    $(function() {
        $('#dgUser').datagrid('reload',{
            options:'GetListUser'
        });
        $('#dgUser').datagrid({
            onSelect: function(rowIndex, rowData){
                reloadGridPage(rowData.userid);
            }
        });
    });
    
    function submitTimKiemUserByCenterId(){
        var tenDangNhap = $( "#txtUserName" ).val();
        $('#dgUser').datagrid('reload',{
            options:'GetListUser',
            tendangnhap:tenDangNhap
        });
    }
    
    function reloadGridPage(pUserId){
        $('#dgRole').datagrid('unselectAll');
        $.getJSON("<%=request.getContextPath()%>/QuanTriHeThongServices",
        {"options":"GetRolesByUserId","userId": pUserId},
        checkPages);
    }
    
    function checkPages(data){
        $.each(data, function(i, page) {
            $('#dgRole').datagrid('selectRecord',page.roleId);
        });
    }
    
    function saveRolePages(){
        $.messager.progress({  
            title:'Xin đợi ',  
            msg:'Đang xử lý dữ liệu...'  
        }); 
        var row = $('#dgUser').datagrid('getSelected');
        var ids = [];
        var rows = $('#dgRole').datagrid('getSelections');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].roleId);
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/QuanTriHeThongServices",
            type: "POST",
            cache: false,
            data: {options:"SaveUserRoles", userId:row.userid,roleIds:ids.join(',')},
            dataType: "html",
            success: function(html){
                $.messager.progress('close');
                $.messager.alert('Thông báo',html);              
            }
        });
    }
    
</script>
<div id="message1"></div>
<%

    List listPage = null;
    listPage = RoleLogic.getRoles();
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USERROLE, "0", userName, "Xem các role của người dùng" , OmapUtils.getClientIpAddr(request));
//    listUser = UserLogic.getListUser();
%>
<div style="padding:5px;">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveRolePages()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Ghi lại</a>  
</div>
<table>
    <tr>
        <td><table id="dgUser" class="easyui-datagrid" data-options="singleSelect:true,toolbar:'#tb',url: '<%=request.getContextPath()%>/QuanTriHeThongServices'"
                   title="Danh sách người dùng" style="width: 385px;height:250px">  
                <thead>  
                    <tr>
                        <th data-options="field:'userid',width:55">Định danh</th>  
                        <th data-options="field:'username',width:100">Tên đăng nhập</th>  
                        <th data-options="field:'fullname',align:'left'">Họ và tên</th>  

                    </tr>  
                </thead>
               
            </table></td>
        <td>&nbsp;</td>
        <td width="65%">
            <table id="dgRole" class="easyui-datagrid" data-options="checkOnSelect:true,idField:'roleId'"
                   title="Danh sách vai trò" style="height:250px">  
                <thead>  
                    <tr>  
                        <th data-options="field:'ck',checkbox:true"></th>  
                        <th data-options="field:'roleId',width:55">Định danh</th>  
                        <th data-options="field:'roleName',width:200,align:'left'">Tên chức năng</th>  
                        <th data-options="field:'roleDesc',align:'left'">Ghi chú</th>
                    </tr>  
                </thead>

                <% if (listPage != null && listPage.size() > 0) {%>
                <% for (Object o : listPage) {%>
                <% Object[] obj = (Object[]) o;%>
                <tr id="datagrid-row-r1-2-5" datagrid-row-index="5" class="datagrid-row">
                    <td field="ck"></td>
                    <td field="roleId"><%=obj[0]%></td>
                    <td field="roleName"><%=obj[1]%></td>
                    <% String strPageDest = String.valueOf(obj[2]);
                        if (strPageDest.equals("null")) {
                            strPageDest = "";
                        }
                    %>
                    <td field="roleDesc"><%=strPageDest%></td>
                </tr>
                <%}%>
                <%}%>

            </table></td>
    </tr>
</table>


<div id="tb" style="padding:5px;height:auto">  
    <div>
        Tên đăng nhập: <input class="easyui-validatebox" maxlength="49" type="text" id="txtUserName" name="txtUserName" style="width:100px"></input>
        <a href="#" onclick="submitTimKiemUserByCenterId()" class="easyui-linkbutton" iconCls="icon-search">Tìm Kiếm</a>  
    </div>
</div>