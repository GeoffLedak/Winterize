
package com.geoffledak.winterize.model;

import org.parceler.Parcel;

@Parcel
public class CustomNozzle {

    private String name;
    private String imageUrl;
    private String category;
    private double inchesPerHour;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomNozzle() {
    }

    /**
     * 
     * @param category
     * @param imageUrl
     * @param name
     * @param inchesPerHour
     */
    public CustomNozzle(String name, String imageUrl, String category, double inchesPerHour) {
        super();
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.inchesPerHour = inchesPerHour;
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

    public double getInchesPerHour() {
        return inchesPerHour;
    }

    public void setInchesPerHour(double inchesPerHour) {
        this.inchesPerHour = inchesPerHour;
    }

}
