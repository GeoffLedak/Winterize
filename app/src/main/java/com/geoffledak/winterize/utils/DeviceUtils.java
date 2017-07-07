package com.geoffledak.winterize.utils;

import android.widget.Toast;

import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.fragment.StatusFragment;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.service.RachioClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Geoff Ledak on 7/6/2017.
 */

public class DeviceUtils {

    public static List<Device> sortDeviceZones(List<Device> deviceList) {

        for (Device device : deviceList) {
            List<Zone> zoneList = device.getZones();
            Collections.sort(zoneList, new Comparator<Zone>() {
                @Override
                public int compare(Zone o1, Zone o2) {
                    return o1.getZoneNumber() - o2.getZoneNumber();
                }
            });
        }

        return deviceList;
    }


    public static void turnDeviceOff(final MainActivity activity, final StatusFragment fragment, Device device) {

        VisualUtils.getInstance().showLoadingDialog(activity);
        Retrofit retrofit = APIUtils.buildRetrofit(activity);
        RachioClient client = retrofit.create(RachioClient.class);
        Call<String> call = client.turnDeviceOff(activity.getAPIToken(), new Info(device.getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                fragment.loadInfoFull();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
        });
    }


    public static void turnDeviceOn(final MainActivity activity, final StatusFragment fragment, Device device) {

        VisualUtils.getInstance().showLoadingDialog(activity);
        Retrofit retrofit = APIUtils.buildRetrofit(activity);
        RachioClient client = retrofit.create(RachioClient.class);
        Call<String> call = client.turnDeviceOn(activity.getAPIToken(), new Info(device.getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                fragment.loadInfoFull();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity, "Network error", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
        });
    }

}
