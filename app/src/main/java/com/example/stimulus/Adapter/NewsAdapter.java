package com.example.stimulus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stimulus.Activity.ArticleWebView;
import com.example.stimulus.Class.NewsArticle;
import com.example.stimulus.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private List<NewsArticle> mNewsArticleList;

    public NewsAdapter(Context mContext, List<NewsArticle> mNewsArticleList) {
        this.mContext = mContext;
        this.mNewsArticleList = mNewsArticleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {

        NewsArticle article = mNewsArticleList.get(position);
        holder.title.setText(article.getArticleName());
        holder.summary.setText(article.getArticleSummary());
        Glide.with(mContext).load(article.getArticleThumbnail()).into(holder.article_thumbnail);
        if(article.getMined().equalsIgnoreCase("false")) {
            holder.linearLayout.setVisibility(View.VISIBLE);
        }
        holder.article_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ArticleWebView.class);
                intent.putExtra("id", mNewsArticleList.get(position).getArticleId());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsArticleList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title, summary;
        public ImageView article_thumbnail;
        public LinearLayout linearLayout;

        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            summary = (TextView) view.findViewById(R.id.summary);
            article_thumbnail = (ImageView) view.findViewById(R.id.news_thumbnail);
            linearLayout = (LinearLayout) view.findViewById(R.id.li_buttons);
        }
    }

}
