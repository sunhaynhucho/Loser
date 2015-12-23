<?php

Class contactController Extends baseController {

    public function index() {
        $this->registry->template->show('/contact/home');
    }

    public function home() {
        /*         * * should not have to call this here.... FIX ME ** */
        $this->registry->template->blog_heading = 'This is the blog heading';
        if ($this->lang == "vn") {
            $this->registry->template->blog_heading = 'Dịch sang tiếng việt nam';
        }

        $this->registry->template->blog_content = 'This is the blog content';
        $this->registry->template->blog_lonruot = 'This is the blog content';
//        $this->registry->template->la = $this->la;
        $this->registry->template->show('/contact/home');
    }

}

?>
