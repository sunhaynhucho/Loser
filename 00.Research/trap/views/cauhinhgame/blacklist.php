<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>
<script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/jcauhinhgame/jblacklist.js"></script>
<table class="easyui-datagrid" title="Danh sách đen" id="gSchedure" style="height: 550px;"
       pagination="true"
       data-options="singleSelect:true,nowrap:false,url:'<?php echo __SITE_URL; ?>/cauhinhgame/listblacklist',
       footer:'#ft',nowrap:false">
    <thead>
        <tr>
            <th data-options="field:'msisdn',width:200">Số điện thoại</th>
            <th data-options="field:'ghichu',width:400">Mô tả</th>
        </tr>
    </thead>
</table>