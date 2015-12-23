package com.loser.map.model;

/**
 *
 * @author VinhNV
 */
public class Cluster {

    private double minLat;
    private double maxLat;
    private double minLon;
    private double maxLon;
    private long sum;

    public Cluster() {
    }

    public Cluster(double minLat, double maxLat, double minLon, double maxLon, long sum) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.sum = sum;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public void setMinLon(double minLon) {
        this.minLon = minLon;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(double maxLon) {
        this.maxLon = maxLon;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Cluster{" + "minLat=" + minLat + ", maxLat=" + maxLat + ", minLon=" + minLon + ", maxLon=" + maxLon + ", sum=" + sum + '}';
    }

    public boolean contains(double minLat, double maxLat, double minLon, double maxLon) {
        return minLat <= this.minLat && maxLat >= this.maxLat && minLon <= this.minLon && maxLon >= this.maxLon;
    }

}
