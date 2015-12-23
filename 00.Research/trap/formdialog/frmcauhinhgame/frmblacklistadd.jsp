<%-- 
    Document   : frmaddblacklist
    Created on : May 9, 2015, 3:39:03 PM
    Author     : KiemPQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="padding:10px 60px 20px 60px">
    <table cellpadding="5">
        <tr>
            <td>Số điện thoại:</td>
            <td><input class="easyui-textbox" type="text" maxlength="20" name="msisdn" id="msisdn" ></input></td>
        </tr>
        <tr>
            <td>Ghi chú:</td>
            <td><input class="easyui-textbox" id="ghichu" name="ghichu" 
                       data-options="multiline:true" style="height:60px"></input></td>
        </tr>
    </table>

    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitAddBlackList()">Gửi</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>
