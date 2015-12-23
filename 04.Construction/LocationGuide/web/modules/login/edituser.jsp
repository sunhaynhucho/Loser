<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style >
    .messageok {
        border:1px solid green; 
        border-radius:5px;
        color: green;
        text-align: center;
    }

    .messageerr{
        border:1px solid red; 
        border-radius:5px;
        color: red;
        text-align: center;
    }
</style>
<script type="text/javascript">
    var requestUrl = "<%=request.getContextPath()%>";
</script>

<%

    OmapUser omapUser = OmapSessionUtils.getOmapUserUserName(request);
    String userId = omapUser.getUserId();
    List lUser = QuanTriHeThongDataLogic.listUserByUserId(userId);
    Object[] oUser = (Object[]) lUser.get(0);
%>
<div class="easyui-panel" title="Thay đổi thông tin" style="float: none;">  
    <div style="padding:10px 0 10px 60px">

        <form id="editUser" method="post" 
              enctype="multipart/form-data" acceptcharset="UTF-8">
            <input type="hidden" id="frmEditUserId" name="userid" value="<%=oUser[0]%>" />
            <table border="0"
                   style="width: 100%; border-collapse: separate; border-spacing: 0 5px;">
                <tr>
                    <td>Họ tên:</td>
                    <td><input class="easyui-validatebox" maxlength="49" type="text" id="frmEditFullName" name="fullname" style="width:195px" value="<%=oUser[3]%>"></input></td>
                    <td><font color="red">*</font></td>
                </tr>
                <tr>
                    <td>UserName:</td>
                    <td><input class="easyui-validatebox" maxlength="49" type="text" disabled id="frmEditUserName" name="tenDangNhap" style="width:195px" value="<%=oUser[1]%>"></input></td>
                    <td><font color="red">*</font></td>
                </tr>
                <tr>
                    <td>Avatar:</td>
                    <td><input class="easyui-filebox" id="fileuploadAvatar" name="file[]" data-options="prompt:'Chọn file...'" style="width:200px;"></td>
                </tr>
                <tr>
                    <td>Mật khẩu hiện tại:</td>
                    <td><input class="easyui-validatebox"  maxlength="40" type="password" id="frmEditPassWord" name="password" style="width:195px"></input></td>
                    <td><font color="red">*</font></td>
                </tr>
                <tr>
                    <td>Mật khẩu mới:</td>
                    <td><input class="easyui-validatebox"  maxlength="40" type="password" id="frmEditNewPassWord" name="passwordNew" style="width:195px"></input></td>
                    <td></td>
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
                        <input class="easyui-validatebox" maxlength="15" type="text" id="frmEditPhone" name="phoneNumber" style="width:195px" value="<%=strPhoneNumber%>"></input></td>
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
                        <input class="easyui-validatebox" maxlength="50" type="text" id="frmEditEmail" name="email" value="<%=strEmail%>" style="width:195px"></input>
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
                    <td><input class="easyui-validatebox" maxlength="20" type="text" disabled id="frmEditIp" name="ipaddress" value="<%=strIP%>" style="width:195px"></input>
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
                    <td><input class="easyui-validatebox" maxlength="100" type="text" id="frmEditAddress" name="address" value="<%=strAddress%>" style="width:195px"></input></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Kiểm tra Ip đăng nhập:</td>
                    <td>
                        <select id="frmEditCheckIp" name="checkip" disabled style="width:199px">
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
</div>
<script>
                            function submitFormEditUser() {
                                var fullName = $("#frmEditFullName").val();
                                var userName = $("#frmEditUserName").val();
                                var passUser = $("#frmEditPassWord").val();
                                var tl = $("#fileuploadAvatar").filebox('getValue');
                                var phoneNumber = $("#frmEditPhone").val();

                                if (fullName == null || fullName == "") {
                                    $.messager.alert('Thông báo', 'Bạn chưa nhập họ tên ', 'warning');
                                    return;
                                }

                                if (passUser == null || passUser == "") {
                                    $.messager.alert('Thông báo', 'Bạn chưa nhập mật khẩu hiện tại ', 'warning');
                                    return;
                                }

                                if (userName == null || userName == "") {
                                    $.messager.alert('Thông báo', 'Bạn chưa nhập UserName ', 'warning');
                                    return;
                                }

                                if (tl.toString().length > 0) {
                                    var chuoiCheck = tl.toString();
                                    var check = chuoiCheck.substr(chuoiCheck.length - 3, chuoiCheck.length);
                                    
                                    if (check == 'png' || check == 'jpg' || check == 'PNG' || check == 'JPG') {
                                        var fileSize = $('input[type="file"]').get(0).files[0];
                                        if (fileSize.size / 1024 / 1024 > 1) {
                                            $.messager.alert("Thông báo", "File không được quá 1Mb", "warning");
                                            return;
                                        }
                                    } else {
                                        $.messager.alert('Thông báo', 'Avatar định dạng jpg hoặc png ', 'warning');
                                        return;
                                    }

                                }

                                if (isNaN(phoneNumber)) {
                                    $.messager.alert('Thông báo', 'Số điện thoại phải là số', 'warning');
                                    return;
                                }

                                $('#submitFormEditUser').linkbutton('disable');
                                
                                $.messager.progress({
                                    title: 'Xin đợi ',
                                    msg: 'Đang xử lý dữ liệu...',
                                    interval: 1000
                                });
                                $('#editUser').form('submit', {
                                    url: requestUrl + '/EditUserServices',
                                    accept: "text/plain; charset=utf-8",
                                    onSubmit: function(param) {
                                    }, success: function(data) {
                                        $.messager.progress('close');
                                        var data = eval('(' + data + ')');
                                        if (data.code == 0) {
//                                            $.messager.alert("Thông báo", "Thành công", "info");
                                            alert('Thay đổi thành công!');
                                            $('#submitFormEditUser').linkbutton('enable');
                                            document.location.href = requestUrl + '/login/edituser';
                                        } else {
                                            $.messager.alert("Thông báo", data.detail, "info");
                                            $('#submitFormEditUser').linkbutton('enable');
                                        }

                                    }
                                });
                            }
                            function clearForm() {
                                $('#fff').form('clear');
                            }
</script>  
