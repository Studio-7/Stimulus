package com.example.stimulus.Class;

public class NewsArticle {

    private String mArticleName;
    private String mArticleSummary;
    private int mArticleThumbnail;


    public NewsArticle(String mArticleName, String mArticleSummary, int mArticleThumbnail) {

        this.mArticleName = mArticleName;
        this.mArticleSummary = mArticleSummary;
        this.mArticleThumbnail = mArticleThumbnail;
    }

    public String getArticleName() {
        return mArticleName;
    }

    public String getArticleSummary() {
        return mArticleSummary;
    }

    public int getArticleThumbnail() {
        return mArticleThumbnail;
    }
}
