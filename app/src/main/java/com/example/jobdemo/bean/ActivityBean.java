package com.example.jobdemo.bean;

/**
 * @Author LYX
 * @Date 2022/4/20 16:51
 */
public class ActivityBean {
    /**
     * activity 类名
     */
    private String activityChinesName;
    /**
     * 首字母
     */
    private String initial;

    /**
     * 全部类名首字母
     */
    private String allInitial;
    /**
     * activity类名
     */
    private Class className;

    public ActivityBean(String activityChinesName, String initial, String allInitial, Class className) {
        this.activityChinesName = activityChinesName;
        this.initial = initial;
        this.allInitial = allInitial;
        this.className = className;
    }

    public String getActivityChinesName() {
        return activityChinesName;
    }

    public void setActivityChinesName(String activityChinesName) {
        this.activityChinesName = activityChinesName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getAllInitial() {
        return allInitial;
    }

    public void setAllInitial(String allInitial) {
        this.allInitial = allInitial;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ActivityBean{" +
                "activityChinesName='" + activityChinesName + '\'' +
                ", initial='" + initial + '\'' +
                ", allInitial='" + allInitial + '\'' +
                ", className=" + className +
                '}';
    }
}
