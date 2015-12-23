<?php
include 'common.php';
//echo $res;
require_once __SITE_PATH . '/model/dbaccess.class.php';
$sqlselect = 'cat_id,cat_url,cat_title title';
if ($lang == 'vn') {
    $sqlselect = 'cat_id,cat_url,cat_title_vn title';
}
$db = new dbaccess(DB_NAME, DB_HOST, DB_USER, DB_PASSWORD);
$db->connect();
$db->select('tbl_category', $sqlselect, 'cat_parent = 0 and status = 0', 'cat_seq');
$res = $db->getResult();
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="<?php echo __SITE_URL; ?>/images/icon.ico" />
        <link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/nav.css">
        <link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/main.css">
        <script src="<?php echo __SITE_URL; ?>/js/jquery.min.js"></script>
        <script src="<?php echo __SITE_URL; ?>/js/modernizr.js"></script>
        <title><?php echo $la["PAGE_TITLE"]; ?></title>
    </head>
    <body>
        <div id="divContainer">
            <div id="divHeader">
                <script>
                    var el = document.getElementsByTagName("body")[0];
                    el.className = "";
                </script>
                <noscript>
                <!--[if IE]>
                <link rel="stylesheet" href="css/ie.css">
            <![endif]-->
                </noscript>
                <div id="divLogo">
                    <a href="<?php echo __SITE_URL; ?>/" id="rt-logo">
                    </a>
                    <span id="rt-logo-accent"></span>
                </div>
                <div id="divNav">
                    <nav id="topNav">
                        <ul>
                            <li><a href="<?php echo __SITE_URL; ?>/" ><?php echo $la["HOME"]; ?></a></li>
                            <?php foreach ($res as $cat) { ?>
                                <li><a href="<?php echo $cat['cat_url']; ?>" ><?php echo $cat['title']; ?></a>
                                    <?php
                                    //echo 'catid:'.$cat['cat_id'];
                                    $db->select('tbl_category', $sqlselect, 'cat_parent = ' . $cat['cat_id'], 'cat_seq');
                                    $ressub = $db->getResult();
                                    if ($ressub != null) {
                                        ?><ul>
                                            <?php foreach ($ressub as $sub) { ?>
                                                <li><a href="<?php echo $sub['cat_url']; ?>" ><?php echo $sub['title']; ?></a></li>
                                            <?php } ?>
                                        </ul><?php
                                    }
                                    ?></li>
                            <?php } ?>
                        </ul>
                    </nav>
                </div>
                <div id="divLanguage">
                    <ul>
                        <li>
                            <a href="<?php echo __SITE_URL; ?>/?lang=en" >
                                <img src="<?php echo __SITE_URL; ?>/images/en.gif" alt="English (UK)" title="English (UK)">
                            </a>
                        </li>
                        <li>
                            <a href="<?php echo __SITE_URL; ?>/?lang=vn" >
                                <img src="<?php echo __SITE_URL; ?>/images/vi.png" style="height: 12;width: 18px;" alt="VietNam" title="VietNam">
                            </a>
                        </li>
                    </ul>
                </div>

                <script>
                    (function($) {

                        //cache nav
                        var nav = $("#topNav");

                        //add indicator and hovers to submenu parents
                        nav.find("li").each(function() {
                            if ($(this).find("ul").length > 0) {
                                $("<span>").appendTo($(this).children(":first"));

                                //show subnav on hover
                                $(this).mouseenter(function() {
                                    $(this).find("ul").stop(true, true).slideDown();
                                });

                                //hide submenus on exit
                                $(this).mouseleave(function() {
                                    $(this).find("ul").stop(true, true).slideUp();
                                });
                            }
                        });
                    })(jQuery);
                </script>
            </div>
            <div id="divContent">
                <?php include($path); ?>
            </div>
            <div id="divFooter">
                <div class="textlogo">
                    <p>
                        <a href="https://www.facebook.com/pages/Trap-Vietnam/447622452073869" target="_blank">
                            <img class="kispicture" onmouseover="this.src = '<?php echo __SITE_URL; ?>/images/likekispng.png';" onmouseout="this.src = '<?php echo __SITE_URL; ?>/images/facebook-like-512.png';" src="<?php echo __SITE_URL; ?>/images/facebook-like-512.png" alt="facebook-like-512">
                        </a>&nbsp; &nbsp;
                        <a href="http://www.tripadvisor.co.hu/Attraction_Review-g274887-d3822174-Reviews-TRAP_Team_Race_Against_Puzzles-Budapest_Central_Hungary.html" target="_blank"><img class="kispicture" onmouseover="this.src = '<?php echo __SITE_URL; ?>/images/tripadvisorkisl.png';" onmouseout="this.src = '<?php echo __SITE_URL; ?>/images/tripadvisor-xxl.png';" src="<?php echo __SITE_URL; ?>/images/tripadvisor-xxl.png" alt="tripadvisor-xxl">
                        </a>&nbsp;&nbsp;
                        <a href="https://www.youtube.com/watch?v=HZTHCpSan40" target="_blank"><img class="kispicture" onmouseover="this.src = '<?php echo __SITE_URL; ?>/images/youtubekispng.png';" onmouseout="this.src = '<?php echo __SITE_URL; ?>/images/youtube-3-256.png';" src="<?php echo __SITE_URL; ?>/images/youtube-3-256.png" alt="youtube-3-256">
                        </a>
                        <span class="textlogo2" style="color: #ffffff;">© 2014 TRAP. All rights reserved.</span>
                        <span class="textlogo2" style="margin-left: 42%;">
                            <span style="color: #ffffff;"><?php echo $la["CREATED_BY"]; ?></span> 
                            <a href="http://webdesignbinghamtom.com/" target="_blank">
                                <span style="color: #999999;">Galocaffe Reklam Agency</span>
                            </a>
                        </span>
                </div>

            </div>
        </div>
        <div class="bookimage">
            <p><a href="<?php echo __SITE_URL; ?>/#exampleBookNow"><img style="z-index: 99999" src="<?php echo __SITE_URL; ?>/images/<?php echo $la["URL_IMAGE_BOOKNOW"]; ?>" /></a></p>
        </div>
        <div class="aboutimage">
            <p><a href="<?php echo __SITE_URL; ?>/#exampleAbout"><img style="z-index: 99999" src="<?php echo __SITE_URL; ?>/images/<?php echo $la["URL_IMAGE_ABOUT"]; ?>" /></a></p>
        </div>
    </body>
</html>