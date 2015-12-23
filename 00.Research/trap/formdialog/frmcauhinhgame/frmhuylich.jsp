<%-- 
    Document   : frmhuylich
    Created on : Mar 30, 2015, 1:40:38 PM
    Author     : KiemPQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="padding:10px 60px 20px 60px">
    <form id="ff" method="post">
        <table cellpadding="5">
            <tr>
                <td>Gửi tới:</td>
                <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
            </tr>
            <tr>
                <td>Tiêu đề:</td>
                <td><input class="easyui-textbox" type="text" name="subject" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>Nội dung:</td>
                <td><input class="easyui-textbox" name="message" data-options="multiline:true" style="height:60px"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Gửi</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>
