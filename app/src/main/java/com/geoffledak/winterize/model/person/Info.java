package com.geoffledak.winterize.model.person;

import org.parceler.Parcel;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

@Parcel
public class Info {

    private String id;


    public Info() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
