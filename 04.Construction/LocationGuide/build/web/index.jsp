<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.quantrihethong.UserRoleLogic"%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Losers</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

        <!-- JQuery from Google APIs-->
        <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>

        <!-- Google Maps API v3 -->
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=geometry,drawing,weather,visualization&sensor=false&key=AIzaSyBC16OUS7APh1J0wJazqn4VMGDlOHFQAc4"></script>

        <!-- Bootstrap (getbootstrap.com) and Bootstrap-map-js (github.com/esri/bootstrap-map-js) -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/bootstrap.css"> <!-- include again to override certain styles -->
        <link rel="stylesheet" href="css/bootstrapmap.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <!-- Custom CSS styling -->
        <link rel="stylesheet" type="text/css" href="css/mapstyle.css">
        <!-- Google Webfonts -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet'  type='text/css'>

        <!-- Main JavaScript for controlling map -->
        <script src="js/infobox.js" type="text/javascript"></script>
        <!--<script src="js/icode-maps-arcgis.js" type="text/javascript"></script>-->
        <script type="text/javascript" src="js/icode-maps-google.js"></script>



    </head>
    <body class="claro" onload="initialize()">
        <div class="navbar navbar-inverse navbar-fixed-top" style="height: 40px; opacity: 0.8;">
            <div class="navbar-inner" style="padding-left: 20px;">
                <a class="e" href="index.html">
                    <img src="images/brand-white.png" alt="brand" style="width: 85px;vertical-align: middle">
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span id="username"></span><img src="images/icons/loginavatar.gif" alt="Avatar" class="avatar"><span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#"><span class="glyphicon glyphicon-wrench"></span> Settings</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                            </ul>
                        </li>              
                    </ul>
                </div>
            </div>
        </div>
        <!-- MAP CANVAS ===================================================================== -->
        <div class="container">
            <div id="map-container">
                <!-- BEGIN MAP AREA -->
                <div id="map_canvas">
                </div>
                <!-- END MAP AREA -->
            </div>
        </div>
        <div id="latlong">
            Mouse cursor (lat, lon)
        </div>

        <jsp:include page="templates/themes.jsp" />

    </body>
</html>