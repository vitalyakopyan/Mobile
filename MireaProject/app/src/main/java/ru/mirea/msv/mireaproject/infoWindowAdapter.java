package ru.mirea.msv.mireaproject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class infoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context mContext;
    private View mWindow;

    infoWindowAdapter(Context context) {
        mContext = context;
        mWindow = ((Activity)context).getLayoutInflater().inflate(R.layout.custom_infowindow, null);
    }

    private void setWindow(Marker marker){
        ((TextView)mWindow.findViewById(R.id.title)).setText(marker.getTitle());
        ((TextView)mWindow.findViewById(R.id.snippet)).setText(marker.getSnippet());
    }

    @Override
    public View getInfoWindow(Marker marker) {
        setWindow(marker);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        setWindow(marker);
        return mWindow;
    }
}
