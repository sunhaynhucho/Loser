<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>
<center>
    <div align="center" style="margin-right: 5px;height: auto; width: 1110px;">
        <div style="height: 110px;"></div>

        <table id="fullTalbe" width="100%" height="500px" border="0" style="border-collapse: collapse">
            <tr style="background-color: #2A2A2A">
                <td width="80%" height="15%" style="border-bottom: #2A2A2A solid 2px">
                    <span style="  font-size: 24px;color: #fff;font-weight: 500;">
                        &nbsp;&nbsp;&nbsp;<?php echo $la["VOUCHER_TITLE"]; ?>
                    </span>
                </td>
            </tr>
            <tr style="background-color: #cccccc">
                <td valign="top" style="border-top: #cccccc solid 5px">
                    <br>
                    <table width="80%">
                        <tr>
                            <td style="height: 30px;width: 150px">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $la["VOUCHER_NAME"]; ?><span style="color: red">*</span> 
                            </td>
                            <td style="height: 30px">
                                <input type="text" id="namevocher" class="namevocher" 
                                       style="width: 300px;height: 23px" required="true" />
                            </td>
                        </tr>
                        <tr>
                            <td style="height: 30px">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;E-mail<span style="color: red">*</span>
                            </td>
                            <td style="height: 30px">
                                <input type="text" id="mailvocher" class="mailvocher" 
                                       style="width: 300px;height: 23px" required="true" />
                            </td>
                        </tr>
                        <tr>
                            <td style="height: 30px">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $la["VOUCHER_PHONE"]; ?><span style="color: red">*</span> 
                            </td>
                            <td style="height: 30px">
                                <input type="text" id="phonevocher" class="phonevocher" style="width: 300px;height: 23px" required="true" />
                            </td>
                        </tr>
                        <tr>
                            <td style="height: 30px">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $la["VOUCHER_GUEST"]; ?>
                                <span style="color: red">*</span>   
                            </td>
                            <td style="height: 30px">
                                <input type="text" id="guestsvocher" class="guestsvocher" placeholder="<?php echo $la["VOUCHER_PLAG"]; ?>" style="width: 300px;height: 23px" required="true" />
                            </td>
                        </tr>
                        <tr>
                            <td style="height: 30px">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $la["VOUCHER_MSG"]; ?>
                            </td>
                            <td style="height: 30px">
                                <textarea id="msgvocher" class="msgvocher" style="width: 298px;height: 150px" required="true"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td style="height: 30px">

                            </td>
                            <td style="height: 30px">
                                <input style="cursor: pointer;background-color: #999999;color: white;border: #cccccc solid 1px;height: 30px;width: 100px" 
                                       type="button" id="submitDangky" class="submitDangky" 
                                       value="<?php echo $la["VOUCHER_SUBMIT"]; ?>" onclick="submitVoucher()" />
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
        </table>

    </div>
</center>

<script type="text/javascript">
    function submitVoucher() {

        var nameVoucher = document.getElementById('namevocher').value;
        var mailVoucher = document.getElementById('mailvocher').value;
        var phoneVoucher = document.getElementById('phonevocher').value;
        var guestsVoucher = document.getElementById('guestsvocher').value;
        var msgVoucher = document.getElementById('msgvocher').value;

        if (nameVoucher.trim() == "") {
            document.getElementById('namevocher').focus();
            return;
        }
        if (mailVoucher.trim() == "") {
            document.getElementById('mailvocher').focus();
            return;
        }
        if (phoneVoucher.trim() == "") {
            document.getElementById('phonevocher').focus();
            return;
        }
        if (guestsVoucher.trim() == "") {
            document.getElementById('guestsvocher').focus();
            return;
        }

        $.ajax({
            url: requestUrl + '/voucher/submitresult',
            type: "POST",
            cache: false,
            dataType: "json",
            data: {
                options: "submitVoucher",
                nameVoucher: nameVoucher,
                mailVoucher: mailVoucher,
                phoneVoucher: phoneVoucher,
                guestsVoucher: guestsVoucher,
                msgVoucher: msgVoucher
            },
            success: function(data) {
                if (data.code == "0") {
                    location.href = requestUrl + "/voucher/successvoucher";
                } else {
                    alert(data.detail);
                }
            }
        });

    }
</script>