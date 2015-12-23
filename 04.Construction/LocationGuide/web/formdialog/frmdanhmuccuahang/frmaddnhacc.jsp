<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<div style="padding:10px 0 10px 60px">  
    <table>  
        <tr>  
            <td>Tên nhà cung cấp:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmAddTenNhaCC" name="frmAddTenKho" maxlength="99" data-options="required:true"></input></td>  
        </tr>  
        <tr>  
            <td>Điện thoại:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmAddDienThoai" maxlength="19" ></input></td>  
        </tr>  
        <tr>  
            <td>Địa chỉ:</td>  
            <td>  
                <input class="easyui-validatebox" type="text" id="frmAddDiaChi" maxlength="499" ></input>
            </td>  
        </tr>  
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormAdd()">Đồng ý</a>  
</div>  
