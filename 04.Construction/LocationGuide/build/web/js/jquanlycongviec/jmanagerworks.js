var hidine1 = -1;
var hidine2 = -1;

$(function() {
    $("#divcot1").hide();
    $("#divcot2").hide();
    $("#divcot3").hide();
    $("#divcot4").hide();
    $('#dg').datagrid({
        url: requestUrl + '/QuanLyCongViecServices',
        view: detailview,
        detailFormatter: function(index, row) {
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        onExpandRow: function(index, row) {
            var ddv = $(this).datagrid('getRowDetail', index).find('table.ddv');
            ddv.datagrid({
                url: requestUrl + '/QuanLyCongViecServices?options=GetListComment&wid=' + row.wid,
                fitColumns: true,
                singleSelect: true,
                rownumbers: true,
                nowrap: false,
                loadMsg: '',
                height: 'auto',
                columns: [[
                        {field: 'ccontent', title: 'Nội dung', align: 'left', width: '200px'},
                        {field: 'cfullname', title: 'Người góp ý', align: 'left', width: '115px'},
                        {field: 'ccreatetime', title: 'Thời gian tạo', align: 'left', width: '100px'},
//                        {field: 'cupdatetime', title: 'Thời gian sửa', align: 'left', width: '100px'},
                        {field: 'wedit', title: 'Sửa', align: 'center', width: '30px'},
                        {field: 'wdelete', title: 'Xóa', align: 'center', width: '30px'}
                    ]],
                onResize: function() {
                    $('#dg').datagrid('fixDetailRowHeight', index);
                },
                onLoadSuccess: function() {
                    setTimeout(function() {
                        $('#dg').datagrid('fixDetailRowHeight', index);
                    }, 0);
                }
            });
            $('#dg').datagrid('fixDetailRowHeight', index);
        }
    });
//    var posType = $("#txtPosType").val();
    var userID = $("#txtUserId").val();
    var fullNameLogin = $("#txtFullName").val();
    if (userID == "-1") {
        return;
    }
    loadDanhSachCapDuoi(userID, '0', fullNameLogin);
    var tblDg = $('#dg').datagrid('getPanel');
    tblDg.panel('setTitle', 'Danh sách công việc (' + fullNameLogin + ')');

    //loadGridWork(userID);


});

function loadDanhSachCapDuoi(userId, cot, fullNameLogin) {
    var cotht = parseInt(cot) + 1;
    var tenrow = cot + '_' + userId;
    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices',
        type: "POST",
        cache: false,
        data: {
            options: 'LoadDanhSachCapDuoi',
            userid: userId

        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                var html = '';
                var imageUrl = '';
                for (i = 0; i < data.rows.length; i++) {
                    if (data.rows[i].imageurl == 'null' || data.rows[i].imageurl == "") {
                        imageUrl = 'dfTB.png';
                    } else {
                        imageUrl = data.rows[i].imageurl;
                    }
                    html += '<tr id="' + cotht + '_' + data.rows[i].id + '" onclick="loadDanhSachCapDuoi(\'' + data.rows[i].id + '\',\'' + cotht + '\',\'' + data.rows[i].fullname + '\');" ' +
                            '  >' +
                            '      <td width="26%" align="center"><img src="../images/imgUser/' + imageUrl + '" width="45" height="45"></td>' +
                            '     <td width="74%" height="30"><table width="100%">' +
                            '      <tr>' +
                            '       <td><span style="font-size: 12px">' + data.rows[i].fullname + '</span></td>' +
                            '    </tr>' +
                            '   <tr>' +
                            '    <td><span style="font-size: 10px;color:#999">Chức vụ : ' + data.rows[i].posname + '</span></td>' +
                            ' </tr>' +
                            ' </table>' +
                            ' </td>' +
                            '</tr>';
                }
                if (data.rows.length > 0) {
                    if (cot == "0") {
                        // cot 1 
                        $("#divcot1").show();
                        document.getElementById('cot1').style.visibility = 'visible';
                        document.getElementById('cot1').style.width = '200px';
                        document.getElementById('tblcot1').innerHTML = html;
                        $("#divcot2").hide();
                        document.getElementById('cot2').style.visibility = 'hidden';
                        document.getElementById('cot2').style.width = '0px';
                        $("#divcot3").hide();
                        document.getElementById('cot3').style.visibility = 'hidden';
                        document.getElementById('cot3').style.width = '0px';
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "1") {
                        $("#divcot2").show();
                        document.getElementById('cot2').style.visibility = 'visible';
                        document.getElementById('cot2').style.width = '200px';
                        document.getElementById('tblcot2').innerHTML = html;
                        $("#divcot3").hide();
                        document.getElementById('cot3').style.visibility = 'hidden';
                        document.getElementById('cot3').style.width = '0px';
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "2") {
                        $("#divcot3").show();
                        document.getElementById('cot3').style.visibility = 'visible';
                        document.getElementById('cot3').style.width = '200px';
                        document.getElementById('tblcot3').innerHTML = html;
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "3") {
                        $("#divcot4").show();
                        document.getElementById('cot4').style.visibility = 'visible';
                        document.getElementById('cot4').style.width = '200px';
                        document.getElementById('tblcot4').innerHTML = html;
                    }

                } else {
                    if (cot == "0") {
                        // cot 1 
                        $("#divcot1").hide();
                        document.getElementById('cot1').style.visibility = 'hidden';
                        document.getElementById('cot1').style.width = '0px';
                        $("#divcot2").hide();
                        document.getElementById('cot2').style.visibility = 'hidden';
                        document.getElementById('cot2').style.width = '0px';
                        $("#divcot3").hide();
                        document.getElementById('cot3').style.visibility = 'hidden';
                        document.getElementById('cot3').style.width = '0px';
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "1") {
                        $("#divcot2").hide();
                        document.getElementById('cot2').style.visibility = 'hidden';
                        document.getElementById('cot2').style.width = '0px';
                        $("#divcot3").hide();
                        document.getElementById('cot3').style.visibility = 'hidden';
                        document.getElementById('cot3').style.width = '0px';
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "2") {
                        $("#divcot3").hide();
                        document.getElementById('cot3').style.visibility = 'hidden';
                        document.getElementById('cot3').style.width = '0px';
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    } else if (cot == "3") {
                        $("#divcot4").hide();
                        document.getElementById('cot4').style.visibility = 'hidden';
                        document.getElementById('cot4').style.width = '0px';
                    }
                }
                if (cot != '0') {
                    var idTable = 'tblcot' + cot;
                    var table = document.getElementById(idTable);
                    var rows = table.getElementsByTagName("tr");
                    for (i = 0; i < rows.length; i++) {
                        if (rows[i].id != "") {
                            rows[i].style.backgroundColor = '#FFF';
                        }
                    }
                    document.getElementById(tenrow).style.backgroundColor = '#FC6';
                    var tblDg = $('#dg').datagrid('getPanel');
                    tblDg.panel('setTitle', 'Danh sách công việc (' + fullNameLogin + ')');
                    loadGridWork(userId);
                }
                var tblDg = $('#dg').datagrid('getPanel');
                tblDg.panel('setTitle', 'Danh sách công việc (' + fullNameLogin + ')');
                loadGridWork(userId);
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function loadGridWork(userId) {
    $("#txtUserIdWork").val(userId);
    $('#dg').datagrid('load', {
        options: 'GetListWork',
        userid: userId
    });
}
/*
 //function MakeColorOnMouseOver1(id) {
 //    document.getElementById(id).bgColor = '#FC6';
 //}
 //
 //function MakeColorOnMouseOut1(id) {
 //
 //    if (hidine1 == id) {
 //        document.getElementById(id).bgColor = '#FC6';
 //    } else {
 //        document.getElementById(id).bgColor = '#FFF';
 //    }
 //}
 //
 //function MakeColorOnMouseOver2(id) {
 //    document.getElementById(id).bgColor = '#FC6';
 //}
 //
 //function MakeColorOnMouseOut2(id) {
 //
 //    if (hidine2 == id) {
 //        document.getElementById(id).bgColor = '#FC6';
 //    } else {
 //        document.getElementById(id).bgColor = '#FFF';
 //    }
 //}
 
 function loadDanhSachPhoPhong(userId) {
 
 $.ajax({
 url: requestUrl + '/QuanLyCongViecServices',
 type: "POST",
 cache: false,
 data: {
 options: 'LayDanhSachPhoPhong',
 userid: userId
 },
 dataType: "json",
 success: function(data) {
 if (data.code == "0") {
 if (data.rows.length > 0) {
 $("#divcot1").show();
 document.getElementById('cot1').style.visibility = 'visible';
 document.getElementById('cot1').style.width = '200px';
 $("#divcot3").hide();
 document.getElementById('cot3').style.visibility = 'hidden';
 document.getElementById('cot3').style.width = '0px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 } else {
 $("#divcot1").hide();
 document.getElementById('cot1').style.visibility = 'hidden';
 document.getElementById('cot1').style.width = '0px';
 $("#divcot3").hide();
 document.getElementById('cot3').style.visibility = 'hidden';
 document.getElementById('cot3').style.width = '0px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 }
 //data.rows
 var html = '';
 var imageUrl = '';
 for (i = 0; i < data.rows.length; i++) {
 if (data.rows[i].imageurl == 'null' || data.rows[i].imageurl == "") {
 imageUrl = 'dfTB.png';
 } else {
 imageUrl = data.rows[i].imageurl;
 }
 html += '<tr id="' + data.rows[i].id + '" onclick="clickPhophong(\'' + data.rows[i].fullname + '\',this.id);" OnMouseOver="MakeColorOnMouseOver1(this.id);"' +
 ' OnMouseOut="MakeColorOnMouseOut1(this.id)" >' +
 '      <td width="26%" align="center"><img src="../images/imgUser/' + imageUrl + '" width="45" height="45"></td>' +
 '     <td width="74%" height="30"><table width="100%">' +
 '      <tr>' +
 '       <td><span style="font-size: 12px">' + data.rows[i].fullname + '</span></td>' +
 '    </tr>' +
 '   <tr>' +
 '    <td><span style="font-size: 10px;color:#999">Chức vụ : ' + data.rows[i].posname + '</span></td>' +
 ' </tr>' +
 ' </table>' +
 ' </td>' +
 '</tr>';
 }
 document.getElementById('cotPhophong').innerHTML = html;
 
 } else {
 $.messager.alert('Thông báo', data.detail, 'error');
 }
 },
 error: ajaxFail
 });
 }
 
 function clickPhophong(fullName, id) {
 if (hidine1 != -1) {
 document.getElementById(hidine1).bgColor = '#FFF';
 }
 
 hidine1 = id;
 hidine2 = -1;
 document.getElementById(id).bgColor = '#FC6';
 
 var tblDg = $('#dg').datagrid('getPanel');
 tblDg.panel('setTitle', 'Danh sách công việc (' + fullName + ')');
 
 loadGridWork(id);
 loadDanhSachTruongBan(id);
 
 }
 
 
 function loadDanhSachTruongBan(userId) {
 
 $.ajax({
 url: requestUrl + '/QuanLyCongViecServices',
 type: "POST",
 cache: false,
 data: {
 options: 'LayDanhSachTruongBan',
 userid: userId
 },
 dataType: "json",
 success: function(data) {
 if (data.code == "0") {
 if (data.rows.length > 0) {
 $("#divcot2").show();
 document.getElementById('cot2').style.visibility = 'visible';
 document.getElementById('cot2').style.width = '200px';
 $("#divcot3").hide();
 document.getElementById('cot3').style.visibility = 'hidden';
 document.getElementById('cot3').style.width = '0px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 } else {
 $("#divcot2").hide();
 document.getElementById('cot2').style.visibility = 'hidden';
 document.getElementById('cot2').style.width = '0px';
 $("#divcot3").hide();
 document.getElementById('cot3').style.visibility = 'hidden';
 document.getElementById('cot3').style.width = '0px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 }
 
 
 //data.rows
 var html = '';
 var imageUrl = '';
 for (i = 0; i < data.rows.length; i++) {
 if (data.rows[i].imageurl == 'null' || data.rows[i].imageurl == "") {
 imageUrl = 'dfTB.png';
 } else {
 imageUrl = data.rows[i].imageurl;
 }
 html += '<tr id="' + data.rows[i].id + '" onclick="clickTruongBan(\'' + data.rows[i].fullname + '\',this.id);" OnMouseOver="MakeColorOnMouseOver1(this.id);"' +
 ' OnMouseOut="MakeColorOnMouseOut1(this.id)" >' +
 '      <td width="26%" align="center"><img src="../images/imgUser/' + imageUrl + '" width="45" height="45"></td>' +
 '     <td width="74%" height="30"><table width="100%">' +
 '      <tr>' +
 '       <td><span style="font-size: 12px">' + data.rows[i].fullname + '</span></td>' +
 '    </tr>' +
 '   <tr>' +
 '    <td><span style="font-size: 10px;color:#999">Chức vụ : ' + data.rows[i].posname + '</span></td>' +
 ' </tr>' +
 ' </table>' +
 ' </td>' +
 '</tr>';
 }
 document.getElementById('cotTruongban').innerHTML = html;
 
 } else {
 $.messager.alert('Thông báo', data.detail, 'error');
 }
 },
 error: ajaxFail
 });
 }
 
 function clickTruongBan(fullName, id) {
 if (hidine1 != -1) {
 document.getElementById(hidine1).bgColor = '#FFF';
 }
 
 hidine1 = id;
 hidine2 = -1;
 document.getElementById(id).bgColor = '#FC6';
 
 var tblDg = $('#dg').datagrid('getPanel');
 tblDg.panel('setTitle', 'Danh sách công việc (' + fullName + ')');
 
 loadGridWork(id);
 loadDanhSachPhoBan(id);
 
 }
 
 function loadDanhSachPhoBan(userId) {
 
 $.ajax({
 url: requestUrl + '/QuanLyCongViecServices',
 type: "POST",
 cache: false,
 data: {
 options: 'LayDanhSachPhoBan',
 userid: userId
 },
 dataType: "json",
 success: function(data) {
 if (data.code == "0") {
 //data.rows
 
 if (data.rows.length > 0) {
 $("#divcot3").show();
 document.getElementById('cot3').style.visibility = 'visible';
 document.getElementById('cot3').style.width = '200px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 } else {
 $("#divcot3").hide();
 document.getElementById('cot3').style.visibility = 'hidden';
 document.getElementById('cot3').style.width = '0px';
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 }
 
 var html = '';
 var imageUrl = '';
 for (i = 0; i < data.rows.length; i++) {
 if (data.rows[i].imageurl == 'null' || data.rows[i].imageurl == "") {
 imageUrl = 'dfTB.png';
 } else {
 imageUrl = data.rows[i].imageurl;
 }
 html += '<tr id="' + data.rows[i].id + '" onclick="clickPhoBan(\'' + data.rows[i].fullname + '\',this.id);" OnMouseOver="MakeColorOnMouseOver1(this.id);"' +
 ' OnMouseOut="MakeColorOnMouseOut1(this.id)" >' +
 '      <td width="26%" align="center"><img src="../images/imgUser/' + imageUrl + '" width="45" height="45"></td>' +
 '     <td width="74%" height="30"><table width="100%">' +
 '      <tr>' +
 '       <td><span style="font-size: 12px">' + data.rows[i].fullname + '</span></td>' +
 '    </tr>' +
 '   <tr>' +
 '    <td><span style="font-size: 10px;color:#999">Chức vụ : ' + data.rows[i].posname + '</span></td>' +
 ' </tr>' +
 ' </table>' +
 ' </td>' +
 '</tr>';
 }
 document.getElementById('cotPhoban').innerHTML = html;
 } else {
 $.messager.alert('Thông báo', data.detail, 'error');
 }
 },
 error: ajaxFail
 });
 }
 
 function clickPhoBan(fullName, id) {
 if (hidine1 != -1) {
 document.getElementById(hidine1).bgColor = '#FFF';
 }
 
 hidine1 = id;
 hidine2 = -1;
 document.getElementById(id).bgColor = '#FC6';
 
 var tblDg = $('#dg').datagrid('getPanel');
 tblDg.panel('setTitle', 'Danh sách công việc (' + fullName + ')');
 
 loadGridWork(id);
 loadDanhSachNhanvien(id);
 
 }
 
 
 function loadDanhSachNhanvien(userId) {
 
 $.ajax({
 url: requestUrl + '/QuanLyCongViecServices',
 type: "POST",
 cache: false,
 data: {
 options: 'LayDanhSachNhanvien',
 userid: userId
 },
 dataType: "json",
 success: function(data) {
 if (data.code == "0") {
 //data.rows
 if (data.rows.length > 0) {
 $("#divcot4").show();
 document.getElementById('cot4').style.visibility = 'visible';
 document.getElementById('cot4').style.width = '200px';
 } else {
 $("#divcot4").hide();
 document.getElementById('cot4').style.visibility = 'hidden';
 document.getElementById('cot4').style.width = '0px';
 }
 var html = '';
 var imageUrl = '';
 for (i = 0; i < data.rows.length; i++) {
 if (data.rows[i].imageurl == 'null' || data.rows[i].imageurl == "") {
 imageUrl = 'dfNV.png';
 } else {
 imageUrl = data.rows[i].imageurl;
 }
 html += '<tr id="' + data.rows[i].id + '" onclick="clickNhanvien(\'' + data.rows[i].fullname + '\',this.id);" OnMouseOver="MakeColorOnMouseOver2(this.id);"' +
 ' OnMouseOut="MakeColorOnMouseOut2(this.id)" >' +
 '      <td width="26%" align="center"><img src="../images/imgUser/' + imageUrl + '" width="45" height="45"></td>' +
 '     <td width="74%" height="30"><table width="100%">' +
 '      <tr>' +
 '       <td><span style="font-size: 12px">' + data.rows[i].fullname + '</span></td>' +
 '    </tr>' +
 '   <tr>' +
 '    <td><span style="font-size: 10px;color:#999">Chức vụ : ' + data.rows[i].posname + '</span></td>' +
 ' </tr>' +
 ' </table>' +
 ' </td>' +
 '</tr>';
 }
 document.getElementById('cotNhanvien').innerHTML = html;
 
 } else {
 $.messager.alert('Thông báo', data.detail, 'error');
 }
 },
 error: ajaxFail
 });
 }
 
 function clickNhanvien(fullName, id) {
 
 if (hidine2 != -1) {
 document.getElementById(hidine2).bgColor = '#FFF';
 }
 
 hidine2 = id;
 document.getElementById(id).bgColor = '#FC6';
 
 var tblDg = $('#dg').datagrid('getPanel');
 tblDg.panel('setTitle', 'Danh sách công việc (' + fullName + ')');
 
 loadGridWork(id);
 }
 */
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

function showAddWork() {

    $('#dlgAdd').dialog({left: 300, top: 60, height: 525, width: 800}).dialog('open').dialog('setTitle', 'Thêm');

}
function saveAddData() {

    var wuser = $('#wAdduser').combobox('getValues').toString();
    var wuserIdWork = $('#userIDWork').val();
    var wAddcontent = document.getElementsByName('wAddcontent')[0].value;
    var wAdddeadline = $('#wAdddeadline').datebox('getValue');
    var atFile = $("#fileCongviec").filebox('getValue');

    if (atFile.toString() == "" || atFile.toString() == null) {

        if (wAddcontent.trim() == '') {
            if (atFile.toString().trim() == '') {
                $.messager.alert('Thông báo', "Bạn chưa nhập nội dung hoặc đính kèm file!", 'info');
                return;
            }
        }

        var toDay = myformatter(new Date());
        if (wAdddeadline == "" || myparser(wAdddeadline) < myparser(toDay)) {
            $.messager.alert('Thông báo', "Phải chọn ngày kết thúc lớn hơn hoặc bằng ngày hiện tại", 'info');
            return;
        }

        if (wuser == 0 || wuser == null || wuser == "") {
            $.messager.alert('Thông báo', "Chưa chọn người nào được giao việc", 'info');
            return;
        }

        $('#saveAddData').linkbutton('disable');

        $.ajax({
            url: requestUrl + '/QuanLyCongViecServices?options=addDataWork',
            type: "POST",
            cache: false,
            data: {
                wuser: wuser,
                wAddcontent: wAddcontent,
                wAdddeadline: wAdddeadline,
                wuserIdWork: wuserIdWork
            },
            dataType: "json",
            success: function(data) {
                if (data.code == "0") {
                    $('#dlgAdd').dialog('close');
                    $('#frmAddRow').form('clear');
                    $('#frmAddCV').form('clear');
                    $('#saveAddData').linkbutton('enable');
                    $.messager.alert('Thông báo', data.detail);
                    $('#dg').datagrid('reload');

                } else {
                    $.messager.alert('Thông báo', data.detail, 'error');
                    $('#saveAddData').linkbutton('enable');
                }
            },
            error: ajaxFail
        });

    } else {
        if (atFile == null || atFile == "") {
            $.messager.alert("Thông báo", "Bạn chưa chọn file upload", "info");
            return;
        }

        $('#saveAddData').linkbutton('disable');

        $.messager.progress({
            title: 'Xin đợi ',
            msg: 'Đang xử lý dữ liệu...',
            interval: 1000
        });
        $('#frmAddCV').form('submit', {
            url: requestUrl + '/UploadFileCongViecServices',
            accept: "text/plain; charset=utf-8",
            onSubmit: function(param) {
                param.options = 'UpLoadCV';
            }, success: function(data) {
                $.messager.progress('close');
                var data = eval('(' + data + ')');
                if (data.code == 0) {
                    $('#dlgAdd').dialog('close');
                    $('#saveAddData').linkbutton('enable');
                    $.messager.alert("Thông báo", "Thành công", "info");
                    loadDuLieuResult();
                    $('#dg').datagrid('reload');
                    $('#upLoadFileIdRS').form('clear');
                    $("#txtDocNameRS").textbox('setValue', '');
                } else {
                    $.messager.alert("Thông báo", data.detail, "info");
                    $('#saveAddData').linkbutton('enable');
                }

            }
        });
    }

}

function showEditWork() {

    var row = $('#dg').datagrid('getSelected');
    var userID = $('#txtUserId').val();

    if (row) {

        if (row.wuserid != userID) {
            $.messager.alert('Thông báo', row.wfullname + " mới có quyền sửa công việc này", 'error');
            return;
        }

        var getTextLength = row.wcontent.length;

        document.getElementById('testTextarea2').innerHTML = getTextLength;
        document.getElementById('testTextarea3').innerHTML = 1000 - getTextLength;

        $('#dlg').dialog({width: 750, height: 450, top: 100, left: 300}).dialog('open').dialog('setTitle', 'Sửa công việc');
        $('#frmEditRow').form('load', row);
    } else {
        $.messager.alert('Thông báo', 'Bạn chưa chọn dữ liệu cần sửa!', 'question');
    }

}

function saveEditData() {

    var wid = document.getElementsByName('wid')[0].value;
    var wcontent = document.getElementsByName('wcontent')[0].value;
    var wdeadline = document.getElementsByName('wdeadline')[0].value;

    if (wcontent.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }
    if (myparser(wdeadline) <= new Date()) {
        $.messager.alert('Thông báo', "Phải chọn ngày kết thúc lớn hơn ngày hiện tại");
        return;
    }

    $('#saveEditData').linkbutton('disable');

    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices?options=editDataWork',
        type: "POST",
        cache: false,
        data: {
            wid: wid,
            wcontent: wcontent,
            wdeadline: wdeadline
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#dlg').dialog('close');
                $('#saveEditData').linkbutton('enable');
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
                $('#saveEditData').linkbutton('enable');
            }
        },
        error: ajaxFail
    });
}

