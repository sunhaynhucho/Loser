<?php

Class cauhinhgameController Extends baseController {

    public function index() {
        $this->registry->template->show('cauhinhgame/home');
    }

    public function gamesettings() {
        $this->registry->template->show('cauhinhgame/gamesettings');
    }

    public function blacklist() {
        $this->registry->template->show('cauhinhgame/blacklist');
    }

    public function schedure() {
        $this->registry->template->show('cauhinhgame/schedure');
    }

    public function managerimagegame() {
        $this->registry->template->show('cauhinhgame/managerimagegame');
    }

    public function getschedure() {
        $page = 1;
        if (isset($_POST["page"]) && $_POST["page"] != "") {
            $page = $_POST["page"];
        }
        $rows = 10;
        if (isset($_POST["rows"]) && $_POST["rows"] != "") {
            $rows = $_POST["rows"];
        }
        $batDau = ($page - 1) * $rows;

        $this->registry->db->connect();
        $sqlquery = "select a.player_id,a.username,a.full_name,a.mobile,a.email,a.address,a.sex
            ,b.sc_id,DATE_FORMAT(b.ngay_choi, '%d/%m/%Y') as ngay_choi,b.so_nguoi_choi,b.email_send,b.status,DATE_FORMAT(b.register_time, '%d/%m/%Y') as register
            ,c.game_id,c.game_name,c.ticket_code,c.game_price,
            d.time_id,d.time_title,TIME_FORMAT(d.time_value, '%H %i') time_value,a.mobile_inviter from tbl_player a 
            join tbl_schedure b on a.player_id = b.player_id 
            join tbl_game_info c on b.game_id = c.game_id 
            join tbl_time_game d on b.time_id = d.time_id where 1=1";
        $sqltotal = "select count(1) as sl from tbl_player a 
            join tbl_schedure b on a.player_id = b.player_id 
            join tbl_game_info c on b.game_id = c.game_id 
            join tbl_time_game d on b.time_id = d.time_id where 1=1";
        if (isset($_POST["keysearch"]) && $_POST["keysearch"] != "") {
            $sqltotal = $sqltotal . " and (c.game_name like '%" . $_POST["keysearch"] . "%'";
            $sqltotal = $sqltotal . " or a.full_name like '%" . $_POST["keysearch"] . "%'";
            $sqltotal = $sqltotal . " or a.username like '%" . $_POST["keysearch"] . "%'";
            $sqltotal = $sqltotal . " or a.email like '%" . $_POST["keysearch"] . "%'";
            $sqltotal = $sqltotal . " or a.address like '%" . $_POST["keysearch"] . "%')";

            $sqlquery = $sqlquery . " and (c.game_name like '%" . $_POST["keysearch"] . "%'";
            $sqlquery = $sqlquery . " or a.full_name like '%" . $_POST["keysearch"] . "%'";
            $sqlquery = $sqlquery . " or a.username like '%" . $_POST["keysearch"] . "%'";
            $sqlquery = $sqlquery . " or a.email like '%" . $_POST["keysearch"] . "%'";
            $sqlquery = $sqlquery . " or a.address like '%" . $_POST["keysearch"] . "%')";
        }
        if (isset($_POST["status"]) && $_POST["status"] != "-1") {
            $sqltotal = $sqltotal . " and b.status = " . $_POST["status"];
            $sqlquery = $sqlquery . " and b.status = " . $_POST["status"];
        }
        if (isset($_POST["fromdate"]) && $_POST["fromdate"] != "") {
            $sqltotal = $sqltotal . " and b.ngay_choi >= STR_TO_DATE('" . $_POST["fromdate"] . "','%d/%m/%Y')";
            $sqlquery = $sqlquery . " and b.ngay_choi >= STR_TO_DATE('" . $_POST["fromdate"] . "','%d/%m/%Y')";
        }
        if (isset($_POST["todate"]) && $_POST["todate"] != "") {
            $sqltotal = $sqltotal . " and b.ngay_choi <= STR_TO_DATE('" . $_POST["todate"] . "','%d/%m/%Y')";
            $sqlquery = $sqlquery . " and b.ngay_choi <= STR_TO_DATE('" . $_POST["todate"] . "','%d/%m/%Y')";
        }
        $sqlquery = $sqlquery . " order by b.register_time desc LIMIT " . $batDau . ", " . $rows . "";
        $this->registry->db->select_special($sqltotal);
        $total = $this->registry->db->getResult();
        $this->registry->db->select_special($sqlquery);
        $result = $this->registry->db->getResult();
        $this->registry->db->disconnect();
        $lResult = array();
        $lList = array();
        $numRow = $this->registry->db->getNumRow();
        if ($numRow == 1) {
            $o = array();
            $o["pid"] = $result["player_id"];
            $o["pusername"] = $result["username"];
            $o["pfullname"] = $result["full_name"];
            $o["pmobile"] = $result["mobile"];
            $o["pemail"] = $result["email"];
            $o["paddress"] = $result["address"];
            $o["psex"] = $result["sex"];
            $o["sid"] = $result["sc_id"];
            $o["sday"] = $result["ngay_choi"];
            $o["snumber"] = $result["so_nguoi_choi"];
            $o["ssendemail"] = cObjectDaGuiEmail($result["email_send"]);
            $o["sstatus"] = cObjectTrangThai($result["status"]);
            $o["stime"] = $result["register"];
            $o["gid"] = $result["game_id"];
            $o["gname"] = $result["game_name"];
            $o["gcode"] = $result["ticket_code"];
            $o["gprice"] = $result["game_price"];
            $o["tid"] = $result["time_id"];
            $o["ttitle"] = $result["time_title"];
            $o["tvalue"] = $result["time_value"];
            $o["pinviter"] = $result["mobile_inviter"];
            array_push($lResult, $o);
        } else {
            foreach ($result as $value) {
                $o = array();
                $o["pid"] = $value["player_id"];
                $o["pusername"] = $value["username"];
                $o["pfullname"] = $value["full_name"];
                $o["pmobile"] = $value["mobile"];
                $o["pemail"] = $value["email"];
                $o["paddress"] = $value["address"];
                $o["psex"] = $value["sex"];
                $o["sid"] = $value["sc_id"];
                $o["sday"] = $value["ngay_choi"];
                $o["snumber"] = $value["so_nguoi_choi"];
                $o["ssendemail"] = cObjectDaGuiEmail($value["email_send"]);
                $o["sstatus"] = cObjectTrangThai($value["status"]);
                $o["stime"] = $value["register"];
                $o["gid"] = $value["game_id"];
                $o["gname"] = $value["game_name"];
                $o["gcode"] = $value["ticket_code"];
                $o["gprice"] = $value["game_price"];
                $o["tid"] = $value["time_id"];
                $o["ttitle"] = $value["time_title"];
                $o["tvalue"] = $value["time_value"];
                $o["pinviter"] = $value["mobile_inviter"];
                array_push($lResult, $o);
            }
        }
        $lList["total"] = $total["sl"];
        $lList["rows"] = $lResult;
        echo json_encode($lList);
    }

    public function DanhSachGameServices() {
        $gname = null;
        if (isset($_POST["gname"]) && $_POST["gname"] != "") {
            $gname = "game_name like '%" . $_POST["gname"] . "%'";
        }
        $this->registry->db->connect();
        $sqlselect = "game_id,game_name,game_content,game_url,game_difficulty,game_physical,game_number_min,game_number_max,
            image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),
            game_price,ticket_code,game_seq,game_promotions,game_name_vn,game_content_vn,game_promotions_vn";
        $order = "game_seq";
        $this->registry->db->select('tbl_game_info', $sqlselect, $gname, $order);
        $result = $this->registry->db->getResult();
        $numRow = $this->registry->db->getNumRow();
        $this->registry->db->disconnect();
        $lResult = array();
        $lList = array();
        $appUtil = new AppUtils();
        if ($numRow == 1) {
            $o = array();
            $o["gid"] = $result["game_id"];
            $o["gname"] = $result["game_name"];
            $o["gprice"] = $result["game_price"];
            $o["gcontent"] = $result["game_content"];
            $o["gdiff"] = $appUtil->cDoKhoGame($result["game_difficulty"]);
            $o["gnumber"] = $appUtil->cSoNguoiChoi($result['game_number_min'], $result['game_number_max']);
            $o["gtime"] = $appUtil->cThoiGianChoiGame($result['game_time']);
            $o["gcode"] = $result["ticket_code"];
            $o["gurl"] = $result["game_url"];
            $o["gimageurl"] = $result["image_url"];
            $o["gpromotions"] = $result["game_promotions"];
            $o["gnamevn"] = $result["game_name_vn"];
            $o["gcontentvn"] = $result["game_content_vn"];
            $o["gpromotionsvn"] = $result["game_promotions_vn"];
            array_push($lResult, $o);
        } else {
            foreach ($result as $value) {
                $o = array();
                $o["gid"] = $value["game_id"];
                $o["gname"] = $value["game_name"];
                $o["gprice"] = $value["game_price"];
                $o["gcontent"] = $value["game_content"];
                $o["gdiff"] = $appUtil->cDoKhoGame($value["game_difficulty"]);
                $o["gnumber"] = $appUtil->cSoNguoiChoi($value['game_number_min'], $value['game_number_max']);
                $o["gtime"] = $appUtil->cThoiGianChoiGame($value['game_time']);
                $o["gcode"] = $value["ticket_code"];
                $o["gurl"] = $value["game_url"];
                $o["gimageurl"] = $value["image_url"];
                $o["gpromotions"] = $value["game_promotions"];
                $o["gnamevn"] = $value["game_name_vn"];
                $o["gcontentvn"] = $value["game_content_vn"];
                $o["gpromotionsvn"] = $value["game_promotions_vn"];
                array_push($lResult, $o);
            }
        }
        $lList["total"] = count($lList);
        $lList["rows"] = $lResult;
        echo json_encode($lList);
    }

    public function addgame() {
        $target_file = null;
        $imagename = null;
        $uploadOk = 0;
        $target_dir = __SITE_PATH . "/images/imgGame/";
        $userName = $_SESSION[__USER_NAME];
        $today = getTimeLocal();
        $target_file = $target_dir . $userName . $today . basename($_FILES["fAnh"]["name"]);
        $imagename = $userName . $today . basename($_FILES["fAnh"]["name"]);
        $imageFileType = pathinfo($target_file, PATHINFO_EXTENSION);

        if (file_exists($target_file)) {
            $uploadOk = 2; // File is already exists
        }
        if ($_FILES["fAnh"]["size"] > 500000) {
            $uploadOk = 3; // your file is too large
        }
        if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif") {
            $uploadOk = 4; //Sorry, only JPG, JPEG, PNG & GIF files are allowed
        }
        $magame = $_POST['txtMaGame'];
        $tengame = $_POST['txtTenGame'];
        $tengamevn = $_POST['txtTenGameVN'];
        $giatien = $_POST['txtGiaTien'];
        $dokho = $_POST['txtDoKho'];
        $min = $_POST['txtMin'];
        $max = $_POST['txtMax'];
        $noidung = $_POST['txtNoiDung'];
        $noidungvn = $_POST['txtNoiDungVN'];
        $sophut = $_POST['txtSoPhut'];
        $mave = $_POST['txtMaVe'];
        $gameurl = $_POST['txtGameUrl'];
        $promotion = $_POST['txtPromotions'];
        $promotionvn = $_POST['txtPromotionsVN'];
        $values = array();
        $values[0] = $tengame;
        $values[1] = $tengamevn;
        $values[2] = intval($giatien);
        $values[3] = intval($dokho);
        $values[4] = intval($min);
        $values[5] = intval($max);
        $values[6] = $noidung;
        $values[7] = $noidungvn;
        $values[8] = intval($sophut);
        $values[9] = $mave;
        $values[10] = $gameurl;
        $values[11] = $promotion;
        $values[12] = $promotionvn;
        $values[13] = $magame;
        $rows = "game_name,game_name_vn,game_price,game_difficulty,game_number_min,game_number_max,
            game_content,game_content_vn,game_time,ticket_code,game_url,game_promotions,game_promotions_vn,game_id";
        if ($uploadOk == 0) {
            if (move_uploaded_file($_FILES["fAnh"]["tmp_name"], $target_file)) {
                $values[14] = $imagename;
                $rows = $rows . ",image_url";
            }
        }
        
        $this->registry->db->connect();
        $kq = $this->registry->db->insert('tbl_game_info', $values, $rows);
        $this->registry->db->disconnect();
        if ($kq) {
            echo "{\"code\":0,\"detail\":\"Bạn đã thêm game thành công\"}";
        } else {
            echo "{\"code\":1,\"detail\":\"lỗi insert \"}";
        }
    }

    public function editgame() {
        $target_file = null;
        $imagename = null;
        $uploadOk = 0;
        $target_dir = __SITE_PATH . "/images/imgGame/";
        $userName = $_SESSION[__USER_NAME];
        $today = getTimeLocal();
        $target_file = $target_dir . $userName . $today . basename($_FILES["fAnh"]["name"]);
        $imagename = $userName . $today . basename($_FILES["fAnh"]["name"]);
        $imageFileType = pathinfo($target_file, PATHINFO_EXTENSION);

        if (file_exists($target_file)) {
            $uploadOk = 2; // File is already exists
        }
        if ($_FILES["fAnh"]["size"] > 1000000) {
            $uploadOk = 3; // your file is too large
        }
        if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif") {
            $uploadOk = 4; //Sorry, only JPG, JPEG, PNG & GIF files are allowed
        }
        $magame = $_POST['txtMaGame'];
        $tengame = $_POST['txtTenGame'];
        $tengamevn = $_POST['txtTenGameVN'];
        $giatien = $_POST['txtGiaTien'];
        $dokho = $_POST['txtDoKho'];
        $min = $_POST['txtMin'];
        $max = $_POST['txtMax'];
        $noidung = $_POST['txtNoiDung'];
        $noidungvn = $_POST['txtNoiDungVN'];
        $sophut = $_POST['txtSoPhut'];
        $mave = $_POST['txtMaVe'];
        $gameurl = $_POST['txtGameUrl'];
        $promotion = $_POST['txtPromotions'];
        $promotionvn = $_POST['txtPromotionsVN'];
        $rows = array();
        $rows['game_name'] = $tengame;
        $rows['game_name_vn'] = $tengamevn;
        $rows['game_price'] = intval($giatien);
        $rows['game_difficulty'] = intval($dokho);
        $rows['game_number_min'] = intval($min);
        $rows['game_number_max'] = intval($max);
        $rows['game_content'] = $noidung;
        $rows['game_content_vn'] = $noidungvn;
        $rows['game_time'] = intval($sophut);
        $rows['ticket_code'] = $mave;
        $rows['game_url'] = $gameurl;
        $rows['game_promotions'] = $promotion;
        $rows['game_promotions_vn'] = $promotionvn;
        if ($uploadOk == 0) {
            if (move_uploaded_file($_FILES["fAnh"]["tmp_name"], $target_file)) {
                $rows['image_url'] = $imagename;
            }
        }
        $where = null;
        $where = "game_id = '" . $magame . "'";
        $this->registry->db->connect();
        $kq = $this->registry->db->update('tbl_game_info', $rows, $where);
        $this->registry->db->disconnect();
        if ($kq) {
            echo "{\"code\":0,\"detail\":\"Bạn đã đăng ký thành công\"}";
        } else {
            echo "{\"code\":1,\"detail\":\"lỗi cập nhật\"}";
        }
    }

    public function deletegame() {
        $magame = $_POST['gid'];
        $where = "game_id = '" . $magame . "'";
        $this->registry->db->connect();
        $kq = $this->registry->db->delete('tbl_game_info', $where);
        $this->registry->db->disconnect();
        if ($kq) {
            echo "{\"code\":0,\"detail\":\"Bạn đã xóa thành công\"}";
        } else {
            echo "{\"code\":1,\"detail\":\"Lỗi không xóa được\"}";
        }
    }

    public function submitsendemail() {
        $scid = $_POST["mailid"];
        $mailname = $_POST["mailname"];
        $mailaddress = $_POST["mailaddress"];
        $mailsubject = $_POST["mailsubject"];
        $mailcontent = $_POST["mailcontent"];
        // gửi email tại đây

        $rows = array();
        $rows["email_send"] = 1;
        $where = "sc_id = " . $scid;
        $this->registry->db->connect();
        $kq = $this->registry->db->update('tbl_schedure', $rows, $where);
        $this->registry->db->disconnect();
        if ($kq) {
            echo "{\"code\":0,\"detail\":\"Gửi email thành công\"}";
        } else {
            echo "{\"code\":1,\"detail\":\"Lỗi gửi email\"}";
        }
    }

    public function confirmschedure() {
        $scid = $_POST["scid"];
        $sday = $_POST["sday"];
        $sstatus = $_POST["sstatus"];
        $pusername = $_POST["pusername"];
        $pfullname = $_POST["pfullname"];
        $pemail = $_POST["pemail"];
        $pmobile = $_POST["pmobile"];
        $paddress = $_POST["paddress"];
        $scnumber = $_POST["scnumber"];
        $tid = $_POST["tid"];
        $gid = $_POST["gid"];
        $this->registry->db->connect();
        $sqlQuery = "select count(1) as v_count from tbl_schedure 
		where sc_id = " . $scid . " and time_id = " . $tid . " and 
                    game_id = '" . $gid . "' and ngay_choi = STR_TO_DATE('" . $sday . "','%d/%m/%Y')";
        $this->registry->db->select_special($sqlQuery);
        $kq = $this->registry->db->getResult();
        $daTonTaiLich = 1;
        $thanhcong = 1;
        if ($kq != null) {
            if ($kq["v_count"] == 1) {
                $daTonTaiLich = 0;
                $thanhcong = 5;
            } else {
                $sqlQuery = "select count(1) as v_count from tbl_schedure 
		where time_id = " . $tid . " and 
                    game_id = '" . $gid . "' and ngay_choi = STR_TO_DATE('" . $sday . "','%d/%m/%Y')";
                $this->registry->db->select_special($sqlQuery);
                $kq2 = $this->registry->db->getResult();
                if ($kq2["v_count"] == 1) {
                    $daTonTaiLich = 1;
                    $thanhcong = 2;
                } else {
                    $daTonTaiLich = 0;
                }
            }
        } else {
            $daTonTaiLich = 0;
            $thanhcong = 6;
        }
        if ($daTonTaiLich == 0) {
            // update player
            $rows = array();
            $rows["full_name"] = $pfullname;
            $rows["mobile"] = $pmobile;
            $rows["address"] = $paddress;
            $rows["email"] = $pemail;
            $where = "username = '" . $pusername . "'";
            $kq = $this->registry->db->update("tbl_player", $rows, $where);
            if ($kq) {
                // update schedure
                $sqlQuery = "select player_id from tbl_player where username = '" . $pusername . "'";
                $this->registry->db->select_special($sqlQuery);
                $kq2 = $this->registry->db->getResult();
                $sql = "update tbl_schedure set player_id = " . $kq2["player_id"] . ", time_id = " . $tid . ", 
                    game_id = '" . $gid . "', ngay_choi = STR_TO_DATE('" . $sday . "','%d/%m/%Y'),
			status = " . $sstatus . ", updated_time = CURRENT_DATE, so_nguoi_choi = " . $scnumber . " where sc_id = " . $scid;

                $kq = $this->registry->db->update_special("tbl_schedure", $sql);
                if ($kq) {
                    $thanhcong = 0;
                } else {
                    $thanhcong = 3;
                }
            } else {
                $thanhcong = 7;
            }
        }
        $this->registry->db->disconnect();
        $trave = array();
        if ($thanhcong == 0) {
            $trave["code"] = 0;
            $trave["detail"] = "Xác nhận đặt lịch thành công";
        } else {
            $trave["code"] = $thanhcong;
            if ($thanhcong == 2) {
                $trave["detail"] = "Xác nhận đặt lịch bị lỗi trong db";
            } else {
                $trave["detail"] = "Lịch này đã được đặt";
            }
        }
        echo json_encode($trave);
    }

    public function addschedure() {
        $sday = $_POST["sday"];
        $sstatus = $_POST["sstatus"];
        $pfullname = $_POST["pfullname"];
        $pemail = $_POST["pemail"];
        $pmobile = $_POST["pmobile"];
        $paddress = $_POST["paddress"];
        $scnumber = $_POST["scnumber"];
        $pusername = $_POST["pusername"];
        $tid = $_POST["tid"];
        $gid = $_POST["gid"];
        $this->registry->db->connect();

        $daTonTaiLich = 1;
        $thanhcong = 1;
        $sqlQuery = "select count(1) as v_count from tbl_schedure 
		where time_id = " . $tid . " and 
                    game_id = '" . $gid . "' and ngay_choi = STR_TO_DATE('" . $sday . "','%d/%m/%Y')";
        $this->registry->db->select_special($sqlQuery);
        $kq2 = $this->registry->db->getResult();
        if ($kq2["v_count"] == 1) {
            $daTonTaiLich = 1;
            $thanhcong = 2;
        } else {
            $daTonTaiLich = 0;
        }
        echo "Tới đây" . $daTonTaiLich;
        if ($daTonTaiLich == 0) {
            // update player
            $sqlQuery = "select count(1) as v_count from tbl_player where username = '" . $pusername . "'";
            $this->registry->db->select_special($sqlQuery);
            $kq2 = $this->registry->db->getResult();
            if ($kq2["v_count"] == 0) {
                $values = array();
                $values[0] = $pusername;
                $values[1] = $pfullname;
                $values[2] = $pmobile;
                $values[3] = $paddress;
                $values[4] = $pemail;
                $values[5] = 0;
                $rows = "username,full_name,mobile,address,email,sex";
                $kq = $this->registry->db->insert("tbl_player", $values, $rows);
            }
            // update schedure
            $sqlQuery = "select player_id from tbl_player where username = '" . $pusername . "'";
            $this->registry->db->select_special($sqlQuery);
            $kq2 = $this->registry->db->getResult();
            $sql = "insert into tbl_schedure(player_id,time_id,game_id,ngay_choi,status,register_time,updated_time,
                    so_nguoi_choi,email_send) values (" . $kq2["player_id"] . "," . $tid . ",'" . $gid . "',STR_TO_DATE('" . $sday . "','%d/%m/%Y'),
                        " . $sstatus . ",CURRENT_DATE,CURRENT_DATE," . $scnumber . ",1)";
            $kq = $this->registry->db->insert_special("tbl_schedure", $sql);
            if ($kq) {
                $thanhcong = 0;
            } else {
                $thanhcong = 3;
            }
        }
        $this->registry->db->disconnect();
        $trave = array();
        if ($thanhcong == 0) {
            $trave["code"] = 0;
            $trave["detail"] = "Xác nhận đặt lịch thành công";
        } else {
            $trave["code"] = $thanhcong;
            if ($thanhcong == 2) {
                $trave["detail"] = "Lịch này đã được đặt";
            } else {
                $trave["detail"] = "Xác nhận đặt lịch bị lỗi trong db";
            }
        }
        echo json_encode($trave);
    }

}

?>
