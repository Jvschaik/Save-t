package com.example.save_t;

import com.google.gson.Gson;

public class SaveTDashboardModel {


    public String address;
    public int inhabitants;
    public Double longitude;
    public Double latitude;
    public String incidentType;


    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public SaveTDashboardModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public SaveTDashboardModel setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
        return this;
    }

    public SaveTDashboardModel setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public SaveTDashboardModel setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public SaveTDashboardModel setIncidentType(String incidentType) {
        this.incidentType = incidentType;
        return this;
    }
}
