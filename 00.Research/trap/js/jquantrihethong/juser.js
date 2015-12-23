$(function() {
    $('#dgUser').datagrid({
        loadFilter: pagerFilter
    }).datagrid('reload', {
        options: 'GetListUser'
    });

    $("#dgAddUser").dialog({
        autoOpen: false,
        title: 'Thêm người dùng',
        width: 400,
        closed: true,
        top: 100,
        href: requestUrl + '/formdialog/frmquantrihethong/frmadduser.php',
        modal: true
    });
});

function showAddUser() {
    $('#uploadImageUser').form('clear');
    $("#dgAddUser").dialog('open');
}

function showEditUser() {
    var selected = $('#dgUser').datagrid('getSelected');
    if (!selected) {
        $.messager.alert('Thông báo', 'Bạn chưa chọn người dùng nào nào. ', 'warning');
        return;
    }
    
    $('#uploadEditImageUser').form('clear');
    
    if (selected) {
        $("#dgEditUser").dialog({
            autoOpen: false,
            title: 'Chỉnh sửa người dùng',
            width: 400,
            top: 100,
            closed: false,
            href: '../formdialog/frmquantrihethong/frmedituser.jsp?userId=' + selected.userid,
            modal: true
        });
    }
}

function setAmountUser() {
    var selected = $('#dgUser').datagrid('getSelected');
    if (!selected) {
        $.messager.alert('Thông báo', 'Bạn chưa chọn người dùng nào nào. ', 'warning');
        return;
    }
    if (selected) {
        $("#dgAmountUser").dialog({
            autoOpen: false,
            title: 'Cộng tiền cho người dùng',
            width: 400,
            top: 100,
            closed: false,
            href: '../formdialog/frmquantrihethong/frmeditamount.jsp?userId=' + selected.userid,
            modal: true
        });
    }
}

function changeTypeUser() {
    var serverType = $("#frmAddServerType").val();
    if (serverType == 1) {
        $("#frmAddPassWord").attr("disabled", true);
    } else {
        $("#frmAddPassWord").attr("disabled", false);
    }

}

