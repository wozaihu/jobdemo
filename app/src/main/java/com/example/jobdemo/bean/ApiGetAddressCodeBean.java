package com.example.jobdemo.bean;

import java.util.List;

/**
 * @Author LYX
 * @Date 2022/10/19 15:27
 */
public class ApiGetAddressCodeBean {

    private DataBean data;
    private String msg;
    private boolean success;
    private int code;
    private String taskNo;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public static class DataBean {
        private int count;
        private List<GeocodesBean> geocodes;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<GeocodesBean> getGeocodes() {
            return geocodes;
        }

        public void setGeocodes(List<GeocodesBean> geocodes) {
            this.geocodes = geocodes;
        }

        public static class GeocodesBean {
            private String country;
            private String formatted_address;
            private String city;
            private String adcode;
            private String level;
            private BuildingBean building;
            private List<?> number;
            private String province;
            private String citycode;
            private List<?> street;
            private String district;
            private String location;
            private NeighborhoodBean neighborhood;
            private List<?> township;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(String formatted_address) {
                this.formatted_address = formatted_address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public BuildingBean getBuilding() {
                return building;
            }

            public void setBuilding(BuildingBean building) {
                this.building = building;
            }

            public List<?> getNumber() {
                return number;
            }

            public void setNumber(List<?> number) {
                this.number = number;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }

            public List<?> getStreet() {
                return street;
            }

            public void setStreet(List<?> street) {
                this.street = street;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public NeighborhoodBean getNeighborhood() {
                return neighborhood;
            }

            public void setNeighborhood(NeighborhoodBean neighborhood) {
                this.neighborhood = neighborhood;
            }

            public List<?> getTownship() {
                return township;
            }

            public void setTownship(List<?> township) {
                this.township = township;
            }

            public static class BuildingBean {
                private List<?> name;
                private List<?> type;

                public List<?> getName() {
                    return name;
                }

                public void setName(List<?> name) {
                    this.name = name;
                }

                public List<?> getType() {
                    return type;
                }

                public void setType(List<?> type) {
                    this.type = type;
                }
            }

            public static class NeighborhoodBean {
                private List<?> name;
                private List<?> type;

                public List<?> getName() {
                    return name;
                }

                public void setName(List<?> name) {
                    this.name = name;
                }

                public List<?> getType() {
                    return type;
                }

                public void setType(List<?> type) {
                    this.type = type;
                }
            }
        }
    }
}
