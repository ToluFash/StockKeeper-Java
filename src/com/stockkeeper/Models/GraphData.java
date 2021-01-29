package com.stockkeeper.Models;

public class GraphData {

    private String title;
    private Integer size;

    public GraphData(String title, Integer size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
