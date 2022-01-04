package com.example.jobdemo.bean;

/**
 * @Author LYX
 * @Date 2021/12/30 15:15
 */
public class DjtVersionBean {
    /**
     * ID : 2
     * Message : 1. 修复了问答详情显示问题$@$2. 优化了问题列表展示界面$@$3. 新增了用户主页相关功能
     * SystemType : ANDROID
     * URL : http://bucket-public-release.51djt.com/download/djt-latest.apk
     * UpdateType : 3
     * Version : 19
     */

    private int id;
    private String message;
    private String systemType;
    private String url;
    private String updateType;
    private String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
