package ru.mirea.msv.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Browser extends Fragment {

    public Browser() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browser, container, false);

        String url = "https://www.google.com/";
        WebView view = (WebView) rootView.findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient());
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLoadsImagesAutomatically(true);
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        view.getSettings().setSupportZoom(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setDisplayZoomControls(false);

        view.loadUrl(url);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonReload).setOnClickListener((view2)->{
            ((WebView)view.findViewById(R.id.webView)).loadUrl( "javascript:window.location.reload( true )" );
        });

        view.findViewById(R.id.buttonBack).setOnClickListener((view2)->{
            WebView wv = ((WebView)view.findViewById(R.id.webView));
            if (wv.canGoBack()) wv.goBack();
        });

        view.findViewById(R.id.buttonForward).setOnClickListener((view2)->{
            WebView wv = ((WebView)view.findViewById(R.id.webView));
            if (wv.canGoForward()) wv.goForward();
        });
    }
}