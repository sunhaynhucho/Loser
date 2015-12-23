<%-- 
    Document   : frmadduser
    Created on : May 8, 2013, 1:56:34 PM
    Author     : Pham Quang Kiem
--%>

<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String userId = request.getParameter("userId");
    List lUser = QuanTriHeThongDataLogic.listUserByUserId(userId);
    Object[] oUser = (Object[]) lUser.get(0);
    List lCV = QuanTriHeThongDataLogic.getListChucVu();
%>


<div style="padding:10px 60px 10px 60px">
    <input type="hidden" id="frmEditUserId" value="<%=oUser[0]%>" />
    <form id="uploadEditImageUser" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
        <table border="0" style="width: 100%; border-collapse: separate; border-spacing: 0 5px;">
            <tr>
                <td>Họ tên:</td>
                <td><input class="easyui-validatebox" maxlength="49" type="text" id="frmEditFullName" name="frmEditFullName" style="width:150px" value="<%=oUser[3]%>"></input></td>
                <td><font color="red">*</font></td>
            </tr>
            <tr>
                <td>UserName:</td>
                <td><input class="easyui-validatebox" maxlength="49" type="text" disabled id="frmEditUserName" name="frmEditUserName" style="width:150px" value="<%=oUser[1]%>"></input></td>
                <td><font color="red">*</font></td>
            </tr>

            <tr>
                <td>Avatar:</td>
                <td><input class="easyui-filebox" id="fileEditAvatarUser" name="file[]" data-options="prompt:'Chọn file...'" style="width:150px;"></td>
            </tr>

            <tr>
                <td>Mật khẩu:</td>
                <td><input class="easyui-validatebox"  maxlength="40" type="password" id="frmEditPassWord" name="frmEditPassWord" value="<%=String.valueOf(oUser[2])%>" style="width:150px"></input></td>
                <td><font color="red">*</font></td>
            </tr>
            <tr>
                <td>Số di động:</td>
                <td>
                    <%
                        String strPhoneNumber = String.valueOf(oUser[6]);
                        if (strPhoneNumber.equals("null")) {
                            strPhoneNumber = "";
                        }
                    %>
                    <input class="easyui-validatebox" maxlength="15" type="text" id="frmEditPhone" name="frmEditPhone" style="width:150px" value="<%=strPhoneNumber%>"></input></td>
                <td></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <%
                        String strEmail = String.valueOf(oUser[4]);
                        if (strEmail.equals("null")) {
                            strEmail = "";
                        }
                    %>
                    <input class="easyui-validatebox" maxlength="50" type="text" id="frmEditEmail" name="frmEditEmail" value="<%=strEmail%>" style="width:150px"></input>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Địa chỉ IP:</td>
                <%
                    String strIP = String.valueOf(oUser[5]);
                    if (strIP.equals("null")) {
                        strIP = "";
                    }
                %>
                <td><input class="easyui-validatebox" maxlength="20" type="text" id="frmEditIp" name="frmEditIp" value="<%=strIP%>" style="width:150px"></input>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Địa chỉ:</td>
                <%
                    String strAddress = String.valueOf(oUser[7]);
                    if (strAddress.equals("null")) {
                        strAddress = "";
                    }
                %>
                <td><input class="easyui-validatebox" maxlength="100" type="text" id="frmEditAddress" name="frmEditAddress" value="<%=strAddress%>" style="width:150px"></input></td>
                <td></td>
            </tr>
            <tr>
                <td>Chức vụ:</td>
                <td><select class="easyui-combobox" id="ccbEditChucVu" name="ccbEditChucVu" style="width:150px;">
                        <%
                            if (lCV != null && lCV.size() > 0) {
                                for (Object os : lCV) {
                                    Object[] o = (Object[]) os;
                        %>

                        <option value="<%=o[0]%>"><%=o[1]%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <script type="text/javascript">
                        for (i = 0; i < document.getElementById("ccbEditChucVu").length; i++) {
                            if (document.getElementById("ccbEditChucVu").options[i].value == '<%=oUser[9]%>') {
                                document.getElementById("ccbEditChucVu").selectedIndex = i;
                            }
                        }
                    </script>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Kiểm tra Ip đăng nhập:</td>
                <td>
                    <select id="frmEditCheckIp" name="frmEditCheckIp">
                        <option value="0">Không kiểm tra</option>
                        <option value="1">Kiểm tra</option>
                    </select>
                    <script type="text/javascript">
                        for (i = 0; i < document.getElementById("frmEditCheckIp").length; i++) {
                            if (document.getElementById("frmEditCheckIp").options[i].value == '<%=oUser[8]%>') {
                                document.getElementById("frmEditCheckIp").selectedIndex = i;
                            }
                        }
                    </script>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <a href="javascript:void(0)" class="easyui-linkbutton" id="submitFormEditUser" onclick="submitFormEditUser()">Đồng ý</a>
                </td>
            </tr>
        </table>
    </form>
</div>
