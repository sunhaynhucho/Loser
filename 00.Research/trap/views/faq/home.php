<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<center>
    <div style="height: 110px;"></div>
    <div align="center" style="margin-left: 22px;height: auto; width: 1020px;">
        <table id="fullTalbe" width="100%" height="auto" border="0">
            <?php
            foreach ($oFAQ as $item) {
            ?>
            <tr>
                <td>
                    <br>
                    <span style="font-size: 17pt; font-weight: bold; color: #00a9ff;">
                        <?php echo $item["faq_answers"];?>
                    </span>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <span style="font-size: 15px; color: #9399A1;">
                        <?php echo $item["faq_questions"];?>
                    </span>
                </td>
            </tr>
            <?php } ?>
        </table>
    </div>
</center>
<div style="height: 50px"></div>