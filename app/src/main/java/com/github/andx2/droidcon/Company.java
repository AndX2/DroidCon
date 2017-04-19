package com.github.andx2.droidcon;

/**
 * Created by Andrew on 17.04.2017.
 */

public class Company {

    public Company(String name, String logoUrl, String url) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.url = url;
    }

    private String name;
    private String logoUrl;
    private String url;

    //region get/set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //endregion
}
