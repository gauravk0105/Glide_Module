package com.example.glide_demo;

public class Store
{
    private String url;
    private String info;

    public Store()
    {
    }

    public Store(String url,String info)
    {
        this.url = url;
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
