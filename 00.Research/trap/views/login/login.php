<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<script >
    var requestUrl = "<?php echo __SITE_URL; ?>";
    $(document).ready(function() {
        $(document).keyup(function(event) {
            if (event.keyCode == 13) {
                submitForm();
            }
        })
    });

</script>
<?php
$errCode = null;
if (isset($_GET["errorcode"])) {
    $errCode = $_GET["errorcode"];
}
$msgString = "";
if ($errCode != null && $errCode == "1") {
    $msgString = "Session của bạn đã hết hoặc bị người khác đăng nhập tại một nơi khác.";
}
?>
<div class="easyui-panel" title="Đăng nhập hệ thống" style="width:400px">  
    <div style="padding:10px 0 10px 60px">  
        <form id="ff">
            <table>  
                <tr>  
                    <td>Tên đăng nhập:</td>  
                    <td><input class="easyui-textbox" type="text" name="userName" id="userName" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38"></input></td>  
                </tr>  
                <tr>  
                    <td>Mật khẩu:</td>  
                    <td><input class="easyui-textbox" type="password" name="password" id="password" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38"></input></td>  
                </tr>
                <tr>  
                    <td colspan="2"><div id="loginMessage" style="color: red;text-align: center;"><?php echo $msgString; ?></div></td>  
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
        var userName = $("#userName").val();
        var userpass = $("#password").val();
        $.ajax({
            url: requestUrl + '/login/submitlogin',
            type: "POST",
            cache: false,
            dataType: "json",
            data: {
                username: userName,
                userpass: userpass
            },
            success: function(data) {
                if (data.code == 0) {
                    $('#loginMessage').html(data.detail);
                    var url = "<?php echo __SITE_URL; ?>/quantrihethong/home";
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