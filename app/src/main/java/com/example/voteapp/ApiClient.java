package com.example.voteapp;


import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "https://your-api-url.com";

    public void get(String endpoint, ApiResponseListener success, ApiResponseListener error) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = new String(connection.getInputStream().readAllBytes());
                    new Handler(Looper.getMainLooper()).post(() -> success.onResponse(response));
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> error.onResponse("Error code: " + responseCode));
                }
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> error.onResponse(e.getMessage()));
            }
        }).start();
    }

    public void post(String endpoint, Map<String, String> data, ApiResponseListener success, ApiResponseListener error) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                String jsonData = new JSONObject(data).toString();
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(jsonData.getBytes());
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = new String(connection.getInputStream().readAllBytes());
                    new Handler(Looper.getMainLooper()).post(() -> success.onResponse(response));
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> error.onResponse("Error code: " + responseCode));
                }
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> error.onResponse(e.getMessage()));
            }
        }).start();
    }

    public interface ApiResponseListener {
        void onResponse(String response);
    }
}
