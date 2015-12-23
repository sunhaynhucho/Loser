<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String appName = Constant.MODULE_NAME;
    String urlWeb = request.getContextPath();
    String strName = "";
    if (request.getParameter("c") != null) {
        strName = (String) request.getParameter("c");
    }
%>

<input type="hidden" id="divName" value="<%=strName%>" />
<script type="text/javascript">

    $(document).ready(function() {
        var divName = $("#divName").val();
        $('.toggle:not(.toggle-open)').addClass('toggle-closed').parents('li').children('ul').hide();
        if ($.browser != null) {
            if ($.browser.msie) {
                $('#menu ul.navmenu li:last-child .menutop').css('border-bottom', '1px solid #CCC');
            }
        }

        $('.toggle').click(function() {
            if ($(this).hasClass('toggle-open')) {
                $(this).removeClass('toggle-open').addClass('toggle-closed').empty('').append('+').parents('li').children('ul').slideUp(250);
                $(this).parent('.menutop').removeClass('menutop-open').addClass('menutop-closed');
            } else {
                $(this).parent('.menutop').removeClass('menutop-closed').addClass('menutop-open');
                $(this).removeClass('toggle-closed').addClass('toggle-open').empty('').append('&ndash;').parents('li').children('ul').slideDown(250);
            }

        })
        if (divName != null || divName != "") {
            $("#" + divName).parent('.menutop').removeClass('menutop-closed').addClass('menutop-open');
            $("#" + divName).removeClass('toggle-closed').addClass('toggle-open').empty('').append('&ndash;').parents('li').children('ul').slideDown(250);
        }


    })
</script>
<%
    String userName = OmapSessionUtils.getUserName(request);
    List lPages = QuanTriHeThongDataLogic.getListPageByUserName(userName);
    boolean ttHome = true;

    boolean qthtHome = false;
    boolean qthtUser = false;
    boolean qthtUserRole = false;
    boolean qthtRole = false;
    boolean qthtRolePage = false;
    boolean qthtPage = false;
    boolean qthtUserPos = false;
    boolean qthtUserHistory = false;
    boolean qlcvHome = false;
    boolean qltlHome = false;
    boolean qltlDanhMuc = false;
    boolean qthtXuatBaoCao = false;
    boolean qltlPermistion = false;

    if (lPages != null && lPages.size() > 0) {
        ttHome = true;
        for (Object o : lPages) {
            Object[] oPage = (Object[]) o;
            String key = String.valueOf(oPage[4]);

            if (key.equals("quantrihethong/home")) {
                qthtHome = true;
            }
            if (key.equals("quantrihethong/users")) {
                qthtUser = true;
            }
            if (key.equals("quantrihethong/user_roles")) {
                qthtUserRole = true;
            }
            if (key.equals("quantrihethong/roles")) {
                qthtRole = true;
            }
            if (key.equals("quantrihethong/role_pages")) {
                qthtRolePage = true;
            }
            if (key.equals("quantrihethong/pages")) {
                qthtPage = true;
            }
            if (key.equals("quantrihethong/user_history")) {
                qthtUserHistory = true;
            }
            if (key.equals("quantrihethong/user_position")) {
                qthtUserPos = true;
            }
            if (key.equals("quanlycongviec/home")) {
                qlcvHome = true;
            }
            if (key.equals("quanlycongviec/managerworks")) {
                qlcvHome = true;
            }
            if (key.equals("quanlytailieu/home")) {
                qltlHome = true;
            }
            if (key.equals("quanlytailieu/category")) {
                qltlDanhMuc = true;
            }
            if (key.equals("quanlytailieu/permission")) {
                qltlPermistion = true;
            }
            if (key.equals("quantrihethong/xuatbaocao")){
                qthtXuatBaoCao = true;
            }

        }
    }

%>

<div id="menu">	
    <ul class="navmenu">
        <% if (ttHome) {%>
        <li><div class="menutop"><a href='<%=urlWeb%>/view/home'>Trang chủ</a><div id="view" class="toggle">+</div></div>
        </li>
        <% }%>
        <% if (qlcvHome) {%>
        <li><div class="menutop"><a href="<%=urlWeb%>/quanlycongviec/managerworks">Quản lý công việc</a><div id="quanlycongviec" class="toggle">+</div></div>
            <% if (qlcvHome) {%>
            <ul class="submenu">
                <li><a href="<%=urlWeb%>/quanlycongviec/home">Quản lý công việc</a></li>
            </ul>
            <% }%>
        </li>
        <% }%>
        <% if (qltlHome) {%>
        <li><div class="menutop"><a href="<%=urlWeb%>/quanlytailieu/home">Quản lý tài liệu </a><div id="quanlytailieu" class="toggle">+</div></div>

            <% if (qltlDanhMuc) {%>
            <ul class="submenu">
                <li><a href="<%=urlWeb%>/quanlytailieu/category">Danh mục tài liệu</a></li>
            </ul>
            <% }%>
            <% if (qltlPermistion) {%>
            <ul class="submenu">
                <li><a href="<%=urlWeb%>/quanlytailieu/permission">Phân quyền tài liệu</a></li>
            </ul>
            <% }%>
        </li>
        <% }%>
        <% if (qthtHome) {%>
        <li><div class="menutop"><a href="<%=urlWeb%>/quantrihethong/home">Quản trị hệ thống</a><div id="quantrihethong" class="toggle">+</div></div>
            <ul class="submenu">
                <% if (qthtUser) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/users">Người dùng</a></li>
                    <% }%>
                    <% if (qthtUserRole) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/user_roles">Người dùng - Vai trò</a></li>
                    <% }%>
                    <% if (qthtUserPos) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/user_position">Quản lý nhân viên</a></li>
                    <% }%>
                    <% if (qthtRole) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/roles">Vai trò</a></li>
                    <% }%>
                    <% if (qthtRolePage) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/role_pages">Vai trò - Chức năng</a></li>
                    <% }%>
                    <% if (qthtPage) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/pages">Chức năng</a></li>
                    <% }%>
                    <% if (qthtUserHistory) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/user_history">Lịch sử truy nhập web</a></li>
                    <% }%>
                    <% if (qthtXuatBaoCao) {%>
                <li><a href="<%=urlWeb%>/quantrihethong/xuatbaocao">Xuất báo cáo</a></li>
                    <% }%>
            </ul>
        </li>
        <% }%>
    </ul>
</div>
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-2577834-5']);
    _gaq.push(['_setDomainName', 'mediaformations.com']);
    _gaq.push(['_trackPageview']);

    (function() {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = "<%=urlWeb%>/js/ga.js";
        //        alert(ga.src);
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

</script>