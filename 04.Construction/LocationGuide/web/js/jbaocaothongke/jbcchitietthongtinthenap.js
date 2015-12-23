function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return (m<10?('0'+m):m)+'/'+y;  
}  

function myparser(s){  
    if (!s) return new Date();  
    var ss = s.split('/');  
    var y = parseInt(ss[2],10);  
    var m = parseInt(ss[1],10);  
    var d = 1;  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d);  
    } else {  
        return new Date();  
    }  
}

$(function(){
    $('#dgReport').datagrid({
        url:requestUrl+'/BaoCaoThongKeServices'
    });
    $('#dgReport').datagrid({
        rowStyler: function(index,row){
            if ((index % 2) > 0){
                return 'background-color:#eef3fc;';
            }
        }
    });
});


function submitTimKiem(){
    var frmTuNgay = $('#txtFromDate').datebox('getValue');
    var frmNhaMang = $('#cboNhaMang').val();
    var frmNguoiGui = $('#cboNguoiGui').val();
    var frmSoTheBao = $('#txtThueBao').val();
    var frmTrangThai = $('#cboTrangThai').val();
    
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return;
    }
    if(frmSoTheBao != null && frmSoTheBao != ""){
        if(!isPhoneVina(frmSoTheBao)){
            $.messager.alert('Thông báo','Số thuê bao ' + frmSoTheBao + ' này không đúng định dạng','warning');  
            return;
        }
    }
    
    $.ajax({
        url: requestUrl +"/BaoCaoThongKeServices",
        type: "POST",
        cache: false,
        data: {
            options:"CheckSessionTimeOut"
        },
        dataType: "json",
        success: function(data){
            if(data.code == "3"){
                $.messager.alert('Thông báo',data.detail,'error');
                return;
            }
        },
        error: ajaxFail
    });
    
    
    $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...'  
    });
    
    
    
    $('#dgReport').datagrid('load',{
        options:'ChiTietThongTinTheNap',
        tungay:frmTuNgay,
        nhamang:frmNhaMang,
        nguoigui:frmNguoiGui,
        sothuebao:frmSoTheBao,
        trangthai:frmTrangThai
    });
    //alert("Khong toi day");
    // get the pager of datagrid
    var pager = $('#dgReport').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageS){
            $('#dgReport').datagrid('reload',{
                options:'ChiTietThongTinTheNap',
                tungay:frmTuNgay,
                nhamang:frmNhaMang,
                nguoigui:frmNguoiGui,
                sothuebao:frmSoTheBao,
                trangthai:frmTrangThai,
                pagenumber: pageNum,
                pagesize: pageS
            });
        }
        
    });	
    $.messager.progress('close');
}

function exportExcelChiTietThongTinTheNap(){
    var frmTuNgay = $('#txtFromDate').datebox('getValue');
    var frmNhaMang = $('#cboNhaMang').val();
    var frmNguoiGui = $('#cboNguoiGui').val();
    var frmSoTheBao = $('#txtThueBao').val();
    var frmTrangThai = $('#cboTrangThai').val();
    var frmFiel = $('#frmFileExport').combobox('getValues');
    
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return;
    }
  
    if(frmSoTheBao != null && frmSoTheBao != ""){
        if(!isPhoneVina(frmSoTheBao)){
            $.messager.alert('Thông báo','Số thuê bao ' + frmSoTheBao + ' này không đúng định dạng','warning');  
            return;
        }
    }
    $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...'  
    });
    $('#frmExport').form('submit', {  
        url:requestUrl +"/ExportFileExcelServices",  
        onSubmit: function(param){  
            param.options="ExportChiTietThongTinTheNap";
            param.tungay=frmTuNgay;
            param.nhamang=frmNhaMang;
            param.nguoigui=frmNguoiGui;
            param.sothuebao=frmSoTheBao;
            param.trangthai=frmTrangThai;
            param.fileexport=frmFiel.join(',');
        }  
    });
    $.messager.progress('close');
}