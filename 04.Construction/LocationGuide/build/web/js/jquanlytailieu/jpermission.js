$(function() {
    loadDuLieuDocument();
    loadDuLieuNhom();
    loadDuLieuUser();
});
function loadDuLieuDocument() {
    var catID = $("#ccbDanhMuc").combotree('getValue');
    if (catID == null || catID == "") {
        catID = "-1";
    }
    var dname = $("#txtName").textbox('getValue');
    $('#griDoc').datagrid('load', {
        options: "GetListDocument",
        cid: catID,
        dname: dname
    });
}

function loadDuLieuNhom() {
    var dname = $("#txtNameGroup").textbox('getValue');
    $('#gridRole').datagrid('load', {
        options: "GetListRole",
        dname: dname
    });
}

function loadDuLieuUser() {
    var dname = $("#txtUserName").textbox('getValue');
    $('#gridUser').datagrid('load', {
        options: "GetListUser",
        dname: dname
    });
}

function onClickSelecRow(index, row) {
    $('#gridRole').datagrid('clearSelections');
    $('#gridUser').datagrid('clearSelections');
    $.ajax({
        url: requestUrl + '/PermissionServices',
        type: "POST",
        cache: false,
        data: {
            options: 'GetRoleByDoc',
            docid: row.did
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.each(data.rows, function(i, page) {
                    $('#gridRole').datagrid('selectRecord', page.roleid);
                });
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
    $.ajax({
        url: requestUrl + '/PermissionServices',
        type: "POST",
        cache: false,
        data: {
            options: 'GetUserByDoc',
            docid: row.did
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.each(data.rows, function(i, page) {
                    $('#gridUser').datagrid('selectRecord', page.username);
                });
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}

function saveDocRole(){
    var row = $('#griDoc').datagrid('getSelected');
    if (row != null) {
        var ids = [];
        var rows = $('#gridRole').datagrid('getSelections');
        if (rows != null) {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].roleid);
            }
        }
        $.ajax({
            url: requestUrl + "/PermissionServices",
            type: "POST",
            cache: false,
            data: {
                options: "SaveDocAndRole"
                , docid: row.did
                , roleids: ids.join(',')},
            dataType: "json",
            success: function(html) {
                $.messager.alert('Thông báo', html.detail);
            }
        });

    }    
}

function saveDocUser(){
    var row = $('#griDoc').datagrid('getSelected');
    if (row != null) {
        var ids = [];
        var rows = $('#gridUser').datagrid('getSelections');
        if (rows != null) {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].username);
            }
        }
        $.ajax({
            url: requestUrl + "/PermissionServices",
            type: "POST",
            cache: false,
            data: {
                options: "SaveDocAndUser"
                , docid: row.did
                , usernames: ids.join(',')},
            dataType: "json",
            success: function(html) {
                $.messager.alert('Thông báo', html.detail);
            }
        });

    }   
}

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
                text: row.name
                        // iconCls: row.iconCls
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