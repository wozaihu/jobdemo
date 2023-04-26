package com.example.jobdemo.bean;

import android.net.Uri;

/**
 * @Author LYX
 * @Date 2023/2/20 9:56
 */
public class ConversationChild {
    private String id;
    private String name;
    private String alias;
    private Uri portraitUri;
    private String extra;

    @Override
    public String toString() {
        return "ConversationChild{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", portraitUri=" + portraitUri +
                ", extra='" + extra + '\'' +
                '}';
    }
}
