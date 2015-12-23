/* -------------------------------------------------------------------------------- */
/**
 *  Global objects 
 */


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
/*
 * 
 * @type Array
 * array of current cluster into current view bound
 */
var currentClusters = [];
/*
 * 
 * @type Array
 * array of cluster 
 */
var clusterBounds = [];
/*
 * 
 * @type Array
 * array of vessels have been selected from database
 */
var vessels = [];
/* -------------------------------------------------------------------------------- */
/** Initialize, called on main page load
 */
function initialize() {
    var centerCoord = new google.maps.LatLng(13.273461807246479, -13.465625000000037); //Zoomed out world view
    var controlStyle;
    var defaultZoom;
    controlStyle = google.maps.MapTypeControlStyle.HORIZONTAL_BAR;
    defaultZoom = 3;
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
        minZoom: 3,
        maxZoom: 19,
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
        maxZoom: 18
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
        clearCluser();
        updateViewBounds();
        getMessageFromDB(1);
        generateClusterBox();
        drawClusterOnMap();
    });
    google.maps.event.addListener(map, 'mousemove', function (event) {
        document.getElementById('latlong').innerHTML =
                Math.round(event.latLng.lat() * 10000000) / 10000000 + ', ' + Math.round(event.latLng.lng() * 10000000) / 10000000;
    });
    google.maps.event.addListener(map, 'zoom_changed', function (event) {
        clearCluser();
    });
}
$(function initializeLayers() {
    getMessageFromDB(0);
});
function updateViewBounds() {
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
        maxLon: maxLon,
        boundStr: "?minlat=" + Math.round(minLat * 1000) / 1000 + "&maxlat=" + Math.round(maxLat * 1000) / 1000 + "&minlon=" + Math.round(minLon * 1000) / 1000 + "&maxlon=" + Math.round(maxLon * 1000) / 1000
    };
}

function getMessageFromDB(_case) {
    var url = "";
    if (_case == 1) {
        url = "/losers/index/getCluster" + viewBounds.boundStr;
        console.log("/losers/index/getCluster" + viewBounds.boundStr);
    } else {
        url = "/losers/index/getCluster";
    }
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        success: function (data) {
            vessels = [];
            vessels = data.slice();
        }
    });
}

function clearCluser() {
//    console.log(currentClusters);
    $.each(currentClusters, function (lbIndex, cluster) {
        cluster.clusterBox.setMap(null);
        cluster.title.close(null);
    });
    currentClusters = [];
}

/*
 * a cluster object is include a cluster box, cluster title is sum of vessel in cluster box
 * this function will generating cluster box: object{minLon,maxLon,minLat,maxLat,sum};
 */

function generateClusterBox() {
    if (map.getZoom() == 3 || map.getZoom() == 5) {
        clusterBounds = [];
        var columns = 48;
        var rows = 64;
        var height = viewBounds.maxLat - viewBounds.minLat;
        var width = viewBounds.maxLon - viewBounds.minLon;
        if (viewBounds.minLon > viewBounds.maxLon) {
            width = viewBounds.maxLon + 360 - viewBounds.minLon;
        }
        var dLat = height / rows;
        var dLon = width / columns;
        var index = 0;
        for (var i = 0; i < rows; i++) {
            var minLat = viewBounds.minLat + dLat * i;
            var maxLat = viewBounds.minLat + dLat + dLat * i;
            for (var j = 0; j < columns; j++) {
                var maxLon = viewBounds.minLon + dLon * j + dLon;
                var minLon = viewBounds.minLon + dLon * j;
                var clusterBound = {minLon: minLon, maxLon: maxLon, minLat: minLat, maxLat: maxLat, sum: 0};
                index++;
                clusterBounds.push(clusterBound);
            }
        }
    }
    sumVesselOnCluster(dLat, dLon, columns);
}

function sumVesselOnCluster(dLat, dLon, columns) {
    for (var i = 0, max = vessels.length; i < max; i++) {
        lon = -viewBounds.minLon + parseFloat(parseFloat(vessels[i].lon));
        lat = -viewBounds.minLat + parseFloat(parseFloat(vessels[i].lat));
        var indexOfLon = parseInt(parseFloat(lon) / parseFloat(dLon * 1));
        var indexOfLat = parseInt(parseFloat(lat) / parseFloat(dLat * 1));
        var index = indexOfLon + columns * indexOfLat;
        if (index >= 0) {
            var temp = clusterBounds[index];
            temp.sum = clusterBounds[index].sum + 1;
            clusterBounds[index] = temp;
//            console.log(indexOfLon +":"+lon +":"+ indexOfLat);
        } else {
//            console.log(lat);
        }
    }
}

function drawClusterOnMap() {
    for (var i = 0, max = clusterBounds.length; i < max; i++) {
        if (clusterBounds[i].sum > 0) {
            var bounds = new google.maps.LatLngBounds(
                    new google.maps.LatLng(clusterBounds[i].minLat, clusterBounds[i].minLon),
                    new google.maps.LatLng(clusterBounds[i].maxLat, clusterBounds[i].maxLon));
            var color = '#00FF00';
            var opacity = 1;
            if (clusterBounds[i].sum < 50) {
                opacity = 0.3;
            }
            else if (clusterBounds[i].sum > 50 && clusterBounds[i].sum < 100) {
                opacity = 0.5;
            }
            else {
                opacity = 0.6;
            }

            var clusterBox = new google.maps.Rectangle({
                strokeColor: color,
                strokeOpacity: opacity,
                strokeWeight: 1,
                fillColor: color,
                fillOpacity: opacity,
                bounds: bounds,
                map: map,
                clickable: true,
                zIndex: 2
            });
            google.maps.event.addListener(clusterBox, 'click', function () {
                map.fitBounds(this.getBounds());
            });
            //Cluster box text label
            var boxLabel = new InfoBox({
                content: clusterBounds[i].sum,
                boxStyle: {
                    border: "0px solid black",
                    textAlign: "center",
                    fontSize: "12px",
                    width: "50px"
                },
                disableAutoPan: true,
                pixelOffset: new google.maps.Size(-25, 5),
                position: new google.maps.LatLng((parseFloat(clusterBounds[i].minLat) + parseFloat(clusterBounds[i].maxLat)) / 2 + 15 / Math.pow(2, map.getZoom()), (parseFloat(clusterBounds[i].minLon) + parseFloat(clusterBounds[i].maxLon)) / 2),
                closeBoxURL: "",
                isHidden: false,
                enableEventPropagation: true
            });
            boxLabel.open(map);
            var cluster = {
                clusterBox: clusterBox,
                title: boxLabel
            };
            currentClusters.push(cluster);
        }
    }
}

