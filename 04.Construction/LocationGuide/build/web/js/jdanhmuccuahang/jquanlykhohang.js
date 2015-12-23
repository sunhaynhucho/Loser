$(function() {
    $('#dgKhoHang').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListKhoHang'
    });
})


function showAddKho(){
    $( "#dgAddKho" ).dialog({
        autoOpen: false,
        title: 'Thêm kho hàng',  
        width: 400,
        cache:false,
        top:100,
        closed: false,
        href: requestUrl +'/formdialog/frmdanhmuccuahang/frmaddkho.jsp',  
        modal: true
    });
}

function showEditKho(){
    var selected = $('#dgKhoHang').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn người dùng nào nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEditKho" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa kho hàng',  
            width: 400,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmeditkho.jsp?makho=' + selected.makho,  
            modal: true
        });
    }
}


function submitFormAddKhoHang(){
    var tenKho = $( "#frmAddTenKho" ).val();
    var diaChi = $( "#frmAddDiaChi" ).val();
    var dienThoai = $( "#frmAddDienThoai" ).val();
    
    if(tenKho == null || tenKho == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên kho ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddKhoHang",
            tenkho:tenKho,
            diachi:diaChi,
            dienthoai:dienThoai
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgAddKho" ).dialog('close');
                $('#dgKhoHang').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function submitFormEditUser(){
    var maKho = $( "#frmEditMaKho" ).val();
    var tenKho = $( "#frmEditTenKho" ).val();
    var diaChi = $( "#frmEditDiaChi" ).val();
    var dienThoai = $( "#frmEditDienThoai" ).val();
    
    if(tenKho == null || tenKho == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên kho ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditKhoHang",
            makho:maKho,
            tenkho:tenKho,
            diachi:diaChi,
            dienthoai:dienThoai
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $.messager.alert('Thông báo',data.detail);
                $( "#dgEditKho" ).dialog('close');
                $('#dgKhoHang').datagrid('reload');
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
                            
        },
        error: ajaxFail
    });
}

function deleteKho(){
    var selected = $('#dgKhoHang').datagrid('getSelected');
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
                    options:"DeleteKhoHang",
                    makho:selected.makho
                },
                dataType: "json",
                success: function(data){
                    if(data.code == "0"){
                        $('#dgKhoHang').datagrid('reload');
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


function submitTimKiemKhoHang(){
    var tenDangNhap = $( "#txtUserName" ).val();
    $('#dgKhoHang').datagrid('reload',{
        options:'GetListKhoHang',
        tenkho:tenDangNhap
    });
}
