package com.loser.map.commons;

public class Constant {

    public final static String BASE_FOLDER = System.getProperty("user.home") + java.io.File.separator;
    public final static String LOG_FOLDER = BASE_FOLDER + "logs" + java.io.File.separator;
    public final static String CONFIG_FOLDER = BASE_FOLDER + "config" + java.io.File.separator + "locationguide" + java.io.File.separator;
    public final static int AIS_MONITOR_TYPE = 0;
    public final static int AIS_DATA_TYPE = 1;
    public final static String MODULE_NAME = "EasySMS";
    public final static String CONFIG_NAME = "EasySMS";
    public final static String CONFIG_FILE_NAME = "websettings.xml";
    
    public final static String LOGGER_NAME = "EasySMS";
    public final static String SYS_CONFIG_NAME = "EasySMS";
    public final static String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
    public final static String USER_NAME = "UserLogin";
    public final static String USER_OBJECT = "OmapUser";
    public static final String UPLOAD_DIRECTORY = "data" + java.io.File.separator + "uploads" + java.io.File.separator;
    public static final String UPLOAD_WEB_DIRECTORY = "data/uploads/";
    public static final String UPLOAD_DIRECTORY_USER = "images" + java.io.File.separator + "imgUser" + java.io.File.separator;
    public static final String UPLOAD_WEB_USER = "images/imgUser/";
    
    // upload settings
    public static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    public static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    public static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    public static final String ACTION_KT_HOME = "Gửi SMS";
    
    public static final String ACTION_BCTK_HOME = "Báo cáo và thống kê";
    public static final String ACTION_BCTK_BAOCAOTHEOTHANG = "Báo cáo và thống kê => Báo cáo theo tháng";
    public static final String ACTION_BCTK_BAOCAOTHEONGAY = "Báo cáo và thống kê => Báo cáo theo ngày";
    public static final String ACTION_BCTK_GIAMSATTRUYCAPNGUOISUDUNG = "Báo cáo và thống kê => Giám sát truy cập người sử dụng";
    public static final String ACTION_BCTK_TONGHOPNGAY = "Báo cáo và thống kê => Tổng hợp ngày";
    public static final String ACTION_BCTK_TONGHOPTHANG = "Báo cáo và thống kê => Tổng hợp tháng";
    public static final String ACTION_BCTK_CACTINNHANCHUAGUI = "Báo cáo và thống kê => Các tin nhắn chưa gửi";
    
    public static final String ACTION_QTHT_HOME = "Quản trị hệ thống";
    public static final String ACTION_QTHT_USER = "Quản trị hệ thống => Người dùng";
    public static final String ACTION_QTHT_USERROLE = "Quản trị hệ thống => Người dùng - vai trò";
    public static final String ACTION_QTHT_ROLE = "Quản trị hệ thống => Vai trò";
    public static final String ACTION_QTHT_ROLEPAGE = "Quản trị hệ thống => Vai trò - Chức năng";
    public static final String ACTION_QTHT_PAGE = "Quản trị hệ thống => Chức năng";
    public static final String ACTION_QTHT_USERHISTORY = "Quản trị hệ thống => Lịch sử truy nhập web";
    
    public static final String ACTION_LOGIN = "Tài khoản => Đăng nhập";
    public static final String ACTION_LOGOUT = "Tài khoản => Đăng xuất";
    public static final String ACTION_EDITUSER = "Tài khoản => Chỉnh sửa thông tin người dùng";
    
    public static final int ERR_FORMAT = 1;
    public static final int ERR_NOT_MONEY = 2;
    public static final int ERR_ADD_SMS_FAILE = 3;
    public static final int ERR_UNKNOW_SUBMIT = 4;
    public static final int ERR_OPERATOR_UNKNOW = 5;
    public static final int ADD_SMS_OK = 0;
    
}

