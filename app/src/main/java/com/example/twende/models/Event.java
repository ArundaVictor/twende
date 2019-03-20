package com.example.twende.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Event {

  private String name;
    private String description;
    private String url;
    private String status;
    private String currency;
//    private String mTimeZone;
private String pushId;
private String index;

    public Event() {}

    public Event (String name, String description, String url, String status, String currency ){

        this.name = name;
        this.description = description;
        this.url = url;
        this.status = status;
        this.currency = currency;
//        this.mTimeZone = timezone;
        this.index = "not specified";

    }

   public String getName(){return name;}

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrency() {
        return currency;
    }

//    public String getTimeZone() { return mTimeZone; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }

    public String getIndex() {return index;}

    public void setIndex(String index) {this.index = index;}

}
