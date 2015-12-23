<?php

class router {
    /*
     * @the registry
     */

    private $registry;

    /*
     * @the controller path
     */
    private $path;
    private $args = array();
    public $file;
    public $controller;
    public $action;
    public $lang = null;

    function __construct($registry) {
        $this->registry = $registry;
    }

    /**
     *
     * @set controller directory path
     *
     * @param string $path
     *
     * @return void
     *
     */
    function setPath($path) {

        /*         * * check if path i sa directory ** */
        if (is_dir($path) == false) {
            throw new Exception('Invalid controller path: `' . $path . '`');
        }
        /*         * * set the path ** */
        $this->path = $path;
    }

    /**
     *
     * @load the controller
     *
     * @access public
     *
     * @return void
     *
     */
    public function loader() {
        //header('Cache-control: private'); // IE 6 FIX

        if (isSet($_GET['lang'])) {
            $this->lang = $_GET['lang'];

// register the session and set the cookie
            $_SESSION['lang'] = $this->lang;
            setcookie("lang", $this->lang, time() + (3600 * 24 * 30));
        } else if (isSet($_SESSION['lang'])) {
            $this->lang = $_SESSION['lang'];
        } else if (isSet($_COOKIE['lang'])) {
            $this->lang = $_COOKIE['lang'];
        } else {
            $this->lang = 'en';
        }
        /*         * * check the route ** */
        $this->getController();

        /*         * * if the file is not there diaf ** */
        if (is_readable($this->file) == false) {
            $this->file = $this->path . '/error404.php';
            $this->controller = 'error404';
        }
        /*         * * include AppUtils ** */
        include __SITE_PATH.'/includes/AppUtils.php';
        /*         * * include the controller ** */
        include $this->file;

        /*         * * a new controller class instance ** */
        $class = $this->controller . 'Controller';
        $controller = new $class($this->registry, $this->lang);
        //$controller = new $class($this->registry);

        /*         * * check if the action is callable ** */
        if (is_callable(array($controller, $this->action)) == false) {
            $action = 'index';
        } else {
            $action = $this->action;
        }
        /*         * * run the action ** */
        $controller->$action();
    }

    /**
     *
     * @get the controller
     *
     * @access private
     *
     * @return void
     *
     */
    private function getController() {

        /*         * * get the route from the url ** */
        $route = (empty($_GET['rt'])) ? '' : $_GET['rt'];

        if (empty($route)) {
            $route = 'index';
        } else {
            /*             * * get the parts of the route ** */
            if ($this->registry->typeview == "admin") {
                if (!isSet($_SESSION[__USER_NAME])) {
                    $route = "login/login";
                }
            }
            $parts = explode('/', $route);

            $this->controller = $parts[0];
            if (isset($parts[1])) {
                $this->action = $parts[1];
            }
        }

        if (empty($this->controller)) {
            $this->controller = 'index';
        }

        /*         * * Get action ** */
        if (empty($this->action)) {
            $this->action = 'index';
        }

        /*         * * set the file path ** */
        $this->file = $this->path . '/' . $this->controller . 'Controller.php';
    }

}

?>
