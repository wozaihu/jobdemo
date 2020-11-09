package com.example.jobdemo.bean;

import java.util.List;

public class demo {

    /**
     * message : 信息已获取到!
     * status : 1
     * userInfo : [{"Address":"广东-深圳-罗湖区 金丽国际珠宝交易中心","BusinesContact":"哈哈","BusinesEmail":"","BusinesFax":"","BusinesTel":"66666888","BusinessCircle":"翠竹,田贝,水贝","BusinessLicenseStatus":"2","BusinessModel":"原料商","City":"","CompanyId":80000153,"CompanyIdea":"","CompanyInformation":"深圳市点金台网络技术有限公司，成立于2015年，是中国首家专注珠宝行业移动互联网服务平台，为首批进驻国家级前海蛇口自贸区珠宝互联网企业。公司先期运营的点金台APP，首创珠宝行业垂直招聘+社交模式，目前为珠宝行业第一移动招聘平台。","CompanyName":"天下大事必作于细。是以圣人终不为大，故能成其大。夫轻诺必寡信","CompanySize":"501-1000人","CompanyTag":"","CompanyType":"民营企业","CompanyTypeList":[{"BasicValue":"中央企业","Id":1},{"BasicValue":"国有企业","Id":2},{"BasicValue":"三资企业","Id":3},{"BasicValue":"集体企业","Id":4},{"BasicValue":"民营企业","Id":5}],"CreateDateTime":"2016-10-21","District":"","Email":"sdivenson@51djt.com","Fax":"075525509436","HeadMan":"","IsOpenBusines":"True","IsSecrecy":"False","Latitude":"22.57151","Longitude":"114.1336","MainProduct":"贵金属、彩宝","MainProductId":"[1],(2),(5)","OrganizationCodeStatus":"2","Path":"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Center/Attachment/80000153/Certification/IMG_20171218_10043820171218100439612.jpg","ProductPicture":"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Member/Attachment/80000153/BusinesInfo/151719354604124020180129103904349.jpg","Province":"","TaxRegistrationStatus":"2","TeamStyle":"http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916690943520171124100606561.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916691134020171124100606717.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916689747520171124100606749.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916689218220171124100606780.jpg","Tel":"15920088522","ThreeCardAll":"False","Website":"www.51djt.com"}]
     */

    private String message;
    private String status;
    private List<UserInfoBean> userInfo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserInfoBean> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfoBean> userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * Address : 广东-深圳-罗湖区 金丽国际珠宝交易中心
         * BusinesContact : 哈哈
         * BusinesEmail :
         * BusinesFax :
         * BusinesTel : 66666888
         * BusinessCircle : 翠竹,田贝,水贝
         * BusinessLicenseStatus : 2
         * BusinessModel : 原料商
         * City :
         * CompanyId : 80000153
         * CompanyIdea :
         * CompanyInformation : 深圳市点金台网络技术有限公司，成立于2015年，是中国首家专注珠宝行业移动互联网服务平台，为首批进驻国家级前海蛇口自贸区珠宝互联网企业。公司先期运营的点金台APP，首创珠宝行业垂直招聘+社交模式，目前为珠宝行业第一移动招聘平台。
         * CompanyName : 天下大事必作于细。是以圣人终不为大，故能成其大。夫轻诺必寡信
         * CompanySize : 501-1000人
         * CompanyTag :
         * CompanyType : 民营企业
         * CompanyTypeList : [{"BasicValue":"中央企业","Id":1},{"BasicValue":"国有企业","Id":2},{"BasicValue":"三资企业","Id":3},{"BasicValue":"集体企业","Id":4},{"BasicValue":"民营企业","Id":5}]
         * CreateDateTime : 2016-10-21
         * District :
         * Email : sdivenson@51djt.com
         * Fax : 075525509436
         * HeadMan :
         * IsOpenBusines : True
         * IsSecrecy : False
         * Latitude : 22.57151
         * Longitude : 114.1336
         * MainProduct : 贵金属、彩宝
         * MainProductId : [1],(2),(5)
         * OrganizationCodeStatus : 2
         * Path : http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Center/Attachment/80000153/Certification/IMG_20171218_10043820171218100439612.jpg
         * ProductPicture : http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Member/Attachment/80000153/BusinesInfo/151719354604124020180129103904349.jpg
         * Province :
         * TaxRegistrationStatus : 2
         * TeamStyle : http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916690943520171124100606561.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916691134020171124100606717.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916689747520171124100606749.jpg,http://djt-bucket-public-dev.oss-cn-shenzhen.aliyuncs.com/Company/Attachment/80000153/TeamStyle/151148916689218220171124100606780.jpg
         * Tel : 15920088522
         * ThreeCardAll : False
         * Website : www.51djt.com
         */

        private String Address;
        private String BusinesContact;
        private String BusinesEmail;
        private String BusinesFax;
        private String BusinesTel;
        private String BusinessCircle;
        private String BusinessLicenseStatus;
        private String BusinessModel;
        private String City;
        private int CompanyId;
        private String CompanyIdea;
        private String CompanyInformation;
        private String CompanyName;
        private String CompanySize;
        private String CompanyTag;
        private String CompanyType;
        private String CreateDateTime;
        private String District;
        private String Email;
        private String Fax;
        private String HeadMan;
        private String IsOpenBusines;
        private String IsSecrecy;
        private String Latitude;
        private String Longitude;
        private String MainProduct;
        private String MainProductId;
        private String OrganizationCodeStatus;
        private String Path;
        private String ProductPicture;
        private String Province;
        private String TaxRegistrationStatus;
        private String TeamStyle;
        private String Tel;
        private String ThreeCardAll;
        private String Website;
        private List<CompanyTypeListBean> CompanyTypeList;

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getBusinesContact() {
            return BusinesContact;
        }

