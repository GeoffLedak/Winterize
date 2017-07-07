package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.model.RunZone;
import com.geoffledak.winterize.model.Zone;
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

public class ZoneDetailFragment extends Fragment {

    View mView;
    MainActivity mActivity;
    Zone mZone;
    EditText mMinuteText;
    Button mStartButton;
    Button mStopButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_zone_detail, container, false);
        mActivity = (MainActivity) getContext();
        mZone = Parcels.unwrap(getArguments().getParcelable(AppKeys.KEY_SELECTED_ZONE));
        mMinuteText = (EditText) mView.findViewById(R.id.edit_text_minutes);
        mStartButton = (Button) mView.findViewById(R.id.zone_start_button);
        mStopButton = (Button) mView.findViewById(R.id.zone_stop_button);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStartButtonClick();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStopButtonClick();
            }
        });

        ((MainActivity)getContext()).getSupportActionBar().setTitle(mZone.getName());

        return mView;
    }


    private void handleStartButtonClick() {

        VisualUtils.getInstance().showLoadingDialog(getContext());

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnZoneOn(mActivity.getAPIToken(), new RunZone(mZone.getId(), Integer.parseInt(mMinuteText.getText().toString()) * 60 ));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), "Zone successfully turned on", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
        });
    }


    private void handleStopButtonClick() {

        VisualUtils.getInstance().showLoadingDialog(getContext());

        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);

        Call<String> call = client.turnZoneOn(mActivity.getAPIToken(), new RunZone(mZone.getId(), 0 ));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), "Zone successfully turned off", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                VisualUtils.getInstance().dismissLoadingDialog();
            }
        });
    }


}
