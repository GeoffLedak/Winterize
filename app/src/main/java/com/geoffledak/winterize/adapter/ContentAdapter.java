package com.geoffledak.winterize.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geoffledak.winterize.R;
import com.geoffledak.winterize.fragment.ZoneDetailFragment;
import com.geoffledak.winterize.model.Device;
import com.geoffledak.winterize.model.Zone;
import com.geoffledak.winterize.utils.AppKeys;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Turbo9000 on 7/6/2017.
 */

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int BLANK_VIEW = -1;
    public static final int DEVICE_VIEW = 0;
    public static final int ZONE_VIEW = 1;

    Context mContext;
    List<Object> mContentList;

    public ContentAdapter(Context context, List<Object> contentList) {

        mContext = context;
        mContentList = contentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == DEVICE_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_device_item, parent, false);
            return new DeviceViewHolder(view);
        }
        else if (viewType == ZONE_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_zone_item, parent, false);
            return new ZoneViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_blank_item, parent, false);
            return new BlankViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if( holder.getItemViewType() == DEVICE_VIEW ) {

            ((DeviceViewHolder)holder).mDeviceName.setText(((Device) mContentList.get(position)).getName());
        }
        else if( holder.getItemViewType() == ZONE_VIEW ) {

            ((ZoneViewHolder)holder).mZoneName.setText(((Zone) mContentList.get(position)).getName());
        }
        else {

        }

    }

    @Override
    public int getItemCount() {

        if (mContentList == null)
            return 0;
        else
            return mContentList.size();
    }

    @Override
    public int getItemViewType(int position) {


        if (mContentList.get(position) instanceof Device)
            return DEVICE_VIEW;
        else if (mContentList.get(position) instanceof Zone)
            return ZONE_VIEW;
        else
            return BLANK_VIEW;
    }







    private class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView mDeviceName;

        private DeviceViewHolder(View view) {
            super(view);
            mDeviceName = (TextView) view.findViewById(R.id.device_name);

        }
    }

    private class ZoneViewHolder extends RecyclerView.ViewHolder {

        TextView mZoneName;

        private ZoneViewHolder(View view) {
            super(view);
            mZoneName = (TextView) view.findViewById(R.id.zone_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), "Clicky", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "CLICK!!!");



                    Bundle args = new Bundle();
                    // args.putString(PrefKeys.KEY_IMAGE_URL, mImageItemList.get(getAdapterPosition()).getMedia().getM());

                    args.putParcelable(AppKeys.KEY_SELECTED_ZONE, Parcels.wrap( mContentList.get(getAdapterPosition())));



                    ZoneDetailFragment fragment = new ZoneDetailFragment();
                    fragment.setArguments(args);

                    ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_content_container, fragment).commit();


                }
            });

        }





    }


    private class BlankViewHolder extends RecyclerView.ViewHolder {

        private BlankViewHolder(View view) {
            super(view);

        }

    }

}
