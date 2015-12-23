<?php

Class loginController Extends baseController {

    public function index() {
        $this->registry->template->show('login/login');
    }

    public function submitlogin() {
        $username = $_POST["username"];
        $userpass = $_POST["userpass"];
        $this->registry->db->connect();
        $sqlselect = "USERID,USERNAME,USERPASS,FULLNAME,EMAIL,IP,PHONENUMBER,ADDRESS,CHECKIP,image_url,status,pos_id";
        $order = null;
        $where = "USERNAME = '" . $username . "'";
        $this->registry->db->select('qtht_users', $sqlselect, $where, $order);
        $result = $this->registry->db->getResult();
        $this->registry->db->disconnect();
        if ($result == null) {
            echo "{\"code\":1,\"detail\":\"Sai Tài khoản/Mật khẩu. Bạn hãy thử lại!\"}";
        } else {
            if($result["USERPASS"] == $userpass) {
                $_SESSION[__USER_NAME] = $username;
                echo "{\"code\":0,\"detail\":\"Đăng nhập thành công!\"}";
            } else {
                echo "{\"code\":1,\"detail\":\"Sai Tài khoản/Mật khẩu. Bạn hãy thử lại!\"}";
            }
        }
    }

    public function logout() {
        unset($_SESSION[__USER_NAME]);
        $this->registry->template->show('login/login');
    }

}

?>
