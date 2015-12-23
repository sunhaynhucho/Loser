$(function() {
    $('#dgList').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListHangSanXuat'
    });
})


function showAdd(){
    $( "#dgAdd" ).dialog({
        autoOpen: false,
        title: 'Thêm hãng sản xuất',  
        width: 400,
        cache:false,
        top:100,
        closed: false,
        href: requestUrl +'/formdialog/frmdanhmuccuahang/frmaddhangsanxuat.jsp',  
        modal: true
    });
}

function showEdit(){
    var selected = $('#dgList').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn hãng sản xuất nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEdit" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa hãng sản xuất',  
            width: 400,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmedithangsanxuat.jsp?mahangsx=' + selected.mahangsx,  
            modal: true
        });
    }
}


function submitFormAdd(){
    
    var tenHangSanXuat = $( "#frmAddTenHangSanXuat" ).val();
    var diaChi = $( "#frmAddDiaChi" ).val();
    var dienThoai = $( "#frmAddDienThoai" ).val();
    
    if(tenHangSanXuat == null || tenHangSanXuat == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên hãng sản xuất ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddHangSanXuat",
            tenhangsanxuat:tenHangSanXuat,
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
    var maNhaCC = $( "#frmEditMaHangSX" ).val();
    var tenNhaCC = $( "#frmEditTenHangSanXuat" ).val();
    var diaChi = $( "#frmEditDiaChi" ).val();
    var dienThoai = $( "#frmEditDienThoai" ).val();
    
    if(tenNhaCC == null || tenNhaCC == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên hãng sản xuất ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditHangSanXuat",
            mahangsx:maNhaCC,
            tenhangsanxuat:tenNhaCC,
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
        $.messager.alert('Thông báo','Bạn chưa chọn hãng sản xuất nào. ','warning');  
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa hãng sản xuất này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl +"/DanhMucCuaHangServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeleteHangSanXuat",
                    mahangsx:selected.mahangsx
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
        options:'GetListHangSanXuat',
        tenhangsanxuat:tenDangNhap
    });
}
