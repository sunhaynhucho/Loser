


$(function() {
    $('#dgList').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListSanPhamTheoGia'
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

function showUpDate(){
    var selected = $('#dgList').datagrid('getSelected');
    if(!selected){
        $.messager.alert('Thông báo','Bạn chưa chọn sản phẩm nào. ','warning');  
        return;
    }
    if(selected){
        $( "#dgEdit" ).dialog({
            autoOpen: false,
            title: 'Chỉnh sửa giá sản phẩm',  
            width: 500,
            cache:false,
            top:100,
            closed: false, 
            href: '../formdialog/frmdanhmuccuahang/frmupdategiasanpham.jsp?masp=' + selected.masp,  
            modal: true
        });
    }
}


function submitFormUpDatePrice(){
    var maSP = $( "#frmEditMaSP" ).val();
    var tenSP = $( "#frmEditTenSP" ).val();
    var maHangSX = $( "#frmEditMaHangSX" ).val();
    var maLoaiSP = $( "#frmEditMaLoaiSP" ).val();
    var giaBan1 = $( "#frmBanGia1" ).val();
    var giaBan2 = $( "#frmBanGia2" ).val();
    var giaBan3 = $( "#frmBanGia3" ).val();
    var giaBan4 = $( "#frmBanGia4" ).val();
    var giaNhap1 = $( "#frmNhapGia1" ).val();
    var giaNhap2 = $( "#frmNhapGia2" ).val();
    var giaNhap3 = $( "#frmNhapGia3" ).val();
    var giaNhap4 = $( "#frmNhapGia4" ).val();
    var donVi1 = $( "#frmDonVi1" ).val();
    var donVi2 = $( "#frmDonVi2" ).val();
    var donVi3 = $( "#frmDonVi3" ).val();
    var donVi4 = $( "#frmDonVi4" ).val();
    var soLuong2 = $( "#frmSoLuong2" ).val();
    var soLuong3 = $( "#frmSoLuong3" ).val();
    var soLuong4 = $( "#frmSoLuong4" ).val();
    var maNhaCC = $( "#frmMaNhaCC" ).val();
    
    if(maSP == null || maSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập mã sản phẩm ','warning');  
        return;
    }
    
    if(tenSP == null || tenSP == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập tên sản phẩm ','warning');  
        return;
    }
    
    if(giaBan1 != null && $.isNumeric(giaBan1) == false){
        $.messager.alert('Thông báo','Giá bán 1 phải là số ','warning');  
        return;
    }
    if(giaBan2 != null && $.isNumeric(giaBan2) == false){
        $.messager.alert('Thông báo','Giá bán 2 phải là số ','warning');  
        return;
    }
    if(giaBan3 != null && $.isNumeric(giaBan3) == false){
        $.messager.alert('Thông báo','Giá bán 3 phải là số ','warning');  
        return;
    }
    if(giaBan4 != null && $.isNumeric(giaBan4) == false){
        $.messager.alert('Thông báo','Giá bán 4 phải là số ','warning');  
        return;
    }
    if(giaNhap1 != null && $.isNumeric(giaNhap1) == false){
        $.messager.alert('Thông báo','Giá nhập 1 phải là số ','warning');  
        return;
    }
    if(giaNhap2 != null && $.isNumeric(giaNhap2) == false){
        $.messager.alert('Thông báo','Giá nhập 2 phải là số ','warning');  
        return;
    }
    if(giaNhap3 != null && $.isNumeric(giaNhap3) == false){
        $.messager.alert('Thông báo','Giá nhập 3 phải là số ','warning');  
        return;
    }
    if(giaNhap4 != null && $.isNumeric(giaNhap4) == false){
        $.messager.alert('Thông báo','Giá nhập 4 phải là số ','warning');  
        return;
    }
    if(soLuong2 != null && $.isNumeric(soLuong2) == false){
        $.messager.alert('Thông báo','Số lượng 2 phải là số ','warning');  
        return;
    }
    if(soLuong3 != null && $.isNumeric(soLuong3) == false){
        $.messager.alert('Thông báo','Số lượng 3 phải là số ','warning');  
        return;
    }
    if(soLuong4 != null && $.isNumeric(soLuong4) == false){
        $.messager.alert('Thông báo','Số lượng 4 phải là số ','warning');  
        return;
    }
    
    
    $.ajax({
        url: requestUrl +"/DanhMucCuaHangServices",
        type: "POST",
        cache: false,
        data: {
            options:"UpdateGiaSanPham",
            masp:maSP,
            tensp:tenSP,
            mahangsx:maHangSX,
            maloaisp:maLoaiSP,
            giaban1:giaBan1,
            giaban2:giaBan2,
            giaban3:giaBan3,
            giaban4:giaBan4,
            gianhap1:giaNhap1,
            gianhap2:giaNhap2,
            gianhap3:giaNhap3,
            gianhap4:giaNhap4,
            donvi1:donVi1,
            donvi2:donVi2,
            donvi3:donVi3,
            donvi4:donVi4,
            soluong2:soLuong2,
            soluong3:soLuong3,
            soluong4:soLuong4,
            manhacc:maNhaCC
            
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

function submitTimKiem(){
    var tenSP = $( "#txtTenSP" ).val();
    var maSP = $( "#txtMaSP" ).val();
    $('#dgList').datagrid('reload',{
        options:'GetListSanPhamTheoGia',
        tensp:tenSP,
        masp:maSP
    });
}
