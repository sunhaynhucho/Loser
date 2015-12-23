package com.loser.map.sql;

import com.loser.map.databases.QueryDictionaries;
import com.loser.map.model.Cluster;
import com.loser.map.model.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author VinhNV
 */
public class SqlMapView {

    private static final Logger logger = LoggerFactory.getLogger(SqlMapView.class);

    public static List<Location> locationGetByAll() {
        List<Location> result = new ArrayList<>();
        try {
            String[] columnName = {"LOCATION_CODE", "ORG_NAME", "LON", "LAT", "ICON"};
            List<Map<String, Object>> records = QueryDictionaries.toList("locations", columnName);
            records.stream().map((record) -> {
                Location location = new Location();
                location.setLocationCode((int) record.get("LOCATION_CODE"));
                location.setName(record.get("ORG_NAME").toString());
                location.setLongitude((double) record.get("LON"));
                location.setLatitude((double) record.get("LAT"));
                location.setIcon(record.get("ICON").toString());
                return location;
            }).forEach((location) -> {
                result.add(location);
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
        return result;
    }

    public static List<Cluster> getCluster(double minLat, double maxLat, double minLon, double maxLon, int row, int column) {
        List<Cluster> result = new ArrayList<>();
        try {
            String geoBounds;
            double dLat = maxLat - minLat;
            double dLon = maxLon - minLon;
            if (minLon > maxLon) {
                dLon = maxLon + 360 - minLon;
                geoBounds = "LAT > " + minLat + " AND LAT < " + maxLat + " AND ((LON > " + minLon + " AND LON <= 180.0) OR (LON < " + maxLon + " AND LON >= -180.0))";
            } else {
                geoBounds = "LAT > " + minLat + " AND LAT < " + maxLat + " AND LON > " + minLon + " AND LON < " + maxLon + "";
            }
            String sql = "SELECT\n"
                    + "   " + dLat + " * (FLOOR(" + row + " * (LAT - " + minLat + ") / " + dLat + ") + 0.5) / " + row + " + " + minLat + " - " + dLat + "/" + row + "/2 AS minLat,\n"
                    + "   " + dLat + " * (FLOOR(" + row + " * (LAT - " + minLat + ") / " + dLat + ") + 0.5) / " + row + " + " + minLat + " + " + dLat + "/" + row + "/2 AS maxLat,\n"
                    + "   " + dLon + " * (FLOOR(" + column + " * (IF(LON > " + minLon + ", LON, LON + 360.0) - " + minLon + ") / " + dLon + ") + 0.5) / " + column + " + " + minLon + " - " + dLon + "/" + column + "/2 AS minLon,\n"
                    + "   " + dLon + " * (FLOOR(" + column + " * (IF(LON > " + minLon + ", LON, LON + 360.0) - " + minLon + ") / " + dLon + ") + 0.5) / " + column + " + " + minLon + " + " + dLon + "/" + column + "/2 AS maxLon,\n"
                    + "   count(*) AS clustersum\n"
                    + "FROM\n"
                    + "   (SELECT * FROM locations) AS tmp2\n"
                    + "WHERE (" + geoBounds + ") \n"
                    + "GROUP BY FLOOR(" + row + " * (LAT - " + minLat + ") / " + dLat + ") * 1000000 + FLOOR(" + column + " * (IF(LON > " + minLon + ", LON, LON + 360.0) - " + minLon + ") / " + dLon + ");";
            List<Map<String, Object>> records = QueryDictionaries.querySpecial(sql, null);
            for (Map<String, Object> record : records) {
                Cluster cluster = new Cluster();
                cluster.setMinLat((double) record.get("minLat"));
                cluster.setMaxLat((double) record.get("maxLat"));
                cluster.setMinLon((double) record.get("minLon"));
                cluster.setMaxLon((double) record.get("maxLon"));
                cluster.setSum((long) record.get("clustersum"));
                result.add(cluster);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
