package com.master;

/**
 * data class.
 * java bean.
 */
public class SensorModel {

   private int id;
   private String macaddr;
   private String data;
   private String lat;
   private String lng;
   private String created_at;
   private String updated_at;

    public SensorModel(int id, String macaddr, String data, String lat, String lng, String created_at, String updated_at) {
        this.id = id;
        this.macaddr = macaddr;
        this.data = data;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.lat = lat;
        this.lng = lng;

    }

    public int getId() {
        return id;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public String getData() {
        return data;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
