/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    loadDuLieuDocument();
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

function showAddDoc() {
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Thêm danh mục',
        width: 450,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlytailieu/addDocument.jsp',
        modal: true
    });
}

function submitAddDoc() {
    var txtDocName = $("#txtDocName").textbox('getValue');
    if (txtDocName == null || txtDocName == "") {
        $.messager.alert("Thông báo", "Bạn chưa điền tiêu đề cho tài liệu", "info");
        return;
    }
    var catId = $("#ccbAddDanhMuc").combotree('getValue');
    if (catId == null || catId == "") {
        $.messager.alert("Thông báo", "Bạn chưa chọn danh mục", "info");
        return;
    }
    var tl = $("#fileupload").filebox('getValue');
    if (tl == null || tl == "") {
        $.messager.alert("Thông báo", "Bạn chưa chọn file upload", "info");
        return;
    }

    var fileSize = $('input[type="file"]').get(0).files[0];
//    if (fileSize.size / 1024 / 1024 > 6) {
//        $.messager.alert("Thông báo", "File không được quá 6Mb", "info");
//        return;
//    }
    var searchKey = $("#txtSearchKey").textbox('getValue');
    $.messager.progress({
        title: 'Xin đợi ',
        msg: 'Đang xử lý dữ liệu...',
        interval: 1000
    });
    $('#upLoadFileId').form('submit', {
        url: requestUrl + '/DocUploadFileServices',
        accept: "text/plain; charset=utf-8",
        onSubmit: function(param) {
            param.options = 'UpLoadDocument';
            param.catid = catId;
            param.dname = txtDocName;
            param.searchkey = searchKey;
        }, success: function(data) {
            $.messager.progress('close');
            var data = eval('(' + data + ')');
            if (data.code == 0) {
                //$( "#infoBuoc3" ).html("Contact hợp lệ:" + data.gcount + ", số contact không hợp lệ:" + data.gcountnok);
                $.messager.alert("Thông báo", "Thành công", "info");
                $("#divDialog").dialog('close');
                loadDuLieuDocument();
                $('#upLoadFileId').form('clear');
                // $("#txtDocName").textbox('setValue', '');
            } else {
                $.messager.alert("Thông báo", data.detail, "info");

            }

        }
    });
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

function dowloadDoc(docid) {
    $.ajax({
        url: requestUrl + '/QuanLyTaiLieuServices',
        type: "POST",
        cache: false,
        data: {
            options: 'DownLoadDocumentAccept',
            docid: docid
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                window.location = data.docurl;
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
    //window.location = docid;
}

function showEditDoc(id) {
    $("#divDialog").dialog({
        autoOpen: false,
        title: 'Sửa tài liệu',
        width: 450,
        top: 100,
        closed: false,
        href: '../formdialog/dquanlytailieu/editDocument.jsp?did=' + id,
        modal: true
    });
}

function submitEditDoc() {
    var idHid = document.getElementById('idDocId').value;
    var txtDocName = $("#nameDocument").textbox('getValue');
    if (txtDocName == null || txtDocName == "") {
        $.messager.alert("Thông báo", "Bạn chưa nhập tên tài liệu", "info");
        return;
    }
    var catId = $("#ccbDanhMucDoc").combotree('getValue');
    if (catId == null || catId == "") {
        $.messager.alert("Thông báo", "Bạn chưa chọn danh mục", "info");
        return;
    }
    var searchKey = $("#searchKey").textbox('getValue');
    $.ajax({
        url: requestUrl + '/QuanLyTaiLieuServices',
        type: "POST",
        cache: false,
        data: {
            options: 'editDataDocs',
            idHid: idHid,
            txtDocName: txtDocName,
            catId: catId,
            searchkey: searchKey
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $('#divDialog').dialog('close');
                $.messager.alert('Thông báo', data.detail);
                loadDuLieuDocument();

            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });

}


function submitDeleteDoc(did) {
    $.messager.confirm('Xác nhận', 'Bạn chắc chắn xóa chứ ?', function(r) {
        if (r) {
            $.ajax({
                url: requestUrl + '/QuanLyTaiLieuServices',
                type: "POST",
                cache: false,
                data: {
                    options: 'delDocsCat',
                    did: did
                },
                dataType: "json",
                success: function(data) {
                    if (data.code == "0") {
                        $.messager.alert('Thông báo', data.detail);
                        loadDuLieuDocument();

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

function showXemDoc(docid) {
    $.ajax({
        url: requestUrl + '/QuanLyTaiLieuServices',
        type: "POST",
        cache: false,
        data: {
            options: 'DownLoadDocumentAccept',
            docid: docid
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $("#txtUrlFileShow").val(data.pdfurl);
                $("#dgABCDE").dialog({
                    autoOpen: false,
                    title: 'Nội dung File',
                    width: 840,
                    height: 550,
                    left: 250,
                    top: 50,
                    closed: false,
                    href: '../formdialog/dquanlytailieu/viewContentFileDocs.jsp',
                    modal: true
                });
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
    

}
function clearFormEditDoc() {
    $("#divDialog").dialog('close');
}

function clearSearch() {
    $("#txtName").textbox('setValue', '');
    $("#ccbDanhMuc").combotree('setValue', '');

    $('#griDoc').datagrid('load', {
        options: "GetListDocument",
        cid: '-1',
        dname: ''
    });
}