function deleteWork() {

    var row = $('#dg').datagrid('getSelected');
    var userID = $('#txtUserId').val();

    if (row) {

        if (row.wuserid != userID) {
            $.messager.alert('Thông báo', row.wfullname + " mới có quyền xóa công việc này", 'error');
            return;
        }

        $.messager.confirm('Xác nhận', 'Bạn chắc chắn xóa chứ ?', function(r) {
            if (r) {
                $.ajax({
                    url: requestUrl + '/QuanLyCongViecServices?options=delDataWork',
                    type: "POST",
                    cache: false,
                    data: {
                        wid: row.wid
                    },
                    dataType: "json",
                    success: function(data) {
                        if (data.code == "0") {
                            $.messager.alert('Thông báo', data.detail);
                            $('#dg').datagrid('reload');

                        } else {
                            $.messager.alert('Thông báo', data.detail, 'error');
                        }
                    },
                    error: ajaxFail
                });
            } else {
                return;
            }
        });
    } else {
        $.messager.alert('Thông báo', 'Bạn chưa chọn dữ liệu cần xóa!', 'question');
    }
}

function showUpdateProcessWork() {

    var row = $('#dg').datagrid('getSelected');
    if (row) {
        if (row.wprocess_id == 0) {
            $.messager.confirm('Xác nhận', 'Bạn đã hoàn thành công việc này ?', function(r) {
                if (r) {
                    $.ajax({
                        url: requestUrl + '/QuanLyCongViecServices',
                        type: "POST",
                        cache: false,
                        data: {
                            options: "updateProcessWork",
                            wid: row.wid,
                            processVal: 1
                        },
                        dataType: "json",
                        success: function(data) {
                            if (data.code == "0") {
                                $.messager.alert('Thông báo', data.detail);
                                $('#dg').datagrid('reload');

                            } else {
                                $.messager.alert('Thông báo', data.detail, 'error');
                            }
                        },
                        error: ajaxFail
                    });
                } else {
                    return;
                }
            });
        } else {
            $.messager.confirm('Xác nhận', 'Bạn chưa hoàn thành công việc này ?', function(r) {
                if (r) {
                    $.ajax({
                        url: requestUrl + '/QuanLyCongViecServices',
                        type: "POST",
                        cache: false,
                        data: {
                            options: "updateProcessWork",
                            wid: row.wid,
                            processVal: 0
                        },
                        dataType: "json",
                        success: function(data) {
                            if (data.code == "0") {
                                $.messager.alert('Thông báo', data.detail);
                                $('#dg').datagrid('reload');

                            } else {
                                $.messager.alert('Thông báo', data.detail, 'error');
                            }
                        },
                        error: ajaxFail
                    });
                } else {
                    return;
                }
            });
        }
    } else {
        $.messager.alert('Thông báo', 'Bạn chưa chọn công việc nào!', 'question');
    }

}

