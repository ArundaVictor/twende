package com.example.twende.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Event {

  private String Name;
    private String Description;
    private String Url;
    private String Status;
    private String Currency;
//    private String mTimeZone;

    public Event() {}

    public Event (String name, String description, String url, String status, String currency ){

        this.Name = name;
        this.Description = description;
        this.Url = url;
        this.Status = status;
        this.Currency = currency;
//        this.mTimeZone = timezone;

    }

   public String getName(){return Name;}

    public String getDescription() {
        return Description;
    }

    public String getUrl() {
        return Url;
    }

    public String getStatus() {
        return Status;
    }

    public String getCurrency() {
        return Currency;
    }

//    public String getTimeZone() { return mTimeZone; }

}
