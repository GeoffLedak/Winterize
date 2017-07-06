package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.adapter.ContentAdapter;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.model.InfoFull;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.service.RachioClient;
import com.geoffledak.winterize.utils.APIUtils;
import com.geoffledak.winterize.utils.DeviceUtils;

import java.util.ArrayList;
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

    private TextView onOrOffText;
    private Button onOrOffButton;

    ContentAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_status, container, false);
        mActivity = ((MainActivity) getContext());
        mPersonId = (TextView) mView.findViewById(R.id.person_id);
        mZoneList = (TextView) mView.findViewById(R.id.zone_list);

        onOrOffText = (TextView) mView.findViewById(R.id.device_on_or_off);
        onOrOffButton = (Button) mView.findViewById(R.id.button_set_device_on_or_off);

        mPersonId.setText(mActivity.getPersonId());

        loadInfoFull();

        return mView;
    }


    private void loadInfoFull() {
        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<InfoFull> call = client.infoFullForPerson(mActivity.getAPIToken(), mActivity.getPersonId());

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


        setDeviceStatusText();

        onOrOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStatusButtonClick();
            }
        });


        printStuff();

    }



    private void handleStatusButtonClick() {


        if( mActivity.getInfoFull().getDevices().get(0).isOn() ) {

            // use Retrofit to turn device off (on = false);
            turnDeviceOff();
        }
        else {
            // use Retrofit to turn device on (on = true);
            turnDeviceOn();
        }

    }


    private void turnDeviceOn() {

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnDeviceOn(mActivity.getAPIToken(), new Info(mActivity.getInfoFull().getDevices().get(0).getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(), "Device successfully turned on", Toast.LENGTH_SHORT).show();
                loadInfoFull();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void turnDeviceOff() {

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnDeviceOff(mActivity.getAPIToken(), new Info(mActivity.getInfoFull().getDevices().get(0).getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(), "Device successfully turned off", Toast.LENGTH_SHORT).show();
                loadInfoFull();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void setDeviceStatusText() {
        if( mActivity.getInfoFull().getDevices().get(0).isOn() )
            onOrOffText.setText("ON");
        else
            onOrOffText.setText("OFF");
    }


    private void printStuff() {

        String daText = "";

        List<Device> deviceList = mActivity.getInfoFull().getDevices();

        List<Object> contentList = new ArrayList<>();

        for( Device device : deviceList ) {
            daText = daText + device.getName() + "\n";
            contentList.add(device);
            for( Zone zone : device.getZones() ) {
                daText = daText + zone.getName() + "\n";
                contentList.add(zone);
            }
        }

        mZoneList.setText( daText );






        mAdapter = new ContentAdapter(getContext(), contentList);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch(mAdapter.getItemViewType(position)){
                    case ContentAdapter.DEVICE_VIEW:
                        return 2;
                    case ContentAdapter.ZONE_VIEW:
                        return 1;
                    default:
                        return 1;
                }

            }
        });

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }


}
