<?php

Class ajaxController Extends baseController {

    public function index() {
        $this->registry->template->blog_heading = 'This is the blog Index';
        $this->registry->template->show('ajax/frmeditgame');
    }

    public function frmeditgame() {
        $gname = null;
        if (isset($_GET["gid"]) && $_GET["gid"] != "") {
            $gname = "game_id = '" . $_GET["gid"] . "'";
        }
        $this->registry->db->connect();
        $sqlselect = "game_id,game_name,game_content,game_url,game_difficulty,game_physical,game_number_min,game_number_max,
            image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),
            game_price,ticket_code,game_seq,game_promotions,game_name_vn,game_content_vn,game_promotions_vn";

        $this->registry->db->select('tbl_game_info', $sqlselect, $gname, null);
        $result = $this->registry->db->getResult();
        $this->registry->db->disconnect();
        $this->registry->template->game = $result;
        $this->registry->template->show('ajax/frmeditgame');
    }

    public function frmaddgame() {
        $this->registry->template->show('ajax/frmaddgame');
    }

    public function confirmschedure() {
        $this->registry->db->connect();
        $scid = null;
        if (isset($_GET["scid"]) && $_GET["scid"] != "") {
            $scid = $_GET["scid"];
        }
        $this->registry->db->connect();
        $sqlquery = "select a.player_id,a.username,a.full_name,a.mobile,a.email,a.address,a.sex
            ,b.sc_id,DATE_FORMAT(b.ngay_choi, '%d/%m/%Y') as ngay_choi,b.so_nguoi_choi,b.email_send,b.status,DATE_FORMAT(b.register_time, '%d/%m/%Y') as register
            ,c.game_id,c.game_name,c.ticket_code,c.game_price,
            d.time_id,d.time_title,TIME_FORMAT(d.time_value, '%H %i') time_value,a.mobile_inviter from tbl_player a 
            join tbl_schedure b on a.player_id = b.player_id 
            join tbl_game_info c on b.game_id = c.game_id 
            join tbl_time_game d on b.time_id = d.time_id where b.sc_id = " . $scid;

        $this->registry->db->select_special($sqlquery);
        $sc = $this->registry->db->getResult();
        //
        $sqlquery = "select game_id,CONCAT(game_name,'-',game_name_vn) game_name from tbl_game_info";
        $this->registry->db->select_special($sqlquery);
        $lgame = $this->registry->db->getResult();
        //
        $sqlquery = "select time_id,time_title from tbl_time_game order by time_id";
        $this->registry->db->select_special($sqlquery);
        $ltime = $this->registry->db->getResult();
        //
        $this->registry->db->disconnect();
        $this->registry->template->lgame = $lgame;
        $this->registry->template->ltime = $ltime;
        $this->registry->template->sc = $sc;
        $this->registry->template->show('ajax/confirmschedure');
    }

    public function sendmailschedure() {
        $scid = null;
        if (isset($_GET["scid"]) && $_GET["scid"] != "") {
            $scid = $_GET["scid"];
        }
        $result = null;
        if ($scid != null) {
            $this->registry->db->connect();
            $sqlquery = "select a.player_id,a.username,a.full_name,a.mobile,a.email,a.address,a.sex
            ,b.sc_id,DATE_FORMAT(b.ngay_choi, '%d/%m/%Y') as ngay_choi,b.so_nguoi_choi,b.email_send,b.status,DATE_FORMAT(b.register_time, '%d/%m/%Y') as register
            ,c.game_id,c.game_name,c.ticket_code,c.game_price,
            d.time_id,d.time_title,TIME_FORMAT(d.time_value, '%H %i') time_value,a.mobile_inviter from tbl_player a 
            join tbl_schedure b on a.player_id = b.player_id 
            join tbl_game_info c on b.game_id = c.game_id 
            join tbl_time_game d on b.time_id = d.time_id where b.sc_id = " . $scid;

            $this->registry->db->select_special($sqlquery);
            $result = $this->registry->db->getResult();
            $this->registry->db->disconnect();
        }
        if ($result == null) {
            $result = array();
            $result["full_name"] = "";
            $result["email"] = "";
            $result["game_name"] = "";
            $result["ngay_choi"] = "";
            $result["time_title"] = "";
            $result["ticket_code"] = "";
            $result["so_nguoi_choi"] = "";
            $result["sc_id"] = "";
        }
        $this->registry->template->lichgame = $result;
        $this->registry->template->show('ajax/sendmailschedure');
    }

    public function addschedure() {
        $this->registry->db->connect();
        $sqlquery = "select game_id,CONCAT(game_name,'-',game_name_vn) game_name from tbl_game_info";
        $this->registry->db->select_special($sqlquery);
        $lgame = $this->registry->db->getResult();
        $sqlquery = "select time_id,time_title from tbl_time_game order by time_id";
        $this->registry->db->select_special($sqlquery);
        $ltime = $this->registry->db->getResult();
        $this->registry->db->disconnect();
        $this->registry->template->lgame = $lgame;
        $this->registry->template->ltime = $ltime;
        $this->registry->template->show('ajax/addschedure');
    }
    
    public function tooltipgameinfo(){
//        echo "Com nea no lên chưa";
        $this->registry->template->show('ajax/tooltipgameinfo');
    }
}

?>
