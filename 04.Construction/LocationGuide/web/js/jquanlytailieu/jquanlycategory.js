/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    loadDuLieuCategory();
});
function loadDuLieuCategory() {
    var dname = $("#txtName").textbox('getValue');
    $('#griDoc').datagrid('load', {
        options: "GetListCategory",
        dname: dname
    });
}

function showAddCategory() {
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Thêm danh mục',
        width: 450,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlytailieu/addCategory.jsp',
        modal: true
    });
}


function showEditCategory(catId) {
    $("#divDialog").dialog({
        autoOpen: false,
        type: "POST",
        title: 'Sửa danh mục',
        width: 450,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlytailieu/editCategory.jsp?cid=' + catId,
        modal: true
    });
}

function submitDeleteCategory(cid) {

    $.messager.confirm('Xác nhận', 'Bạn chắc chắn xóa chứ ?', function(r) {
        if (r) {
            $.ajax({
                url: requestUrl + '/QuanLyTaiLieuServices',
                type: "POST",
                cache: false,
                data: {
                    options: 'delCategery',
                    cid: cid
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == "0") {
                        $.messager.alert('Thông báo', data.detail);
                        loadDuLieuCategory();

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
function submitAddCategory() {

    var cname = document.getElementsByName('nameCategory')[0].value;
    var cdesc = document.getElementsByName('descCategory')[0].value;
    var cselect = document.getElementsByName('selectCha')[0].value;

    if (cname.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập tên danh mục");
        return;
    }
    if (cdesc.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập tên miêu tả");
        return;
    }

    $.ajax({
        url: requestUrl + '/QuanLyTaiLieuServices',
        type: "POST",
        cache: false,
        data: {
            options: 'addDataCategory',
            cname: cname,
            cdesc: cdesc,
            cselect: cselect
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#divDialog').dialog('close');
                $.messager.alert('Thông báo', data.detail);
                $('#ff').form('clear');
                loadDuLieuCategory();

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });

}
function submitEditCategory() {

    var cid = $("#idEditCate").val();
    var cname = document.getElementsByName('nameCategory')[0].value;
    var cdesc = document.getElementsByName('descCategory')[0].value;
    var cselect = document.getElementsByName('selectCha')[0].value;

    if (cname.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập tên danh mục");
        return;
    }
    if (cdesc.trim() == '') {
        $.messager.alert('Thông báo', "Bạn chưa nhập tên miêu tả");
        return;
    }

    $.ajax({
        url: requestUrl + '/QuanLyTaiLieuServices',
        type: "POST",
        cache: false,
        data: {
            options: 'editDataCategory',
            cid: cid,
            cname: cname,
            cdesc: cdesc,
            cselect: cselect
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#divDialog').dialog('close');
                $.messager.alert('Thông báo', data.detail);
                $('#ff').form('clear');
                loadDuLieuCategory();

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });

}

function clearForm() {
    $('#ff').form('clear');
    $("#divDialog").dialog('close');
}



function convert(rows) {
    rows.push({
        id: 0,
        name: '-- Chọn --',
        parentId: -1
                // iconCls: row.iconCls
    });
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
                text: row.name
                        // iconCls: row.iconCls
            });
        }
    }
//    nodes.push({
//        id: 0,
//        text: '-- Chọn --'
//                // iconCls: row.iconCls
//    });
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
                    text: row.name
                            //iconCls: row.iconCls
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

function clearSearchCat() {
    $("#txtName").textbox('setValue', '');

    $('#griDoc').datagrid('load', {
        options: "GetListCategory",
        dname: ''
    });

}