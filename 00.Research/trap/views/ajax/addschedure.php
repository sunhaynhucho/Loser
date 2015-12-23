<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<div style="padding:10px 60px 20px 60px">
    <table cellpadding="5">
        <tr>
            <td>Họ và tên:</td>
            <td><input class="easyui-textbox" type="text" id="txtHoVaTen"
                       name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input class="easyui-textbox" type="text" id="txtEmail"
                       name="email" data-options="required:true,validType:'email'"></input></td>
        </tr>
        <tr>
            <td>Điện thoại:</td>
            <td><input class="easyui-textbox" type="text" id="txtDienThoai"
                       name="subject" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input class="easyui-textbox" id="txtDiaChi"
                       name="message" ></input></td>
        </tr>
        <tr>
            <td>Ngày chơi:</td>
            <td><input class="easyui-datebox" id="txtNgayChoi"
                       data-options="formatter:myformatter,parser:myparser,prompt:'Ngày chơi'" style="width:110px">
            </td>
        </tr>
        <tr>
            <td>Giờ chơi:</td>
            <td>
                <select class="easyui-combobox" id="ccbGioChoi" name="language">

                    <?php foreach ($ltime as $o) {
                        ?>
                        <option value="<?php echo $o["time_id"]; ?>"><?php echo $o["time_title"]; ?></option>
                        <?php
                    }
                    ?>
                </select>
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
            </td>
        </tr>
        <tr>
            <td>Số người chơi:</td>
            <td>
                <input class="easyui-numberspinner" id="txtSoNguoiChoi" style="width:80px;" ></input>
            </td>
        </tr>
        <tr>
            <td>Trạng thái:</td>
            <td>
                <select class="easyui-combobox" id="ccbStatus" name="ccbTrangThai">
                    <option value="0">Chưa xác nhận</option>
                    <option value="1">Xác nhận OK</option>
                    <option value="2">Hủy lịch</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDatLich()">Xác nhận</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>