<script src="<?php echo __SITE_URL; ?>/js/jquery-ui.js"></script>
<link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/tooltipster.css">
<script type="text/javascript">
    $(function() {
        $(document).tooltip({
            track: true,
            html: true,
            content: function() {
//                var element = $(this);
                $.ajax({
                    type: 'POST',
                    url: '<?php echo __SITE_URL; ?>/formdialog/tooltipgameinfo',
                    success: function(data) {
                        return data;
                    }
                });
//                return element;
            }

        });
    });
</script>
<div style="height: 50px"></div>
<div class="fchworldh1">&nbsp;</div>
<!--<div class="fchworld">
    <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
    <span style="color: #ffffff;"><a href="http://trap.hu/" target="_blank">
            <span style="color: #ffffff;">Budapest</span></a></span><br>
    <span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trapberlin.com/" target="_blank">
            <span style="color: #ffffff;">Berlin</span>
        </a>
    </span><br><span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trapprague.com/index.php/cs/" target="_blank">
            <span style="color: #ffffff;">Prague</span></a></span><br>
    <span style="color: #ffffff;">
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trapkonstanz.com/index.php/de/" target="_blank">
            <span style="color: #ffffff;">Konstanz</span></a></span><br>
    <span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trap.rs/home/" target="_blank">
            <span style="color: #ffffff;">Belgrade</span></a></span><br>
    <span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://www.happyhourescapegame.com/" target="_blank">
            <span style="color: #ffffff;">Paris</span></a></span><br>
    <span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trapkrakow.com/index.php/pl/" target="_blank">
            <span style="color: #ffffff;">Krakow</span></a></span><br>
    <span style="color: #ffffff;"> 
        <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        <a href="http://trapathens.com/" target="_blank">
            <span style="color: #ffffff;">Athens</span></a></span><br>
    <a href="http://trapmilan.com/" target="_blank"> 
        <span style="color: #ffffff;"> 
            <img class="kistrap" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
            <span style="color: #ffffff;">Milan&nbsp;</span></span></a><br>
    <img class="kistrap" style="color: #ffffff;" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
    <span style="color: #ffffff;"><a href="http://trapbogota.com/" target="_blank">
            <span style="color: #ffffff;">Bogota</span></a></span>
    <span style="color: #ffffff;"><a href="http://trapbogota.com/" target="_blank"><br>
            <img class="kistrap" style="color: #ffffff;" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2"></a>
    </span><span style="color: #ffffff;">
        <a href="http://traprome.com/" target="_blank">
            <span style="color: #ffffff;">R</span></a>
        <a href="http://traprome.com/" target="_blank">
            <span style="color: #ffffff;">ome</span></a>&nbsp;(coming soon)</span>
    <span style="color: #ffffff;"><a href="http://traprome.com/" target="_blank"><br></a>
        <a href="http://trapbogota.com/" target="_blank">
            <img class="kistrap" style="color: #ffffff;" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        </a>
    </span>
    <span style="color: #ffffff;">
        <a href="http://trap4istanbul.com/" target="_blank">
            <span style="color: #ffffff;">Istanbul</span>
        </a>&nbsp;(Coming soon)
    </span>

    <span style="color: #ffffff;"><a href="http://trap.vn/" target="_blank"><br></a>
        <a href="http://trap.vn/" target="_blank">
            <img class="kistrap" style="color: #ffffff;" src="<?php echo __SITE_URL; ?>/images/trapicon2.png" alt="trapicon2">
        </a>
    </span>
    <span style="color: #ffffff;">
        <a href="http://trap.vn/" target="_blank">
            <span style="color: #ffffff;">Viet Nam</span>
        </a>
    </span>
    <a href="http://trapbogota.com/" target="_blank"><br></a>
</div>-->
<div id="divNavEmap">
    <img src="<?php echo __SITE_URL; ?>/images/europemap.png">
