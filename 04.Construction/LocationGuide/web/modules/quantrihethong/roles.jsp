<%-- 
    Document   : roles
    Created on : Apr 26, 2013, 3:01:54 PM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="../js/jquantrihethong/jroles.js"></script>
<%
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLE, "0", userName, "Lấy danh sách vai trò", OmapUtils.getClientIpAddr(request));
%>
<table width="100%">
    <tr>
        <td><table id="dgRoles"
                   data-options="rownumbers:true,pageSize:10,autoRowHeight:false,pagination:true,singleSelect:true,  
                   toolbar:toolbar,url: '<%=request.getContextPath()%>/QuanTriHeThongServices'"
                   class="easyui-datagrid" title="Danh sách role">  
                <thead>  
                    <tr>
                        <th data-options="field:'roleid',width:55">Định danh</th>  
                        <th data-options="field:'rolename',width:100">Tên vai trò</th>  
                        <!--                            <th data-options="field:'roletype',align:'left'">Kiểu vai trò</th>-->
                        <th data-options="field:'descrition',align:'left'">Miêu tả</th>  
                    </tr>  
                </thead>
            </table></td>

    </tr>
</table>



<div id="dialog" title="Basic dialog">
</div>
<div id="editdialog" title="Chinh sửa trang"></div>            


