package com.geoffledak.winterize.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.fragment.LoginFragment;
import com.geoffledak.winterize.model.InfoFull;

public class MainActivity extends AppCompatActivity {

    final private static String TAG = MainActivity.class.getSimpleName();
    private String mToken;
    private String mPersonId;
    private InfoFull mInfoFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToken = "";

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new LoginFragment()).commit();
    }

    public void setAPIToken(String token) { mToken = token; }
    public String getAPIToken() { return mToken; }
    public void setPersonId(String id) { mPersonId = id; }
    public String getPersonId() { return mPersonId; }
    public void setInfoFull(InfoFull infoFull) { mInfoFull = infoFull; }
    public InfoFull getInfoFull() { return mInfoFull; }
}
