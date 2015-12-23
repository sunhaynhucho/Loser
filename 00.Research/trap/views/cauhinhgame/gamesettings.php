<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<style type="text/css">
    .c-label{
        display:inline-block;
        width:100px;
        font-weight:bold;
    }
</style>
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL;?>";
</script>
<script type="text/javascript" src="<?php echo __SITE_URL;?>/js/jcauhinhgame/jgamesettings.js"></script>
<table class="easyui-datagrid" title="Danh sách các game" id="gGame" style="height: 550px;width: 100%;"
       data-options="singleSelect:true,nowrap:false,url:'<?php echo __SITE_URL;?>/cauhinhgame/DanhSachGameServices',toolbar:'#tb'">
    <thead>
        <tr>
            <th data-options="field:'gid'">Game ID</th>
            <th data-options="field:'gname'">Tên hiển thị(EN)</th>
            <th data-options="field:'gnamevn'">Tên hiển thị(VN)</th>
            <th data-options="field:'gprice'">Giá</th>
            <th data-options="field:'gcontent'">Nội dung(EN)</th>
            <th data-options="field:'gcontentvn'">Nội dung(VN)</th>
            <th data-options="field:'gdiff'">Độ khó</th>
            <th data-options="field:'gnumber'">Team</th>
            <th data-options="field:'gtime'">Thời gian</th>
            <th data-options="field:'gcode'">Mã vé</th>
            <th data-options="field:'gpromotions'">Khuyến mãi(EN)</th>
            <th data-options="field:'gpromotionsvn'">Khuyến mãi(VN)</th>
        </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <input class="easyui-textbox" id="txtGameName" data-options="prompt:'Nhập ký tự tìm kiếm theo tên game...'" style="width:40%;">
    <a href="#" onclick="searchGames()" class="easyui-linkbutton" iconCls="icon-search">Tìm kiếm</a><br>
    <a href="#" class="easyui-linkbutton" onclick="showAddGame()" iconCls="icon-add">Thêm</a>
    <a href="#" class="easyui-linkbutton" onclick="showEditGame()" iconCls="icon-edit" plain="true">Sửa</a>
    <a href="#" class="easyui-linkbutton" onclick="deleteGame()" iconCls="icon-remove" plain="true">Xóa</a>
</div>
<div id ="dgDialog"></div>