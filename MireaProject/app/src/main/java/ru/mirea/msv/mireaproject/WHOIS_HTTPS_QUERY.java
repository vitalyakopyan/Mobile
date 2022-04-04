package ru.mirea.msv.mireaproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class WHOIS_HTTPS_QUERY extends Fragment {
    private Gson gson;
    private TextView textViewIP, textViewQueryRes;
    private String pubIP;
    private String urlIP = "https://api.ipify.org?format=json";
    private String urlWHOIS = "http://ip-api.com/json/";

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_whois_https, container, false);
        textViewIP = rootView.findViewById(R.id.textViewIP);
        textViewQueryRes = rootView.findViewById(R.id.textViewResult);
        gson = new Gson();
        new DownloadPageTaskHTTPS(textViewIP).execute(urlIP);
        return rootView;
    }

    private class DownloadPageTaskHTTP extends AsyncTask<String, Void, String> {
        private TextView view;

        DownloadPageTaskHTTP(TextView view){
            this.view = view;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return getHTTP(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && result.charAt(0) == '{') {
                StringBuilder sb = new StringBuilder();
                JsonObject jo = gson.fromJson(result, JsonObject.class);
                ArrayList<String> keys = new ArrayList<>(jo.keySet());
                for (String k : keys) {
                    sb.append(k + " : ");
                    sb.append(jo.get(k).getAsString() + "\n");
                }
                view.setText(sb.toString().trim());
                if (view == textViewIP) {
                    pubIP = jo.get("ip").getAsString();
                    new DownloadPageTaskHTTP(textViewQueryRes).execute(urlWHOIS + pubIP);
                }
            }
            else {
                view.setText(result);
            }
        }
    }

    private class DownloadPageTaskHTTPS extends AsyncTask<String, Void, String> {
        private TextView view;

        DownloadPageTaskHTTPS(TextView view){
            this.view = view;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return getHTTPS(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null)
            if (result.charAt(0) == '{'){
                StringBuilder sb = new StringBuilder();
                JsonObject jo = gson.fromJson(result, JsonObject.class);
                ArrayList<String> keys = new ArrayList<>(jo.keySet());
                for (String k : keys) {
                    sb.append(k + " : ");
                    sb.append(jo.get(k).getAsString() + "\n");
                }
                view.setText(sb.toString().trim());
                if (view == textViewIP) {
                    pubIP = jo.get("ip").getAsString();
                    new DownloadPageTaskHTTP(textViewQueryRes).execute(urlWHOIS + pubIP);
                }
            }
            else {
                view.setText(result);
            }
        }
    }
    private String getHTTP(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(1000);
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                data = new String(result);
            } else {
                data = connection.getResponseMessage() + " . Error Code : " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private String getHTTPS(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpsURLConnection connection = (HttpsURLConnection) url
                    .openConnection();
            connection.setReadTimeout(1000);
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                data = new String(result);
            } else {
                data = connection.getResponseMessage() + " . Error Code : " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}
