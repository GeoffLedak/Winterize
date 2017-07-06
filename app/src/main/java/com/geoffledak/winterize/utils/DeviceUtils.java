package com.geoffledak.winterize.utils;

import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.Zone;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Turbo9000 on 7/6/2017.
 */

public class DeviceUtils {

    public static List<Device> sortDeviceZones(List<Device> deviceList) {

        for (Device device : deviceList) {
            List<Zone> zoneList = device.getZones();
            zoneList.sort(new Comparator<Zone>() {
                @Override
                public int compare(Zone o1, Zone o2) {
                    return o1.getZoneNumber() - o2.getZoneNumber();
                }
            });
        }

        return deviceList;
    }

}