        public void setBusinesContact(String BusinesContact) {
            this.BusinesContact = BusinesContact;
        }

        public String getBusinesEmail() {
            return BusinesEmail;
        }

        public void setBusinesEmail(String BusinesEmail) {
            this.BusinesEmail = BusinesEmail;
        }

        public String getBusinesFax() {
            return BusinesFax;
        }

        public void setBusinesFax(String BusinesFax) {
            this.BusinesFax = BusinesFax;
        }

        public String getBusinesTel() {
            return BusinesTel;
        }

        public void setBusinesTel(String BusinesTel) {
            this.BusinesTel = BusinesTel;
        }

        public String getBusinessCircle() {
            return BusinessCircle;
        }

        public void setBusinessCircle(String BusinessCircle) {
            this.BusinessCircle = BusinessCircle;
        }

        public String getBusinessLicenseStatus() {
            return BusinessLicenseStatus;
        }

        public void setBusinessLicenseStatus(String BusinessLicenseStatus) {
            this.BusinessLicenseStatus = BusinessLicenseStatus;
        }

        public String getBusinessModel() {
            return BusinessModel;
        }

        public void setBusinessModel(String BusinessModel) {
            this.BusinessModel = BusinessModel;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public String getCompanyIdea() {
            return CompanyIdea;
        }

        public void setCompanyIdea(String CompanyIdea) {
            this.CompanyIdea = CompanyIdea;
        }

        public String getCompanyInformation() {
            return CompanyInformation;
        }

        public void setCompanyInformation(String CompanyInformation) {
            this.CompanyInformation = CompanyInformation;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getCompanySize() {
            return CompanySize;
        }

        public void setCompanySize(String CompanySize) {
            this.CompanySize = CompanySize;
        }

        public String getCompanyTag() {
            return CompanyTag;
        }

        public void setCompanyTag(String CompanyTag) {
            this.CompanyTag = CompanyTag;
        }

        public String getCompanyType() {
            return CompanyType;
        }

        public void setCompanyType(String CompanyType) {
            this.CompanyType = CompanyType;
        }

        public String getCreateDateTime() {
            return CreateDateTime;
        }

        public void setCreateDateTime(String CreateDateTime) {
            this.CreateDateTime = CreateDateTime;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getFax() {
            return Fax;
        }

        public void setFax(String Fax) {
            this.Fax = Fax;
        }

        public String getHeadMan() {
            return HeadMan;
        }

        public void setHeadMan(String HeadMan) {
            this.HeadMan = HeadMan;
        }

        public String getIsOpenBusines() {
            return IsOpenBusines;
        }

        public void setIsOpenBusines(String IsOpenBusines) {
            this.IsOpenBusines = IsOpenBusines;
        }

        public String getIsSecrecy() {
            return IsSecrecy;
        }

        public void setIsSecrecy(String IsSecrecy) {
            this.IsSecrecy = IsSecrecy;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getMainProduct() {
            return MainProduct;
        }

        public void setMainProduct(String MainProduct) {
            this.MainProduct = MainProduct;
        }

        public String getMainProductId() {
            return MainProductId;
        }

        public void setMainProductId(String MainProductId) {
            this.MainProductId = MainProductId;
        }

        public String getOrganizationCodeStatus() {
            return OrganizationCodeStatus;
        }

        public void setOrganizationCodeStatus(String OrganizationCodeStatus) {
            this.OrganizationCodeStatus = OrganizationCodeStatus;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getProductPicture() {
            return ProductPicture;
        }

        public void setProductPicture(String ProductPicture) {
            this.ProductPicture = ProductPicture;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getTaxRegistrationStatus() {
            return TaxRegistrationStatus;
        }

        public void setTaxRegistrationStatus(String TaxRegistrationStatus) {
            this.TaxRegistrationStatus = TaxRegistrationStatus;
        }

        public String getTeamStyle() {
            return TeamStyle;
        }

        public void setTeamStyle(String TeamStyle) {
            this.TeamStyle = TeamStyle;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getThreeCardAll() {
            return ThreeCardAll;
        }

        public void setThreeCardAll(String ThreeCardAll) {
            this.ThreeCardAll = ThreeCardAll;
        }

        public String getWebsite() {
            return Website;
        }

        public void setWebsite(String Website) {
            this.Website = Website;
        }

        public List<CompanyTypeListBean> getCompanyTypeList() {
            return CompanyTypeList;
        }

        public void setCompanyTypeList(List<CompanyTypeListBean> CompanyTypeList) {
            this.CompanyTypeList = CompanyTypeList;
        }

        public static class CompanyTypeListBean {
            /**
             * BasicValue : 中央企业
             * Id : 1
             */

            private String BasicValue;
            private int Id;

            public String getBasicValue() {
                return BasicValue;
            }

            public void setBasicValue(String BasicValue) {
                this.BasicValue = BasicValue;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }
        }
    }
}
