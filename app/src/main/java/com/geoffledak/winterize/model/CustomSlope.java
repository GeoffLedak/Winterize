
package com.geoffledak.winterize.model;


public class CustomSlope {

    private String name;
    private String imageUrl;
    private String variance;
    private int sortOrder;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomSlope() {
    }

    /**
     * 
     * @param imageUrl
     * @param sortOrder
     * @param name
     * @param variance
     */
    public CustomSlope(String name, String imageUrl, String variance, int sortOrder) {
        super();
        this.name = name;
        this.imageUrl = imageUrl;
        this.variance = variance;
        this.sortOrder = sortOrder;
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

    public String getVariance() {
        return variance;
    }

    public void setVariance(String variance) {
        this.variance = variance;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

}
