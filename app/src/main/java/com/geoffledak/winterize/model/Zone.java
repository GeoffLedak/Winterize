
package com.geoffledak.winterize.model;


public class Zone {

    private String id;
    private int zoneNumber;
    private String name;
    private boolean enabled;
    private CustomNozzle customNozzle;
    private CustomSoil customSoil;
    private CustomSlope customSlope;
    private CustomCrop customCrop;
    private CustomShade customShade;
    private double availableWater;
    private double rootZoneDepth;
    private double managementAllowedDepletion;
    private double efficiency;
    private int yardAreaSquareFeet;
    private String imageUrl;
    private int lastWateredDuration;
    private long lastWateredDate;
    private boolean scheduleDataModified;
    private int fixedRuntime;
    private double saturatedDepthOfWater;
    private double depthOfWater;
    private int maxRuntime;
    private int runtimeNoMultiplier;
    private int runtime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Zone() {
    }

    /**
     * 
     * @param scheduleDataModified
     * @param enabled
     * @param customSoil
     * @param saturatedDepthOfWater
     * @param runtimeNoMultiplier
     * @param yardAreaSquareFeet
     * @param imageUrl
     * @param maxRuntime
     * @param lastWateredDuration
     * @param customNozzle
     * @param runtime
     * @param managementAllowedDepletion
     * @param lastWateredDate
     * @param customShade
     * @param id
     * @param name
     * @param customSlope
     * @param customCrop
     * @param availableWater
     * @param fixedRuntime
     * @param efficiency
     * @param zoneNumber
     * @param rootZoneDepth
     * @param depthOfWater
     */
    public Zone(String id, int zoneNumber, String name, boolean enabled, CustomNozzle customNozzle, CustomSoil customSoil, CustomSlope customSlope, CustomCrop customCrop, CustomShade customShade, double availableWater, double rootZoneDepth, double managementAllowedDepletion, double efficiency, int yardAreaSquareFeet, String imageUrl, int lastWateredDuration, long lastWateredDate, boolean scheduleDataModified, int fixedRuntime, double saturatedDepthOfWater, double depthOfWater, int maxRuntime, int runtimeNoMultiplier, int runtime) {
        super();
        this.id = id;
        this.zoneNumber = zoneNumber;
        this.name = name;
        this.enabled = enabled;
        this.customNozzle = customNozzle;
        this.customSoil = customSoil;
        this.customSlope = customSlope;
        this.customCrop = customCrop;
        this.customShade = customShade;
        this.availableWater = availableWater;
        this.rootZoneDepth = rootZoneDepth;
        this.managementAllowedDepletion = managementAllowedDepletion;
        this.efficiency = efficiency;
        this.yardAreaSquareFeet = yardAreaSquareFeet;
        this.imageUrl = imageUrl;
        this.lastWateredDuration = lastWateredDuration;
        this.lastWateredDate = lastWateredDate;
        this.scheduleDataModified = scheduleDataModified;
        this.fixedRuntime = fixedRuntime;
        this.saturatedDepthOfWater = saturatedDepthOfWater;
        this.depthOfWater = depthOfWater;
        this.maxRuntime = maxRuntime;
        this.runtimeNoMultiplier = runtimeNoMultiplier;
        this.runtime = runtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(int zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public CustomNozzle getCustomNozzle() {
        return customNozzle;
    }

    public void setCustomNozzle(CustomNozzle customNozzle) {
        this.customNozzle = customNozzle;
    }

    public CustomSoil getCustomSoil() {
        return customSoil;
    }

    public void setCustomSoil(CustomSoil customSoil) {
        this.customSoil = customSoil;
    }

    public CustomSlope getCustomSlope() {
        return customSlope;
    }

    public void setCustomSlope(CustomSlope customSlope) {
        this.customSlope = customSlope;
    }

    public CustomCrop getCustomCrop() {
        return customCrop;
    }

    public void setCustomCrop(CustomCrop customCrop) {
        this.customCrop = customCrop;
    }

    public CustomShade getCustomShade() {
        return customShade;
    }

    public void setCustomShade(CustomShade customShade) {
        this.customShade = customShade;
    }

    public double getAvailableWater() {
        return availableWater;
    }

    public void setAvailableWater(double availableWater) {
        this.availableWater = availableWater;
    }

    public double getRootZoneDepth() {
        return rootZoneDepth;
    }

    public void setRootZoneDepth(double rootZoneDepth) {
        this.rootZoneDepth = rootZoneDepth;
    }

    public double getManagementAllowedDepletion() {
        return managementAllowedDepletion;
    }

    public void setManagementAllowedDepletion(double managementAllowedDepletion) {
        this.managementAllowedDepletion = managementAllowedDepletion;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public int getYardAreaSquareFeet() {
        return yardAreaSquareFeet;
    }

    public void setYardAreaSquareFeet(int yardAreaSquareFeet) {
        this.yardAreaSquareFeet = yardAreaSquareFeet;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLastWateredDuration() {
        return lastWateredDuration;
    }

    public void setLastWateredDuration(int lastWateredDuration) {
        this.lastWateredDuration = lastWateredDuration;
    }

    public long getLastWateredDate() {
        return lastWateredDate;
    }

    public void setLastWateredDate(long lastWateredDate) {
        this.lastWateredDate = lastWateredDate;
    }

    public boolean isScheduleDataModified() {
        return scheduleDataModified;
    }

    public void setScheduleDataModified(boolean scheduleDataModified) {
        this.scheduleDataModified = scheduleDataModified;
    }

    public int getFixedRuntime() {
        return fixedRuntime;
    }

    public void setFixedRuntime(int fixedRuntime) {
        this.fixedRuntime = fixedRuntime;
    }

    public double getSaturatedDepthOfWater() {
        return saturatedDepthOfWater;
    }

    public void setSaturatedDepthOfWater(double saturatedDepthOfWater) {
        this.saturatedDepthOfWater = saturatedDepthOfWater;
    }

    public double getDepthOfWater() {
        return depthOfWater;
    }

    public void setDepthOfWater(double depthOfWater) {
        this.depthOfWater = depthOfWater;
    }

    public int getMaxRuntime() {
        return maxRuntime;
    }

    public void setMaxRuntime(int maxRuntime) {
        this.maxRuntime = maxRuntime;
    }

    public int getRuntimeNoMultiplier() {
        return runtimeNoMultiplier;
    }

    public void setRuntimeNoMultiplier(int runtimeNoMultiplier) {
        this.runtimeNoMultiplier = runtimeNoMultiplier;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

}
