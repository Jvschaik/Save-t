package com.example.save_t;

import com.google.gson.Gson;

public class SaveTDashboardModel {


    public Address address;
    public int inhabitants;
    public Double longitude;
    public Double latitude;
    public String type;


    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public SaveTDashboardModel setAddress(Address address) {
        this.address = address;
        return this;
    }

    public SaveTDashboardModel setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
        return this;
    }

    public SaveTDashboardModel setInhabitants(String inhabitants) {
        this.inhabitants = Integer.parseInt(inhabitants);
        return this;
    }

    public SaveTDashboardModel setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public SaveTDashboardModel setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
        return this;
    }

    public SaveTDashboardModel setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public SaveTDashboardModel setLatitude(String latitude) {
        this.latitude = Double.parseDouble(latitude);
        return this;
    }
    public SaveTDashboardModel setType(String type) {
        this.type = type;
        return this;
    }
}