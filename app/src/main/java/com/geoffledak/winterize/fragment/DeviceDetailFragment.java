package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.utils.AppKeys;

import org.parceler.Parcels;

/**
 * Created by Turbo9000 on 7/6/2017.
 */

public class DeviceDetailFragment extends Fragment {

    View mView;
    TextView mModel;
    Device mDevice;
    Switch mStandbyModeSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_device_detail, container, false);
        mDevice = Parcels.unwrap(getArguments().getParcelable(AppKeys.KEY_SELECTED_DEVICE));

        mModel = (TextView) mView.findViewById(R.id.model);
        mStandbyModeSwitch = (Switch) mView.findViewById(R.id.standby_mode_switch);
        mStandbyModeSwitch.setChecked( !mDevice.isOn() );
        mStandbyModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStandbyModeSwitchClick();
            }
        });

        mModel.setText(mDevice.getModel());

        ((MainActivity)getContext()).getSupportActionBar().setTitle(mDevice.getName());

        return mView;
    }



    private void handleStandbyModeSwitchClick() {



    }


}
