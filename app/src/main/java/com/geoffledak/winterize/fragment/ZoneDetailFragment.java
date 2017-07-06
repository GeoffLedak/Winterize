package com.geoffledak.winterize.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.utils.AppKeys;

import org.parceler.Parcels;

/**
 * Created by Turbo9000 on 7/6/2017.
 */

public class ZoneDetailFragment extends Fragment {

    View mView;
    TextView mZoneName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_zone_detail, container, false);
        mZoneName = (TextView) mView.findViewById(R.id.zone_name);

        Zone zone = Parcels.unwrap(getArguments().getParcelable(AppKeys.KEY_SELECTED_ZONE));
        mZoneName.setText(zone.getName());

        return mView;
    }
}
