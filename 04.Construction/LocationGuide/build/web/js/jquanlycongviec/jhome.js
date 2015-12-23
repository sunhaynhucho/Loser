/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function convert(rows) {
    function exists(rows, parentId) {
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].id == parentId)
                return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row.parentId)) {
            nodes.push({
                id: row.id,
                text: row.name,
                iconCls: row.iconCls
            });
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift(); // the parent node
        // get the children nodes
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row.parentId == node.id) {
                var child = {
                    id: row.id,
                    text: row.name,
                    iconCls: row.iconCls
                };
                if (node.children) {
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}

$(function() {
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
                loadMsg: '',
                height: 'auto',
                columns: [[
                        {field: 'ccontent', title: 'Nội dung', align: 'left'},
                        {field: 'cfullname', title: 'Người comment', align: 'left'},
                        {field: 'wedit', title: '', align: 'center'},
                        {field: 'wdelete', title: '', align: 'center'}
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
});
function loadGridWork(userId) {
    $("#txtUserIdWork").val(userId);
    $('#dg').datagrid('load', {
        options: 'GetListWork',
        userid: userId
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

function editComment(id, uid) {

    var userId = $("#txtUserId").val();
//    alert(userId );
    if (userId != uid) {
        $.messager.alert('Thông báo', 'Bạn không có quyền sửa comment này', 'info');
        return;
    }
    $("#dgABC").dialog({
        autoOpen: false,
        title: 'Sửa comment',
        width: 250,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/editcomment.jsp?cid=' + id,
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
        title: 'Thêm mới comment',
        width: 260,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlycongviec/addcomment.jsp?wid=' + row.wid,
        modal: true
    });
}

function closeDialogABC() {
    $("#dgABC").dialog('close');
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

function adfdb() {
//    formatter:function(node){
//        var s = node.text;
//        s += '&nbsp;<img src=\'../images/img/tp.png\'>';
//        return s;
//    }
}


function formatProcess(val, row) {
    return '<a href="#" class="easyui-linkbutton" iconCls="icon-man" plain="true">Comment</a>';
}




function showAddWork() {

    $('#dlgAdd').dialog({top: 100}).dialog('open').dialog('setTitle', 'Thêm');

}

function saveAddData() {

    var wuser = $('#wAdduser').combobox('getValues').toString();
    var wAddcontent = document.getElementsByName('wAddcontent')[0].value;
    var wAdddeadline = document.getElementsByName('wAdddeadline')[0].value;

    if (wAddcontent.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }
    var toDay = myformatter(new Date());
    if (myparser(wAdddeadline) < myparser(toDay)) {
        $.messager.alert('Thông báo', "Phải chọn ngày kết thúc lớn hơn hoặc bằng ngày hiện tại", 'info');
        return;
    }

    if (wuser == 0) {
        $.messager.alert('Thông báo', "Chưa chọn người nào được giao việc");
        return;
    }
    $.ajax({
        url: requestUrl + '/QuanLyCongViecServices?options=addDataWork',
        type: "POST",
        cache: false,
        data: {
            wuser: wuser,
            wAddcontent: wAddcontent,
            wAdddeadline: wAdddeadline
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#dlgAdd').dialog('close');
                $.messager.alert('Thông báo', data.detail);
                $('#frmAddRow').form('clear');
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function showEditWork() {

    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#dlg').dialog({top: 100}).dialog('open').dialog('setTitle', 'Sửa');
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
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function deleteWork() {

    var row = $('#dg').datagrid('getSelected');
    if (row) {
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
//    var row = $('#dg').datagrid('getSelected');
//    if (!row) {
//        $.messager.alert('Info', 'Bạn chưa chọn công việc nào', 'question');
//        return;
//    }
//    var giaTri = row.wprocess;
//    var p = giaTri.replace(" %", "");
//    $("#divProcess").dialog({
//        autoOpen: false,
//        title: 'Cập nhật tiến độ công việc',
//        width: 450,
//        top: 100,
//        closed: false,
//        href: '../formdialog/dquanlycongviec/updateprocesswork.jsp?p=' + p + "&wid=" + row.wid,
//        modal: true
//    });

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
    var processVal = $("#ruleProcess").slider('getValue');
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
                $('#divProcess').dialog('close');
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}


function submitAddComment() {
    var wid = $("#txtWorkId").val();
    var commentWork = $("#txtComment").textbox('getValue');

    if (commentWork.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }
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
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function submitEditComment() {
    var cid = $("#txtCmnId").val();
    var wid = $("#txtWorkId").val();
    var commentWork = $("#txtComment").textbox('getValue');

    if (commentWork.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập nội dung");
        return;
    }

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
                $.messager.alert('Thông báo', data.detail);
                $('#dg').datagrid('reload');

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
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
//            var CSRF_TOKEN = $("#CSRF_TOKEN").val();
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
                        ddownload: '<a href="javascript:void(0)"  onclick="downWork(' + value['docid'] + ',\'' + value['docurl'] + '\')"><img src=\'../css/icons/filesave.png\' /></a>',
                        dremove: '<a href="javascript:void(0)"  onclick="removeWork(' + value['docid'] + ')"><img src=\'../css/icons/no.png\' /></a>',
                        dview: '<a href="javascript:void(0)"  onclick="viewWork(' + value['docid'] + ')"><img src=\'../css/icons/search.png\' /></a>'
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