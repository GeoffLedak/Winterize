package com.geoffledak.winterize.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Geoff Ledak on 7/5/2017.
 */

public class StatusFragment extends Fragment {

    private View mView;
    private MainActivity mActivity;
    private TextView mPersonName;
    private TextView mPersonEmail;
    private LinearLayout mCommandButton;

    private ContentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_status, container, false);
        mActivity = ((MainActivity) getContext());
        mPersonName = (TextView) mView.findViewById(R.id.person_name);
        mPersonEmail = (TextView) mView.findViewById(R.id.person_email);
        mCommandButton = (LinearLayout) mView.findViewById(R.id.command_button);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadInfoFull();
            }
        });
        mCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCommandClick();
            }
        });

        initListAndAdapter();
        populateNameAndEmail();
        loadInfoFull();

        mActivity.setSelectedDrawerItem(0);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        if( mAdapter.getItemCount() > 0 )
            mView.findViewById(R.id.devices_heading).setVisibility(View.VISIBLE);


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

        if( contentList.size() > 0 )
            mView.findViewById(R.id.devices_heading).setVisibility(View.VISIBLE);

        mAdapter.setItemList(contentList);
        mAdapter.notifyDataSetChanged();
        if( mSwipeRefreshLayout.isRefreshing() )
            Toast.makeText(getContext(), "Refresh successful", Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private void handleCommandClick() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your command");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), "Speech not supported", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String command = result.get(0);
                    command = command.trim();

                    Toast.makeText(getContext(), command, Toast.LENGTH_SHORT).show();

                    if( command.equalsIgnoreCase("turn device off") )
                        DeviceUtils.turnDeviceOff(mActivity, mActivity.getInfoFull().getDevices().get(0));

                    if( command.equalsIgnoreCase("turn device on") )
                        DeviceUtils.turnDeviceOn(mActivity, mActivity.getInfoFull().getDevices().get(0));





                }
                break;
            }

        }
    }
}
