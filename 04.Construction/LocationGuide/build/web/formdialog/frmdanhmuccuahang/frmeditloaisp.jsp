<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.danhmuccuahang.DanhMucCuaHangDataLogic"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String maKho = request.getParameter("maloaisp");
    List lKhoHang = DanhMucCuaHangDataLogic.getListLoaiSanPhamTheoMaLoai(maKho);
    Object[] obj = (Object[])lKhoHang.get(0);
    String tenKho = String.valueOf(obj[1]);
  
%>
<div style="padding:10px 0 10px 60px">  
    <input type="hidden" id="frmEditMaLoaiSanPham" value="<%=maKho%>" />
    <table>  
        <tr>  
            <td>Tên loại sản phẩm:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmEditTenLoaiSanPham" maxlength="99" data-options="required:true" value="<%=tenKho%>"></input></td>  
        </tr>  
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">Đồng ý</a>  
</div>  
