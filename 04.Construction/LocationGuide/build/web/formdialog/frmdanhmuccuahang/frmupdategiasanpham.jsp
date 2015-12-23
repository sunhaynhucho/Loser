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
    List lSanPham = DanhMucCuaHangDataLogic.getListSanPhamTheoMa(maSP);
    Object[] obj = (Object[]) lSanPham.get(0);
    String tenSP = String.valueOf(obj[1]);
    String maHangSX = String.valueOf(obj[4]);
    String maLoaiSP = String.valueOf(obj[5]);
    String giaban1 = String.valueOf(obj[8]);
    String giaban2 = String.valueOf(obj[9]);
    String giaban3 = String.valueOf(obj[10]);
    String giaban4 = String.valueOf(obj[11]);
    String gianhap1 = String.valueOf(obj[12]);
    String gianhap2 = String.valueOf(obj[13]);
    String gianhap3 = String.valueOf(obj[14]);
    String gianhap4 = String.valueOf(obj[15]);
    String donvi1 = String.valueOf(obj[16]);
    String donvi2 = String.valueOf(obj[17]);
    String donvi3 = String.valueOf(obj[18]);
    String donvi4 = String.valueOf(obj[19]);
    String soluong2 = String.valueOf(obj[20]);
    String soluong3 = String.valueOf(obj[21]);
    String soluong4 = String.valueOf(obj[22]);
    String manhacc = String.valueOf(obj[23]);
    String tennhacc = String.valueOf(obj[24]);
    if (giaban1.equals("null")) {
        giaban1 = "0";
    }
    if (giaban2.equals("null")) {
        giaban2 = "0";
    }
    if (giaban3.equals("null")) {
        giaban3 = "0";
    }
    if (giaban4.equals("null")) {
        giaban4 = "0";
    }

    if (gianhap1.equals("null")) {
        gianhap1 = "0";
    }
    if (gianhap2.equals("null")) {
        gianhap2 = "0";
    }
    if (gianhap3.equals("null")) {
        gianhap3 = "0";
    }
    if (gianhap4.equals("null")) {
        gianhap4 = "0";
    }

    if (donvi1.equals("null")) {
        donvi1 = "";
    }
    if (donvi2.equals("null")) {
        donvi2 = "";
    }
    if (donvi3.equals("null")) {
        donvi3 = "";
    }
    if (donvi4.equals("null")) {
        donvi4 = "";
    }
    
    if (soluong2.equals("null")) {
        soluong2 = "0";
    }
    if (soluong3.equals("null")) {
        soluong3 = "0";
    }
    if (soluong4.equals("null")) {
        soluong4 = "0";
    }
   
    
    List lHangSX = DanhMucCuaHangDataLogic.getListHangSanXuat(null);
    List lLoaiSP = DanhMucCuaHangDataLogic.getListLoaiSanPham(null);
    List lNhaCC = DanhMucCuaHangDataLogic.getListNhaPhanPhoi(null);
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

        <tr>
            <td colspan="2"><table>
                    <tr>
                        <td></td>
                        <td>Giá bán</td>
                        <td>SL</td>
                        <td>Tên đơn vị</td>
                    </tr>
                    <tr>
                        <td>Giá 1:</td>
                        <td><input type="text" id="frmBanGia1" maxlength="99" value="<%=giaban1%>" /></td>
                        <td><input type="text" id="frmSoLuong1" maxlength="10" size="1" value="1" /></td>
                        <td><input type="text" id="frmDonVi1" maxlength="99" value="<%=donvi1%>" /></td>
                    </tr> 
                    <tr>
                        <td>Giá 2:</td>
                        <td><input type="text" id="frmBanGia2" maxlength="99" value="<%=giaban2%>" /></td>
                        <td><input type="text" id="frmSoLuong2" maxlength="10" size="1" value="<%=soluong2%>" /></td>
                        <td><input type="text" id="frmDonVi2" maxlength="99" value="<%=donvi2%>" /></td>
                    </tr>
                    <tr>
                        <td>Giá 3:</td>
                        <td><input type="text" id="frmBanGia3" maxlength="99" value="<%=giaban3%>" /></td>
                        <td><input type="text" id="frmSoLuong3" maxlength="10" size="1" value="<%=soluong3%>" /></td>
                        <td><input type="text" id="frmDonVi3" maxlength="99" value="<%=donvi3%>" /></td>
                    </tr>
                    <tr>
                        <td>Giá 4</td>
                        <td><input type="text" id="frmBanGia4" maxlength="99" value="<%=giaban4%>" /></td>
                        <td><input type="text" id="frmSoLuong4" maxlength="10" size="1" value="<%=soluong4%>" /></td>
                        <td><input type="text" id="frmDonVi4" maxlength="99" value="<%=donvi4%>" /></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td >Giá nhập:</td>
                        <td colspan="2">
                            <select id="frmMaNhaCC">
                                <%
                                    for (Object o : lNhaCC) {
                                        Object[] oNhaCC = (Object[]) o;
                                        String ma = String.valueOf(oNhaCC[0]);
                                        String ten = String.valueOf(oNhaCC[1]);
                                %><option value="<%=ma%>" ><%=ten%></option><%
                                    }
                                %>
                            </select>
                            <script type="text/javascript" >
                                for(i=0;i<document.getElementById("frmMaNhaCC").length;i++){
                                    if(document.getElementById("frmMaNhaCC").options[i].value=='<%=maLoaiSP%>') {
                                        document.getElementById("frmMaNhaCC").selectedIndex=i;
                                    }
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td>Giá 1:</td>
                        <td><input type="text" id="frmNhapGia1" maxlength="99" value="<%=gianhap1%>" /></td>
                        <td></td>

                    </tr>
                    <tr>
                        <td>Giá 2:</td>
                        <td><input type="text" id="frmNhapGia2" maxlength="99" value="<%=gianhap2%>" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Giá 3:</td>
                        <td><input type="text" id="frmNhapGia3" maxlength="99" value="<%=gianhap3%>" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Giá 4:</td>
                        <td><input type="text" id="frmNhapGia4" maxlength="99" value="<%=gianhap4%>" /></td>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>  
</div>  
<div style="text-align:center;padding:5px">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormUpDatePrice()">Đồng ý</a>  
</div>  
