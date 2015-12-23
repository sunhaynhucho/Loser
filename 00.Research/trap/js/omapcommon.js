var timeRefeshLoadData = 10000;

function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array  
        data = {  
            total: data.length,  
            rows: data  
        }  
    }  
    var dg = $(this);  
    var opts = dg.datagrid('options');  
    var pager = dg.datagrid('getPager');  
    pager.pagination({  
        onSelectPage:function(pageNum, pageSize){  
            opts.pageNumber = pageNum;  
            opts.pageSize = pageSize;  
            pager.pagination('refresh',{  
                pageNumber:pageNum,  
                pageSize:pageSize  
            });  
            dg.datagrid('loadData',data);  
        }  
    });  
    if (!data.originalRows){  
        data.originalRows = (data.rows);  
    }  
    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);  
    var end = start + parseInt(opts.pageSize);  
    data.rows = (data.originalRows.slice(start, end));  
    return data;  
}

function formatPhoneVina(strMsisdn){
    var sodienthoai="" + strMsisdn;
    if(strMsisdn.charAt(0) == "+" ){
        sodienthoai = strMsisdn.substr(1);
    }
    if(strMsisdn.charAt(0) == "0" ){
        sodienthoai = strMsisdn.substr(1);
    }
    if(sodienthoai.charAt(0) == "8" && sodienthoai.charAt(1) == "4"){
        sodienthoai = strMsisdn.substr(3);
    }
    return sodienthoai;
}

function isPhoneVina(strMsisdn){
    if(strMsisdn==""||strMsisdn==null){
        return false;
    }
    var sodienthoai="" + strMsisdn;	
    if(strMsisdn.charAt(0) == "+" ){
        sodienthoai = strMsisdn.substr(1);
    }
    if(strMsisdn.charAt(0) == "0" ){
        sodienthoai = strMsisdn.substr(1);
    }
    if(sodienthoai.charAt(0) == "8" && sodienthoai.charAt(1) == "4"){
        sodienthoai = strMsisdn.substr(3);
    }
    if(isNaN(sodienthoai)){
        return false;
    }
    if(sodienthoai.charAt(0) == "9" && sodienthoai.length == 9){
        return true;
    }
    if(sodienthoai.charAt(0) == "1" && sodienthoai.length == 10){
        return true;
    }
    return false;
//    if(strNumber.charAt(0) == "8" && strNumber.charAt(1) == "4"){
//        strNumber = strNumber.substr(2);
//    }
//    if (strNumber.charAt(0) == '9')
//    {
//        if (strNumber.charAt(1) == '0' || strNumber.charAt(1) == '3')
//            return true;
//    }
//    else if (strNumber.charAt(1) == '2' && strNumber.charAt(0) == '1') 
//    {
//        if (strNumber.charAt(2) == '0' || strNumber.charAt(2)  == '1' || strNumber.charAt(2) == '2' ||strNumber.charAt(2) == '6' || strNumber.charAt(2) == '8')
//            return true;
//    }

        
    return false;
        
}

function ajaxFail(data){
    $.messager.progress('close');
    $.messager.alert('Thông báo','Mất kết nối tới server','error');  
}