</div>    
<div id="divNavEmap2">
    <img src="<?php echo __SITE_URL; ?>/images/traplogonagy.png">
</div>    
<div style="height: 50px"></div>
<div class="bigestescae"><?php echo $la["EUROPE"]; ?></div>
<div style="height: 50px"></div>
<div style="height: 470px"></div>
<a id="exampleBookNow"></a>
<div style="height: 80px"></div>
<style>
    .prod_url {
        /*width: 100px;*/
        float: left;
    }
    .urllink {
        text-align: left;
        float: left;
        background: #00a8f3;
        padding: 10px;
        color: #ffffff;
        border-radius: 3px;
        text-transform: uppercase;
        padding-left: 15px;
        padding-right: 15px;
        text-decoration: none;
    }
</style>
<center>
    <div align="center" style="height: auto; width: 1020px;">

        <table width="100%" height="1px" border="0" style="margin-right: 30px">
            <tr>
                <td style="width: 200px;background-color: #fff"></td>
            </tr>
        </table>
        <table width="92%" height="300px" border="0">
            <tr>
                <td colspan="2" style="height: 50px"></td>
            </tr>
            <tr>
                <td width="50%" valign="top" align="center">
                    <?php
                    $count1 = 0;
                    foreach ($gameInfo as $value) {
                        $count1++;
                        if ($count1 % 2 != 0) {
                            $sao = $value['game_difficulty'];
                            ?>
                            <table width="66%" height="auto" cellpadding="0" cellspacing="0" border="0" style="background-image: url('<?php echo __SITE_URL; ?>/images/bg-item2.png');background-repeat:repeat;">
                                <tr>
                                    <td colspan="3" align="center" valign="bottom">
                                        <img src="<?php echo __SITE_URL; ?>/images/imgGame/<?php echo $value['image_url']; ?>" class="tooltip" title="Hello thar, mate!" width="302px" height="330px" style="border: 15px solid black;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="padding-left: 15px;padding-top: 15px;">
                                        <span style="color: #fff;font-size: 25px;font-variant: caps"><?php echo $value['gameName']; ?></span> 
                                    </td>
                                </tr>
                                <tr>
                                    <td width="73" height="31" style="padding-left: 15px;">
                                        <span style="color: #00a8f3"><?php echo $la["DIFFICULT"]; ?></span> 
                                    </td>
                                    <td colspan="2">
                                        <?php for ($k = 0; $k < $sao; $k++) { ?> 
                                            <img src="<?php echo __SITE_URL; ?>/images/trapicon2.png" width="16px" height="16px" />   
                                        <?php } ?>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td width="140" colspan="3" style="padding-left: 15px;padding-bottom: 15px;">
                                        <div class="prod_url">
                                            <a class="urllink" target="_blank" href="<?php echo __SITE_URL; ?>/thegame/schedure?gameId=<?php echo $value['game_id']; ?>"><?php echo $la["SCHEDULE"]; ?></a>
                                        </div>
                                    </td>
                                </tr>

                            </table>
                            <div style="height: 30px"></div>
                            <?php
                        }
                    }
                    ?>
                </td>
                <td valign="top" align="center">
                    <?php
                    $count2 = 0;
                    foreach ($gameInfo as $value) {
                        $count2++;
                        if ($count2 % 2 == 0) {
                            $sao = $value['game_difficulty'];
                            ?>

                            <table width="66%" height="auto" cellpadding="0" cellspacing="0" border="0" style="background-image: url('<?php echo __SITE_URL; ?>/images/bg-item2.png');background-repeat:repeat;">
                                <tr>
                                    <td colspan="3" align="center" valign="bottom">
                                        <img src="<?php echo __SITE_URL; ?>/images/imgGame/<?php echo $value['image_url']; ?>" width="302px" height="330px" style="border: 15px solid black;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="padding-left: 15px;padding-top: 15px;">
                                        <span style="color: #fff;font-size: 25px;font-variant: caps"><?php echo $value['gameName']; ?></span> 
                                    </td>
                                </tr>
                                <tr>
                                    <td width="73" height="31" style="padding-left: 15px;">
                                        <span style="color: #00a8f3"><?php echo $la["DIFFICULT"]; ?></span> 
                                    </td>
                                    <td colspan="2">
                                        <?php for ($k = 0; $k < $sao; $k++) { ?> 
                                            <img src="<?php echo __SITE_URL; ?>/images/trapicon2.png" width="16px" height="16px" />   
                                        <?php } ?>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td width="140" colspan="3" style="padding-left: 15px;padding-bottom: 15px;">
                                        <div class="prod_url">
                                            <a class="urllink" target="_blank" href="<?php echo __SITE_URL; ?>/thegame/schedure?gameId=<?php echo $value['game_id']; ?>"><?php echo $la["SCHEDULE"]; ?></a>
                                        </div>
                                    </td>
                                </tr>

                            </table>
                            <div style="height: 30px"></div>
                            <?php
                        }
                    }
                    ?>
                </td>
            </tr>
        </table>
    </div>


    <a id= "exampleAbout"></a>
    <div style="height: 50px"></div>
    <div style="height: auto; width: 1020px;">

        <table width="100%" height="300px" border="0">
            <tr>
                <td width="100%" colspan="2" style="background-color: #fff;"></td>
            </tr>  
            <tr>
                <td width="100%" height="50px" colspan="2"></td>
            </tr>  
            <tr>
                <td colspan="2" height=10px"></td>
            </tr>
            <tr>
                <td width="50%">
                    <iframe id="videoPlayGame" style="border: #000 solid 0px" width="560" height="345" src="" frameborder="0" allowfullscreen></iframe>
                </td>
                <td rowspan="2" align="left" valign="top">
                    <table border="0" width ="100%" height="100%">
                        <tr>
                            <td colspan="2" valign="top">
                                <span id="videoTitle" style="color: #00a9ff;  font-weight: 900; font-size: 30px;font-variant: caps"></span>
                                <br>
                                <br>
                                <span id="videoContent" style="color: white;font-size: 16px;"></span>
                                <br>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <br><br>
                    <?php
                    $count3 = 0;
                    foreach ($playVideo as $play) {
                        $count3++;
                        ?>

                        <img id="<?php echo $play['video_url']; ?>@@<?php echo $count3 ?>" src="<?php echo $play['video_url_image']; ?>" onclick="clickPlayVideo(this.id)" alt="Mountain View" style="width:100px;height:60px;cursor: pointer" />
                        <input type="hidden" id="title<?php echo $count3; ?>" value="<?php echo $play['videoTittle']; ?>" >
                        <input type="hidden" id="content<?php echo $count3; ?>" value="<?php echo $play['videoContent']; ?>" >
                        <input type="hidden" id="videoStart<?php echo $count3; ?>" value="<?php echo $play['video_url']; ?>" >
                    <?php } ?>
                </td>
            </tr>
            <tr>
                <td colspan="2" height=100px"></td>
            </tr>
            <tr>
                <td colspan="2" style="background-color: #fff;" ></td>
            </tr>
        </table>
    </div>

    <div align="center" style="margin-left: 0px;height: auto; width: 1020px;">
        <table id="fullTalbecontact" width="80%" height="350px" border="0">
            <tr>
                <td colspan="2" height="20px">
                </td>
            </tr>
            <tr>
                <td width="50%" height="30%" valign="top">
                    <table>
                        <tr>
                            <td>
                                <span style="font-size: 32px; font-weight: bold; color: #00a9ff">
                                    <?php echo $la["CONTACT_LOCATION"]; ?>
                                </span>
                                <br>
                                <span style="font-size: 22px;">
                                    <?php echo $la["CONTACT_ADDRESS"]; ?><br>
                                    trap.escape.vn@gmail.com<br>
                                    (+04) 9888888888<br>
                                </span>
                            </td>
                        </tr>
                    </table>    
                </td>
                <td valign="top">
                    <table>
                        <tr>
                            <td>
                                <span style="font-size: 32px; font-weight: bold; color: #00a9ff">
                                    <?php echo $la["CONTACT_OPENING"]; ?>
                                </span>
                                <br>
                                <span style="font-size: 22px;"><?php echo $la["CONTACT_HOUR"]; ?></span>
                                <br><br>

                            </td>
                        </tr>
                    </table>  
                </td>
            </tr>
            <tr>
                <td colspan="2" height="20px">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <span style="font-size: 24px; font-weight: bold; color: #00a9ff">
                        <?php echo $la["CONTACT_DIACHIBANDO"]; ?>
                    </span>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <iframe src="<?php echo $la["CONTACT_URLMAP"]; ?>" width="100%" height="330" frameborder="0" style="border:0"></iframe>
                </td>
            </tr>
        </table>
        <br><br><br>
        <table width="100%" height="1px" border="0" style="margin-right: 30px">
            <tr>
                <td style="width: 200px;background-color: #fff;"></td>
            </tr>
        </table>
    </div>
    <p style="color:#fff"><?php echo $la["HOME_LIKE_FACEBOOK_TITLE"]; ?>  </p>
    <div id="facebook">
        <div class="fb-page fb_iframe_widget" data-href="https://www.facebook.com/pages/Trap-Vietnam/447622452073869" data-width="1000" data-hide-cover="false" data-show-facepile="true" data-show-posts="false" fb-xfbml-state="rendered" fb-iframe-plugin-query="app_id=414540278703945&amp;container_width=930&amp;hide_cover=false&amp;href=https://www.facebook.com/pages/Trap-Vietnam/447622452073869&amp;locale=en_GB&amp;sdk=joey&amp;show_facepile=true&amp;show_posts=false&amp;width=1000">
            <span style="vertical-align: bottom; width: 500px; height: 224px;">
                <iframe name="f11a144544" width="1000px" height="1000px" frameborder="0" allowtransparency="true" allowfullscreen="true" scrolling="no" title="fb:page Facebook Social Plugin" src="http://www.facebook.com/v2.3/plugins/page.php?app_id=414540278703945&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FNM7BtzAR8RM.js%3Fversion%3D41%23cb%3Df25b0107fc%26domain%3Dwww.weescape.vn%26origin%3Dhttp%253A%252F%252Fwww.trap.vn%252Ff28fe7b1b4%26relation%3Dparent.parent&amp;container_width=930&amp;hide_cover=false&amp;href=https://www.facebook.com/pages/Trap-Vietnam/447622452073869&amp;locale=en_GB&amp;sdk=joey&amp;show_facepile=true&amp;show_posts=false&amp;width=1000" style="border: none; visibility: visible; width: 500px; height: 224px;" class="">
                </iframe>
            </span>
        </div>
    </div>
</center>
<div style="height: 110px"></div>

<script type="text/javascript" >
    function clickPlayVideo(url) {

        var urlVideo = url.split("@@");

        document.getElementById("videoPlayGame").src = urlVideo[0];
        var title = document.getElementById("title" + urlVideo[1]).value;
        var content = document.getElementById("content" + urlVideo[1]).value;

        document.getElementById("videoTitle").innerHTML = title;
        document.getElementById("videoContent").innerHTML = content;

    }

    function clickPlayImage(url) {
        document.getElementById("imagePlayGame").src = url;
    }

    $(function() {

        var linkVideo = document.getElementById("videoStart1").value;
        var title = document.getElementById("title1").value;
        var content = document.getElementById("content1").value;

        document.getElementById("videoPlayGame").src = linkVideo;
        document.getElementById("videoTitle").innerHTML = title;
        document.getElementById("videoContent").innerHTML = content;

    });

</script>