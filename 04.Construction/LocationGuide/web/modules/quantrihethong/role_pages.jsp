<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.quantrihethong.RoleLogic"%>
<%@page import="com.elcom.omap.quantrihethong.PageLogic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script> 
<script type="text/javascript" src="../js/jquantrihethong/jrolefunction.js"></script>
<div id="message1"></div>
<%
    List listRole = null;
    List listPage = null;
    listPage = PageLogic.getPages();
    listRole = RoleLogic.getRoles();
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_ROLEPAGE, "0", userName, "Xem danh sách page của vai trò " , OmapUtils.getClientIpAddr(request));
%>
    <div style="padding:5px;">  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveRolePages()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Ghi lại</a>  
    </div>
    <table>
        <tr>
            <td style="vertical-align: top;"><table id="dgRole" class="easyui-datagrid" data-options="singleSelect:true"
                                                    title="Danh sách role" style="width: 385px;height:250px">  
                    <thead>  
                        <tr>
                            <th data-options="field:'roleId',width:55">Định danh</th>  
                            <th data-options="field:'roleName',width:100">Tên vai trò</th>  
                            <th data-options="field:'roleDesc',align:'left'">Ghi chú</th>  

                        </tr>  
                    </thead>
                    <% if (listRole != null && listRole.size() > 0) {%>
                    <% for (Object o : listRole) {%>
                    <% Object[] obj = (Object[]) o;%>
                    <tr id="datagrid-row-r1-2-5" datagrid-row-index="5" class="datagrid-row">
                        <td field="roleId"><%=obj[0]%></td>
                        <td field="roleName"><%=obj[1]%></td>
                        <% String strDest = String.valueOf(obj[2]);
                            if (strDest.equals("null")) {
                                strDest = "";
                            }
                        %>
                        <td field="roleDesc"><%=strDest%></td>
                    </tr>
                    <%}%>
                    <%}%>
                </table></td>
            <td style="width: 3px;"></td>
            <td width="65%" style="vertical-align: top;">
                <div class="easyui-panel" title="Danh sách các chức năng"> 
                    <ul id="tt" data-options="animate:true,checkbox:true"></ul>
                </div>
            </td>
        </tr>
    </table>

