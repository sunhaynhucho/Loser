$(function() {
    $('#dgList').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListNhaCungCap'
    });
})


function showAdd(){
    $( "#dgAdd" ).dialog({
        autoOpen: false,
        title: 'Thêm nhà cung cấp',  
        width: 400,
        cache:false,
        top:100,
        closed: false,
        href: requestUrl +'/formdialog/frmdanhmuccuahang/frmaddnhacc.jsp',  
        modal: true
    });
}

function showEdit(){
    var selected = $('#dgList').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn nhà cung cấp nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEdit" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa nhà cung cấp',  
            width: 400,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmeditnhacc.jsp?manhacc=' + selected.manhacc,  
            modal: true
        });
    }
}


function submitFormAdd(){
    var tenNhaCC = $( "#frmAddTenNhaCC" ).val();
    var diaChi = $( "#frmAddDiaChi" ).val();
    var dienThoai = $( "#frmAddDienThoai" ).val();
    
    if(tenNhaCC == null || tenNhaCC == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên nhà cung cấp ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddNhaCungCap",
            tennhacc:tenNhaCC,
            diachi:diaChi,
            dienthoai:dienThoai
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgAdd" ).dialog('close');
                $('#dgList').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function submitFormEdit(){
    var maNhaCC = $( "#frmEditMaNhaCC" ).val();
    var tenNhaCC = $( "#frmEditTenNhaCC" ).val();
    var diaChi = $( "#frmEditDiaChi" ).val();
    var dienThoai = $( "#frmEditDienThoai" ).val();
    
    if(tenNhaCC == null || tenNhaCC == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên nhà cung cấp ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditNhaCungCap",
            manhacc:maNhaCC,
            tennhacc:tenNhaCC,
            diachi:diaChi,
            dienthoai:dienThoai
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgEdit" ).dialog('close');
                $('#dgList').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function submitFormDelete(){
    var selected = $('#dgList').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn nhà cung cấp nào. ','warning');  
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa nhà cung cấp này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl +"/DanhMucCuaHangServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeleteNhaCungCap",
                    manhacc:selected.manhacc
                },
                dataType: "json",
                success: function(data){
                    if(data.code == "0"){
                        $('#dgList').datagrid('reload');
                        $.messager.alert('Thông báo',data.detail);
                    }else{
                        $.messager.alert('Thông báo',data.detail,'error');  
                    }
                            
                },
                error: ajaxFail
            });
        }
    });
}


function submitTimKiem(){
    var tenDangNhap = $( "#txtUserName" ).val();
    $('#dgList').datagrid('reload',{
        options:'GetListNhaCungCap',
        tennhacc:tenDangNhap
    });
}
