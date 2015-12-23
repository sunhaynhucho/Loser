package com.loser.map.model;

/**
 *
 * @author VinhNV
 */
public class Location {

    private int locationCode;
    private User user;
    private String name;
    private String key;
    private double longitude;
    private double latitude;
    private String address;
    private int notyficationStatus;
    private int privateStatus;
    private String phoneNumber;
    private float ticketPrice;
    private String description;
    private String addedTime;
    private String types;
    private String icon;

    public Location() {
    }

    public Location(int locationCode) {
        this.locationCode = locationCode;
    }

    public Location(String name, double longitude, double latitude, String address, String types, String icon) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.types = types;
        this.icon = icon;
    }

    public Location(int locationCode, User user, String name, float longitude, float latitude, String address, String addedTime) {
        this.locationCode = locationCode;
        this.user = user;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.addedTime = addedTime;
    }

    public Location(int locationCode, User user, String name, String key, float longitude, float latitude, String address, int notyficationStatus, int privateStatus, String phoneNumber, float ticketPrice, String description, String addedTime) {
        this.locationCode = locationCode;
        this.user = user;
        this.name = name;
        this.key = key;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.notyficationStatus = notyficationStatus;
        this.privateStatus = privateStatus;
        this.phoneNumber = phoneNumber;
        this.ticketPrice = ticketPrice;
        this.description = description;
        this.addedTime = addedTime;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNotyficationStatus() {
        return notyficationStatus;
    }

    public void setNotyficationStatus(int notyficationStatus) {
        this.notyficationStatus = notyficationStatus;
    }

    public int getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(int privateStatus) {
        this.privateStatus = privateStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    @Override
    public String toString() {
        return "Location{" + "locationCode=" + locationCode + ", user=" + user + ", name=" + name + ", key=" + key + ", longitude=" + longitude + ", latitude=" + latitude + ", address=" + address + ", notyficationStatus=" + notyficationStatus + ", privateStatus=" + privateStatus + ", phoneNumber=" + phoneNumber + ", ticketPrice=" + ticketPrice + ", description=" + description + ", addedTime=" + addedTime + '}';
    }

    public boolean contains(double minLat, double maxLat, double minLon, double maxLon) {
        return latitude > minLat && latitude <= maxLat && longitude > minLon && longitude <= maxLon;
    }

}
