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
        <link rel="stylesheet" type="text/css" href="<?php echo __SITE_URL; ?>/themes/css/default/easyui.css">  
        <link rel="stylesheet" type="text/css" href="<?php echo __SITE_URL; ?>/themes/css/icon.css">  
        <link rel="stylesheet" type="text/css" href="<?php echo __SITE_URL; ?>/themes/css/cssmenu.css">
        <link rel="stylesheet" type="text/css" href="<?php echo __SITE_URL; ?>/themes/css/main_admin.css">
        <script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/jquery.min.js"></script>  
        <script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/easyui-lang-vn.js"></script>
        <script type="text/javascript" src="<?php echo __SITE_URL; ?>/js/omapcommon.js"></script>
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
                    <a href="<?php echo __SITE_URL; ?>" id="rt-logo">
                    </a>
                    <span id="rt-logo-accent"></span>
                </div>
                <div id="divNav">
                    <nav id="topNav">
                        <ul>
                            <li><a href="<?php echo __SITE_URL; ?>" ><?php echo $la["HOME"]; ?></a></li>
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
                            <a href="?lang=en" >
                                <img src="<?php echo __SITE_URL; ?>/images/en.gif" alt="English (UK)" title="English (UK)">
                            </a>
                        </li>
                        <li>
                            <a href="?lang=vn" >
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
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                    <tr>
                        <td style="width: 150px;" valign="top">
                            <div style="height: 100px;"></div>
                            <?php include('a_menu.php'); ?>
                        </td>
                        <td><div style="width: 5px;"></div></td>
                        <td valign="top" align="left">
                            <div style="height: 100px;"></div>
                            <?php include($path); ?>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </body>
</html>