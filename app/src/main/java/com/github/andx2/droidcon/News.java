package com.github.andx2.droidcon;

/**
 * Created by Andrew on 16.04.2017.
 */

public class News {

    public News(String titlePicUrl, String title, String body) {
        this.titlePicUrl = titlePicUrl;
        this.title = title;
        this.body = body;
    }

    private String titlePicUrl;
    private String title;
    private String body;

    //region get/set
    public String getTitlePicUrl() {
        return titlePicUrl;
    }

    public void setTitlePicUrl(String titlePicUrl) {
        this.titlePicUrl = titlePicUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //endregion
}
