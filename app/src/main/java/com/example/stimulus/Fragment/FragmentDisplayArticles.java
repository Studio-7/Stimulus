package com.example.stimulus.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stimulus.API.Model.Children.Children;
import com.example.stimulus.API.Model.Feed;
import com.example.stimulus.API.RedditApi;
import com.example.stimulus.Adapter.NewsAdapter;
import com.example.stimulus.Class.NewsArticle;
import com.example.stimulus.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDisplayArticles extends Fragment {

    private static final String BASE_URL = "https://www.reddit.com/";
    FragmentActivity fragmentBelongActivity = null;
    ArrayList<NewsArticle> articles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articles = new ArrayList<>();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_news_adapter, null, false);
        fragmentBelongActivity = (FragmentActivity) getActivity();

        final RecyclerView recyclerView = (RecyclerView) retView.findViewById(R.id.recycler_view);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditApi redditAPI = retrofit.create(RedditApi.class);
        Call<Feed> call = redditAPI.getData();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {

                ArrayList<Children> childrenList = response.body().getData().getChildren();
                for( int i = 0; i<childrenList.size(); i++){


                    String subReddit = childrenList.get(i).getData().getSubreddit();
                    String title = childrenList.get(i).getData().getTitle();
                    NewsArticle newsArticle = new NewsArticle(subReddit,title, R.drawable.news1);
                    articles.add(newsArticle);


                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(fragmentBelongActivity, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        NewsAdapter adapter = new NewsAdapter(fragmentBelongActivity, articles);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(fragmentBelongActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return retView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}