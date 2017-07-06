package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.InfoFull;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.service.RachioClient;
import com.geoffledak.winterize.utils.APIUtils;
import com.geoffledak.winterize.utils.DeviceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Geoff Ledak on 7/5/2017.
 */

public class StatusFragment extends Fragment {

    private View mView;
    private MainActivity mActivity;
    private TextView mPersonId;
    private TextView mZoneList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_status, container, false);
        mActivity = ((MainActivity) getContext());
        mPersonId = (TextView) mView.findViewById(R.id.person_id);
        mZoneList = (TextView) mView.findViewById(R.id.zone_list);

        mPersonId.setText(mActivity.getPersonId());

        loadInfoFull();

        return mView;
    }


    private void loadInfoFull() {
        final String token = mActivity.getAPIToken();
        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<InfoFull> call = client.infoFullForPerson(token, mActivity.getPersonId());

        call.enqueue(new Callback<InfoFull>() {
            @Override
            public void onResponse(Call<InfoFull> call, Response<InfoFull> response) {
                InfoFull infoFull = response.body();

                if( infoFull == null )
                    Toast.makeText(getContext(), "Failed to fetch information", Toast.LENGTH_SHORT).show();
                else {
                    mActivity.setInfoFull(infoFull);
                    populateStatusInfo();
                }
            }
            @Override
            public void onFailure(Call<InfoFull> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to fetch information", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateStatusInfo() {

        // Iterate through all devices and sort each zone list by 'zoneNumber'
        mActivity.getInfoFull().setDevices(DeviceUtils.sortDeviceZones( mActivity.getInfoFull().getDevices() ) );

        List<Device> deviceList = mActivity.getInfoFull().getDevices();




        String daText = "";

        for( Device device : deviceList ) {

            daText = daText + device.getName() + "\n";

            for( Zone zone : device.getZones() ) {
                daText = daText + zone.getName() + "\n";
            }
        }

        mZoneList.setText( daText );
    }


}
