/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    $('#dgUser').datagrid({
        loadFilter: pagerFilter
    }).datagrid('reload', {
        options: 'GetListUser'
    });

    $('#dgUser2').datagrid('reload', {
        options: 'GetListUser'
    });

})


function submitTimKiemUserByCenterId() {
    var tenDangNhap = $("#txtUserName").val();
    $('#dgUser').datagrid('reload', {
        options: 'GetListUser',
        tendangnhap: tenDangNhap
    });
}


function reloadGridPage(id,postype) {
   // alert(postype);
    $('#dgUser2').datagrid('unselectAll');
    $('#dgUser2').datagrid('reload', {
        options: 'GetListUser',
        postype:postype
    });
    $.getJSON(requestUrl + "/QuanTriHeThongServices",
            {"options": "GetUserByParentId", "userId": id},
    checkPages);
}

function checkPages(data) {
    $.each(data.rows, function(i, page) {
        $('#dgUser2').datagrid('selectRecord', page.userid);
    });
}

function submitManageUser() {
    var row = $('#dgUser').datagrid('getSelected');
    if (row != null) {
        var ids = [];
        var rows = $('#dgUser2').datagrid('getSelections');
        if (rows != null) {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].userid);
            }
        }
        $.ajax({
            url: requestUrl + "/QuanTriHeThongServices",
            type: "POST",
            cache: false,
            data: {options: "SaveUserPosition", userId: row.userid, posids: ids.join(',')},
            dataType: "html",
            success: function(html) {
                $.messager.progress('close');
                $.messager.alert('Thông báo', html);
            }
        });

    }
}