/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();
    var h = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    return (d<10?('0'+d):d)+'/'+(m<10?('0'+m):m)+'/'+y + ' ' + (h<10?('0'+h):h) + ':' + (mm<10?('0'+mm):mm) + ':' + (s<10?('0'+s):s);  
}
function myparser(s){  
    if (!s) return new Date();  
    var sss = s.split(' ');  
    var dayMY = sss[0].split('/');
    var timeHM = sss[1].split(':');
    var y = parseInt(dayMY[2],10);  
    var m = parseInt(dayMY[1],10);  
    var d = parseInt(dayMY[0],10);  
    var h = parseInt(timeHM[0],10);  
    var mm = parseInt(timeHM[1],10);
    var s = parseInt(timeHM[2],10);
    //alert(sss + ':' + dayMY + '==>' + timeHM + "==>" +y+'-'+m+'-'+ d);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d,h,mm,s);  
    } else {  
        return new Date();  
    }  
}



function ThemCauHinhSMS(){
    var fileName = $("#frmFile").val();
    var soThueBao = $("#txtSoThueBao").val();
    var smsValues = $("#smsValues").val();
    var smsTime = $("#txtFromDate").datetimebox('getValue');
    if(soThueBao != null && soThueBao != ""){
        if(fileName != null && fileName != ""){
            $.messager.alert('Thông báo','Bạn không được nhập cả số điện thoại và file. Chỉ được phép chọn file hoặc điền số','warning');  
            return;
        }
        if(!isPhoneVina(soThueBao)){
            $.messager.alert('Thông báo','Số thuê bao ' + soThueBao + ' này không đúng định dạng','warning');  
            return ;
        }
    }
    
    if(smsValues == null || smsValues == ""){
        $.messager.alert('Thông báo','Bạn chưa nhập nội dung tin nhắn','warning');  
        return;
    }
    
    var currentDate = new Date();
    var khoangcach = myparser(smsTime) - currentDate;
    
    if( khoangcach > (3*86400 * 1000) || khoangcach < 0) {
        $.messager.alert('Thông báo','Thời gian gửi tin nhắn phải lớn hơn hiện tại và nhỏ hơn 3 ngày!','warning'); 
        return;
    }
    
    $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...',
        interval:1000         
    });
    $('#fff').submit();
}