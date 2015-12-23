<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<?php
$appName = "";
$urlWeb = __SITE_URL;
$strName = "";
if ($_GET["rt"] != null) {
    $strName = explode('/', $_GET["rt"]);
}
if (isSet($_SESSION[__USER_NAME])) {
    ?>

    <input type="hidden" id="divName" value="<?php echo $strName[0]; ?>" />
    <script type="text/javascript">

        $(document).ready(function() {
            var divName = $("#divName").val();
            $('.toggle:not(.toggle-open)').addClass('toggle-closed').parents('li').children('ul').hide();
            if ($.browser != null) {
                if ($.browser.msie) {
                    $('#menu ul.navmenu li:last-child .menutop').css('border-bottom', '1px solid #CCC');
                }
            }

            $('.toggle').click(function() {
                if ($(this).hasClass('toggle-open')) {
                    $(this).removeClass('toggle-open').addClass('toggle-closed').empty('').append('+').parents('li').children('ul').slideUp(250);
                    $(this).parent('.menutop').removeClass('menutop-open').addClass('menutop-closed');
                } else {
                    $(this).parent('.menutop').removeClass('menutop-closed').addClass('menutop-open');
                    $(this).removeClass('toggle-closed').addClass('toggle-open').empty('').append('&ndash;').parents('li').children('ul').slideDown(250);
                }

            })
            if (divName != null || divName != "") {
                $("#" + divName).parent('.menutop').removeClass('menutop-closed').addClass('menutop-open');
                $("#" + divName).removeClass('toggle-closed').addClass('toggle-open').empty('').append('&ndash;').parents('li').children('ul').slideDown(250);
            }


        })
    </script>
    <?php
//$userName = $_SESSION["UserName"];
//List lPages = QuanTriHeThongDataLogic.getListPageByUserName(userName);
    $ttHome = true;

    $qthtHome = true;
    $qthtUser = true;
    $qthtUserRole = true;
    $qthtRole = true;
    $qthtRolePage = true;
    $qthtPage = true;
    $qthtUserPos = true;
    $qthtUserHistory = true;
    $qlcvHome = true;
    $qltlHome = true;
    $qltlDanhMuc = true;
    ?>

    <div id="menu">	
        <ul class="navmenu">
            <?php if ($ttHome) { ?>
                <li><div class="menutop"><a href='<?php echo $urlWeb; ?>/quantrihethong/home'>Trang chủ</a><div id="view" class="toggle">+</div></div>
                </li>
            <?php } ?>
            <?php if ($qlcvHome) { ?>
                <li><div class="menutop"><a href="<?php echo $urlWeb; ?>/cauhinhgame/home">Quản lý game</a><div id="cauhinhgame" class="toggle">+</div></div>
                    <?php if ($qlcvHome) { ?>
                        <ul class="submenu">
                            <li><a href="<?php echo $urlWeb; ?>/cauhinhgame/schedure">Quản lý lịch game</a></li>
                        </ul>
                    <?php }; ?>
                    <?php if ($qlcvHome) { ?>
                        <ul class="submenu">
                            <li><a href="<?php echo $urlWeb; ?>/cauhinhgame/gamesettings">Cấu hình games</a></li>
                        </ul>
                    <?php } ?>
                    <?php if ($qlcvHome) { ?>
                        <ul class="submenu">
                            <li><a href="<?php echo $urlWeb; ?>/cauhinhgame/blacklist">Danh sách đen</a></li>

                        </ul>
                    <?php } ?>
                    <?php if ($qlcvHome) { ?>
                        <ul class="submenu">
                            <li><a href="<?php echo $urlWeb; ?>/cauhinhgame/managerimagegame">Quản lý ảnh</a></li>
                        </ul>
                    <?php } ?>
                </li>
            <?php } ?>
            <?php if ($qthtHome) { ?>
                <li><div class="menutop"><a href="<?php echo $urlWeb; ?>/quantrihethong/home">Quản trị hệ thống</a><div id="quantrihethong" class="toggle">+</div></div>
                    <ul class="submenu">
                        <?php if ($qthtUser) { ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/users">Người dùng</a></li>
                        <?php } ?>
                        <?php if ($qthtUserRole) { ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/user_roles">Người dùng - Vai trò</a></li>
                        <?php }; ?>
                        <?php if ($qthtUserPos) { ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/user_position">Quản lý nhân viên</a></li>
                        <?php } ?>
                        <?php if ($qthtRole) { ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/roles">Vai trò</a></li>
                        <?php }; ?>
                        <?php
                        if ($qthtRolePage) {
                            ;
                            ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/role_pages">Vai trò - Chức năng</a></li>
                        <?php }; ?>
                        <?php if ($qthtPage) { ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/pages">Chức năng</a></li>
                        <?php } ?>
                        <?php
                        if ($qthtUserHistory) {
                            ;
                            ?>
                            <li><a href="<?php echo $urlWeb; ?>/quantrihethong/user_history">Lịch sử truy nhập web</a></li>
                <?php } ?>
                    </ul>
                </li>
    <?php } ?>
            <li><div class="menutop"><a href='<?php echo $urlWeb; ?>/login/logout'>Thoát</a><div id="view" class="toggle">+</div></div>
            </li>
        </ul>
    </div>
    <script type="text/javascript">

        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-2577834-5']);
        _gaq.push(['_setDomainName', 'mediaformations.com']);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = "<?php echo $urlWeb; ?>/js/ga.js";
            //        alert(ga.src);
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();

    </script>
<?php } ?>