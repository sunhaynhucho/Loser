/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $('#gSchedure').datagrid('reload', {
        options: 'GetListMsisdn'
    });
});

function showAddBlackList() {
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Thêm số điện thoại',
        width: 500,
        closed: false,
        top: 100,
        href: requestUrl + '/formdialog/frmcauhinhgame/frmblacklistadd.jsp',
        modal: true
    });
}


function deleteBlackList() {
    var row = $('#gSchedure').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Xác nhận', 'Bạn chắc chắn xóa chứ ?', function(r) {
            if (r) {
                $.ajax({
                    url: requestUrl + '/BlackListServices',
                    type: "POST",
                    cache: false,
                    data: {
                        options: "DeleteMsisdn",
                        msisdn: row.msisdn
                    },
                    dataType: "json",
                    success: function(data) {
                        if (data.code == "0") {
                            $.messager.alert('Thông báo', data.detail);
                            $('#gSchedure').datagrid('reload');
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


function submitAddBlackList() {
    var msisdn = $("#msisdn").textbox('getValue');
    if(!isPhoneVina(msisdn)){
        $.messager.alert('Thông báo', 'Số điện thoại sai định dạng', 'info');
        return;
    }
    var ghichu = $("#ghichu").textbox('getValue');
    $.ajax({
        url: requestUrl + '/BlackListServices',
        type: "POST",
        cache: false,
        data: {
            options: "AddMsisdn",
            msisdn: msisdn,
            ghichu:ghichu
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.messager.alert('Thông báo', data.detail);
                $('#gSchedure').datagrid('reload');
                $('#divDialog').dialog('close');
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}