function submitUpdateProcess() {
    var wid = $("#hidWid").val();
    var processVal = $("#comboTiendo").combobox('getValue');

    $('#submitUpdateProcess').linkbutton('disable');

    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices',
        type: "POST",
        cache: false,
        data: {
            options: "updateProcessWork",
            wid: wid,
            processVal: processVal
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');
                $('#dgABC').dialog('close');
                $('#submitUpdateProcess').linkbutton('enable');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
                $('#submitUpdateProcess').linkbutton('enable');
            }
        },
        error: ajaxFail
    });
}
function showDocument() {
    var row = $('#dg').datagrid('getSelected');
    if (!row) {
        $.messager.alert('Info', 'Bạn chưa chọn công việc nào');
        return;
    }
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Tài liệu công việc',
        width: 450,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/viewdocumnet.jsp?wid=' + row.wid,
        modal: true
    });
}

function showAddComment() {
    var row = $('#dg').datagrid('getSelected');
    if (!row) {
        $.messager.alert('Info', 'Bạn phải chọn thêm comment cho công việc nào');
        return;
    }
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Thêm góp ý',
        width: 750,
        height: 380,
        top: 100,
        left: 300,
        closed: false,
        href: '../formdialog/dquanlycongviec/addcomment.jsp?wid=' + row.wid,
        modal: true
    });
}

