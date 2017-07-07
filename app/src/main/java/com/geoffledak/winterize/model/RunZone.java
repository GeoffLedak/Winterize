package com.geoffledak.winterize.model;

import org.parceler.Parcel;

/**
 * Created by Geoff Ledak on 7/3/2017.
 */

@Parcel
public class RunZone {

    private String id;
    private int duration;


    public RunZone() { }

    public RunZone(String id, int duration) {
        this.id = id;
        this.duration = duration;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
