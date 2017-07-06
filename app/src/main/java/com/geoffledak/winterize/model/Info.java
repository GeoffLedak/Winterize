package com.geoffledak.winterize.model;

import org.parceler.Parcel;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

@Parcel
public class Info {

    private String id;


    public Info() { }

    public Info(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
