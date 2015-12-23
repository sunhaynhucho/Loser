
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar-inner" style="padding-left: 20px;">
    <a href="/home" style="float: left; padding: 10px 15px;">
        <img src="<%=request.getContextPath()%>/images/applications/brand-white.png" alt="brand" style="width: 85px;vertical-align: middle">
    </a>
    <div class="nav-collapse navbar-inverse-collapse">
        <ul class="nav">
            <li class="active"><a href="#">Trang chủ</a></li>
            <li><a href="#">Thiên hạ</a></li>
            <li><a href="#">Khám phá</a></li>
            <li><a href="#">Sự kiện</a></li>
        </ul>
        <ul class="nav pull-right">
            <!--                        <form id="searchBarForm" class="navbar-form navbar-nav form-horizontal" style="margin-right: 100px;margin-top: 0">
                                        <div id="searchGroup" class="form-group input-group">
                                            <span class="input-group-btn">
                                                <button id="searchBtn" class="btn btn-default" type="submit"  style="padding: 6px 12px;">
                                                    <span id="search-icon" class="glyphicon glyphicon-search" style="padding: 0"></span>
                                                </button>
                                            </span>
                                            <input id="search" class="form-control"  type="text" name="query" autocomplete="off" placeholder="Search for locations, peoples, image,..." style="height: 34px;">
                                        </div>
                                    </form>-->

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-bell"></span> <span class="alertCountLabel badge">0</span><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#" onclick="toggleAlertsPanel();
                            return false;">View Alerts <span class="alertCountLabel badge">0</span></a></li>
                    <li class="divider"></li>
                    <li><a href="#">Setup New Alerts</a></li>
                    <li><a href="#">Edit Alerts</a></li>
                </ul>
            </li>
            <!-- User login nav --> 
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span id="username"></span><img src="images/libraries/icons/loginavatar.gif" alt="Avatar" class="avatar"><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#"><span class="glyphicon glyphicon-wrench"></span> Settings</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </li>              
        </ul>
    </div>
</div>
