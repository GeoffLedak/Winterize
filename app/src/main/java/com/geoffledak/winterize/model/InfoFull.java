
package com.geoffledak.winterize.model;

import java.util.List;

public class InfoFull {

    private long createDate;
    private String id;
    private String username;
    private String fullName;
    private String email;
    private List<Device> devices = null;
    private boolean enabled;
    private String displayUnit;
    private boolean deleted;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InfoFull() {
    }

    /**
     * 
     * @param id
     * @param enabled
     * @param username
     * @param email
     * @param displayUnit
     * @param fullName
     * @param devices
     * @param createDate
     * @param deleted
     */
    public InfoFull(long createDate, String id, String username, String fullName, String email, List<Device> devices, boolean enabled, String displayUnit, boolean deleted) {
        super();
        this.createDate = createDate;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.devices = devices;
        this.enabled = enabled;
        this.displayUnit = displayUnit;
        this.deleted = deleted;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDisplayUnit() {
        return displayUnit;
    }

    public void setDisplayUnit(String displayUnit) {
        this.displayUnit = displayUnit;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
