
package com.geoffledak.winterize.model;

import org.parceler.Parcel;

@Parcel
public class CustomCrop {

    private String name;
    private String imageUrl;
    private double coefficient;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomCrop() {
    }

    /**
     * 
     * @param coefficient
     * @param imageUrl
     * @param name
     */
    public CustomCrop(String name, String imageUrl, double coefficient) {
        super();
        this.name = name;
        this.imageUrl = imageUrl;
        this.coefficient = coefficient;
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

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

}
