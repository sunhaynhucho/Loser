<?php

Class indexController Extends baseController {

    public function index() {
        /*         * * set a template variable ** */
        $this->registry->db->connect();
        $sqlselect1 = "";
        $sqlselect2 = "";
        if ($this->lang == "vn") {
            $sqlselect1 = "game_id,game_name_vn as gameName,game_content_vn as gameContent,game_url,game_difficulty,game_physical,game_number_min,game_number_max,image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions_vn as gamePromotions ";
            $sqlselect2 = "video_id, video_url,video_content_vn as videoContent,video_title_vn as videoTittle,video_url_image,video_seq ";
        } else {
            $sqlselect1 = "game_id,game_name as gameName,game_content as gameContent,game_url,game_difficulty,game_physical,game_number_min,game_number_max,image_url,game_time,DATE_FORMAT(created_time, '%d/%m/%Y'),DATE_FORMAT(updated_time, '%d/%m/%Y'),game_price,ticket_code,game_seq,game_promotions as gamePromotions ";
            $sqlselect2 = "video_id, video_url,video_content as videoContent,video_title as videoTittle,video_url_image,video_seq ";
        }
        $order = "game_seq";
        $this->registry->db->select('tbl_game_info', $sqlselect1, null, $order);
        $gameInfo = $this->registry->db->getResult();
        $order = "video_seq";
        $this->registry->db->select('tbl_video', $sqlselect2, null, $order);
        $playVideo = $this->registry->db->getResult();

        $this->registry->template->gameInfo = $gameInfo;
        $this->registry->template->playVideo = $playVideo;
        
        $this->registry->db->disconnect();
        /*         * * load the index template ** */
        $this->registry->template->show('index');
    }


}
?>
