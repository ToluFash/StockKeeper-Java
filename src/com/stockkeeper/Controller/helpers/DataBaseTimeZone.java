package com.stockkeeper.Controller.helpers;

public class DataBaseTimeZone {

    private String tzInfo;
    private String operator;
    private int hourOffset;
    private int minuteOffset;
    private int dstHourOffset;
    private int dstMinuteOffset;

    public DataBaseTimeZone(String tzInfo, String operator, int hourOffset, int minuteOffset, int dstHourOffset, int dstMinuteOffset) {
        this.tzInfo = tzInfo;
        this.operator = operator;
        this.hourOffset = hourOffset;
        this.minuteOffset = minuteOffset;
        this.dstHourOffset = dstHourOffset;
        this.dstMinuteOffset = dstMinuteOffset;
    }

    public String getTzInfo() {
        return tzInfo;
    }

    public void setTzInfo(String tzInfo) {
        this.tzInfo = tzInfo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getHourOffset() {
        return hourOffset;
    }

    public void setHourOffset(int hourOffset) {
        this.hourOffset = hourOffset;
    }

    public int getMinuteOffset() {
        return minuteOffset;
    }

    public void setMinuteOffset(int minuteOffset) {
        this.minuteOffset = minuteOffset;
    }

    public int getDstHourOffset() {
        return dstHourOffset;
    }

    public void setDstHourOffset(int dstHourOffset) {
        this.dstHourOffset = dstHourOffset;
    }

    public int getDstMinuteOffset() {
        return dstMinuteOffset;
    }

    public void setDstMinuteOffset(int dstMinuteOffset) {
        this.dstMinuteOffset = dstMinuteOffset;
    }
}
