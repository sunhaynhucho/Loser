<?php

Class voucherController Extends baseController {

    public function index() {

        $this->registry->template->show('voucher/home');
    }

    public function submitresult() {
        //$this->registry->template->show('voucher/submitresult');
        $vcname = $_POST["nameVoucher"];
        $vcemail = $_POST["mailVoucher"];
        $vcphone = $_POST["phoneVoucher"];
        $vcguest = $_POST["guestsVoucher"];
        $vcmsg = $_POST["msgVoucher"];
        $this->registry->db->connect();
        $values = array($vcname,$vcemail,$vcphone,$vcguest,$vcmsg);
        $rows = "voucher_name,voucher_mail,voucher_phone,voucher_guests,voucher_msg";
        $result = $this->registry->db->insert('tbl_game_info_voucher', $values, $rows);
        $this->registry->db->disconnect();
        if($result){
            echo "{\"code\":0,\"detail\":\"Bạn đã đăng ký thành công\"}";
        }else{
            echo "{\"code\":1,\"detail\":\"Bạn đã đăng ký không thành công. Vui lòng lần sau quay lại !!!\"}";
        }
    }

    public function successvoucher() {
        $this->registry->template->show('voucher/successvoucher');
    }

}

?>
