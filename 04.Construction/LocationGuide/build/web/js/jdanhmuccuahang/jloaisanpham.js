$(function() {
    $('#dgLoaiSP').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListLoaiSanPham'
    });
})


function showAdd(){
    $( "#dgAdd" ).dialog({
        autoOpen: false,
        title: 'Thêm loại sản phẩm',  
        width: 400,
        cache:false,
        top:100,
        closed: false,
        href: requestUrl +'/formdialog/frmdanhmuccuahang/frmaddloaisp.jsp',  
        modal: true
    });
}

function showEdit(){
    var selected = $('#dgLoaiSP').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn người dùng nào nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEdit" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa loại sản phẩm',  
            width: 400,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmeditloaisp.jsp?maloaisp=' + selected.maloaisp,  
            modal: true
        });
    }
}


function submitFormAdd(){
    var tenKho = $( "#frmAddTenLoaiSanPham" ).val();
    
    if(tenKho == null || tenKho == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên loại sản phẩm ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddLoaiSanPham",
            tenloaisp:tenKho
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgAdd" ).dialog('close');
                $('#dgLoaiSP').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function submitFormEdit(){
    var maKho = $( "#frmEditMaLoaiSanPham" ).val();
    var tenKho = $( "#frmEditTenLoaiSanPham" ).val();
    
    if(tenKho == null || tenKho == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên kho ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditLoaiSanPham",
            maloaisp:maKho,
            tenloaisp:tenKho
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgEdit" ).dialog('close');
                $('#dgLoaiSP').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function submitFormDelete(){
    var selected = $('#dgLoaiSP').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn người dùng nào. ','warning');  
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa kho hàng này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl +"/DanhMucCuaHangServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeleteLoaiSanPham",
                    maloaisp:selected.maloaisp
                },
                dataType: "json",
                success: function(data){
                    if(data.code == "0"){
                        $('#dgLoaiSP').datagrid('reload');
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
    $('#dgLoaiSP').datagrid('reload',{
        options:'GetListLoaiSanPham',
        tenloaisp:tenDangNhap
    });
}
