
package com.geoffledak.winterize.model;

import org.parceler.Parcel;

@Parcel
public class CustomSoil {

    private long createDate;
    private long lastUpdateDate;
    private String id;
    private String name;
    private String imageUrl;
    private String category;
    private double infiltrationRate;
    private boolean editable;
    private double percentAvailableWater;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomSoil() {
    }

    /**
     * 
     * @param id
     * @param category
     * @param imageUrl
     * @param name
     * @param percentAvailableWater
     * @param createDate
     * @param infiltrationRate
     * @param editable
     * @param lastUpdateDate
     */
    public CustomSoil(long createDate, long lastUpdateDate, String id, String name, String imageUrl, String category, double infiltrationRate, boolean editable, double percentAvailableWater) {
        super();
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.infiltrationRate = infiltrationRate;
        this.editable = editable;
        this.percentAvailableWater = percentAvailableWater;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getInfiltrationRate() {
        return infiltrationRate;
    }

    public void setInfiltrationRate(double infiltrationRate) {
        this.infiltrationRate = infiltrationRate;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public double getPercentAvailableWater() {
        return percentAvailableWater;
    }

    public void setPercentAvailableWater(double percentAvailableWater) {
        this.percentAvailableWater = percentAvailableWater;
    }

}
