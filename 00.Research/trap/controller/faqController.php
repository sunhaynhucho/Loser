<?php

Class faqController Extends baseController {

    public function index() {
        $sqlselect = 'faq_id, faq_answers,faq_questions';
        if ($this->lang == 'vn') {
            $sqlselect = 'faq_id, faq_answers_vn as faq_answers, faq_questions_vn as faq_questions';
        }
        $this->registry->db->connect();
        $this->registry->db->select('tbl_faq', $sqlselect, null, 'faq_id');
        /*         * * set a template variable ** */
        $this->registry->template->oFAQ = $this->registry->db->getResult();
        /*         * * load the index template ** */
        $this->registry->db->disconnect();
        $this->registry->template->show('faq/home');
    }

}

?>
