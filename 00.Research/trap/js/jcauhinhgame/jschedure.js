/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $('#gSchedure').datagrid('reload', {
        options: 'GetListGame'
    });
});

function searchSchedure() {
    var txtSearch = $("#txtGameName").textbox('getValue');
    var trangthai = $("#ccbTrangThai").combobox('getValue');
    var tuNgay = $("#txtTuNgay").datebox('getValue');
    var denNgay = $("#txtDenNgay").datebox('getValue');
    //alert(txtSearch);
    $('#gSchedure').datagrid('reload', {
        keysearch: txtSearch,
        status: trangthai,
        fromdate: tuNgay,
        todate: denNgay
    });
//    $.ajax({
//        url: requestUrl + '/cauhinhgame/getschedure',
//        type: "POST",
//        cache: false,
//        data: {
//            keysearch: txtSearch,
//            status: trangthai,
//            fromdate: tuNgay,
//            todate: denNgay
//        },
//        dataType: "text",
//        success: function(data) {
//            alert(data);
////            if (data.code == 0) {
////                $.messager.alert('Thông báo', data.detail);
////                $("#divDialog").dialog('close');
////                searchSchedure();
////            } else {
////                $.messager.alert('Thông báo', data.code + "|" + data.detail, 'error');
////            }
//        },
//        error: ajaxFail
//    });

}

function showConfirmSchedure() {
    var row = $('#gSchedure').datagrid('getSelected');
    if (row) {
        $("#divDialog").dialog({
            autoOpen: false,
            title: 'Xác nhận đặt lịch',
            width: 500,
            closed: false,
            top: 100,
            href: requestUrl + '/formdialog/confirmschedure?scid=' + row.sid,
            modal: true
        });
    }
    else {
        $.messager.alert('Thông báo', 'Bạn chưa chọn dữ liệu cần sửa!', 'question');
    }
}

function showGuiMail() {
    var rowId = "-1";
    var row = $('#gSchedure').datagrid('getSelected');
    if (row) {
        rowId = row.sid;
    }
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Xác nhận đặt lịch',
        width: 400,
        closed: false,
        top: 100,
        href: requestUrl + '/formdialog/sendmailschedure?scid=' + rowId,
        modal: true
    });
}

function showDatLich() {
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Xác nhận đặt lịch',
        width: 500,
        closed: false,
        top: 100,
        href: requestUrl + '/formdialog/addschedure',
        modal: true
    });
}

function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return (d < 10 ? ('0' + d) : d) + '/' + (m < 10 ? ('0' + m) : m) + '/' + y;
}