function submitAddComment() {
    var wid = $("#txtWorkId").val();
    var commentWork = $("#txtComment").val();

    if (commentWork.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }
    $('#submitAddComment').linkbutton('disable');

    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices',
        type: "POST",
        cache: false,
        data: {
            options: "addCommentWork",
            wid: wid,
            commentWork: commentWork
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#dgABC').dialog('close');
                $('#submitAddComment').linkbutton('enable');
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
                $('#submitAddComment').linkbutton('enable');
            }
        },
        error: ajaxFail
    });
}

function submitEditComment() {
    var cid = $("#txtCmnId").val();
    var wid = $("#txtWorkId").val();
    var commentWork = $("#txtComment").val();

    if (commentWork.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }

    $('#submitEditComment').linkbutton('disable');

    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices',
        type: "POST",
        cache: false,
        data: {
            options: "editCommentWork",
            wid: wid,
            cid: cid,
            commentWork: commentWork
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#dgABC').dialog('close');
                $('#submitEditComment').linkbutton('enable');
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
                $('#submitEditComment').linkbutton('enable');
            }
        },
        error: ajaxFail
    });
}


function deleteComment(id, uid) {

    var userId = $("#txtUserId").val();
    if (userId != uid) {
        $.messager.alert('Thông báo', 'Bạn không có quyền xóa comment này', 'info');
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn chắc chắn muốn xóa comment này ?', function(r) {
        if (r) {
            $.ajax({
                type: "POST",
                url: requestUrl + "/QuanLyCongViecServices",
                data: {
                    options: "deleteComment",
                    cid: id
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == 0) {
                        $.messager.alert('Thông báo', 'Xóa comment thành công', 'info');
                        $('#dg').datagrid('reload');
                    } else {
                        $.messager.alert('Thông báo', data.detail, 'error');
                    }
                },
                error: ajaxFail
            });
        }
    });
}


