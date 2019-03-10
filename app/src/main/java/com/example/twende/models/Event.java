package com.example.twende.models;

import java.util.ArrayList;

public class Event {

  private String mName;
    private String mDescription;
    private String mUrl;
    private String mStatus;
    private String mCurrency;
//    private String mTimeZone;

    public Event (String name, String description, String url, String status, String currency ){

        this.mName = name;
        this.mDescription = description;
        this.mUrl = url;
        this.mStatus = status;
        this.mCurrency = currency;
//        this.mTimeZone = timezone;

    }

   public String getName(){return mName;}

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getCurrency() {
        return mCurrency;
    }

//    public String getTimeZone() { return mTimeZone; }

}
