package com.example.twende;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class EventBriteService {

    public static void findEvents (String location, Callback callback){

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
}
