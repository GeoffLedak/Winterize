
package com.geoffledak.winterize.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Device {

    private long createDate;
    private String id;
    private String status;
    private List<Zone> zones = null;
    private String timeZone;
    private double latitude;
    private double longitude;
    private String zip;
    private String name;
    private String serialNumber;
    private String macAddress;
    private double elevation;
    private boolean paused;
    private boolean on;
    private String model;
    private String scheduleModeType;
    private boolean deleted;
    private int utcOffset;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Device() {
    }

    /**
     * 
     * @param zip
     * @param model
     * @param status
     * @param elevation
     * @param scheduleModeType
     * @param paused
     * @param serialNumber
     * @param timeZone
     * @param macAddress
     * @param zones
     * @param deleted
     * @param id
     * @param name
     * @param utcOffset
     * @param on
     * @param longitude
     * @param latitude
     * @param createDate
     */
    public Device(long createDate, String id, String status, List<Zone> zones, String timeZone, double latitude, double longitude, String zip, String name, String serialNumber, String macAddress, double elevation, boolean paused, boolean on, String model, String scheduleModeType, boolean deleted, int utcOffset) {
        super();
        this.createDate = createDate;
        this.id = id;
        this.status = status;
        this.zones = zones;
        this.timeZone = timeZone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zip = zip;
        this.name = name;
        this.serialNumber = serialNumber;
        this.macAddress = macAddress;
        this.elevation = elevation;
        this.paused = paused;
        this.on = on;
        this.model = model;
        this.scheduleModeType = scheduleModeType;
        this.deleted = deleted;
        this.utcOffset = utcOffset;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScheduleModeType() {
        return scheduleModeType;
    }

    public void setScheduleModeType(String scheduleModeType) {
        this.scheduleModeType = scheduleModeType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

}
