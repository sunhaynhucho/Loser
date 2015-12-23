<?php

session_start();
error_reporting(E_ALL);

/* * * define the site path ** */
$site_path = realpath(dirname(__FILE__));
define('__SITE_PATH', $site_path);
define('__USER_NAME', "USER_NAME");
define('__SITE_URL', "/trap");

/* * * include the init.php file ** */
include 'config.php';
include 'includes/init.php';
// include 'themes/common.php';
include 'themes/functions.php';

/* * * load the router ** */
$registry->router = new router($registry);
$registry->typeview = "admin";
/* * * set the controller path ** */
$registry->router->setPath(__SITE_PATH . '/controller');

/* * * load up the template ** */
$registry->template = new template($registry);

/* * * load the controller ** */
$registry->router->loader();
?>
