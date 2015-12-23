
<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
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
    <body>
    <center>
        <div style="height: 80px;"></div>
        <div style="height: auto; width: 1020px">

            <table width="100%" height="300px" border="0">
                <tr>
                    <td width="50%" valign="top">
                        <table width="100%" height="300px" border="0">
                            <tr>
                                <td width="100%" height="20px" colspan="2"></td>
                            </tr>  
                            <tr>
                                <td width="50%" align="center">
                                    <img id="imagePlayGame" src="" style="width:500px;height:277px" />
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <?php
                                    $count001 = 0;

                                    foreach ($listGameImageNho as $value01) {
                                        $count001++;
                                        ?>


                                        <img id="<?php echo __SITE_URL . '/images/imgPlay/' . $value01['image_name']; ?>" src="<?php echo __SITE_URL . '/images/imgPlay/' . $value01['image_name']; ?>" onclick="clickPlayImage(this.id)" alt="Mountain View" style="width:104px;height:59px;cursor: pointer" />

                                        <?php if ($count001 == 1) { ?>

                                            <input type="hidden" id="imageso1" value="<?php echo __SITE_URL . '/images/imgPlay/' . $value01['image_name']; ?>" />

                                            <?php
                                        }
                                    }
                                    ?>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" height=30px"></td>
                            </tr>
<!--                            <tr>
                                <td colspan="2" style="background-color: #999999"></td>
                            </tr>-->
                        </table>
                    </td>
                    <td rowspan="2" align="center" valign="top">

                        <table width="100%" border="0">
                            <tr>
                                <td width="100%" height="20px"></td>
                            </tr> 
                            <tr>
                                <td valign="top">
                                    <span style="color: #fff;font-size: 40px;font-variant: caps">
                                        <?php echo $listGameImageInfo['gameName'] ?>
                                    </span><br><br>        
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span style="color: #00a8f3"><?php echo $la["GALLERY_GIA"]; ?> <?php echo $listGameImageInfo['game_price']; ?> vnd</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span style="color: #00a8f3"><?php echo $la["GALLERY_SONGUOI"]; ?> <?php echo $soNguoiChoi; ?></span>   
                                    <br><br><br><br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="prod_url">
                                        <a class="urllink" target="_blank" href="<?php echo __SITE_URL ?>./thegame/schedure?gameId=<?php echo $listGameImageInfo['game_id']; ?>&gameName=<?php echo $listGameImageInfo['gameName']; ?>"><?php echo $la["SCHEDULE"]; ?></a>
                                    </div>
                                    <br><br><br><br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span style="color: #00a8f3"><?php echo $la["GALLERY_DIFFICULT"]; ?></span> 
                                    <?php
                                    foreach ($listGameImageInfo as $value02) {
                                        $sao = $listGameImageInfo['game_difficulty'];
                                        ?>
                                        <img src="<?php echo __SITE_URL; ?>/images/trapicon2.png" width="20px" height="20px" />   
                                    <?php } ?>
                                    <br><br>
                                </td>
                            </tr>
                            <tr>
                                <td height="600px" valign="top">
                                    <span style="color: white;font-size: 14px"><?php echo $listGameImageInfo['gameContent']; ?></span>
                                </td>
                            </tr>
                        </table>


                    </td>
                </tr>
<!--                <tr>
                    <td>
                        <table width="100%" height="300px" border="0">
                            <tr>
                                <td width="100%" height="20px" colspan="2"></td>
                            </tr>  
                            <tr>
                                <td width="50%" align="center">
                                    <img id="imagePlayGame2" src="" style="width:500px;height:277px" />
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <?php
                                    $count002 = 0;
                                    foreach ($listGameImageNguoichoi as $value03) {
                                        $count002++;
                                        ?>

                                        <img id="<?php echo __SITE_URL . '/images/imgPlay/' . $value03['image_name']; ?>" src="<?php echo __SITE_URL . '/images/imgPlay/' . $value03['image_name']; ?>" onclick="clickPlayImage2(this.id)" alt="Mountain View" style="width:104px;height:59px;cursor: pointer" />

                                        <?php if ($count002 == 1) { ?>

                                            <input type="hidden" id="imageso11" value="<?php echo __SITE_URL . '/images/imgPlay/' . $value03['image_name']; ?>" />

                                            <?php
                                        }
                                    }
                                    ?>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" height=30px"></td>
                            </tr>
                                                        <tr>
                                                            <td colspan="2" style="background-color: #999999"></td>
                                                        </tr>
                        </table>
                    </td>
                </tr>-->

            </table>

            <div style="height: 30px"></div>
        </div>  
    </center>
</body>
</html>
<script type="text/javascript" >

    function clickPlayImage(url) {

        document.getElementById("imagePlayGame").src = url;
    }

    function clickPlayImage2(url) {
        document.getElementById("imagePlayGame2").src = url;
    }

    $(function() {
        if (document.getElementById("imageso1") != null) {
            var linkImageSo1 = document.getElementById("imageso1").value;
            document.getElementById("imagePlayGame").src = linkImageSo1;
        }

        if (document.getElementById("imageso11") != null) {
            var linkImageSo11 = document.getElementById("imageso11").value;
            document.getElementById("imagePlayGame2").src = linkImageSo11;
        }

    });

</script>