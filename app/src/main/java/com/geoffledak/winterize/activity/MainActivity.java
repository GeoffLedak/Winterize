package com.geoffledak.winterize.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.fragment.LoadingFragment;
import com.geoffledak.winterize.fragment.LoginFragment;
import com.geoffledak.winterize.model.InfoFull;
import com.geoffledak.winterize.utils.AppKeys;
import com.geoffledak.winterize.utils.PrefUtils;
import com.geoffledak.winterize.utils.VisualUtils;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private String mToken;
    private String mPersonId;
    private InfoFull mInfoFull;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        initDrawer();
        shouldDisplayHomeUp();

        if( !TextUtils.isEmpty(PrefUtils.getString(this, AppKeys.PREF_API_TOKEN)) )
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new LoadingFragment()).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new LoginFragment()).commit();
    }


    private void initDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        String[] sectionTitles = {"Overview", "Sign Out"};

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sectionTitles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDrawerItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if( getSupportActionBar() != null ) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }


    public void selectDrawerItem(int position) {

        // Sign Out
        if(position == 1) {
            PrefUtils.removeKey(this, AppKeys.PREF_API_TOKEN);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content_container, new LoginFragment()).commit();
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    public void shouldDisplayHomeUp(){

        if( getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VisualUtils.getInstance().destroyLoadingDialog();
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setSelectedDrawerItem(int position) {
        mDrawerList.setItemChecked(position, true);
    }

    public void setAPIToken(String token) { mToken = token; }
    public String getAPIToken() { return mToken; }
    public void setPersonId(String id) { mPersonId = id; }
    public String getPersonId() { return mPersonId; }
    public void setInfoFull(InfoFull infoFull) { mInfoFull = infoFull; }
    public InfoFull getInfoFull() { return mInfoFull; }
}
