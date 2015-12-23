<?php

Class thegameController Extends baseController {

    public function index() {
        $this->registry->template->blog_heading = 'This is the blog heading';
        if ($this->lang == "vn") {
            $this->registry->template->blog_heading = 'Dịch sang tiếng việt nam';
        }
        $this->registry->template->sayhello = $this->registry->db->sayHello();
//        $this->registry->template->la = $this->la;
        $this->registry->template->show('/thegame/index');
    }

    public function success() {

        $ngaydat = $_GET['ngaydat'];
        $giodat = $_GET['giodat'];
        $this->registry->template->ngaydat = $ngaydat;
        $this->registry->template->giodat = $giodat;
        $this->registry->template->show('/thegame/success');
    }

    public function schedure() {
        $SchedureGameId = $_GET['gameId'];

        $this->registry->db->connect();
        $sqlselect1 = "";
        if ($this->lang == "vn") {
            $sqlselect1 = "game_id,game_name_vn as gameName,game_content_vn as gameContent,game_url,game_difficulty,game_physical,game_number_min,game_number_max,image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions_vn as gamePromotions ";
        } else {
            $sqlselect1 = "game_id,game_name as gameName,game_content as gameContent,game_url,game_difficulty,game_physical,game_number_min,game_number_max,image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions as gamePromotions ";
        }

        $this->registry->db->select('tbl_game_info', $sqlselect1, "game_id = '" . $SchedureGameId . "'", null);
        $SchedureGameInfo = $this->registry->db->getResult();


        $this->registry->template->SchedureGameInfo = $SchedureGameInfo;
        $this->registry->template->show('/thegame/schedure');
    }

    public function dateTodayTime() {
        $dateToday = $_POST['dateToday'];
        $gameId = $_POST['gameId'];

        $this->registry->db->connect();

        $sqlselect1 = "sc.time_id";

        $this->registry->db->select('tbl_schedure sc', $sqlselect1, "sc.game_id = '" . $gameId . "' and DATE_FORMAT(sc.ngay_choi, '%d/%m/%Y') = DATE_FORMAT('" . $dateToday . "', '%d/%m/%Y')", null);
        $lTimeFull = $this->registry->db->getResult();

        $sqlselect2 = "time_id,time_title";

        $this->registry->db->select('tbl_time_game', $sqlselect2, null, "time_id");
        $lTime = $this->registry->db->getResult();
        $this->registry->db->disconnect();
        $lResult = array();

        foreach ($lTime as $value1) {

            $ngayGio = date_create($dateToday . " " . $value1['time_title'], timezone_open("Asia/Ho_Chi_Minh"));
            $dNow = new DateTime('NOW', timezone_open("Asia/Ho_Chi_Minh"));

            if ($ngayGio <= $dNow) {
                $o = array();
                $o["id"] = $value1["time_id"];
                $o["title"] = $value1["time_title"];
                $o["isfull"] = 0;
                $o["isold"] = 1;

                array_push($lResult, $o);
            } else {

                // chi can check full
                $isFull = false;
                foreach ($lTimeFull as $value2) {
                    if ($value2["sc.time_id"] == $value1["time_id"]) {
                        $isFull = true;
                        break;
                    }
                }
                if ($isFull) {
                    $o1 = array();
                    $o1["id"] = $value1["time_id"];
                    $o1["title"] = $value1["time_title"];
                    $o1["isfull"] = 1;
                    $o1["isold"] = 0;

                    array_push($lResult, $o1);
                } else {
                    $o1 = array();
                    $o1["id"] = $value1["time_id"];
                    $o1["title"] = $value1["time_title"];
                    $o1["isfull"] = 0;
                    $o1["isold"] = 0;
                    array_push($lResult, $o1);
                }
            }
        }

        echo json_encode($lResult);
    }

    public function schedure2() {
        $gameId = $_GET['gameId'];
        $gameTime = $_GET['gameTime'];
        $gameNgay = $_GET['gameNgay'];
        $gameTitle = $_GET['title'];

        $ngayGio = date_create($gameNgay . " " . $gameTitle, timezone_open("Asia/Ho_Chi_Minh"));
        $ngayHT = date_format($ngayGio, "d-m H:i");
        $this->registry->template->ngayHT = "$ngayHT";
        $this->registry->template->gameId = "$gameId";
        $this->registry->template->gameTime = "$gameTime";
        $this->registry->template->gameNgay = "$gameNgay";
        $this->registry->template->gameTitle = "$gameTitle";
        $this->registry->template->show('/thegame/schedure2');
    }

    public function insertBook() {


        $gameIdDatlich = $_POST['gameIdDatlich'];
        $gameNgay = $_POST['gameNgay'];
        $gameTime = $_POST['gameTime'];
        $client_name = $_POST['client_name'];
        $conVertFullName = $_POST['conVertFullName'];
        $client_email = $_POST['client_email'];
        $client_phone_area_code1 = $_POST['client_phone_area_code1'];
        $client_phone_area_code2 = $_POST['client_phone_area_code2'];


        $this->registry->db->connect();

        $sqlselect1 = "count(1) as so_nguoi";
        $this->registry->db->select('tbl_player', $sqlselect1, "username =  '" . $conVertFullName . "'", null);
        $checkUsername = $this->registry->db->getResult();
        $kq = 0;
        if ($checkUsername['so_nguoi'] == 1) {

            $sqlselect2 = "player_id as v_player_id";

            $this->registry->db->select('tbl_player', $sqlselect2, "username =  '" . $conVertFullName . "'", null);
            $checkv_player_id1 = $this->registry->db->getResult();


            date_default_timezone_set("Asia/Ho_Chi_Minh");
            $arrayIs1 = Array();
            $arrayIs1[0] = intval($checkv_player_id1['v_player_id']);
            $arrayIs1[1] = intval($gameTime);
            $arrayIs1[2] = $gameIdDatlich;
            $arrayIs1[3] = $gameNgay;
            $arrayIs1[4] = 0;
            $arrayIs1[5] = date('Y-m-d h:i:s');
            $arrayIs1[6] = date('Y-m-d h:i:s');
            $arrayIs1[7] = 0;
            $arrayIs1[8] = 1;

            $kq = $this->registry->db->insert('tbl_schedure', $arrayIs1, "player_id,time_id,game_id,ngay_choi,status,register_time,updated_time,so_nguoi_choi,email_send");
        } else {

            $sqlselect3 = "IFNULL(max(player_id),0) + 1 as v_player_id";

            $this->registry->db->select('tbl_player', $sqlselect3, null, null);
            $checkv_player_id2 = $this->registry->db->getResult();


            $arrayIs2 = Array();
            $arrayIs2[0] = $checkv_player_id2['v_player_id'];
            $arrayIs2[1] = $conVertFullName;
            $arrayIs2[2] = $client_name;
            $arrayIs2[3] = $client_phone_area_code1;
            $arrayIs2[4] = " ";
            $arrayIs2[5] = $client_email;
            $arrayIs2[6] = 1;
            $arrayIs2[7] = $client_phone_area_code2;

            $this->registry->db->insert('tbl_player', $arrayIs2, "player_id,username,full_name,mobile,address,email,sex,mobile_inviter");

            date_default_timezone_set("Asia/Ho_Chi_Minh");
            $arrayIs3 = Array();
            $arrayIs3[0] = intval($checkv_player_id2['v_player_id']);
            $arrayIs3[1] = intval($gameTime);
            $arrayIs3[2] = $gameIdDatlich;
            $arrayIs3[3] = $gameNgay;
            $arrayIs3[4] = 0;
            $arrayIs3[5] = date('Y-m-d h:i:s');
            $arrayIs3[6] = date('Y-m-d h:i:s');
            $arrayIs3[7] = 0;
            $arrayIs3[8] = 1;

            $kq = $this->registry->db->insert('tbl_schedure', $arrayIs3, "player_id,time_id,game_id,ngay_choi,status,register_time,updated_time,so_nguoi_choi,email_send");
        }
        $this->registry->db->disconnect();
        if($kq){
            $this->sendmail("Trap.vn", "kiemcp@gmail.com", "chauphong", "contents.html", "Xác nhận đăng ký game", 
                    "smtp.gmail.com", 587, $client_email, $client_name);
        }
        echo $kq;
    }
    
    private function sendmail($mailname,$mailaddress,$mailpass,$mailcontent,$mailsubject,$mailhost,$mailport,$mailto,$mailnameto){
        date_default_timezone_set('Etc/UTC');
        require __SITE_PATH . '/includes/PHPMailerAutoload.php';
        $mail = new PHPMailer;
        $mail->isSMTP();
        $mail->SMTPDebug = 0;
        $mail->Debugoutput = 'html';
        $mail->Host = $mailhost;
        $mail->Port = $mailport;
        $mail->SMTPSecure = 'tls';
        $mail->SMTPAuth = true;
        $mail->Username = $mailaddress;
        $mail->Password = $mailpass;
        $mail->setFrom($mailaddress, $mailname);
        $mail->addReplyTo($mailaddress, $mailname);
        $mail->addAddress($mailto, $mailnameto);
        $mail->Subject = $mailsubject;
        $mail->msgHTML(file_get_contents($mailcontent), dirname(__FILE__));
        $mail->CharSet = 'utf-8';
        if (!$mail->send()) {
            return false;
        } else {
            return true;
        }
    }

}

?>
