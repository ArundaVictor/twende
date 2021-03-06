package com.example.twende.services;

import com.example.twende.Constants;
import com.example.twende.models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventBriteService {

    public static void findEvents(String location, Callback callback) {

        //client to create and send request
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.EVENT_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.EVENT_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.EVENT_TOKEN)
                .build();

        //calling a request Asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    //parsing json and creating objects
    public ArrayList<Event> processResults(Response response) {

        ArrayList<Event> events = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject eventsJSON = new JSONObject(jsonData);
            JSONArray activityJSON = eventsJSON.getJSONArray("events");

            if (response.isSuccessful()) {

                for (int i = 0; i < activityJSON.length(); i++) {
                    JSONObject eventJSON = activityJSON.getJSONObject(i);
                    String description = eventJSON.getString("description");
                    String url = eventJSON.getString("url");
                    String currency = eventJSON.getString("currency");
                    String status = eventJSON.getString("status");
                    String name = eventJSON.getJSONObject("name").getString("text");

                    Event event = new Event(name,description, url, status, currency);
                    events.add(event);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return events;
    }
}


