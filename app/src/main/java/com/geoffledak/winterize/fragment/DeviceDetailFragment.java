package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.service.RachioClient;
import com.geoffledak.winterize.utils.APIUtils;
import com.geoffledak.winterize.utils.AppKeys;
import com.geoffledak.winterize.utils.VisualUtils;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Geoff Ledak on 7/6/2017.
 */

public class DeviceDetailFragment extends Fragment {

    View mView;
    MainActivity mActivity;
    TextView mModel;
    Device mDevice;
    Switch mStandbyModeSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_device_detail, container, false);
        mActivity = (MainActivity) getContext();
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

        if( mDevice.isOn() )
            turnDeviceOff();
        else
            turnDeviceOn();
    }


    private void turnDeviceOn() {

        VisualUtils.getInstance().showLoadingDialog(getContext());

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnDeviceOn(mActivity.getAPIToken(), new Info(mDevice.getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(), "Device successfully turned on", Toast.LENGTH_SHORT).show();
                mDevice.setOn(true);
                VisualUtils.getInstance().dismissLoadingDialog();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
                mStandbyModeSwitch.setChecked(true);
            }
        });
    }


    private void turnDeviceOff() {

        VisualUtils.getInstance().showLoadingDialog(getContext());

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnDeviceOff(mActivity.getAPIToken(), new Info(mDevice.getId()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(), "Device successfully turned off", Toast.LENGTH_SHORT).show();
                mDevice.setOn(false);
                VisualUtils.getInstance().dismissLoadingDialog();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
                mStandbyModeSwitch.setChecked(false);
            }
        });
    }
}
