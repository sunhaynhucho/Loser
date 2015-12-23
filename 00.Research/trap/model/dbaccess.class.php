<?php

class dbaccess {
    /*     * * Declare instance ** */

    private static $instance = NULL;
    private $db_host = "";
    private $db_user = "";
    private $db_pass = "";
    private $db_name = "";
    private $con = "";
    private $numrows = 0;

    /**
     *
     * the constructor is set to private so
     * so nobody can create a new instance using new
     *
     */
    public function __construct($db_name, $db_host, $db_user, $db_pass) {
        /*         * * maybe set the db name here later ** */
        $this->db_host = $db_host;
        $this->db_name = $db_name;
        $this->db_pass = $db_pass;
        $this->db_user = $db_user;
    }

    /**
     *
     * Return DB instance or create intitial connection
     *
     * @return object (PDO)
     *
     * @access public
     *
     */
//    public static function getInstance() {
//        if (!self::$instance) {
////            self::$instance = new PDO("mysql:host=192.168.6.105;dbname=web_gameinfo", 'web_gameinfo', 'web_gameinfo@123');
////            ;
////            self::$instance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
//            self::$instance = new dbaccess(DB_NAME,DB_HOST,DB_USER,DB_PASSWORD);
//        }
//        return self::$instance;
//    }

    /**
     *
     * Like the constructor, we make __clone private
     * so nobody can clone the instance
     *
     */
    private function __clone() {
        
    }

    public function sayHello() {
        return "Hello world";
    }

    public function connect() {
        if (!$this->con) {
            $myconn = @mysql_connect($this->db_host, $this->db_user, $this->db_pass);
            mysql_set_charset('utf8', $myconn);
            if ($myconn) {
                $seldb = @mysql_select_db($this->db_name, $myconn);
                if ($seldb) {
                    $this->con = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public function disconnect() {
        if ($this->con) {
            if (@mysql_close()) {
                $this->con = false;
                return true;
            } else {
                return false;
            }
        }
    }

    public function select_special($sql) {
        $this->result = array();
        $this->numrows = 0;
        $query = @mysql_query($sql);
        if ($query) {
            $this->numResults = mysql_num_rows($query);
            for ($i = 0; $i < $this->numResults; $i++) {
                $r = mysql_fetch_array($query);
                $key = array_keys($r);
                for ($x = 0; $x < count($key); $x++) {
                    // Sanitizes keys so only alphavalues are allowed
                    if (!is_int($key[$x])) {
                        if (mysql_num_rows($query) > 1) {
                            $this->numrows = 2;
                            $this->result[$i][$key[$x]] = $r[$key[$x]];
                        } else if (mysql_num_rows($query) < 1) {
                            $this->result = null;
                            $this->numrows = 0;
                        } else {
                            $this->result[$key[$x]] = $r[$key[$x]];
                            $this->numrows = 1;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public function select($table, $rows = '*', $where = null, $order = null) {
        $this->result = array();
        $this->numrows = 0;
        $q = 'SELECT ' . $rows . ' FROM ' . $table;
        if ($where != null)
            $q .= ' WHERE ' . $where;
        if ($order != null)
            $q .= ' ORDER BY ' . $order;
        if ($this->tableExists($table)) {
            $query = @mysql_query($q);
            if ($query) {
                $this->numResults = mysql_num_rows($query);
                for ($i = 0; $i < $this->numResults; $i++) {
                    $r = mysql_fetch_array($query);
                    $key = array_keys($r);
                    for ($x = 0; $x < count($key); $x++) {
                        // Sanitizes keys so only alphavalues are allowed
                        if (!is_int($key[$x])) {
                            if (mysql_num_rows($query) > 1) {
                                $this->numrows = 2;
                                $this->result[$i][$key[$x]] = $r[$key[$x]];
                            } else if (mysql_num_rows($query) < 1) {
                                $this->result = null;
                                $this->numrows = 0;
                            } else {
                                $this->result[$key[$x]] = $r[$key[$x]];
                                $this->numrows = 1;
                            }
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        else
            return false;
    }

    public function insert($table, $values, $rows = null) {
        if ($this->tableExists($table)) {
            $insert = 'INSERT INTO ' . $table;
            if ($rows != null) {
                $insert .= ' (' . $rows . ')';
            }

            for ($i = 0; $i < count($values); $i++) {
                if (is_string($values[$i]))
                    $values[$i] = '"' . $values[$i] . '"';
            }
            $values = implode(',', $values);
            $insert .= ' VALUES (' . $values . ')';
            $ins = @mysql_query($insert);
            if ($ins) {
                return true;
            } else {
                return false;
            }
        }
    }

    public function insert_special($table, $sql) {
        if ($this->tableExists($table)) {
            $ins = @mysql_query($sql);
            if ($ins) {
                return true;
            } else {
                return false;
            }
        }
    }

    public function delete($table, $where = null) {
        if ($this->tableExists($table)) {
            if ($where == null) {
                $delete = 'DELETE ' . $table;
            } else {
                $delete = 'DELETE FROM ' . $table . ' WHERE ' . $where;
            }
            $del = @mysql_query($delete);

            if ($del) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public function update($table, $rows, $where) {
        if ($this->tableExists($table)) {
            $update = 'UPDATE ' . $table . ' SET ';
            $keys = array_keys($rows);
            for ($i = 0; $i < count($rows); $i++) {
                if (is_string($rows[$keys[$i]])) {
                    $update .= $keys[$i] . '="' . $rows[$keys[$i]] . '"';
                } else {
                    $update .= $keys[$i] . '=' . $rows[$keys[$i]];
                }
                if ($i != count($rows) - 1) {
                    $update .= ',';
                }
            }
            $update .= ' WHERE ' . $where;
            $query = @mysql_query($update);
            if ($query) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public function update_special($table, $sql) {
        if ($this->tableExists($table)) {
            $query = @mysql_query($sql);
            if ($query) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public function getResult() {
        return $this->result;
    }
    
    public function getNumRow() {
        return $this->numrows;
    }

    private $result = array();

    private function tableExists($table) {
        $tablesInDb = @mysql_query('SHOW TABLES FROM ' . $this->db_name . ' LIKE "' . $table . '"');
        if ($tablesInDb) {
            if (mysql_num_rows($tablesInDb) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

}

/* * * end of class ** */
?>
