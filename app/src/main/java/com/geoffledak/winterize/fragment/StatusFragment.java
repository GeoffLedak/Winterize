package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.adapter.ContentAdapter;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.InfoFull;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.service.RachioClient;
import com.geoffledak.winterize.utils.APIUtils;
import com.geoffledak.winterize.utils.DeviceUtils;
import com.geoffledak.winterize.utils.VisualUtils;

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
    private TextView mPersonName;
    private TextView mPersonEmail;

    ContentAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_status, container, false);
        mActivity = ((MainActivity) getContext());
        mPersonName = (TextView) mView.findViewById(R.id.person_name);
        mPersonEmail = (TextView) mView.findViewById(R.id.person_email);
        mRecyclerView = (RecyclerView)mView.findViewById(R.id.recycler_view);

        initListAndAdapter();
        populateNameAndEmail();
        loadInfoFull();

        mActivity.getSupportActionBar().setTitle("Overview");

        return mView;
    }


    private void initListAndAdapter() {
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

        if( mAdapter == null )
            mAdapter = new ContentAdapter((getContext()));

        mRecyclerView.setAdapter(mAdapter);
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
                    VisualUtils.getInstance().dismissLoadingDialog();
                }
            }
            @Override
            public void onFailure(Call<InfoFull> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to fetch information", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateNameAndEmail() {

        if( mActivity != null && mActivity.getInfoFull() != null ) {

            if( mPersonName != null && mActivity.getInfoFull().getFullName() != null ) {
                mView.findViewById(R.id.name_heading).setVisibility(View.VISIBLE);
                mPersonName.setText(mActivity.getInfoFull().getFullName());
            }

            if( mPersonEmail != null && mActivity.getInfoFull().getEmail() != null ) {
                mView.findViewById(R.id.email_heading).setVisibility(View.VISIBLE);
                mPersonEmail.setText(mActivity.getInfoFull().getEmail());
            }
        }
    }


    private void populateStatusInfo() {

        populateNameAndEmail();

        // Iterate through all devices and sort each zone list by 'zoneNumber'
        mActivity.getInfoFull().setDevices(DeviceUtils.sortDeviceZones( mActivity.getInfoFull().getDevices() ) );

        List<Device> deviceList = mActivity.getInfoFull().getDevices();
        List<Object> contentList = new ArrayList<>();

        for( Device device : deviceList ) {
            contentList.add(device);
            for( Zone zone : device.getZones() ) {
                contentList.add(zone);
            }
        }

        mAdapter.setItemList(contentList);
        mAdapter.notifyDataSetChanged();
    }

}
