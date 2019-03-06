package com.example.twende;

import java.util.ArrayList;

public class Event {

    private String mName;
    private String mDescription;
    private String mUrl;
    private String mStatus;
    private String mCurrency;
    private ArrayList <String> mStart;
    private ArrayList <String> mEnd;

    public Event (String name, String description, String url, String status, String currency, ArrayList <String> start, ArrayList <String> end){

        this.mName = name;
        this.mDescription = description;
        this.mUrl = url;
        this.mStatus = status;
        this.mCurrency = currency;
        this.mStart = start;
        this.mEnd = end;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public ArrayList<String> getStart() {
        return mStart;
    }

    public ArrayList<String> getEnd() {
        return mEnd;
    }
}
