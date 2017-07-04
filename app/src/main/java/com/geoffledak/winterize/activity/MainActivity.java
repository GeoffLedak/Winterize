package com.geoffledak.winterize.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.model.person.Info;
import com.geoffledak.winterize.service.RachioClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final private static String TAG = MainActivity.class.getSimpleName();

    private TextView mDaTextView;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDaTextView = (TextView) findViewById(R.id.stuff);
        mToken = getResources().getString(R.string.api_authorization);
        Retrofit retrofit = buildRetrofit();

        RachioClient client = retrofit.create(RachioClient.class);
        Call<Info> call = client.infoForPerson(mToken);

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();
                mDaTextView.setText(info.getId());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private Retrofit buildRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }




}
