<?php
Class franchiseController Extends baseController {

    public function index() {
        $this->registry->template->show('franchise/home');
    }

}
?>
