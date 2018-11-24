package com.example.stimulus.Class;

public class NewsArticle {

    private String mArticleName;
    private String mArticleSummary;
    private int mArticleThumbnail;
    private String mArticleId;


    public NewsArticle(String mArticleId, String mArticleName, String mArticleSummary, int mArticleThumbnail) {


        this.mArticleName = mArticleName;
        this.mArticleSummary = mArticleSummary;
        this.mArticleThumbnail = mArticleThumbnail;
        this.mArticleId = mArticleId;
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
