<%-- 
    Document   : editrole
    Created on : Jan 22, 2013, 10:02:32 AM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.quantrihethong.RoleLogic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style >
    .messageok {
        border:1px solid green; 
        border-radius:5px;
        color: green;
    }

    .messageerr{
        border:1px solid red; 
        border-radius:5px;
        color: red;
    }
</style>
<%
    String roleId = "-1";
    if (request.getParameter("roleId") != null) {
        roleId = request.getParameter("roleId");
    }
    if (roleId.equals("-1")) {
        return;
    }
    List listRole = RoleLogic.getRoles(roleId);
    if (listRole == null || listRole.size() <= 0) {
        return;
    }

    Object object = listRole.get(0);
    Object[] obj = (Object[]) object;
%>
<div style="padding:10px 0 10px 60px">  
    <form id="ffEdit" method="post" accept-charset="UTF-8">  
        <table>
            <tr>  
                <td>Định danh:</td>  
                <td><input class="easyui-validatebox" type="text" id="roleIdEdit" maxlength="100"
                           name="roleIdEdit" disabled data-options="required:true" value="<%=obj[0]%>"></input></td>  
            </tr>
            <tr>  
                <td>Tên vai trò:</td>  
                <td><input class="easyui-validatebox" type="text" id="nameEdit" maxlength="49" name="nameEdit" data-options="required:true" value="<%=obj[1]%>"></input></td>  
            </tr>  


            <tr>  
                <td>Miêu tả:</td>  
                <td><textarea name="descritionEdit" id ="descritionEdit" maxlength="200" style="height:60px;"><%=obj[2]%></textarea></td>  
            </tr>  

        </table>  
    </form>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEditRole()">Đồng ý</a>  
</div>  
