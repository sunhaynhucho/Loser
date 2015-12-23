<%-- 
    Document   : login
    Created on : Jan 20, 2013, 4:58:29 PM
    Author     : KiemPQ-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script >
    $(document).ready(function() {
        $(document).keyup(function(event) {
            if (event.keyCode == 13) {
                submitForm();
            }
        })
    });

</script>
<%
    String errCode = request.getParameter("errorcode");
    String msgString = "";
    if (errCode != null && errCode.equals("1")) {
        msgString = "Session của bạn đã hết hoặc bị người khác đăng nhập tại một nơi khác.";
    }
%>

<div class="easyui-panel" title="Đăng nhập hệ thống" style="width:400px">  
    <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post">  
            <table>  
                <tr>  
                    <td>Tên đăng nhập:</td>  
                    <td><input class="easyui-validatebox" type="text" name="userName" id="userName" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>Mật khẩu:</td>  
                    <td><input class="easyui-validatebox" type="password" name="password" id="password" data-options="required:true,validType:'passwork'"></input></td>  
                </tr>
                <tr>  
                    <td colspan="2"><div id="loginMessage" style="color: red;text-align: center;"><%=msgString%></div></td>  
                </tr>
                <tr>  
                    <td colspan="2" style="text-align: center;" ><div style="text-align:center;padding:5px">  
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Đăng nhập</a>  
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Nhập lại</a>  
                        </div></td>  
                </tr>
            </table>  
        </form>  
    </div> 


</div>  
<script>
    function submitForm() {
        $('#ff').form('submit', {
            url: "<%=request.getContextPath()%>/LoginServices",
            onSubmit: function(param) {
                param.options = 'Login';
            },
            success: function(data) {
                var data = eval('(' + data + ')');
                if (data.code == 0) {
                    $('#loginMessage').html(data.detail);
                    var url = "<%=request.getContextPath()%>/view/";
                    $(location).attr('href', url);
                } else {
                    $('#loginMessage').html(data.detail);
                }
            }
        });
    }
    function clearForm() {
        $('#ff').form('clear');
    }
</script>  