function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
//    var d = date.getDate();  
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
    var frmLoaiBaoCao = $('#cboLoaiBaoCao').val();
    
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return;
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
        options:'BCTongHopTheoThang',
        tungay:frmTuNgay,
        nhamang:frmNhaMang,
        nguoigui:frmNguoiGui,
        loaibaocao:frmLoaiBaoCao
    });
    // get the pager of datagrid
    var pager = $('#dgReport').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageS){
            $('#dgReport').datagrid('reload',{
                options:'BCTongHopTheoThang',
                tungay:frmTuNgay,
                nhamang:frmNhaMang,
                nguoigui:frmNguoiGui,
                loaibaocao:frmLoaiBaoCao,
                pagenumber: pageNum,
                pagesize: pageS
            });
        }
        
    });	
    $.messager.progress('close');
}


function exportExcelBaoCao(){
    var frmTuNgay = $('#txtFromDate').datebox('getValue');
    var frmNhaMang = $('#cboNhaMang').val();
    var frmNguoiGui = $('#cboNguoiGui').val();
    var frmLoaiBaoCao = $('#cboLoaiBaoCao').val();
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return;
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
    $('#frmExport').form('submit', {  
        url:requestUrl +"/ExportFileExcelServices",  
        onSubmit: function(param){  
            param.options="ExportBCTongHopTheoThang";
            param.tungay=frmTuNgay;
            param.nhamang=frmNhaMang;
            param.nguoigui=frmNguoiGui;
            param.loaibaocao=frmLoaiBaoCao;
        }  
    });
    $.messager.progress('close');
}