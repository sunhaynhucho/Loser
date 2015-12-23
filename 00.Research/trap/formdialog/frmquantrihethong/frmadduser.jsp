<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List lCV = QuanTriHeThongDataLogic.getListChucVu();
%>

<div style="padding:10px 60px 10px 60px">
    <form id="uploadImageUser" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
        <table border="0"
               style="width: 100%; border-collapse: separate; border-spacing: 0 5px;">
            <tr>
                <td>Họ tên:</td>
                <td><input class="easyui-validatebox" maxlength="240" type="text" id="frmAddFullName" name="frmAddFullName" style="width:150px"></input></td>
                <td><font color="red">*</font></td>
            </tr>
            <tr>
                <td>UserName:</td>
                <td><input class="easyui-validatebox" maxlength="49" type="text" id="frmAddUserName" name="frmAddUserName" style="width:150px"></input></td>
                <td><font color="red">*</font></td>
            </tr>

            <tr>
                <td>Avatar:</td>
                <td><input class="easyui-filebox" id="fileAvatarUser" name="file[]" data-options="prompt:'Chọn file...'" style="width:150px;"></td>
            </tr>

            <tr>
                <td>Mật khẩu:</td>
                <td><input class="easyui-validatebox"  maxlength="40" type="password" id="frmAddPassWord" name="frmAddPassWord" style="width:150px"></input></td>
                <td><font color="red">*</font></td>
            </tr>

            <tr>
                <td>Số di động:</td>
                <td><input class="easyui-validatebox" maxlength="15" type="text" id="frmAddPhone" name="frmAddPhone" style="width:150px"></input></td>
                <td></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <input class="easyui-validatebox" maxlength="50" type="text" id="frmAddEmail" name="frmAddEmail" style="width:150px"></input>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Địa chỉ IP:</td>
                <td><input class="easyui-validatebox" maxlength="20" type="text" id="frmAddIp" name="frmAddIp" style="width:150px"></input>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Địa chỉ:</td>
                <td><input class="easyui-validatebox" maxlength="100" type="text" id="frmAddAddress" name="frmAddAddress" style="width:150px"></input></td>
                <td></td>
            </tr>
            <tr>
                <td>Chức vụ:</td>
                <td><select class="easyui-combobox" id="ccbAddChucVu" name="ccbAddChucVu" style="width:150px;">
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
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Kiểm tra Ip đăng nhập:</td>
                <td>
                    <select id="frmAddCheckIp" name="frmAddCheckIp">
                        <option value="0">Không kiểm tra</option>
                        <option value="1">Kiểm tra</option>
                    </select>

                </td>
                <td></td>
            </tr>

            <tr>
                <td colspan="3" align="center">
                    <a href="javascript:void(0)" class="easyui-linkbutton" id="submitFormAddUser" onclick="submitFormAddUser()">Đồng ý</a>
                </td>
            </tr>
        </table>
    </form>
</div>