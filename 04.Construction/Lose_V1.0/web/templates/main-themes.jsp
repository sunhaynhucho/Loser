<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ${requestScope.title} </title>

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=geometry,drawing,weather,visualization&sensor=false&key=AIzaSyBC16OUS7APh1J0wJazqn4VMGDlOHFQAc4"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/libraries/jquery.min.js"></script>  
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/libraries/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/applications/utils.js"></script>  
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/applications/infobubble.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/applications/infobox.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/applications/map.js"></script>

        <link rel="stylesheet" type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet'  type='text/css'>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/applications/mapstyle.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/libraries/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/libraries/bootstrapmap.css" />
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" style="height: 40px;">
            <jsp:include page="${requestScope.header}" />
        </div>
        <div class="container">
            <jsp:include page="${requestScope.body}" />
            <!--                <div id="map-container">
                                 BEGIN MAP AREA 
                                <div id="map_canvas">
                                </div>
                                 END MAP AREA 
                            </div>-->
        </div>
        <div class="footer">
            <%--<jsp:include page="${requestScope.footer}" />--%>
        </div>
    </body>
</html>
