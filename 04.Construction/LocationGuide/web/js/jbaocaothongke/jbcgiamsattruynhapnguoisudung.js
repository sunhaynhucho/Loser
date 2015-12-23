
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return (d<10?('0'+d):d)+'/'+(m<10?('0'+m):m)+'/'+y;  
}  

function myparser(s){  
    if (!s) return new Date();  
    var ss = s.split('/');  
    var y = parseInt(ss[2],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[0],10);  
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
    var frmDenNgay = $('#txtToDate').datebox('getValue');
    var frmTrungTam = $('#cboTrungTam').val();
    var frmUserName = $('#txtUserName').val();
    var frmCongViec = $('#cboCongViec').val();
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return; 
    }
    if( frmDenNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày kết thúc!','warning'); 
        return;
    }
    
    if( myparser(frmTuNgay) > myparser(frmDenNgay) ) {
        $.messager.alert('Thông báo','Ngày bắt đầu phải nhỏ hơn hay bằng ngày kết thúc!','warning'); 
        return;
    }
  
    //    $.ajax({
    //        url: requestUrl +"/BaoCaoThongKeServices",
    //        type: "POST",
    //        cache: false,
    //        data: {
    //            options:"CheckSessionTimeOut"
    //        },
    //        dataType: "json",
    //        success: function(data){
    //            if(data.code == "3"){
    //                $.messager.alert('Thông báo',data.detail,'error');
    //                return;
    //            }
    //        },
    //        error: ajaxFail
    //    });
    
    
    $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...'  
    });
    
    
    
    $('#dgReport').datagrid('load',{
        options:'GiamSatTruyNhapNguoiSuDung',
        tungay:frmTuNgay,
        denngay:frmDenNgay,
        trungtam:frmTrungTam,
        username:frmUserName,
        conviec:frmCongViec
    });
    // get the pager of datagrid
    var pager = $('#dgReport').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageS){
            $('#dgReport').datagrid('reload',{
                options:'GiamSatTruyNhapNguoiSuDung',
                tungay:frmTuNgay,
                denngay:frmDenNgay,
                trungtam:frmTrungTam,
                username:frmUserName,
                conviec:frmCongViec,
                pagenumber: pageNum,
                pagesize: pageS
            });
        }
        
    });	
    $.messager.progress('close');
}

function exportExcelGiamSatTruyNhapNguoiSuDung(){
    var frmTuNgay = $('#txtFromDate').datebox('getValue');
    var frmDenNgay = $('#txtToDate').datebox('getValue');
    var frmTrungTam = $('#cboTrungTam').val();
    var frmUserName = $('#txtUserName').val();
    var frmCongViec = $('#cboCongViec').val();
    var frmFiel = $('#frmFileExport').combobox('getValues');
    
    if( frmTuNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày bắt đầu!','warning'); 
        return; 
    }
    if( frmDenNgay =="" ) {
        $.messager.alert('Thông báo','Bạn phải nhập giá trị cho ngày kết thúc!','warning'); 
        return;
    }
    
    if( myparser(frmTuNgay) > myparser(frmDenNgay) ) {
        $.messager.alert('Thông báo','Ngày bắt đầu phải nhỏ hơn hay bằng ngày kết thúc!','warning'); 
        return;
    }
    $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...'  
    });
    $('#frmExport').form('submit', {  
        url:requestUrl +"/ExportFileExcelServices",  
        onSubmit: function(param){  
            param.options="ExportGiamSatTruyNhapNguoiSuDung";
            param.tungay=frmTuNgay;
            param.denngay=frmDenNgay;
            param.trungtam=frmTrungTam;
            param.username=frmUserName;
            param.conviec=frmCongViec;
            param.fileexport=frmFiel.join(',');
        }  
    });
    $.messager.progress('close');
}