function myparser(s) {
    if (!s)
        return new Date();
    var ss = s.split('/');
    var y = parseInt(ss[2], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[0], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}

function submitXacNhan() {
    var scid = $("#txtSid").val();
    var pusername = $("#txtUserName").val();
    var pfullname = $("#txtHoVaTen").textbox('getValue');
    var pemail = $("#txtEmail").textbox('getValue');
    var pmobile = $("#txtDienThoai").textbox('getValue');
    var paddress = $("#txtDiaChi").textbox('getValue');
    var sday = $("#txtNgayChoi").datebox('getValue');
    var tid = $("#ccbGioChoi").combobox('getValue');
    var scnumber = $("#txtSoNguoiChoi").numberspinner('getValue');
    var gid = $("#ccbGame").combobox('getValue');
    var sstatus = $("#ccbStatus").combobox('getValue');
    $.ajax({
        url: requestUrl + '/cauhinhgame/confirmschedure',
        type: "POST",
        cache: false,
        data: {
            scid: scid,
            sday: sday,
            sstatus: sstatus,
            pusername: pusername,
            pfullname: pfullname,
            pemail: pemail,
            pmobile: pmobile,
            paddress: paddress,
            scnumber: scnumber,
            tid: tid,
            gid: gid
        },
        dataType: "json",
        success: function(data) {
            if (data.code == 0) {
                $.messager.alert('Thông báo', data.detail);
                $("#divDialog").dialog('close');
                searchSchedure();
            } else {
                $.messager.alert('Thông báo', data.code + "|" + data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function submitDatLich() {
    var pfullname = $("#txtHoVaTen").textbox('getValue');
    var pusername = change_alias(pfullname);
    var pemail = $("#txtEmail").textbox('getValue');
    var pmobile = $("#txtDienThoai").textbox('getValue');
    var paddress = $("#txtDiaChi").textbox('getValue');
    var sday = $("#txtNgayChoi").datebox('getValue');
    var tid = $("#ccbGioChoi").combobox('getValue');
    var scnumber = $("#txtSoNguoiChoi").numberspinner('getValue');
    var gid = $("#ccbGame").combobox('getValue');
    var sstatus = $("#ccbStatus").combobox('getValue');
    $.ajax({
        url: requestUrl + '/cauhinhgame/addschedure',
        type: "POST",
        cache: false,
        data: {
            pusername: pusername,
            sday: sday,
            sstatus: sstatus,
            pfullname: pfullname,
            pemail: pemail,
            pmobile: pmobile,
            paddress: paddress,
            scnumber: scnumber,
            tid: tid,
            gid: gid
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.messager.alert('Thông báo', data.detail);
                $("#divDialog").dialog('close');
                searchSchedure();
            } else {
                $.messager.alert('Thông báo', data.code + "|" + data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}
/*
 function submitDangkyGame() {
 var gameLang = $("#gameLang").val();
 var gameIdDatlich = $("#gameIdDatlich").val();
 var gameNgay = $("#gameNgay").val();
 var gameTime = $("#gameTime").val();
 var gameTitle = $("#gameTitle").val();
 var client_name = $("#client_name").val();
 if (client_name.trim() == "") {
 if (gameLang == "vn") {
 alert('Bạn chưa nhập họ tên!');
 } else {
 alert('You did not enter a Name!');
 }
 document.getElementById("client_name").focus();
 return;
 }
 var conVertFullName = change_alias(client_name);
 var client_email = $("#client_email").val();
 if (client_email.trim() == "") {
 if (gameLang == "vn") {
 alert('Bạn chưa nhập Mail!');
 } else {
 alert('You have not entered Mail!');
 }
 document.getElementById("client_email").focus();
 return;
 }
 var conVertClient_email = IsEmail(client_email);
 var client_phone_area_code1 = $("#client_phone_area_code1").val();
 if (client_phone_area_code1.trim() == "") {
 if (gameLang == "vn") {
 alert('Bạn chưa nhập số điện thoại!');
 } else {
 alert('You did not enter a phone number!');
 }
 document.getElementById("client_phone_area_code1").focus();
 return;
 }
 var conVertPhone1 = validatePhone(client_phone_area_code1);
 var client_phone_area_code2 = $("#client_phone_area_code2").val();
 if (client_phone_area_code2.trim() == "") {
 client_phone_area_code2 = 0;
 }
 var conVertPhone2 = validatePhone(client_phone_area_code2);
 
 $.ajax({
 url: requestUrl + '/GameGeneralServices',
 type: "POST",
 cache: false,
 data: {
 options: "DatLichSchedure",
 gameIdDatlich: gameIdDatlich,
 gameNgay: gameNgay,
 gameTime: gameTime,
 client_name: client_name,
 conVertFullName: conVertFullName,
 client_email: client_email,
 client_phone_area_code1: client_phone_area_code1,
 client_phone_area_code2: client_phone_area_code2
 },
 dataType: "json",
 success: function(data) {
 if (data.code == "0") {
 
 location.href = requestUrl + "/thegame/success?ngaydat=" + gameNgay + "&giodat=" + gameTitle;
 
 } else {
 //alert(data.detail);
 location.href = requestUrl + "/thegame/home";
 }
 }
 });
 
 }
 */

function submitDangkyGame() {
    var gameLang = $("#gameLang").val();
    var gameIdDatlich = $("#gameIdDatlich").val();
    var gameNgay = $("#gameNgay").val();
    var gameTime = $("#gameTime").val();
    var gameTitle = $("#gameTitle").val();
    var client_name = $("#client_name").val();
    var requestUrl = $("#requestUrl").val();

    if (client_name.trim() == "") {
        if (gameLang == "vn") {
            alert('Bạn chưa nhập họ tên!');
        } else {
            alert('You did not enter a Name!');
        }
        document.getElementById("client_name").focus();
        return;
    }
    var conVertFullName = change_alias(client_name);

    var client_email = $("#client_email").val();
    if (client_email.trim() == "") {
        if (gameLang == "vn") {
            alert('Bạn chưa nhập Mail!');
        } else {
            alert('You have not entered Mail!');
        }
        document.getElementById("client_email").focus();
        return;
    }

    var conVertClient_email = IsEmail(client_email);

    if (conVertClient_email == false) {
        if (gameLang == "vn") {
            alert('Bạn nhập Email không đúng!');
        } else {
            alert('You enter incorrect Email!');
        }
        document.getElementById("client_email").focus();
        document.getElementById("client_email").value = "";
        return;
    }

    var client_phone_area_code1 = $("#client_phone_area_code1").val();
    if (client_phone_area_code1.trim() == "") {
        if (gameLang == "vn") {
            alert('Bạn chưa nhập số điện thoại!');
        } else {
            alert('You did not enter a phone number!');
        }
        document.getElementById("client_phone_area_code1").focus();
        return;
    }

    var client_phone_area_code2 = $("#client_phone_area_code2").val();
    if (client_phone_area_code2.trim() == "") {
        client_phone_area_code2 = 0;
    }

    $.ajax({
        url: requestUrl + '/thegame/insertBook',
        type: "POST",
        cache: false,
        data: {
            gameIdDatlich: gameIdDatlich,
            gameNgay: gameNgay,
            gameTime: gameTime,
            client_name: client_name,
            conVertFullName: conVertFullName,
            client_email: client_email,
            client_phone_area_code1: client_phone_area_code1,
            client_phone_area_code2: client_phone_area_code2
        },
        dataType: "html",
        success: function(data) {
            if (data == 1) {
                location.href = requestUrl + "/thegame/success?ngaydat=" + gameNgay + "&giodat=" + gameTitle;
            } else {
                alert("Không kết nối được DB");
                return;
            }
        }
    });

}

function change_alias(alias) {
    var str = alias;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ  |ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ  |ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g, "");
    /* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
    str = str.replace(/-+-/g, ""); //thay thế 2- thành 1-
    str = str.replace(/^\-+|\-+$/g, "");
    //cắt bỏ ký tự - ở đầu và cuối chuỗi 
    return str;
}

function IsEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

function validatePhone(phone) {
    var a = phone;
    var filter = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
    if (filter.test(a)) {
        return true;
    }
    else {
        return false;
    }
}

function submitFormGuiMail() {
    var mailName = $("#mailName").textbox('getValue');
    var mailAddress = $("#mailAddress").textbox('getValue');
    var mailSubject = $("#mailSubject").textbox('getValue');
    var mailContent = $("#mailContent").textbox('getValue');
    var mailID = $("#mailId").val();
    if (mailAddress == null || mailAddress == "") {
        $.messager.alert('Thông báo', 'Bạn phải điền địa chỉ mail', 'error');
        return;
    }
    if (mailSubject == null || mailSubject == "") {
        $.messager.alert('Thông báo', 'Bạn phải điền tiêu đề mail', 'error');
        return;
    }
    if (mailContent == null || mailContent == "") {
        $.messager.alert('Thông báo', 'Bạn phải điền nội dung mail', 'error');
        return;
    }

    $.ajax({
        url: requestUrl + '/cauhinhgame/submitsendemail',
        type: "POST",
        cache: false,
        data: {
            mailid: mailID,
            mailname: mailName,
            mailaddress: mailAddress,
            mailsubject: mailSubject,
            mailcontent: mailContent
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.messager.alert('Thông báo', data.detail);
                $("#divDialog").dialog('close');
                searchSchedure();
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        }
    });

}