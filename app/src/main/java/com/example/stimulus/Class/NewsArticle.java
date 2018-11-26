package com.example.stimulus.Class;

public class NewsArticle {

    private String mArticleName;
    private String mArticleSummary;
    private int mArticleThumbnail;
    private String mArticleId;
    private String mined;


    public NewsArticle(String mArticleId, String mArticleName, String mArticleSummary, int mArticleThumbnail, String mined) {


        this.mArticleName = mArticleName;
        this.mArticleSummary = mArticleSummary;
        this.mArticleThumbnail = mArticleThumbnail;
        this.mArticleId = mArticleId;
        this.mined = mined;
    }

    public String getMined() {
        return mined;
    }

    public String getArticleId() { return mArticleId; }

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
