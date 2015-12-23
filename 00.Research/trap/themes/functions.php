<?php

include('includes/class-theme-methods.php');

function do_main_nav() {
    global $dtm;

    $class = "main_nav";
    
//    $items_array = array(
//        array('text' => 'Home', 'url' => __SITE_URL),
//        array('text' => 'The game', 'url' => __SITE_URL.'/thegame/home'),
//        array('text' => 'About', 'url' => __SITE_URL.'/blog/view')
//    );

    return $dtm->navigation($res, $class);
}

function do_html_title($page_title) {
    $title = $page_title . ' | John Morris Demo Theme';

    return $title;
}

?>