<?php

Class quantrihethongController Extends baseController {

    public function index() {
        $this->registry->template->blog_heading = 'This is the blog heading';
        if ($this->lang == "vn") {
            $this->registry->template->blog_heading = 'Dịch sang tiếng việt nam';
        }
        $this->registry->template->sayhello = $this->registry->db->sayHello();
//        $this->registry->template->la = $this->la;
        $this->registry->template->show('/quantrihethong/home');
    }
    
    public function users(){
        $this->registry->template->show('/quantrihethong/users');
    }

}

?>
