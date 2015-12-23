<%-- 
    Document   : addrole
    Created on : Jan 22, 2013, 10:02:22 AM
    Author     : KiemPQ-PC
--%>

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

<div id="message"></div>
<div style="padding:10px 0 10px 60px">  
    <form id="ff" method="post" accept-charset="UTF-8">  
        <table>  
            <tr>  
                <td>Tên vai trò:</td>  
                <td><input class="easyui-validatebox" type="text" id="name" maxlength="50"
                           name="name" data-options="required:true"></input></td>  
            </tr>  

<!--            <tr>  
                <td>Kiểu vai trò:</td>  
                <td>  
                    <select name="roletype" id="roletype">
                        <option value="1">Vai trò Administrator</option>
                        <option value="2">Vai trò trang</option>
                        <option value="3">Vai trò thường</option>
                    </select>  
                </td>  
            </tr>-->
            <tr>  
                <td>Miêu tả:</td>  
                <td><textarea name="descrition" id ="descrition" style="height:60px;" maxlength="255"></textarea></td>  
            </tr>  

        </table>  
    </form>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormAddRoles()">Đồng ý</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormAddRole()">Xóa</a>  
</div>  
