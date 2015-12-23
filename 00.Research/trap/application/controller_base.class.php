<?php

Abstract Class baseController {

/*
 * @registry object
 */
protected $registry;
protected $lang;

function __construct($registry,$lang) {
	$this->registry = $registry;
        $this->lang = $lang;
}

/**
 * @all controllers must contain an index method
 */
abstract function index();
}

?>
