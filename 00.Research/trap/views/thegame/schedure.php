<link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/schedure.css">
<link rel="stylesheet" href="<?php echo __SITE_URL; ?>/themes/css/jquery-ui.css?87">
<script src="<?php echo __SITE_URL; ?>/js/jquery-ui.js?87"></script>

<script type="text/javascript" >
    var requestUrl = "<?php echo __SITE_URL; ?>";
</script>

<div id="timeline" class="menu classic long-timeframe modern daily">

    <div id="step_title2" class="step-title"><?php echo $la["SCHEDURE_YOURNAME1"]; ?></div>
    <div id="step_info_container">
        <div id="date_time_header" class="step-header active" style="width: 773px;">
            <div class="step-title">
                <span class="step-number">1.</span>
                <span class="step-name"><?php echo $la["SCHEDURE_TIME"]; ?></span>
            </div>
            <div class="step-info"></div>
        </div>
        <div id="client_info_header" class="step-header" style="width: 155px;">
            <div class="step-title">
                <span class="step-number">2.</span>
                <span class="step-name"><?php echo $la["SCHEDURE_DETAILS"]; ?></span>
            </div>
            <div class="step-info"></div>
        </div>
    </div>
    <ul id="errors"></ul>
    <div id="date_time_step" class="step active" style="display: block;">
        <div class="step-content">
            <div class="date_time_container">
                <div id="startDate"></div>
                <script type="text/javascript">
                    var dateToday = "";
                    $(function() {

                        var date = new Date();
                        var newNgay = "";
                        var newThang = "";


                        if (date.getDate() < 10) {
                            newNgay = "0" + date.getDate();
                        } else {
                            newNgay = date.getDate();
                        }

                        if ((date.getMonth() + 1) < 10) {
                            newThang = "0" + (date.getMonth() + 1);
                        } else {
                            newThang = (date.getMonth() + 1);
                        }

                        var dateString = date.getFullYear() + "-" + newThang + "-" + newNgay;
                        dateToday = dateString;
                        
                        $.ajax({
                            url: requestUrl + '/thegame/dateTodayTime',
                            type: "POST",
                            cache: false,
                            data: {
                                dateToday: dateString,
                                gameId: "<?php echo $SchedureGameInfo["game_id"];?>"
                            },
                            dataType: "json",
                            success: function(data) {
                                
                                var html = "";
                                $.each(data, function(index, value) {
                                    if (value.isold == 1) {
                                        html += '<div class="time-select-item" style="border: 1px solid #ffffff!important;background: #000 !important;color: #ffffff  !important;cursor: auto;" id="time_150000" >' + value.title + '</div>';
                                    } else {
                                        if (value.isfull == 1) {
                                            html += '<div class="time-select-item " style="border: 1px solid #ffffff!important; background: #00498f url(' + requestUrl + '/css/images/ui-bg_dots-small_40_00498f_2x2.png) 50% 50% repeat  !important;color: #ffffff  !important;cursor: auto;" id="time_170000" >' + value.title + '</div>';
                                        } else {
                                            html += '<div class="time-select-item" id="' + value.id + "," + value.title + '" onclick="clickSubmit(this.id)">' + value.title + '</div>';
                                        }
                                    }
                                });
                                document.getElementById('time_matrix').innerHTML = html;
                            },
                            error:function(data){
                                alert("loi me no roi");
                            }        

                        });
                    });



                    $("#startDate").datepicker({minDate: '0', dateFormat: 'yy-mm-dd', onSelect: function(date) {
                            dateToday = date;
                            $.ajax({
                                url: requestUrl + '/thegame/dateTodayTime',
                                type: "POST",
                                cache: false,
                                data: {
                                    dateToday: date,
                                    gameId: "<?php echo $SchedureGameInfo["game_id"];?>"
                                },
                                dataType: "json",
                                success: function(data) {
                                    var html = "";
                                    $.each(data, function(index, value) {
                                        if (value.isold == 1) {
                                            html += '<div class="time-select-item" style="border: 1px solid #ffffff!important;background: #000 !important;color: #ffffff  !important;cursor: auto;" id="time_150000" >' + value.title + '</div>';
                                        } else {
                                            if (value.isfull == 1) {
                                                html += '<div class="time-select-item " style="border: 1px solid #ffffff!important; background: #00498f url(' + requestUrl + '/css/images/ui-bg_dots-small_40_00498f_2x2.png) 50% 50% repeat  !important;color: #ffffff  !important;cursor: auto;" id="time_170000" >' + value.title + '</div>';
                                            } else {
                                                html += '<div class="time-select-item" id="' + value.id + "," + value.title + '" onclick="clickSubmit(this.id)">' + value.title + '</div>';
                                            }
                                        }
                                    });
                                    document.getElementById('time_matrix').innerHTML = html;
                                }
                            });
                        }});
                    function clickSubmit(time) {
                        var timeArr = time.split(",");
                        var timeId = timeArr[0];
                        var timeChitiet = timeArr[1];
                        location.href = "<?php echo __SITE_URL; ?>/thegame/schedure2?gameId=<?php echo $SchedureGameInfo['game_id']; ?>&gameNgay=" + dateToday + "&gameTime=" + timeId + "&title=" + timeChitiet + "";
                    }
                </script>
                <div class="time-select" id="time_select" style="display: block;">
                    <div id="time_matrix">

                        <!--                        <div class="time-select-item " style="border: 1px solid #ffffff!important; 
                                                     background: #000 !important;
                                                     color: #ffffff  !important;cursor: auto;" id="time_150000" >10:30</div>
                                                <div class="time-select-item " id="time_150000" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/thegame/schedure2'">13:30</div>
                                                <div class="time-select-item " id="time_150000" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/thegame/schedure2'">15:00</div>
                                                <div class="time-select-item " style="border: 1px solid #ffffff!important; 
                                                     background: #00498f url(<%=request.getContextPath()%>/css/images/ui-bg_dots-small_40_00498f_2x2.png) 50% 50% repeat  !important;
                                                     color: #ffffff  !important;cursor: auto;" id="time_170000" >17:00</div>
                                                <div class="time-select-item " id="time_190000" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/thegame/schedure2'">19:00</div>
                                                <div class="time-select-item " id="time_210000" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/thegame/schedure2'">21:00</div>
                                                <div class="time-select-item " id="time_223000" onclick="javascript:window.location.href = '<%=request.getContextPath()%>/thegame/schedure2'">22:30</div>
                                                <div style="clear: both"></div>-->

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="info" class="bottom-info">
        <div>
            <div class="selected-event-container">
                <span class="title"><?php echo $la["SCHEDURE_SERVICE"]; ?></span>
                <span class="name"><?php echo $SchedureGameInfo['gameName']; ?></span><br/>
                <span ><?php echo $SchedureGameInfo['gameContent']; ?></span>
                <br>
                <span class="description"><?php echo $la["SCHEDURE_CHECKMAIL"]; ?></span>
            </div>
            <div class="selected-event-duration-container">
                <span class="title"><?php echo $la["SCHEDURE_GAMETIME"]; ?></span> 
                <span class="time"><?php echo $SchedureGameInfo['game_time']; ?><?php echo $la["SCHEDURE_MINUTE"]; ?></span>
            </div>
            
            <div class="selected-unit-container">
                <span class="title"><?php echo $la["SCHEDURE_EMPLOYEE"]; ?></span>  
                <span class="unit"><?php echo $SchedureGameInfo['game_number_min']; ?><?php echo $la["SCHEDURE_TODEN"]; ?><?php echo $SchedureGameInfo['game_number_max']; ?><?php echo $la["SCHEDURE_PEOPLE"]; ?></span>
            </div>
        </div>
    </div>
    <div id="navigation" style="display: block;">
        <div id="back_button" class="back-button" onclick="javascript:window.location.href = '<?php echo __SITE_URL; ?>#exampleBookNow'"><?php echo $la["SCHEDURE_BACK"]; ?></div>
    </div>
    <div class="loader" style="display: none;">
    </div>
    <div style="height: 100px;"></div>
</div>