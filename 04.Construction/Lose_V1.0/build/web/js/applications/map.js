/* -------------------------------------------------------------------------------- */
/* global google */

/**
 *  Global objects 
 */
/*
 * 
 */
var iconBase = '/images/applications/';
/*
 * 
 * @type google.maps.Map
 * map object using all under function
 */
var map;
/*
 * 
 * @type object
 * bound of current screen view
 */
var viewBounds;

var theFirstRunTime = true;
var isSelectedPlace = false;
var fixZoom = 11;
var maxClusterZoom = 16;
var done = true;
var timeOut;
var isZoomChanged = false;
/* -------------------------------------------------------------------------------- */
/** Initialize, called on main page load
 */
function initialize() {
    //get default property of map
    var defaultZoom;
    var data = getParameterByName('data');
    var centerLat;
    var centerLon;
    var centerCoord;
    if (data != '') {
        var datas = data.split(',');
        centerLat = parseFloat(datas[0]);
        centerLon = parseFloat(datas[1]);
        defaultZoom = parseInt(datas[2]);
        centerCoord = new google.maps.LatLng(centerLat, centerLon); //Zoomed out world view
    }
    else {
        centerCoord = new google.maps.LatLng(20.0, 105.0); //Zoomed out world view
        defaultZoom = 5;
    }

//-------------------setup map on startup------------------------
    var controlStyle;
    controlStyle = google.maps.MapTypeControlStyle.HORIZONTAL_BAR;

    var mapOptions = {
        zoom: defaultZoom,
        center: centerCoord,
        scaleControl: true,
        streetViewControl: false,
        overviewMapControl: true,
        zoomControl: true,
        zoomControlOptions: {
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        mapTypeId: google.maps.MapTypeId.HYBRID,
        mapTypeControlOptions: {
            mapTypeIds: [google.maps.MapTypeId.ROADMAP,
                google.maps.MapTypeId.SATELLITE,
                google.maps.MapTypeId.HYBRID,
                google.maps.MapTypeId.TERRAIN,
                'OpenStreetMap'
            ],
            style: controlStyle,
            position: google.maps.ControlPosition.LEFT_BOTTOM
        },
        minZoom: 5,
        maxZoom: 22,
        panControl: false
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
    //Set default map layer
    map.setMapTypeId(google.maps.MapTypeId.ROADMAP);
    //Define OSM map type pointing at the OpenStreetMap tile server
    map.mapTypes.set("OpenStreetMap", new google.maps.ImageMapType({
        getTileUrl: function (coord, zoom) {
            return "http://tile.openstreetmap.org/" + zoom + "/" + coord.x + "/" + coord.y + ".png";
        },
        tileSize: new google.maps.Size(256, 256),
        name: "OpenStreetMap",
        maxZoom: 22
    }));
    var styles = [
        {
            featureType: "poi",
            elementType: "all",
            stylers: [
                {visibility: "off"}
            ]
        }
    ];

    map.setOptions({styles: styles});
    google.maps.event.addListener(map, 'idle', function () {
//        if (theFirstRunTime) {
//            clusterFillLayer('reload');
//            theFirstRunTime = false;
//        }
    });
    google.maps.event.addListener(map, 'mousemove', function (event) {
        document.getElementById('latlong').innerHTML =
                Math.round(event.latLng.lat() * 10000000) / 10000000 + ', ' + Math.round(event.latLng.lng() * 10000000) / 10000000;
    });
    google.maps.event.addListener(map, 'zoom_changed', function (event) {
        clusterClearAll();
        isZoomChanged = true;
//        placeClearAll();
    });
    var zoom = {oldZoom: defaultZoom, newZoom: defaultZoom};
    google.maps.event.addListener(map, 'bounds_changed', function (event) {
        clearTimeout(timeOut);
        winUrlUpdate();
        viewBoundUpdate();
        timeOut = setTimeout(onBoundsChanged, 500, zoom);
//        zoom = currentZoom;
    });
}

/**
 * Comment
 */
function onBoundsChanged(zoom) {
    zoom.newZoom = map.getZoom();
    if (theFirstRunTime) {
        clusterFillLayer('reload');
        theFirstRunTime = false;
    } else {
        if (zoom.newZoom > fixZoom) {
            if (zoom.newZoom == zoom.oldZoom) {
                clusterFillLayer('update');
            } else {
                clusterFillLayer('reload');
                zoom.oldZoom = map.getZoom();
            }
        } else {
            placeClearAll();
            isSelectedPlace = false;
            if (zoom.newZoom == zoom.oldZoom) {
                if (isZoomChanged) {
                    clusterClearAll();
                    clusterFillLayer('reload');
                } else {
                    clusterFillLayer('update');

                }
            } else {
                isZoomChanged = true;
                clusterClearAll();
                clusterFillLayer('reload');
                zoom.oldZoom = map.getZoom();
            }
        }
    }
    isZoomChanged = false;
}
$(function initializeLayers() {
});

/**
 * Comment
 */
function winUrlUpdate() {
    var path = location.pathname;
    var url = '';
    var full = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + path;
    var center = map.getBounds().getCenter();
    var zoom = map.getZoom();
    var data = center.lat() + ',' + center.lng() + ',' + zoom;
    url += full + '?data=' + data;
    history.pushState(null, 'Loser', '?data=' + data);
}

/*
 * 
 * @returns {undefined}
 * this function is called when another event of map make a change of view bound
 */
function viewBoundUpdate() {
    var bounds = map.getBounds();
    var ne = bounds.getNorthEast();
    var sw = bounds.getSouthWest();
    var viewMinLat = sw.lat();
    var viewMaxLat = ne.lat();
    var viewMinLon = sw.lng();
    var viewMaxLon = ne.lng();
    var minLat = viewMinLat; // - latLonBuffer;
    var maxLat = viewMaxLat; // + latLonBuffer;
    var minLon = viewMinLon; // - latLonBuffer;
    var maxLon = viewMaxLon; // + latLonBuffer;

    latlonbounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(minLat, minLon),
            new google.maps.LatLng(maxLat, maxLon));
    //Update global view bounds
    viewBounds = {
        minLat: minLat,
        maxLat: maxLat,
        minLon: minLon,
        maxLon: maxLon
    };
}

/*------------------------------Global object-------------------------------
 * 
 * @type Array
 * array of current cluster into current view bound
 */
var innerClusters = [];
var outerClusters = [];
/*
 * 
 * @type Array
 * array of cluster 
 */
var innerClusterBounds = [];
var outerClusterBounds = [];
var removedClusters = [];
var appendClusters = [];
var clusterHasSinglePlace = [];
/*------------------------------Global object-------------------------------
 * 
 * @type Number|@exp;rows|height
 * Global variable using in under functions
 */
var columns = 24; //number of clumns on map
var rows = 24;//number of rows on map
//--------------------------------------------------------------------------

/**
 * Comment
 */
function clusterFillLayer(type) {
//    if (map.getZoom() > fixZoom) {
//        rows = 8;
//        columns = 8;
//    } else {
//        rows = 24;
//        columns = 24;
//    }
    var minlat = viewBounds.minLat;
    var maxlat = viewBounds.maxLat;
    var minlon = viewBounds.minLon;
    var maxlon = viewBounds.maxLon;
    if (type == 'reload') {
        innerClusterBounds = [];
        outerClusterBounds = [];
        innerClusters = [];
        outerClusters = [];
        var currentWidth = 0.0;
        var currentHeight = 0.0;
        currentHeight = Math.abs(maxlat - minlat);
        if (minlon > maxlon) {
            currentWidth = Math.abs(maxlon + 360 - minlon);
        } else {
            currentWidth = Math.abs(maxlon - minlon);
        }
        var dlat = currentHeight / rows;
        var dlon = currentWidth / columns;
        var newRow = parseInt(180 / dlat);
        var newColumn = parseInt(360 / dlon);
        $.ajax({
            type: "POST",
            url: "/GetClusters",
            dataType: 'json',
            data: {
                minlat: minlat,
                maxlat: maxlat,
                minlon: minlon,
                maxlon: maxlon,
                row: newRow,
                column: newColumn
            },
            success: function (data, textStatus, jqXHR) {
                innerClusterBounds = data[0];
                outerClusterBounds = data[1];
                $.each(innerClusterBounds, function (i, clusterBound) {
                    clusterCreateOverlay(clusterBound, 'inner');
                });
                $.each(outerClusterBounds, function (j, clusterBound) {
                    clusterCreateOverlay(clusterBound, 'outer');
                });
                clusterDrawOnMap('reload');
                if (map.getZoom() > fixZoom) {
                    if (!isSelectedPlace) {
                        placeFillLayer('select');
                        isSelectedPlace = true;
                    } else {
                        placeFillLayer('update');
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('lỗi cmnr:' + errorThrown);
            }
        });
    } else {
        if (type == 'update') {
            clusterUpdateLayer();
            clusterDrawOnMap('update');
            if (map.getZoom() > fixZoom) {
                placeFillLayer('update');
            }
        }
    }
}

function clusterCreateOverlay(clusterBound, type) {
    if (clusterBound.sum > 0) {
        var bounds = new google.maps.LatLngBounds(
                new google.maps.LatLng(clusterBound.minLat, clusterBound.minLon),
                new google.maps.LatLng(clusterBound.maxLat, clusterBound.maxLon));
        var color = '#00FF00';
        var opacity = 1;
        if (clusterBound.sum < 25) {
            opacity = 0.5;
        }
        else if (clusterBound.sum > 25 && clusterBound.sum < 100) {
            opacity = 0.7;
        }
        else {
            opacity = 0.8;
        }
        var clusterBox = new google.maps.Rectangle({
            strokeColor: color,
            strokeOpacity: opacity,
            strokeWeight: 0,
            fillColor: color,
            fillOpacity: opacity,
            bounds: bounds,
            clickable: true,
            zIndex: 2
        });
        google.maps.event.addListener(clusterBox, 'click', function () {
            map.setCenter(clusterBox.bounds.getCenter());
            map.setZoom(12);
//                    map.fitBounds(this.getBounds());
        });
        //Cluster box text label
        var boxLabel = new InfoBox({
            content: clusterBound.sum,
            boxStyle: {
                border: "0px solid black",
                textAlign: "center",
                fontSize: "10px",
                width: "50px"
            },
            disableAutoPan: true,
            pixelOffset: new google.maps.Size(-25, 5),
            position: new google.maps.LatLng((parseFloat(clusterBound.minLat) + parseFloat(clusterBound.maxLat)) / 2 + 15 / Math.pow(2, map.getZoom()), (parseFloat(clusterBound.minLon) + parseFloat(clusterBound.maxLon)) / 2),
            closeBoxURL: "",
            isHidden: false,
            enableEventPropagation: true
        });
        var cluster = {
            clusterBox: clusterBox,
            title: boxLabel,
            sum: clusterBound.sum
        };
        if (type == 'inner') {
            innerClusters.push(cluster);

        }
        if (type == "outer") {
            outerClusters.push(cluster);
        }
    }
}


function clusterDrawOnMap(type) {
    if (map.getZoom() <= maxClusterZoom) {
        if (type == 'reload') {
            $.each(innerClusters, function (i, cluster) {
                if (cluster.sum != 1) {
                    cluster.clusterBox.setMap(map);
                    cluster.title.open(map);
                } else {
//                    if (map.getZoom() <= fixZoom) {
                    cluster.clusterBox.setMap(map);
                    cluster.title.open(map);
//                    }
                }
            });
        }
        else {
            if (type == 'update') {
                $.each(removedClusters, function (j, cluster) {
                    cluster.clusterBox.setMap(null);
                    cluster.title.close(null);
//                outerClusters.push(cluster);
                });
                $.each(appendClusters, function (k, cluster) {
                    if (cluster.sum != 1) {
                        cluster.clusterBox.setMap(map);
                        cluster.title.open(map);
                    } else {
//                        if (map.getZoom() <= fixZoom) {
                        cluster.clusterBox.setMap(map);
                        cluster.title.open(map);
//                        }
                    }
//                innerClusters.push(cluster);
                });
            }
        }
    }
}

/**
 * Comment
 */
function clusterUpdateLayer() {
    var innerTemp = [];
    var outerTemp = [];
    removedClusters = [];
    appendClusters = [];
    $.each(innerClusters, function (i, cluster) {
        if (!latlonbounds.contains(cluster.clusterBox.bounds.getCenter())) {
            removedClusters.push(cluster);
            outerTemp.push(cluster);
        } else {
            innerTemp.push(cluster);
        }
    });
    $.each(outerClusters, function (j, cluster) {
        if (latlonbounds.contains(cluster.clusterBox.bounds.getCenter())) {
            appendClusters.push(cluster);
            innerTemp.push(cluster);
        } else {
            outerTemp.push(cluster);
        }
    });
    innerClusters = innerTemp;
    outerClusters = outerTemp;
}

/**
 * Comment
 */
function clusterClear(clusterArr) {
    $.each(clusterArr, function (index, cluster) {
        cluster.clusterBox.setMap(null);
        cluster.title.close(null);
    });
}

/*
 * 
 * @returns {undefined}
 * clear all clusters on map
 */

function clusterClearAll() {
    $.each(innerClusters, function (lbIndex, cluster) {
        cluster.clusterBox.setMap(null);
        cluster.title.close(null);
    });
}

/*-----------------------------------Global object---------------------
 * 
 */
var innerPlaces = [];
var outerPlaces = [];
var innerMarkers = [];
var outerMarkers = [];
var removedMarkers = [];
var appenedMarkers = [];
var drawMarkers = [];
var drawingMarkers = [];
//-----------------------------------------------------------------------

/**
 * Comment
 */
function placeFillLayer(type) {
    var minlat = viewBounds.minLat;
    var maxlat = viewBounds.maxLat;
    var minlon = viewBounds.minLon;
    var maxlon = viewBounds.maxLon;
    if (type == 'select') {
        innerPlaces = [];
        outerPlaces = [];
        innerMarkers = [];
        outerMarkers = [];
        var url = "/LocationGetByAll";
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            data: {
                minlon: minlon,
                maxlon: maxlon,
                minlat: minlat,
                maxlat: maxlat
            },
            success: function (data) {
                console.debug(data);
                innerPlaces = data[0];
                for (var i = 0, max = innerPlaces.length; i < max; i++) {
                    innerMarkers.push(placeCreateMaker(innerPlaces[i]));
                }
                outerPlaces = data[1];
//                for (var j = 0, max2 = outerPlaces.length; j < max2; j++) {
//                    placeCreateMaker(outerPlaces[j], 'outer');
//                }
                placeUpdateLayer();
                placeDrawOnMap('reload');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('lỗi cmnr:' + errorThrown);
            }
        });
    } else {
//        if (type == 'reload') {
//            placeUpdateLayer();
//            placeDrawOnMap('reload');
//        } else {
        if (type == 'update') {
            placeUpdateLayer();
            placeDrawOnMap('update');
//            }
        }
    }
}
//---------------------------------------------------------------------------
var NUM_INFO_BUBBLE = 4;
var infoBubble = new InfoBubble({
    disableAnimation: false,
    disableAutoPan: false,
    arrowStyle: 2,
    padding: '8px',
    borderRadius: 10,
    minWidth: 400,
    minHeight: 250
            //maxWidth:         400,
            //minHeight:        360
});
/**
 * Comment
 */
function placeCreateMaker(place) {

    var point = new google.maps.LatLng(
            parseFloat(place.latitude),
            parseFloat(place.longitude));
//        var iconPath = iconBase + '1447326851_middle_finger_gesture_fuck.png';
    var icon = iconBase + 'spotlight-poi.png';
    var marker = new google.maps.Marker({
        position: point,
        id: '23232',
        map: map
    });
//    google.maps.event.addListener(marker, 'click', function (event) {
//        markerInfoBubble(marker, place, infoBubble);
////            console.log(this);
//    });
    marker.addListener('click', function (event) {
        markerInfoBubble(this, place, infoBubble);
        console.log(event);
    });
    google.maps.event.addListener(map, 'click', function () {
        infoBoxClear();
    });
    var name = place.name;
    //Listener for mouseover marker to display tracks
    google.maps.event.addListener(marker, 'mouseover', function () {
        //Hover display name
        if (name != null && name.length != 0) {
            marker.setTitle(name.trim());
        }
        else {
            marker.setTitle(place.name);
        }
    });

    //Prepare place labels
    var placelabel = '';
    if (name != null && name != '') {
        placelabel = name;
        //placelabel = place.name;
    }
    else {
        placelabel = place.name;
    }
    //add placenameLabel to markersDisplayed array
    var placenameLabel = new InfoBox({
        content: placelabel,
        boxStyle: {
            border: "0px dashed black",
            textAlign: "center",
            fontSize: "7pt", //Define text styling properties in CSS file
            width: "120px"
        },
        disableAutoPan: true,
        pixelOffset: new google.maps.Size(-60, 0),
        position: new google.maps.LatLng(place.latitude, place.longitude),
        closeBoxURL: "",
        isHidden: false,
        pane: "mapPane",
        enableEventPropagation: true
    });
    placenameLabel.open(map);
    var currentMarker = {place: marker, title: placenameLabel};
    currentMarker.place.setMap(null);
    currentMarker.title.close(null);
    return currentMarker;
}
/**
 * Comment
 */
function placeUpdateLayer() {
    var innerTemp = [];
    var outerTemp = [];
    var outerPlaceTemp = [];
    removedMarkers = [];
    appenedMarkers = [];

    outerPlaces.forEach(function (outerPlace) {
        var placePosition = new google.maps.LatLng(outerPlace.latitude, outerPlace.longitude);
        if (latlonbounds.contains(placePosition)) {
            var marker = placeCreateMaker(outerPlace);
            appenedMarkers.push(marker);
            innerTemp.push(marker);
        } else {
            outerPlaceTemp.push(outerPlace);
        }
    });
    outerPlaces = outerPlaceTemp.slice();

    for (var j = 0, max1 = innerMarkers.length; j < max1; j++) {
        if (!latlonbounds.contains(innerMarkers[j].place.position)) {
            removedMarkers.push(innerMarkers[j]);
            outerTemp.push(innerMarkers[j]);
        } else {
            innerTemp.push(innerMarkers[j]);
        }
    }
    for (var i = 0, max = outerMarkers.length; i < max; i++) {
        if (latlonbounds.contains(outerMarkers[i].place.position)) {
            appenedMarkers.push(outerMarkers[i]);
            innerTemp.push(outerMarkers[i]);
        } else {
            outerTemp.push(outerMarkers[i]);
        }
    }
    innerMarkers = innerTemp.slice();
    outerMarkers = outerTemp.slice();
}
/**
 * Comment
 */
function placeDrawOnMap(type) {
    var drawTemp = [];
    var drawingTemp = [];
    if (type == 'reload') {
        drawMarkers = [];
        drawingMarkers = [];
        placeClearAll();
        if (map.getZoom() > maxClusterZoom) {
            innerMarkers.forEach(function (marker) {
                marker.place.setMap(map);
                marker.title.open(map);
                drawMarkers.push(marker);
            });
        } else {
            for (var i = 0, max1 = innerClusters.length; i < max1; i++) {
                var bound = innerClusters[i].clusterBox.bounds;
                if (innerClusters[i].sum == 1) {
                    for (var j = 0, max2 = innerMarkers.length; j < max2; j++) {
                        if (bound.contains(innerMarkers[j].place.position)) {
                            innerMarkers[j].place.setMap(map);
                            innerMarkers[j].title.open(map);
                            drawMarkers.push(innerMarkers[j]);
                        }
                    }
                } else {
                    innerMarkers.forEach(function (marker) {
                        if (bound.contains(marker.place.position)) {
                            marker.place.setMap(null);
                            marker.title.close(null);
                            drawingMarkers.push(marker);
                        }
                    });
                }
            }
        }
    }
    else {
        if (type == 'update') {
            removedMarkers.forEach(function (removedMarker) {
                removedMarker.place.setMap(null);
                removedMarker.title.setMap(null);
            });
            if (map.getZoom() > maxClusterZoom) {
                appenedMarkers.forEach(function (appenedMarker) {
                    appenedMarker.place.setMap(map);
                    appenedMarker.title.open(map);
                    drawTemp.push(appenedMarker);
                });
                drawingMarkers.forEach(function (drawingMarker) {
                    drawingMarker.place.setMap(map);
                    drawingMarker.title.open(map);
                    drawTemp.push(drawingMarker);
                });
            } else {
                innerClusters.forEach(function (innerCluster) {
                    if (innerCluster.sum == 1) {
                        appenedMarkers.forEach(function (appenedMarker) {
                            if (isBelong(innerCluster, appenedMarker)) {
                                appenedMarker.place.setMap(map);
                                appenedMarker.title.open(map);
                                drawTemp.push(appenedMarker);
                            }
                        });
                        drawingMarkers.forEach(function (drawingMarker) {
                            if (isBelong(innerCluster, drawingMarker)) {
                                drawingMarker.place.setMap(map);
                                drawingMarker.title.open(map);
                                drawTemp.push(drawingMarker);
                            }
                        });
                        drawMarkers.forEach(function (drawMarker) {
                            if (isBelong(innerCluster, drawMarker)) {
                                drawTemp.push(drawMarker);
                            }
                        });
                    } else {
                        appenedMarkers.forEach(function (appenedMarker) {
                            if (isBelong(innerCluster, appenedMarker)) {
                                appenedMarker.place.setMap(null);
                                appenedMarker.title.close(null);
                                drawingTemp.push(appenedMarker);
                            }
                        });
                        drawMarkers.forEach(function (drawMaker) {
                            if (isBelong(innerCluster, drawMaker)) {
                                drawMaker.place.setMap(null);
                                drawMaker.title.close(null);
                                drawingTemp.push(drawMaker);
                            }
                        });
                        drawingMarkers.forEach(function (drawingMarker) {
                            if (isBelong(innerCluster, drawingMarker)) {
                                drawingTemp.push(drawingMarker);
                            }
                        });
                    }
                });
            }
        }
        drawMarkers = drawTemp.slice();
        drawingMarkers = drawingTemp.slice();
    }
}
/**
 * Comment
 */
function placeClearAll() {
    $.each(innerMarkers, function (indexMarker, marker) {
        marker.place.setMap(null);
        marker.title.close(null);
    });
    if (map.getZoom() <= fixZoom) {
        infoBoxClear();
    }
}

/**
 * Comment
 */
function isBelong(cluster, marker) {
    return cluster.clusterBox.bounds.contains(marker.place.position);
}

/* -------------------------------------------------------------------------------- */
/**
 * Function to attach information associated with marker, or call track 
 * fetcher to get track line
 */
function markerInfoBubble(marker, place, infoBubble) {
    //Prepare HTML for infoWindow
    infoBubble.setContent(generateInfoHTML(place, 'AIS', 'INfo'));
    //Add a tab for the regular infoBubble info
//    infoBubble.addTab(0);
//    infoBubble.updateTab(0, 'place Based Info', generateInfoHTML());
    //Open the bubble
    infoBubble.open(map, marker);
}

/* -------------------------------------------------------------------------------- */
/**
 * Function to generate the HTML for infoBubble/infoWindow
 * for a AIS or LAISIC place marker.
 */
function generateInfoHTML(place, type, title) {
    var url = 'https://maps.gstatic.com/mapfiles/place_api/icons/' + place.icon
    var htmlTitle =
            '<div id="content">' +
            '<span style="vertical-align: middle;display:inline-block;height: 30px;"><span id="firstHeading" class="firstHeading">' + title + '</span></span>' +
            '<div id="bodyContent">';

    var htmlLeft =
            '<div id="content-left">' +
            '<a href="https://marinetraffic.com/ais/shipdetails.aspx?MMSI=' + place.name + '"  target="_blank"> ' +
            '<img id="marinetrafficimage" title="Click to open MarineTraffic page" width=180px src="' + url + '" onError="this.onerror=null;this.src="icons/noimage.png";>' +
            '</a><br>' +
            '<a href="http://www.sea-web.com/lrupdate.aspx?param1=spatab833&param2=719766&script_name=authenticated/authenticated_handler.aspx&control=list&SearchString=MMSI+=+' + place.name + '&ListType=Ships" target="_blank">Sea-Web link</a><br>' +
            '<div id="content-sub" border=1>' +
            '<b>MMSI</b>: ' + place.name + '<br>' +
            '<b>IMO</b>: ' + ' <font color="red">(invalid)</font>' + '<br>' +
            //'<b>Vessel Type</b>: ' + placetype + '<br>' +
            '<b>Vessel Type</b>: ' + place.ticketPrice + '<br>' +
            '<b>Last Message Type</b>: ' + place.ticketPrice + '<br>' +
            '</div>' +
            '<div>' +
            '<a id="showtracklink" link="" href="javascript:void(0);" onClick="getAISTrack(\'' + place.name + '\', \'' + place.ticketPrice + '\');">Show place track history</a>' +
            '</div>' +
            (type == 'AIS' ?
                    '<div id="content-port" border=1>' +
                    '<b>Last 3 Port Calls (entry times)</b><br>' +
                    '<div id="port_calls">' +
                    '<div id="query_spinner"><div style="width: 24px; height: 24px;"></div></div>' +
                    '</div>' + //close for port_calls data div
                    '</div>' : '') + //close for port_calls div (content-sub)
            '</div>';  //close for content-left div

    var htmlRight =
            '<div id="content-right">' +
            '<div id="content-sub">' +
            '<b>Report Date</b>: <br>======' + '<br>' +
            //TODO: change local time to be dynamic, not hard coded to Pacific Time zone
            '<b>(local time)</b>: <br>======' + '<br>' +
            '<div id="placeLastUpdated">Last Updated on...</div>' +
            '<b>Lat</b>: ' + place.latitude + '<br>' +
            '<b>Lon</b>: ' + place.longitude + '<br>' +
            '<b>Navigation Status</b>: <br> navstatus' + '<br>' +
            '<b>Speed Over Ground</b>: sog' + '<br>' +
            '<b>Course Over Ground</b>: cog' + '<br>' +
            '<b>Length x Width</b>: <br>length' + ' x ' + 'shipwidth<br>' +
            '<b>Draught</b>: draught' + '<br>' +
            '<b>Destination</b>: destination' + '<br>' +
            '<b>ETA</b>: eta' + '<br>' +
            '<b>Source</b>: streamid' + '<br>' +
            '<b>Risk Security</b>: risk_score_security ' + '<br>' +
            '<b>Risk Safety</b>: risk_score_safety' + '<br>' +
            '</div>' + //close for content-sub
            '<br><br>' +
            '<br>' +
            '</div>' + //close for content-right
            '</div>' + //close for bodyContent
            '</div>';      //close for content

    var html = htmlTitle + htmlLeft + htmlRight;
    //END Prepare HTML for infoWindow

    return html;
}


/**
 * Comment
 */
function infoBoxClear() {
    for (var i = 0; i < NUM_INFO_BUBBLE; i++) {
//            console.log('Removing tab ' + i);
        infoBubble.removeTab(0);
    }
    infoBubble.setMap(null);
    infoBubble.close();
}