function loadDuLieuDocument() {
    var wid = $("#txtWorkId").val();
    $.ajax({
        type: "POST",
        url: requestUrl + "/QuanLyCongViecServices",
        data: {
            options: "listDocument",
            wid: wid
        },
        dataType: "json",
        success: function(data) {
            $('#griDoc').datagrid('loadData', []);
            if (data.total > 0) {
                $.each(data.rows, function(key, value) {
                    $('#griDoc').datagrid('appendRow', {
                        dname: value['doctitle'],
                        dtime: value['doctime'],
                        ddownload: '<a href="javascript:void(0)"  onclick="downWork(' + value['docid'] + ',\'' + value['docurl'] + '\')"><img src=\'../css/icons/filesave.png\' /></a>',
                        dremove: '<a href="javascript:void(0)"  onclick="removeWork(' + value['docid'] + ')"><img src=\'../css/icons/no.png\' /></a>',
                        dview: '<a href="javascript:void(0)"  onclick="showXemDocWork(\'' + value['docpdf'] + '\')"><img src=\'../css/icons/search.png\' /></a>'
                    });
                });
            } else {
            }
        },
        error: ajaxFail
    });
}

function loadDuLieuResult() {
    var wid = $("#txtWorkIdRS").val();
    $.ajax({
        type: "POST",
        url: requestUrl + "/QuanLyCongViecServices",
        data: {
            options: "listResult",
            wid: wid
        },
        dataType: "json",
        success: function(data) {
            $('#griDoc').datagrid('loadData', []);
            if (data.total > 0) {
                $.each(data.rows, function(key, value) {

                    var htmlSave = '';
                    var htmlView = '';
                    var htmlEdit = '';
                    if (value['docpath'].trim() != "") {
                        htmlSave = '<img src=\'../css/icons/filesave.png\'/></a>';
                        htmlView = '<img src=\'../css/icons/search.png\'/></a>';
                    } else {
                        htmlEdit = '<img src=\'../css/icons/pencil.png\'/></a>';
                    }

                    $('#griDoc').datagrid('appendRow', {
                        left: 100,
                        dcontent: value['doccontent'],
                        dname: value['doctitle'],
                        dtime: value['doctime'],
                        ddownload: '<a href="javascript:void(0)"  onclick="downResult(' + value['docid'] + ',\'' + value['docurl'] + '\')">' + htmlSave + '',
                        dremove: '<a href="javascript:void(0)"  onclick="removeResult(' + value['docid'] + ')"><img src=\'../css/icons/no.png\' /></a>',
                        dview: '<a href="javascript:void(0)"  onclick="showXemDocWork(\'' + value['docpdf'] + '\')">' + htmlView + '',
                        dedit: '<a href="javascript:void(0)"  onclick="viewDialogEditResult(' + value['docid'] + ')">' + htmlEdit + ''
                    });
                });
            } else {
            }
        },
        error: ajaxFail
    });
}

