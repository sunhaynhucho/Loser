<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.danhmuccuahang.DanhMucCuaHangDataLogic"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String maNhaCC = request.getParameter("manhacc");
    List lKhoHang = DanhMucCuaHangDataLogic.getListNhaCungCapTheoMa(maNhaCC);
    Object[] obj = (Object[])lKhoHang.get(0);
    String tenKho = String.valueOf(obj[1]);
    String diaChi = String.valueOf(obj[2]);
    String dienThoai = String.valueOf(obj[3]);
    if("null".equals(diaChi)){
        diaChi = "";
    }
    if("null".equals(dienThoai)){
        dienThoai = "";
    }
%>
<div style="padding:10px 0 10px 60px">  
    <input type="hidden" id="frmEditMaNhaCC" value="<%=maNhaCC%>" />
    <table>  
        <tr>  
            <td>Tên nhà cung cấp:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmEditTenNhaCC" maxlength="99" data-options="required:true" value="<%=tenKho%>"></input></td>  
        </tr>  
        <tr>  
            <td>Điện thoại:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmEditDienThoai" maxlength="19" value="<%=dienThoai%>" ></input></td>  
        </tr>  
        <tr>  
            <td>Địa chỉ:</td>  
            <td>  
                <input class="easyui-validatebox" type="text" id="frmEditDiaChi" maxlength="499" value="<%=diaChi%>" ></input>
            </td>  
        </tr>  
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">Đồng ý</a>  
</div>  
