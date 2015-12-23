function submitForm(){  
    $('#ff').form('submit', {  
        url: requestUrl + "/LoginServices",
        onSubmit: function(param){  
            param.options = 'Login';  
        },  
        success:function(data){
            var data = eval('(' + data + ')');
            if(data.code == 0){
                $('#loginMessage').html(data.detail);
                var url = requestUrl + "/view/";    
                $(location).attr('href',url);
            }else{
                $('#loginMessage').html(data.detail);
            }
        }  
    });   
}  
function clearForm(){  
    $('#ff').form('clear');  
}  