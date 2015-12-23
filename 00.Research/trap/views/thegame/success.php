<?php
$date = date_create($ngaydat);
?>

<div style="position: absolute;top: 245px;left: 266px;background-color: #096ac8;color: white;margin: auto;text-align: center;font-size: 14px; width: 60%;height: 30px;line-height: 30px;border: white solid 1px">
    <?php echo $la["SCHEDURE_SUCCESS"]; ?>
</div>
<div style="position: absolute;top: 335px;left: 266px;color: white;margin: auto;text-align: center;font-size: 14px; width: 60%;height: 100px;line-height: 30px">
    <?php echo $la["SCHEDURE_DATETIME"]; ?><?php echo date_format($date, "d-m-Y"); ?>, <?php echo $giodat; ?>
</div>
<div class="content-columns">
    <div id="wrap" style="margin: 0px">
        <div id="include" style="padding-bottom: 30px;">
            <div id="pageErrors" class="ui-state-error ui-corner-all"
                 style="display: none;">
                <table>
                    <tr>
                        <td style="padding: 5px; width: 1%">
                            <span class="ui-icon ui-icon-alert"></span>
                        </td>
                        <td class="messages">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>