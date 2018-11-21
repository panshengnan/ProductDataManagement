package com.cgwx.data.dto;

public class CountryInfo {
    String countryName;
    Object imageGeo;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }

    public String getCountryName() {
        return countryName;
    }

    public Object getImageGeo() {
        return imageGeo;
    }
}
