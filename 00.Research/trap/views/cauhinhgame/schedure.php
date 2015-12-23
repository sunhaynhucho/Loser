<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>
<script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/jcauhinhgame/jschedure.js"></script>
<table class="easyui-datagrid" title="Danh sách đăng ký chơi game" id="gSchedure" style="height: 550px;"
       pagination="true"
       data-options="singleSelect:true,nowrap:false,url:'<?php echo __SITE_URL; ?>/cauhinhgame/getschedure',
       toolbar:'#tb',nowrap:false">
    <thead>
        <tr>
            <th data-options="field:'sstatus',width:150">Trạng thái</th>
            <th data-options="field:'ssendemail',width:80">Đã gửi email</th>
            <th data-options="field:'pfullname',width:150">Họ và tên</th>
            <th data-options="field:'pmobile',width:100">Điện thoại</th>
            <th data-options="field:'pinviter',width:100">Giới thiệu</th>
            <th data-options="field:'pemail',width:150">Email</th>
            <th data-options="field:'gname',width:100">Game</th>
            <th data-options="field:'sday',width:100">Ngày chơi</th>
            <th data-options="field:'ttitle',width:100">Thời gian chơi</th>
            <th data-options="field:'gcode',width:100">Mã vé</th>
            <th data-options="field:'snumber',width:70">Số người</th>
            
        </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <input class="easyui-textbox" id="txtGameName" data-options="prompt:'Nhập ký tự tìm kiếm theo tên game...'" style="width:20%;">
    <select class="easyui-combobox" 
            id="ccbTrangThai"
            data-options="prompt:'Trạng thái đặt lịch'" name="state" style="width:150px;">
        <option value="-1">Trạng thái đặt lịch</option>
        <option value="0">Chưa xác nhận</option>
        <option value="1">Xác nhận OK</option>
        <option value="2">Đã hủy</option>
    </select>
    <input class="easyui-datebox" id="txtTuNgay" data-options="formatter:myformatter,parser:myparser,prompt:'Từ ngày'" style="width:110px">
    <input class="easyui-datebox" id="txtDenNgay" data-options="formatter:myformatter,parser:myparser,prompt:'Đến ngày'" style="width:110px">
    <a href="#" onclick="searchSchedure()" class="easyui-linkbutton" iconCls="icon-search">Tìm kiếm</a>
    <br>
    <a href="#" class="easyui-linkbutton" onclick="showConfirmSchedure()" iconCls="icon-process" plain="true">Xác nhận</a>
    <a href="#" class="easyui-linkbutton" onclick="showDatLich()" iconCls="icon-add" plain="true">Đặt lịch</a>
<!--    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Hủy lịch</a>-->
    <a href="#" class="easyui-linkbutton" onclick="showGuiMail()" iconCls="icon-email" plain="true">Gửi mail</a>
</div>

<div id="divDialog"></div>