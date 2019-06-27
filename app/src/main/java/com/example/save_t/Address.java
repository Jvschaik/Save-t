package com.example.save_t;

import com.google.gson.Gson;

public class Address {
    public String street;
    public int houseNumber;
    public String postalCode;
    public String city;

    public Address(String street, String houseNumber, String postalCode, String city) {
        this.street = street;
        this.houseNumber = Integer.parseInt(houseNumber);
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}