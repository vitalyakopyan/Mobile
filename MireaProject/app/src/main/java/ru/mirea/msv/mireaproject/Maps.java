package ru.mirea.msv.mireaproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends Fragment {
    private GoogleMap mMap;
    private MapView mMapView;
    private String[] Permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
            //,Manifest.permission.ACCESS_BACKGROUND_LOCATION
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!hasPermissions(getContext(), Permissions))
            requestPermissions(Permissions, 100);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                mMap = gMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.setInfoWindowAdapter(new infoWindowAdapter(getContext()));
                mMap.setMyLocationEnabled(true);

                LatLng moscow = new LatLng(55.751244, 37.618423);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(moscow).zoom(11).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.getUiSettings().setZoomControlsEnabled(true);

                mMap.addMarker(new MarkerOptions().position(new LatLng(55.669459, 37.482289)).title("РТУ МИРЭА").snippet("" +
                        "Координаты: 55.669459, 37.482289\n" +
                        "Адрес: проспект Вернадского, 78с3\n" +
                        "Москва, Россия, 119454\n" +
                        "Год основания: 28 мая 1947 г."));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.728670, 37.572941)).title("ВУЦ РТУ МИРЭА").snippet("" +
                        "Координаты: 55.728670, 37.572941\n" +
                        "Адрес: Усачёва улица, 7/1\n" +
                        "Москва, Россия, 119048\n" +
                        "Год основания: 28 мая 1947 г."));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.731504, 37.574856)).title("МИТХТ РТУ МИРЭА").snippet("" +
                        "Координаты: 55.731504, 37.574856\n" +
                        "Адрес: Малая Пироговская улица, 1с5\n" +
                        "Москва, Россия, 119435\n" +
                        "Год основания: 14 июля 1900 г."));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.724526, 37.631735)).title("КПиИТ РТУ МИРЭА").snippet("" +
                        "Координаты: 55.724526, 37.631735\n" +
                        "Адрес: 1-й Щипковский переулок, 23с1\n" +
                        "Москва, Россия, 115093\n" +
                        "Год основания: 28 мая 1947 г."));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.764961, 37.741853)).title("КДО РТУ МИРЭА").snippet("" +
                        "Координаты: 55.764961, 37.741853\n" +
                        "Адрес: 5-я улица Соколиной Горы, 22\n" +
                        "Москва, Россия, 105275\n" +
                        "Год основания: 28 мая 1947 г."));
                mMap.addMarker(new MarkerOptions().position(new LatLng(55.794001, 37.701717)).title("МГУПИ РТУ МИРЭА").snippet("" +
                        "Координаты: 55.794001, 37.701717\n" +
                        "Адрес: улица Стромынка, 20\n" +
                        "Москва, Россия, 107076\n" +
                        "Год основания: 29 августа 1936 г."));
            }
        });
        return rootView;
    }

    private boolean hasPermissions(Context context, String[] permissions) {
        for(String p : permissions)
            if (ActivityCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED)
                return false;
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
