package com.example.jobdemo.bean;

/**
 * @Author LYX
 * @Date 2022/10/14 9:26
 */
public class AddressCodeBean {

    private LocationBean location;
    private String status;
    private String msg;
    private String searchVersion;

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSearchVersion() {
        return searchVersion;
    }

    public void setSearchVersion(String searchVersion) {
        this.searchVersion = searchVersion;
    }

    public static class LocationBean {
        private double lon;
        private String level;
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