function downWork(docid, docurl) {
    window.location = docurl;
}

function downResult(docid, docurl) {
    window.location = docurl;
}

function viewWork(id) {

    $.ajax({
        type: "POST",
        url: requestUrl + "/ShowContentFileServices",
        data: {
            options: "showDocDocument",
            id: id
        },
        dataType: "html",
        success: function(data) {

            var html = '';
            if (data == 'null' || data == '') {
                html = 'Không đọc được dữ liệu, vui lòng tải về !';
            } else {
                html = data;
            }

            $("#dgABCD").dialog({
                autoOpen: false,
                title: 'Nội dung File',
                width: 800,
                height: 410,
                left: 250,
                top: 100,
                closed: false,
                href: '../formdialog/dquanlycongviec/viewContentFile.jsp?data=' + html,
                modal: true
            });
        },
        error: ajaxFail
    });
}

function viewResult(id) {
    $.ajax({
        type: "POST",
        url: requestUrl + "/ShowContentFileServices",
        data: {
            options: "showDocResult",
            id: id
        },
        dataType: "html",
        success: function(data) {

            var html = '';
            if (data == 'null' || data == '') {
                html = 'Không đọc được dữ liệu, vui lòng tải về !';
            } else {
                html = data;
            }

            $("#dgABCD").dialog({
                autoOpen: false,
                title: 'Nội dung File',
                width: 800,
                height: 410,
                left: 250,
                top: 100,
                closed: false,
                href: '../formdialog/dquanlycongviec/viewContentFile.jsp?data=' + html,
                modal: true
            });
        },
        error: ajaxFail
    });
}

