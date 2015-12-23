<?php

Class galleryController Extends baseController {

    public function index() {
        /*         * * set a template variable ** */
        $this->registry->template->welcome = 'Welcome to PHPRO MVC';
        if ($this->lang == "vn") {
            $this->registry->template->welcome = 'Chào mừng bạn đến việt nam';
        }
        /*         * * load the index template ** */
        $this->registry->template->show('index');
    }

    public function home() {

        $gameId = $_GET["gameID"];

        $this->registry->db->connect();
        $sqlselect1 = "";
        $sqlselect2 = "";
        $sqlselect3 = "";
        if ($this->lang == "en") {
            $sqlselect1 = "game_id,game_name as gameName,game_content as gameContent, game_url,game_difficulty,game_physical,game_number_min,game_number_max, image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions as gamePromotions ";
            $sqlselect2 = "image_id, image_name,image_seq ";
            $sqlselect3 = "image_id, image_name,image_seq ";
        } else {
            $sqlselect1 = "game_id,game_name_vn as gameName,game_content_vn as gameContent,game_url,game_difficulty,game_physical,game_number_min,game_number_max,image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions_vn as gamePromotions ";
            $sqlselect2 = "image_id, image_name,image_seq ";
            $sqlselect3 = "image_id, image_name,image_seq ";
        }

        $this->registry->db->select('tbl_game_image', $sqlselect2, ' image_type = 0 AND game_id = "' . $gameId . '"', null);
        $listGameImageNho = $this->registry->db->getResult();

        $this->registry->db->select('tbl_game_info', $sqlselect1, ' game_id = "' . $gameId . '"', null);
        $listGameImageInfo = $this->registry->db->getResult();

        $this->registry->db->select('tbl_game_image', $sqlselect3, ' image_type = 1 AND game_id = "' . $gameId . '"', null);
        $listGameImageNguoichoi = $this->registry->db->getResult();

        $soNguoiChoi = "";
        if ($this->lang == "en") {
            $soNguoiChoi = $listGameImageInfo['game_number_min'] . " to " . $listGameImageInfo['game_number_max'] . " people";
        } else {
            $soNguoiChoi = $listGameImageInfo['game_number_min'] . " đến " . $listGameImageInfo['game_number_max'] . " người";
        }

        $this->registry->template->soNguoiChoi = $soNguoiChoi;
        $this->registry->template->listGameImageInfo = $listGameImageInfo;
        $this->registry->template->listGameImageNho = $listGameImageNho;
        $this->registry->template->listGameImageNguoichoi = $listGameImageNguoichoi;
        $this->registry->db->disconnect();
        $this->registry->template->show('gallery/home');
    }

}

?>
