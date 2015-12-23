   
var toolbar = [{  
    text:'Thêm',  
    iconCls:'icon-add',  
    handler:function(){
        showAddRole();
    }  
},'-',{  
    text:'Sửa',  
    iconCls:'icon-edit',  
    handler:function(){
        showEditRole();
    }  
},'-',{  
    text:'Xóa',  
    iconCls:'icon-remove',  
    handler:function(){
        deleteRole();
    }  
}];

function reloadGridRole(){
    $('#dgRoles').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetRoles'
    });
    
}  
    
$(function() {
    $( "#dialog" ).dialog({
        autoOpen: false,
        title: 'Thêm mới vai trò',  
        width: 400,
        top:100,
        closed: true, 
        href: requestUrl +'/formdialog/frmquantrihethong/frmaddrole.jsp',  
        modal: true
    });
    $('#dgRoles').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetRoles'
    });
//    $('#dgRoles').datagrid({
//        rowStyler: function(index,row){
//            if ((index % 2) > 0){
//                return 'background-color:#eef3fc;';
//            }
//        }
//    });
});
     
function showEditRole(){
    var selected = $('#dgRoles').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn vai trò nào. ','warning');  
        return;
    }
    $( "#editdialog" ).dialog({
        autoOpen: false,
        title: 'Chỉnh sửa vai trò',  
        width: 400,
        top:100,
        href: requestUrl +'/formdialog/frmquantrihethong/frmeditrole.jsp?roleId=' + selected.roleid,  
        modal: true
    });
}
    
function showAddRole(){
    $("#dialog").dialog("open");
}
    
function deleteRole(){
    var ids = [];
    var rows = $('#dgRoles').datagrid('getSelections');
    if(rows == null || rows.length <= 0){
        $.messager.alert('Thông báo','Bạn chưa chọn vai trò nào. ','warning');  
        return;
    }
    for(var i=0;i<rows.length;i++){
        ids.push(rows[i].roleid);
    }
    
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa vai trò này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl +"/QuanTriHeThongServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeleteRole",
                    roleId:ids.join(',')
                },
                dataType: "json",
                success: function(data){
                    if(data.code == "0"){
                        reloadGridRole();
                        $.messager.alert('Thông báo',data.detail);
                        $( "#editdialog" ).dialog('close');
                    }else{
                        $.messager.alert('Thông báo',data.detail,'error');  
                    }
                            
                },
                error: function(data){
                    $.messager.alert('Thông báo','Mất kết nối tới server','error');  
                }
            });
        }
    });
}

function submitFormAddRoles(){
    var strName = $("#name").val();
    var strDesc = $("#descrition").val();
    if(strName == null || strName == ""){
        $.messager.alert('Thông báo','Bạn chưa điền tên vai trò. ','warning');  
        return;
    }
    $.ajax({
        url: requestUrl + "/QuanTriHeThongServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddRole",
            name:strName, 
            descrition:strDesc
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                reloadGridRole();
                $.messager.alert('Thông báo',data.detail);
                $( "#dialog" ).dialog('close');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: function(data){
            $.messager.alert('Thông báo','Mất kết nối tới server','error');  
        }
    });
}
function clearFormAddRole(){  
    $('#ff').form('clear');  
}  

function submitFormEditRole(){
    var strName = $("#nameEdit").val();
    var strDesc = $("#descritionEdit").val();
    var strRoleId = $("#roleIdEdit").val();
    if(strName == null || strName == ""){
        $.messager.alert('Thông báo','Bạn chưa điền tên vai trò. ','warning');  
        return;
    }
    $.ajax({
        url: requestUrl +"/QuanTriHeThongServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditRole", 
            roleId:strRoleId,
            name:strName, 
            descrition:strDesc
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                reloadGridRole();
                $.messager.alert('Thông báo',data.detail);
                $( "#editdialog" ).dialog('close');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: function(data){
            $.messager.alert('Thông báo','Mất kết nối tới server','error');  
        }
    });
}
function clearFormEditRole(){  
    $('#ffEdit').form('clear');  
}  