function removeWork(docid) {
    $.messager.confirm('Xác nhận', 'Bạn chắc chắn muốn xóa file này ?', function(r) {
        if (r) {

            $.ajax({
                type: "POST",
                url: requestUrl + "/QuanLyCongViecServices",
                data: {
                    options: "deleteFileDocument",
                    docid: docid
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == 0) {
                        $.messager.alert('Thông báo', 'Xóa file thành công', 'info');
                        loadDuLieuDocument();
                        $('#dg').datagrid('reload');
                    } else {
                        $.messager.alert('Thông báo', data.detail, 'error');
                    }
                },
                error: ajaxFail
            });
        }
    });
}

function removeResult(docid) {
    $.messager.confirm('Xác nhận', 'Bạn chắc chắn muốn xóa ?', function(r) {
        if (r) {

            $.ajax({
                type: "POST",
                url: requestUrl + "/QuanLyCongViecServices",
                data: {
                    options: "deleteFileResult",
                    docid: docid
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == 0) {
                        $.messager.alert('Thông báo', 'Xóa file thành công', 'info');
                        loadDuLieuResult();
                        $('#dg').datagrid('reload');
                    } else {
                        $.messager.alert('Thông báo', data.detail, 'error');
                    }
                },
                error: ajaxFail
            });
        }
    });
}


