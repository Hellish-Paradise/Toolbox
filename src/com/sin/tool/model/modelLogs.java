package com.sin.tool.model;

import java.io.Serializable;

/**
 * @Date 2021/9/2   16:19
 */
public class modelLogs implements Serializable {
    private String date;
    private String info;

    public modelLogs(String date, String info) {
        this.date = date;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "["+date+"]"+"~"+"<"+info+">";
    }
}
