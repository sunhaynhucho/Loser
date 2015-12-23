<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.danhmuccuahang.DanhMucCuaHangDataLogic"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<% 
    List lHangSX = DanhMucCuaHangDataLogic.getListHangSanXuat(null);
    List lLoaiSP = DanhMucCuaHangDataLogic.getListLoaiSanPham(null);
%>
<script type="text/javascript">
    $( "#frmAddMaSP" ).focus();
</script>
<div style="padding:10px 0 10px 60px">  
    <table>
        <tr>  
            <td>Mã sản phẩm:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmAddMaSP" onchange="maSanPhamThayDoi()" maxlength="99" data-options="required:true"></input></td>  
        </tr>  
        <tr>  
            <td>Tên sản phẩm:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmAddTenSP" maxlength="99" data-options="required:true"></input></td>  
        </tr>  
        <tr>  
            <td>Hãng sản xuất:</td>  
            <td>
                <select id="frmAddMaHangSX" >
<!--                    <option value="-1" >Mặc định</option>-->
                    <% 
                    for(Object o:lHangSX){
                        Object[] oHangSX = (Object[])o;
                        String maHangSX = String.valueOf(oHangSX[0]);
                        String tenHangSanXuat = String.valueOf(oHangSX[1]);
                        %><option value="<%=maHangSX%>" ><%=tenHangSanXuat%></option><%
                    }
                    %>
                    
                </select>
            </td>  
        </tr>  
        <tr>  
            <td>Loại sản phẩm:</td>  
            <td>  
                <select id="frmAddMaLoaiSP" >
<!--                    <option value="-1" >Mặc định</option>-->
                    <% 
                    for(Object o:lLoaiSP){
                        Object[] oLoaiSP = (Object[])o; 
                        String maHangSX = String.valueOf(oLoaiSP[0]);
                        String tenHangSanXuat = String.valueOf(oLoaiSP[1]);
                        %><option value="<%=maHangSX%>" ><%=tenHangSanXuat%></option><%
                    }
                    %>
                </select>
            </td>  
        </tr>  
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormAdd()">Đồng ý</a>  
</div>  
