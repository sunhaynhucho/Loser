<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

class AppUtils {

    public function cSoNguoiChoi($oMin, $oMax) {
        return $oMin . " đến " . $oMax . " người";
    }

    public function cThoiGianChoiGame($oTime) {
        return $oTime . " phút";
    }

    public function cGiaTienGame($gia) {
        return $gia . " vnd";
    }

    public function cDoKhoGame($oTime) {
        return $oTime . " sao";
    }

    public function cObjectDaGuiEmail($oTime) {

        if ($oTime == 1) {
            return "<img src='../css/icons/ok.png' style=\"float:left;\" />OK";
        }
        return "<img src='../css/icons/no.png' style=\"float:left;\" />NOK";
    }

    public function cObjectGioiTinh($oTime) {
        if ($oTime == 0) {
            return "Nam";
        }
        return "Nữ";
    }

    public function cObjectTrangThai($oTime) {
        if ($oTime == 0) {
            return "<img src='../css/icons/help.png' style=\"float:left;\" />Chưa xác nhận";
        }
        if ($oTime == 1) {
            return "<img src='../css/icons/ok.png' style=\"float:left;\" />Xác nhận OK";
        }
        if ($oTime == 2) {
            return "<img src='../css/icons/cancel.png' style=\"float:left;\" />Đã Hủy";
        }
        if ($oTime == 3) {
            return "<img src='../css/icons/tb.png' style=\"float:left;\" />Black list";
        }
        if ($oTime == 4) {
            return "<img src='../css/icons/help.png' style=\"float:left;\" />Đã chơi";
        }
        return "N/A";
    }

}

function getTimeLocal() {
    date_default_timezone_set('UTC');
    $today = date("YmdHis");
    return $today;
}

function cObjectDaGuiEmail($oTime) {
    if ($oTime == 1) {
        return "<img src='../themes/css/icons/ok.png' style=\"float:left;\" />OK";
    }
    return "<img src='../themes/css/icons/no.png' style=\"float:left;\" />NOK";
}

function cObjectTrangThai($oTime) {
    if ($oTime == 0) {
        return "<img src='../themes/css/icons/help.png' style=\"float:left;\" />Chưa xác nhận";
    }
    if ($oTime == 1) {
        return "<img src='../themes/css/icons/ok.png' style=\"float:left;\" />Xác nhận OK";
    }
    if ($oTime == 2) {
        return "<img src='../themes/css/icons/cancel.png' style=\"float:left;\" />Đã Hủy";
    }
    if ($oTime == 3) {
        return "<img src='../themes/css/icons/tb.png' style=\"float:left;\" />Black list";
    }
    if ($oTime == 4) {
        return "<img src='../themes/css/icons/help.png' style=\"float:left;\" />Đã chơi";
    }
    return "N/A";
}
?>
