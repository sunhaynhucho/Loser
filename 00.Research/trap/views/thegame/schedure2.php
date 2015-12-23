<link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/schedure2.css">
<link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/jquery-ui.css?87">
<script src="<?php echo __SITE_URL; ?>/js/jquery-ui.js?87"></script>

<script src="<?php echo __SITE_URL; ?>/js/jquery.easyui.min.js?87"></script>
<script src="<?php echo __SITE_URL; ?>/js/jcauhinhgame/jschedure.js"></script>
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>
<?php
$gameId = $_GET['gameId'];
$gameNgay = $_GET['gameNgay'];
$gameTime = $_GET['gameTime'];
$gameTitle = $_GET['title'];
?>


<div id="timeline" class="menu classic long-timeframe modern daily">

    <div id="step_title" class="step-title"><?php echo $la["SCHEDURE_YOURNAME2"]; ?></div>
    <div id="step_info_container">
        <div id="date_time_header" class="step-header" style="width: 185px;">
            <div class="step-title">
                <span class="step-number">1.</span>
                <span class="step-name"><?php echo $la["SCHEDURE_TIME"]; ?></span>
            </div>
            <div class="step-info" style="position: static;"><?php echo $ngayHT; ?></div>
        </div>
        <div id="client_info_header" class="step-header active" style="width: 185px;">
            <div class="step-title">
                <span class="step-number">2.</span>
                <span class="step-name"><?php echo $la["SCHEDURE_DETAILS"]; ?></span>
            </div>
            <div class="step-info"></div>
        </div>
    </div>
    <ul id="errors"></ul>
    <div id="client_info_step" class="step active" style="display: block;"><div class="step-content"><div class="client-info-step-form-container"><div id="client_form">
                    <div class="client_form_wrapper">
                        <form id="frm_submitDangky" name="frm_submitDangky" method="post" >

                            <input type="hidden" id="gameIdDatlich" name="gameIdDatlich" value="<?php echo $gameId; ?>" />
                            <input type="hidden" id="gameNgay" name="gameNgay" value="<?php echo $gameNgay; ?>" />
                            <input type="hidden" id="gameTime" name="gameTime" value="<?php echo $gameTime; ?>" />
                            <input type="hidden" id="gameTitle" name="gameTitle" value="<?php echo $gameTitle; ?>" />
                            <input type="hidden" id="gameLang" name="gameLang" value="<?php echo $lang; ?>" />
                            <input type="hidden" id="requestUrl" name="requestUrl" value="<?php echo __SITE_URL; ?>" />
                            <dl class="zend_form">

                                <dt><b><?php echo $la["SCHEDURE_FULLNAME"]; ?></b></dt>
                                <dd style="margin-bottom: 5px;">
                                    <input type="text" id="client_name" name="client_name" value="" onkeyup="if (isNaN(this.value)) {
        } else {
            this.value = null
        }
        ;">
                                </dd>

                                <dt>E-mail</dt>
                                <dd style="margin-bottom: 5px;">
                                    <input type="text" id="client_email" name="client_email" value="" onkeyup="if (isNaN(this.value)) {
        } else {
            this.value = null
        }
        ;">
                                </dd>

                                <dt><b><?php echo $la["SCHEDURE_PHONE"]; ?></b></dt>
                                <dd style="margin-bottom: 15px;">
                                    <div class="fixed_prefix"> (+84) </div><input style="width: 240px;height: 25px" type="email" id="client_phone_area_code1" name="client_phone_area_code1" value="" onkeyup="if (isNaN(this.value)) {
            this.value = null
        }
        ;">



                                    <div id="phone_description" style="width: 230px; clear: both;">
                                        <?php echo $la["SCHEDURE_MAQG"]; ?>
                                    </div>
                                </dd>
<!--                                <dt><b><?php echo $la["SCHEDURE_PRESSTER"]; ?></b></dt>-->
<!--                                <dd style="margin-bottom: 15px;">-->
<!--                                    <div class="fixed_prefix"> (+04) </div>-->
<input style="width: 240px;height: 25px" type="hidden" id="client_phone_area_code2" name="client_phone_area_code2" value="" onkeyup="if (isNaN(this.value)) {
            this.value = null
        }
        ;">
<!--                                    <div id="phone_description" style="width: 230px; clear: both;">
                                        <?php echo $la["SCHEDURE_MAQG"]; ?>
                                    </div>-->
<!--                                </dd>-->



<!--                                <div class="facebook-form-wrapper">
                                    <input type="hidden" id="token" name="token" value="">
                                    <a href="#" id="facebook_login"></a>
                                </div>-->
                            </dl>
                        </form>
                    </div>



                    <div id="fb-root" class=" fb_reset"><div style="position: absolute; top: -10000px; height: 0px; width: 0px;"><div><iframe name="fb_xdm_frame_http" frameborder="0" allowtransparency="true" allowfullscreen="true" scrolling="no" id="fb_xdm_frame_http" aria-hidden="true" title="Facebook Cross Domain Communication Frame" tabindex="-1" src="http://static.ak.facebook.com/connect/xd_arbiter/KvoNGODIqPG.js?version=41#channel=fa5fc76d&amp;origin=http%3A%2F%2Ftrap.simplybook.me" style="border: none;"></iframe><iframe name="fb_xdm_frame_https" frameborder="0" allowtransparency="true" allowfullscreen="true" scrolling="no" id="fb_xdm_frame_https" aria-hidden="true" title="Facebook Cross Domain Communication Frame" tabindex="-1" src="https://s-static.ak.facebook.com/connect/xd_arbiter/KvoNGODIqPG.js?version=41#channel=fa5fc76d&amp;origin=http%3A%2F%2Ftrap.simplybook.me" style="border: none;"></iframe></div></div><div style="position: absolute; top: -10000px; height: 0px; width: 0px;"><div></div></div></div>





                </div>

                <div class="license">
                    <div>
                        <?php echo $la["SCHEDURE_DIEUKHOAN"]; ?> <a href="/index/license/" style="color: #cccccc;">trap.vn</a>

                    </div>

                </div><div class="buttons_container"><div class="submit-button" id="submit_button" onclick="submitDangkyGame()"><?php echo $la["SCHEDULE"]; ?></div></div></div></div></div>
    <div id="info" class="bottom-info"></div>
    <div id="navigation" style="display: block;">
        <div id="back_button" class="back-button" onclick="javascript:window.location.href = '<?php echo __SITE_URL; ?>/thegame/schedure?gameId=<?php echo $gameId;?>'"><?php echo $la["SCHEDURE_BACK"]; ?></div></div>
    <div class="loader" style="display: none;"></div>
    <div style="height: 100px;"></div>
</div>