function submitFormAddUser() {
    
    var fullName = $("#frmAddFullName").val();
    var userName = $("#frmAddUserName").val();
    var passWord = $("#frmAddPassWord").val();
    var phoneNumber = $("#frmAddPhone").val();
    var email = $("#frmAddEmail").val();
    var ipAddress = $("#frmAddIp").val();
    var address = $("#frmAddAddress").val();
    var checkIp = $("#frmAddCheckIp").val();
    var posId = $("#ccbAddChucVu").combobox('getValue');
    var tl = $("#fileAvatarUser").filebox('getValue');


    if (fullName == null || fullName == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập họ tên ', 'warning');
        return;
    }

    if (userName == null || userName == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập tên đăng nhập ', 'warning');
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

    if (passWord == null || passWord == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập Mật khẩu ', 'warning');
        return;
    }
    if (isNaN(phoneNumber)) {
        $.messager.alert('Thông báo', 'Số điện thoại phải là số', 'warning');
        return;
    }
    if (posId == null || posId == "") {
        $.messager.alert('Thông báo', 'Bạn chưa chọn chức vụ', 'warning');
        return;
    }
    
    if (checkIp == null || checkIp == "") {
        $.messager.alert('Thông báo', 'Bạn chưa chọn kiểm tra IP', 'warning');
        return;
    }

    if (tl.toString().trim() == "") {

        $('#submitFormAddUser').linkbutton('disable');

        $.messager.progress({
            title: 'Xin đợi ',
            msg: 'Đang xử lý dữ liệu...',
            interval: 1000
        });

        $.ajax({
            url: requestUrl + "/QuanTriHeThongServices",
            type: "POST",
            cache: false,
            data: {
                options: "AddUser",
                fullname: fullName,
                username: userName,
                email: email,
                password: passWord,
                phonenumber: phoneNumber,
                ipaddress: ipAddress,
                address: address,
                checkip: checkIp,
                posid: posId,
                urlImg: ""
            },
            dataType: "json",
            success: function(data) {
                $.messager.progress('close');
                if (data.code == "0") {
                    $('#submitFormAddUser').linkbutton('enable');
                    $.messager.alert('Thông báo', data.detail);
                    $("#dgAddUser").dialog('close');
                    $('#dgUser').datagrid('reload');
                } else {
                    $.messager.alert('Thông báo', data.detail, 'error');
                    $('#submitFormAddUser').linkbutton('enable');
                }

            },
            error: ajaxFail
        });

    } else {

        $('#submitFormAddUser').linkbutton('disable');

        $('#uploadImageUser').form('submit', {
            url: requestUrl + '/UploadImageUserServices',
            accept: "text/plain; charset=utf-8",
            onSubmit: function(param) {
            },
            success: function(data) {
                $.messager.progress('close');
                var data = eval('(' + data + ')');
                if (data.code == "0") {

                    $.messager.progress({
                        title: 'Xin đợi ',
                        msg: 'Đang xử lý dữ liệu...',
                        interval: 1000
                    });

                    $.ajax({
                        url: requestUrl + "/QuanTriHeThongServices",
                        type: "POST",
                        cache: false,
                        data: {
                            options: "AddUser",
                            fullname: fullName,
                            username: userName,
                            email: email,
                            password: passWord,
                            phonenumber: phoneNumber,
                            ipaddress: ipAddress,
                            address: address,
                            checkip: checkIp,
                            posid: posId,
                            urlImg: data.fileName
                        },
                        dataType: "json",
                        success: function(data) {
                            $.messager.progress('close');
                            if (data.code == "0") {
                                $('#submitFormAddUser').linkbutton('enable');
                                $.messager.alert('Thông báo', data.detail);
                                $("#dgAddUser").dialog('close');
                                $('#dgUser').datagrid('reload');
                            } else {
                                $.messager.alert('Thông báo', data.detail, 'error');
                                $('#submitFormAddUser').linkbutton('enable');
                            }

                        },
                        error: ajaxFail
                    });

                } else {
                    $('#submitFormAddUser').linkbutton('enable');
                    $.messager.alert('Thông báo', 'Tên file đã tồn tại. Bạn phải đổi sang tên file khác trước khi upload', 'warning');
                }
            }
        });
    }
}

function submitFormEditUser() {

    var userId = $("#frmEditUserId").val();
    var fullName = $("#frmEditFullName").val();
    var userName = $("#frmEditUserName").val();
    var passWord = $("#frmEditPassWord").val();
    var phoneNumber = $("#frmEditPhone").val();
    var email = $("#frmEditEmail").val();
    var ipAddress = $("#frmEditIp").val();
    var address = $("#frmEditAddress").val();
    var checkIp = $("#frmEditCheckIp").val();
    var posId = $("#ccbEditChucVu").combobox('getValue');
    var tl = $("#fileEditAvatarUser").filebox('getValue');

    if (fullName == null || fullName == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập họ tên ', 'warning');
        return;
    }

    if (userName == null || userName == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập tên đăng nhập ', 'warning');
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

    if (passWord == null || passWord == "") {
        $.messager.alert('Thông báo', 'Bạn chưa nhập Mật khẩu ', 'warning');
        return;
    }
    if (isNaN(phoneNumber)) {
        $.messager.alert('Thông báo', 'Số điện thoại phải là số', 'warning');
        return;
    }
    if (posId == null || posId == "") {
        $.messager.alert('Thông báo', 'Bạn chưa chọn chức vụ', 'warning');
        return;
    }
    
    if (checkIp == null || checkIp == "") {
        $.messager.alert('Thông báo', 'Bạn chưa chọn kiểm tra IP', 'warning');
        return;
    }

    if (tl.toString().trim() == "") {

        $('#submitFormEditUser').linkbutton('disable');

        $.messager.progress({
            title: 'Xin đợi ',
            msg: 'Đang xử lý dữ liệu...',
            interval: 1000
        });

        $.ajax({
            url: requestUrl + "/QuanTriHeThongServices",
            type: "POST",
            cache: false,
            data: {
                options: "EditUser",
                userid: userId,
                fullname: fullName,
                username: userName,
                email: email,
                password: passWord,
                phonenumber: phoneNumber,
                ipaddress: ipAddress,
                address: address,
                checkip: checkIp,
                posid: posId,
                urlImg: ""
            },
            dataType: "json",
            success: function(data) {
                $.messager.progress('close');
                if (data.code == "0") {
                    $('#submitFormEditUser').linkbutton('enable');
                    $.messager.alert('Thông báo', data.detail);
                    $("#dgEditUser").dialog('close');
                    $('#dgUser').datagrid('reload');
                } else {
                    $.messager.alert('Thông báo', data.detail, 'error');
                    $('#submitFormEditUser').linkbutton('enable');
                }

            },
            error: ajaxFail
        });

    } else {

        $('#submitFormEditUser').linkbutton('disable');

        $('#uploadEditImageUser').form('submit', {
            url: requestUrl + '/UploadImageUserServices',
            accept: "text/plain; charset=utf-8",
            onSubmit: function(param) {
            },
            success: function(data) {
                $.messager.progress('close');
                var data = eval('(' + data + ')');
                if (data.code == "0") {

                    $.messager.progress({
                        title: 'Xin đợi ',
                        msg: 'Đang xử lý dữ liệu...',
                        interval: 1000
                    });

                    $.ajax({
                        url: requestUrl + "/QuanTriHeThongServices",
                        type: "POST",
                        cache: false,
                        data: {
                            options: "EditUser",
                            userid: userId,
                            fullname: fullName,
                            username: userName,
                            email: email,
                            password: passWord,
                            phonenumber: phoneNumber,
                            ipaddress: ipAddress,
                            address: address,
                            checkip: checkIp,
                            posid: posId,
                            urlImg: data.fileName
                        },
                        dataType: "json",
                        success: function(data) {
                            $.messager.progress('close');
                            if (data.code == "0") {
                                $('#submitFormEditUser').linkbutton('enable');
                                $.messager.alert('Thông báo', data.detail);
                                $("#dgEditUser").dialog('close');
                                $('#dgUser').datagrid('reload');
                            } else {
                                $.messager.alert('Thông báo', data.detail, 'error');
                                $('#submitFormEditUser').linkbutton('enable');
                            }

                        },
                        error: ajaxFail
                    });

                } else {
                    $('#submitFormEditUser').linkbutton('enable');
                    $.messager.alert('Thông báo', 'Tên file đã tồn tại. Bạn phải đổi sang tên file khác trước khi upload', 'warning');
                }
            }
        });
    }

}

function deleteUser() {
    var selected = $('#dgUser').datagrid('getSelected');
    if (!selected) {
        $.messager.alert('Thông báo', 'Bạn chưa chọn người dùng nào. ', 'warning');
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa user này này ?', function(r) {
        if (r) {
            $.ajax({
                url: requestUrl + "/QuanTriHeThongServices",
                type: "POST",
                cache: false,
                data: {
                    options: "DeleteUser",
                    userid: selected.userid
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == "0") {
                        $('#dgUser').datagrid('reload');
                        $.messager.alert('Thông báo', data.detail);
                    } else {
                        $.messager.alert('Thông báo', data.detail, 'error');
                    }

                },
                error: ajaxFail
            });
        }
    });
}


function submitTimKiemUserByCenterId() {
    var tenDangNhap = $("#txtUserName").val();
    $('#dgUser').datagrid('reload', {
        options: 'GetListUser',
        tendangnhap: tenDangNhap
    });
}
