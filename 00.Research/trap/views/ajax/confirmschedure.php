<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<div style="padding:10px 60px 20px 60px">
    <input type="hidden" id="txtSid" value="<?php echo $sc["sc_id"];?>" />
    <input type="hidden" id="txtUserName" value="<?php echo $sc["username"];?>" />
    <table cellpadding="5">
        <tr>
            <td>Họ và tên:</td>
            <td><input class="easyui-textbox" type="text" id="txtHoVaTen"
                       value="<?php echo $sc["full_name"];?>"
                       name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input class="easyui-textbox" type="text" id="txtEmail"
                       value="<?php echo $sc["email"];?>"
                       name="email" data-options="required:true,validType:'email'"></input></td>
        </tr>
        <tr>
            <td>Điện thoại:</td>
            <td><input class="easyui-textbox" type="text" id="txtDienThoai"
                       value="<?php echo $sc["mobile"];?>"
                       name="subject" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input class="easyui-textbox" id="txtDiaChi"
                       value="<?php echo $sc["address"];?>"
                       name="message" ></input></td>
        </tr>
        <tr>
            <td>Ngày chơi:</td>
            <td><input class="easyui-datebox" id="txtNgayChoi"
                       value="<?php echo $sc["ngay_choi"];?>"
                       data-options="formatter:myformatter,parser:myparser,prompt:'Ngày chơi'" style="width:110px">
                <input type="hidden" id="txtNgayChoiOld" value="<?php echo $sc["ngay_choi"];?>"/>
            </td>
        </tr>
        <tr>
            <td>Giờ chơi:</td>
            <td>
                <input type="hidden" id="txtGioChoiOld" value="<?php echo $sc["time_id"];?>"/>
                <select class="easyui-combobox" id="ccbGioChoi" name="language">

                    <?php foreach ($ltime as $o) {
                        ?>
                        <option value="<?php echo $o["time_id"]; ?>"><?php echo $o["time_title"]; ?></option>
                        <?php
                    }
                    ?>
                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbGioChoi").length; i++) {
                        if (document.getElementById("ccbGioChoi").options[i].value == '<?php echo $sc["time_id"];?>') {
                            document.getElementById("ccbGioChoi").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
        <tr>
            <td>Game:</td>
            <td>
                <select class="easyui-combobox" id="ccbGame" name="language" style="width: 250px;">

                    <?php foreach ($lgame as $o) {
                        ?>
                        <option value="<?php echo $o["game_id"]; ?>"><?php echo $o["game_name"]; ?></option>
                        <?php
                    }
                    ?>
                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbGame").length; i++) {
                        if (document.getElementById("ccbGame").options[i].value == '<?php echo $sc["game_id"];?>') {
                            document.getElementById("ccbGame").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
        <tr>
            <td>Số người chơi:</td>
            <td>
                <input class="easyui-numberspinner" id="txtSoNguoiChoi" style="width:80px;" 
                       value="<?php echo $sc["so_nguoi_choi"];?>"></input>
            </td>
        </tr>
        <tr>
            <td>Trạng thái:</td>
            <td>
                <select class="easyui-combobox" id="ccbStatus" name="ccbTrangThai">
                    <option value="0">Chưa xác nhận</option>
                    <option value="1">Xác nhận OK</option>
                    <option value="2">Hủy lịch</option>
                    <option value="3">Black list</option>
                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbStatus").length; i++) {
                        if (document.getElementById("ccbStatus").options[i].value == '<?php echo $sc["status"];?>') {
                            document.getElementById("ccbStatus").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
    </table>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitXacNhan()">Xác nhận</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>