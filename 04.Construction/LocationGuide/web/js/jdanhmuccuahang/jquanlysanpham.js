$(function() {
    $('#dgList').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListSanPham'
    });
})


function showAdd(){
    $( "#dgAdd" ).dialog({
        autoOpen: false,
        title: 'Thêm sản phẩm',  
        width: 400,
        cache:false,
        top:100,
        closed: false,
        href: requestUrl +'/formdialog/frmdanhmuccuahang/frmaddsanpham.jsp',  
        modal: true
    });
}

function showEdit(){
    var selected = $('#dgList').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn sản phẩm nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEdit" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa sản phẩm',  
            width: 400,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmeditsanpham.jsp?masp=' + selected.masp,  
            modal: true
        });
    }
}


function submitFormAdd(){
    var maSP = $( "#frmAddMaSP" ).val();
    var tenSP = $( "#frmAddTenSP" ).val();
    var maHangSX = $( "#frmAddMaHangSX" ).val();
    var maLoaiSP = $( "#frmAddMaLoaiSP" ).val();
    
    if(maSP == null || maSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập mã sản phẩm ','warning');  
        return;
    }
    
    if(tenSP == null || tenSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên sản phẩm ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddSanPham",
            masp:maSP,
            tensp:tenSP,
            mahangsx:maHangSX,
            maloaisp:maLoaiSP
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
    var maSP = $( "#frmEditMaSP" ).val();
    var tenSP = $( "#frmEditTenSP" ).val();
    var maHangSX = $( "#frmEditMaHangSX" ).val();
    var maLoaiSP = $( "#frmEditMaLoaiSP" ).val();
    
    if(maSP == null || maSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập mã sản phẩm ','warning');  
        return;
    }
    
    if(tenSP == null || tenSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên sản phẩm ','warning');  
        return;
    }
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditSanpham",
            masp:maSP,
            tensp:tenSP,
            mahangsx:maHangSX,
            maloaisp:maLoaiSP
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
        $.messager.alert('Thông báo','Bạn chưa chọn sản phẩm nào. ','warning');  
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa sản phẩm này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl +"/DanhMucCuaHangServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeleteSanPham",
                    masp:selected.masp
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
        options:'GetListSanPham',
        tensp:tenDangNhap
    });
}

function maSanPhamThayDoi(){
    var maSP = $( "#frmAddMaSP" ).val();
    checkMaSanPhamDaTonTai(maSP);
    
}

function setFocusText(){
    $( "#frmAddMaSP" ).focus();
}

function checkMaSanPhamDaTonTai(maSP){
//    var result = false;
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"KiemTraMaSanPhamDaTonTai",
            masp:maSP
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                $( "#frmAddMaSP" ).val("");
                $.messager.alert('Thông báo',data.detail,'info',setFocusText);
            }
        },
        error: ajaxFail
    });
//    return result;
}
