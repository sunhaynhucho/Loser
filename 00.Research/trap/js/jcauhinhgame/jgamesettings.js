/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
    renderRow: function(target, fields, frozen, rowIndex, rowData) {
        var cc = [];
        cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
        if (!frozen && rowData.gid) {
            var img = rowData.gimageurl;
            cc.push('<img src="../images/imgGame/' + img + '" style="width:150px;float:left">');
            cc.push('<div style="float:left;margin-left:20px;width:80%;">');
            for (var i = 0; i < fields.length; i++) {
                var copts = $(target).datagrid('getColumnOption', fields[i]);
                cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
            }
            cc.push('</div>');
        }
        cc.push('</td>');
        return cc.join('');
    }
});

$(function() {
    $('#gGame').datagrid({
        view: cardview
    }).datagrid('reload', {
        options: 'GetListGame'
    });
});


function searchGames() {
    var gname = $("#txtGameName").val();
    $('#gGame').datagrid('reload', {
        options: 'GetListGame',
        gname: gname
    });
}


function showAddGame() {
    $("#dgDialog").dialog({
        autoOpen: false,
        title: 'Thêm game',
        width: 400,
        closed: false,
        top: 100,
        href: requestUrl + '/formdialog/frmaddgame',
        modal: true
    });
}

function showEditGame() {
    var row = $('#gGame').datagrid('getSelected');
    if (row) {
        $("#dgDialog").dialog({
            autoOpen: false,
            title: 'Sửa game',
            width: 400,
            closed: false,
            top: 100,
            href: requestUrl + '/formdialog/frmeditgame?gid=' + row.gid,
            modal: true
        });
    } else {
        $.messager.alert('Thông báo', 'Bạn chưa chọn game cần sửa!', 'question');
    }
}

function clearForm() {
    $('#frmGame').form('clear');
}

function submitAddGame() {
    $("#divErrMsg").val("");
    var gid = $("#txtMaGame").textbox('getValue');
    var gname = $("#txtTenGame").textbox('getValue');
    var gprice = $("#txtTenGame").numberbox('getValue');
    var gdiff = $("#txtDoKho").numberbox('getValue');
    var gmin = $("#txtMin").numberbox('getValue');
    var gmax = $("#txtMax").numberbox('getValue');
    var gcontent = $("#txtTenGame").textbox('getValue');
    var gtime = $("#txtSoPhut").numberbox('getValue');
    var gticket = $("#txtMaVe").textbox('getValue');
    var gurl = $("#txtGameUrl").textbox('getValue');
    var atFile = $("#fAnh").filebox('getValue');

    if (atFile.toString() == null || atFile.toString() == "") {
        $("#divErrMsg").html("Bạn chưa chọn file ảnh cho game !!!");
        return;
    }
    if (gid == null || gid == "") {
        $("#divErrMsg").html("Bạn chưa điền game id !!!");
        return;
    }
    if (gname == null || gname == "") {
        $("#divErrMsg").val("Bạn chưa điền tiêu đề game !!!");
        return;
    }
    if (gprice == null || gprice == "") {
        $("#divErrMsg").html("Bạn chưa điền giá tiền !!!");
        return;
    }
    if (gdiff == null || gdiff == "") {
        $("#divErrMsg").html("Bạn chưa điền độ khó của game !!!");
        return;
    }
    if (gmin == null || gmin == "") {
        $("#divErrMsg").html("Bạn chưa điền số người chơi ít nhất trong game !!!");
        return;
    }
    if (gmax == null || gmax == "") {
        $("#divErrMsg").html("Bạn chưa điền số người chơi nhiều nhất trong game !!!");
        return;
    }
    if (gcontent == null || gcontent == "") {
        $("#divErrMsg").html("Bạn chưa điền nội dung của game !!!");
        return;
    }
    if (gtime == null || gtime == "") {
        $("#divErrMsg").html("Bạn chưa điền thời gian của game !!!");
        return;
    }
    if (gticket == null || gticket == "") {
        $("#divErrMsg").html("Bạn chưa điền mã vé của game !!!");
        return;
    }
    $('#frmGame').form('submit', {
        url: requestUrl + "/cauhinhgame/addgame",
        success: function(data) {
            var data1 = eval('(' + data + ')');
            if (data1.code == 0) {

                $("#divErrMsg").val("Thêm thành công");
                $("#dgDialog").dialog("close");
                searchGames();
            } else {
                $("#divErrMsg").val(data1.detail);
            }
        }
    });
}

function submitEditGame() {
    $("#divErrMsg").val("");
    var gname = $("#txtTenGame").textbox('getValue');
    var gprice = $("#txtTenGame").numberbox('getValue');
    var gdiff = $("#txtDoKho").numberbox('getValue');
    var gmin = $("#txtMin").numberbox('getValue');
    var gmax = $("#txtMax").numberbox('getValue');
    var gcontent = $("#txtTenGame").textbox('getValue');
    var gtime = $("#txtSoPhut").numberbox('getValue');
    var gticket = $("#txtMaVe").textbox('getValue');

    if (gname == null || gname == "") {
        $("#divErrMsg").html("Bạn chưa điền tiêu đề game !!!");
        return;
    }
    if (gprice == null || gprice == "") {
        $("#divErrMsg").html("Bạn chưa điền giá tiền !!!");
        return;
    }
    if (gdiff == null || gdiff == "") {
        $("#divErrMsg").html("Bạn chưa điền độ khó của game !!!");
        return;
    }
    if (gmin == null || gmin == "") {
        $("#divErrMsg").html("Bạn chưa điền số người chơi ít nhất trong game !!!");
        return;
    }
    if (gmax == null || gmax == "") {
        $("#divErrMsg").html("Bạn chưa điền số người chơi nhiều nhất trong game !!!");
        return;
    }
    if (gcontent == null || gcontent == "") {
        $("#divErrMsg").html("Bạn chưa điền nội dung của game !!!");
        return;
    }
    if (gtime == null || gtime == "") {
        $("#divErrMsg").html("Bạn chưa điền thời gian của game !!!");
        return;
    }
    if (gticket == null || gticket == "") {
        $("#divErrMsg").html("Bạn chưa điền mã vé của game !!!");
        return;
    }

    $('#frmGame').form('submit', {
        url: requestUrl + "/cauhinhgame/editgame",
        success: function(data) {
            alert(data);
//            var data1 = eval('(' + data + ')');
//            if (data1.code == 0) {
//                $("#divErrMsg").val("Thêm thành công");
//                $("#dgDialog").dialog("close");
//                searchGames();
//            } else {
//                $("#divErrMsg").val(data1.detail);
//            }
        },
        error: function(data) {
//            alert('Loi roi cu a');
        }
    });
}

function deleteGame() {
    var row = $('#gGame').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Xác nhận', 'Bạn chắc chắn xóa chứ ?', function(r) {
            if (r) {
                $.ajax({
                    url: requestUrl + '/cauhinhgame/deletegame',
                    type: "POST",
                    cache: false,
                    data: {
                        gid: row.gid
                    },
                    dataType: "json",
                    success: function(data) {
                        if (data.code == "0") {
                            $.messager.alert('Thông báo', data.detail);
                            searchGames();
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