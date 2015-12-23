<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.danhmuccuahang.DanhMucCuaHangDataLogic"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String maSP = request.getParameter("masp");
    List lKhoHang = DanhMucCuaHangDataLogic.getListSanPhamTheoMa(maSP);
    Object[] obj = (Object[]) lKhoHang.get(0);
    String tenSP = String.valueOf(obj[1]);
    String maHangSX = String.valueOf(obj[4]);
    String maLoaiSP = String.valueOf(obj[5]);
    List lHangSX = DanhMucCuaHangDataLogic.getListHangSanXuat(null);
    List lLoaiSP = DanhMucCuaHangDataLogic.getListLoaiSanPham(null);
%>
<div style="padding:10px 0 10px 60px">  
    <table>
        <tr>  
            <td>Mã sản phẩm:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmEditMaSP" disabled maxlength="99" data-options="required:true" value="<%=maSP%>" ></input></td>  
        </tr> 
        <tr>  
            <td>Tên sản phẩm:</td>  
            <td><input class="easyui-validatebox" type="text" id="frmEditTenSP" maxlength="99" data-options="required:true" value="<%=tenSP%>"></input></td>  
        </tr>  
        <tr>  
            <td>Hãng sản xuất:</td>  
            <td>
                <select id="frmEditMaHangSX" >
<!--                    <option value="-1" >Mặc định</option>-->
                    <%
                        for (Object o : lHangSX) {
                            Object[] oHangSX = (Object[]) o;
                            String ma = String.valueOf(oHangSX[0]);
                            String ten = String.valueOf(oHangSX[1]);
                    %><option value="<%=ma%>" ><%=ten%></option><%
                            }
                    %>

                </select>
                <script type="text/javascript" >
                        for(i=0;i<document.getElementById("frmEditMaHangSX").length;i++){
                            if(document.getElementById("frmEditMaHangSX").options[i].value=='<%=maHangSX%>') {
                                document.getElementById("frmEditMaHangSX").selectedIndex=i;
                            }
                        }
                </script>
            </td>  
        </tr>  
        <tr>  
            <td>Loại sản phẩm:</td>  
            <td>  
                <select id="frmEditMaLoaiSP" >
<!--                    <option value="-1" >Mặc định</option>-->
                    <%
                        for (Object o : lLoaiSP) {
                            Object[] oLoaiSP = (Object[]) o;
                            String ma = String.valueOf(oLoaiSP[0]);
                            String ten = String.valueOf(oLoaiSP[1]);
                    %><option value="<%=ma%>" ><%=ten%></option><%
                            }
                    %>
                </select>
                <script type="text/javascript" >
                    for(i=0;i<document.getElementById("frmEditMaLoaiSP").length;i++){
                        if(document.getElementById("frmEditMaLoaiSP").options[i].value=='<%=maLoaiSP%>') {
                            document.getElementById("frmEditMaLoaiSP").selectedIndex=i;
                        }
                    }
                </script>
            </td>  
        </tr>  
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">Đồng ý</a>  
</div>  
