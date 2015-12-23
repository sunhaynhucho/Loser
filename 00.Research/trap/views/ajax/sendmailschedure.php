<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<input type="hidden" id="mailId" value="<?php echo $lichgame["sc_id"];?>"/>
<div style="padding:10px 60px 20px 60px">
    <table cellpadding="5">
        <tr>
            <td>Gửi tới:</td>
            <td><input class="easyui-textbox" type="text" name="name" id="mailName"
                       value="<?php echo $lichgame["full_name"];?>"
                       data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input class="easyui-textbox" type="text" name="email" id="mailAddress" 
                       value="<?php echo $lichgame["email"];?>"
                       data-options="required:true,validType:'email'"></input></td>
        </tr>
        <tr>
            <td>Tiêu đề:</td>
            <td><input class="easyui-textbox" type="text" id="mailSubject"
                       value="<?php echo $lichgame["game_name"];?>"
                       name="subject" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Nội dung:</td>
            <td><input class="easyui-textbox" id="mailContent" name="message" 
                       value="Bạn đã đăng ký thành công game <?php echo $lichgame["game_name"];?>. 
                       Bạn sẽ chơi vào <?php echo $lichgame["ngay_choi"];?> <?php echo $lichgame["time_title"];?>. 
                       Mã vé của bạn là <?php echo $lichgame["ticket_code"];?>. 
                       Bạn đăng ký có <?php echo $lichgame["so_nguoi_choi"];?> người chơi"
                       data-options="multiline:true" style="height:60px"></input></td>
        </tr>
    </table>

    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormGuiMail()">Gửi</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>