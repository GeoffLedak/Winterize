package com.geoffledak.winterize.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    final private static String TAG = MainActivity.class.getSimpleName();
    private String mToken;
    private String mPersonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToken = "";

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new LoginFragment()).commit();
    }

    public void setAPIToken(String token) { mToken = token; }
    public void setPersonId(String id) { mPersonId = id; }

    public String getPersonId() {
        return mPersonId;
    }
}