function searchWork() {
    var startDate = $("#startDate").datebox("getValue");
    var endDate = $("#endDate").datebox("getValue");
    if (startDate == null || startDate == "") {
        $.messager.alert('Thông báo', 'Chưa chọn ngày bắt đầu', 'info');
        return;
    }
    if (endDate == null || endDate == "") {
        $.messager.alert('Thông báo', 'Chưa chọn ngày kết thúc', 'info');
        return;
    }
    if (myparser(startDate) > myparser(endDate)) {
        $.messager.alert('Thông báo', 'Ngày bắt đầu phải nhỏ hơn ngày kết thúc', 'info');
        return;
    }
    var userId = $("#txtUserIdWork").val();
    $('#dg').datagrid('load', {
        options: "GetListWork",
        startdate: startDate,
        enddate: endDate,
        userid: userId
    });
}

function clearSearchWork() {
    $('#startDate').datebox('setValue', '');
    $("#endDate").datebox('setValue', '');

    var startDate = $("#startDate").datebox("getValue");
    var endDate = $("#endDate").datebox("getValue");
    var userId = $("#txtUserIdWork").val();

    $('#dg').datagrid('load', {
        options: "GetListWork",
        startdate: startDate,
        enddate: endDate,
        userid: userId
    });
}

function closeDialogABC() {
    $("#dgABC").dialog('close');
}

function editComment(id, uid) {

    var userId = $("#txtUserId").val();
    if (userId != uid) {
        $.messager.alert('Thông báo', 'Bạn không có quyền sửa comment này', 'info');
        return;
    }
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Sửa góp ý',
        width: 750,
        height: 380,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/editcomment.jsp?cid=' + id,
        modal: true
    });
}

function showKetqua(wid) {

    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Kết quả công việc',
        width: 880,
        height: 420,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/viewresultWork.jsp?wid=' + wid,
        modal: true
    });
}

function showTailieu(wid) {
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Tài liệu công việc',
        width: 550,
        height: 300,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/viewdocumnet.jsp?wid=' + wid,
        modal: true
    });

}

function showComboProcess() {
    var row = $('#dg').datagrid('getSelected');
    if (!row) {
        $.messager.alert('Info', 'Bạn chưa chọn công việc nào');
        return;
    }
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Tiến độ công việc',
        width: 350,
        height: 150,
        top: 100,
        left: 500,
        closed: false,
        href: '../formdialog/dquanlycongviec/showUpdateProcess.jsp?wid=' + row.wid,
        modal: true
    });
}

function  viewDialogEditResult(id) {
    $.ajax({
        type: "POST",
        url: requestUrl + "/ShowContentFileServices",
        data: {
            options: "showEditResult",
            id: id
        },
        dataType: "html",
        success: function(data) {
            $("#dgABCD").dialog({
                autoOpen: false,
                title: 'Nội dung',
                width: 600,
                height: 440,
                top: 100,
                closed: false,
                href: '../formdialog/dquanlycongviec/viewShowEditResult.jsp?id=' + id + '&data=' + data,
                modal: true
            });
        },
        error: ajaxFail
    });
}

function saveEditDataResult() {

    var id = $('#idEditResult').val();
    var data = $('#wcontentEditRS').val();

    if (data.trim() == "") {
        $.messager.alert('Cảnh báo', 'Bạn chưa nhập nội dung', 'info');
        return;
    }

    $.ajax({
        type: "POST",
        url: requestUrl + "/QuanLyCongViecServices",
        data: {
            options: "submitEditResult",
            id: id,
            data: data
        },
        dataType: "json",
        success: function(data) {
            if (data.code == 0) {
                $.messager.alert('Thông báo', data.detail, 'info');
                loadDuLieuResult();
                $('#griDoc').datagrid('reload');
                $("#dgABCD").dialog('close');
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function showXemDocWork(url) {
    
    $("#txtUrlFileShow").val(url);

    $("#dgABCDE").dialog({
        autoOpen: false,
        title: 'Nội dung File',
        width: 930,
        height: 530,
        left: 250,
        top: 50,
        closed: false,
        href: '../formdialog/dquanlycongviec/viewContentFileWork.jsp',
        modal: true
    });

}