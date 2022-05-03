package com.example.wezacare1;

import java.util.Arrays;

public class CruiseShipsModel {

    String ship_id;
    String ship_name;
    String ship_model;
    String ship_type;
    String active;
    String year_built;
    String home_port;
    String status;
    String url;
    String image;

    @Override
    public String toString() {
        return "CruiseShipsModel{" +
                "ship_id='" + ship_id + '\'' +
                ", ship_name='" + ship_name + '\'' +
                ", ship_model='" + ship_model + '\'' +
                ", ship_type='" + ship_type + '\'' +
                ", active='" + active + '\'' +
                ", year_built='" + year_built + '\'' +
                ", home_port='" + home_port + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CruiseShipsModel() {

    }

    public CruiseShipsModel(String ship_id, String ship_name, String ship_model, String ship_type, String active, String year_built, String home_port, String status, String url,String image) {
        this.ship_id = ship_id;
        this.ship_name = ship_name;
        this.ship_model = ship_model;
        this.ship_type = ship_type;
        this.active = active;
        this.year_built = year_built;
        this.home_port = home_port;
        this.status = status;
        this.url = url;
        this.image = image;
    }


    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_model() {
        return ship_model;
    }

    public void setShip_model(String ship_model) {
        this.ship_model = ship_model;
    }

    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getYear_built() {
        return year_built;
    }

    public void setYear_built(String year_built) {
        this.year_built = year_built;
    }

    public String getHome_port() {
        return home_port;
    }

    public void setHome_port(String home_port) {
        this.home_port = home_port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
