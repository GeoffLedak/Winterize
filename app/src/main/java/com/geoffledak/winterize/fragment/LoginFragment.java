package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.activity.MainActivity;
import com.geoffledak.winterize.model.Info;
import com.geoffledak.winterize.service.RachioClient;
import com.geoffledak.winterize.utils.APIUtils;
import com.geoffledak.winterize.utils.AppKeys;
import com.geoffledak.winterize.utils.PrefUtils;
import com.geoffledak.winterize.utils.VisualUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Geoff Ledak on 7/4/2017.
 */

public class LoginFragment extends Fragment {

    private View mView;
    private MainActivity mActivity;
    private EditText mTokenText;
    private Button mSubmitButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_login, container, false);
        mActivity = ((MainActivity)getContext());
        mTokenText = (EditText) mView.findViewById(R.id.token_text);

        String token = PrefUtils.getString(getContext(), AppKeys.PREF_API_TOKEN);

        if( !TextUtils.isEmpty(token) )
            mTokenText.setText(token);
        else
            mTokenText.setText(getResources().getString(R.string.api_authorization));

        mSubmitButton = (Button) mView.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyToken();
            }
        });

        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mActivity.getSupportActionBar().hide();

        return mView;
    }

    private void verifyToken() {
        final String token = APIUtils.buildTokenString(getContext(), mTokenText.getText().toString());
        Retrofit retrofit = APIUtils.buildRetrofit(getContext());
        RachioClient client = retrofit.create(RachioClient.class);
        Call<Info> call = client.idForPerson(token);

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();

                if( info == null )
                    Toast.makeText(getContext(), "Invalid token", Toast.LENGTH_SHORT).show();
                else {
                    PrefUtils.saveString(getContext(), AppKeys.PREF_API_TOKEN, token);
                    mActivity.setAPIToken(token);
                    mActivity.setPersonId(info.getId());
                    loadStatusFragment();
                }
            }
            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadStatusFragment() {
        VisualUtils.getInstance().showLoadingDialog(getContext());
        mActivity.getSupportActionBar().show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new StatusFragment()).commit();
    }
}
