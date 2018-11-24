package com.example.stimulus.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.stimulus.API.APIPost;
import com.example.stimulus.API.Model_POST_getNews.Data;
import com.example.stimulus.API.Model_POST_getNews.News;
import com.example.stimulus.Adapter.NewsAdapter;
import com.example.stimulus.Class.NewsArticle;
import com.example.stimulus.R;
import com.example.stimulus.Utils.APIUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDisplayArticles extends Fragment {

    private static final String BASE_URL = "https://www.reddit.com/";
    FragmentActivity fragmentBelongActivity = null;
    ArrayList<NewsArticle> articles;
    RecyclerView recyclerView;
    ProgressBar mProgress;
    private APIPost mAPIPost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articles = new ArrayList<>();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_news_adapter, null, false);
        fragmentBelongActivity = (FragmentActivity) getActivity();
        recyclerView = (RecyclerView) retView.findViewById(R.id.recycler_view);
        mProgress = retView.findViewById(R.id.progress);
        recyclerView.setVisibility(View.INVISIBLE);
        mAPIPost = APIUtils.getAPIService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    makeAPICall();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return retView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }

    public void makeAPICall() throws UnsupportedEncodingException {

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        //RedditApi redditAPI = retrofit.create(RedditApi.class);



        Call<Data> call = mAPIPost.getData("0x68643896723021f581ec1106da242f3441276134e1667d528fd2459d717af7fc5e5e60463bbc200386b6dcdce553f6be5e06167818d29678d62e4c59a1e454161c", "Qi4RMIiv3YHGBoKZxMWhjfMu0PoOB4vv");

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                /*ArrayList<Children> childrenList = response.body().getData().getChildren();
                for( int i = 0; i<childrenList.size(); i++){


                    String subReddit = childrenList.get(i).getData().getSubreddit();
                    String title = childrenList.get(i).getData().getTitle();
                    NewsArticle newsArticle = new NewsArticle(subReddit,title, R.drawable.news1);
                    articles.add(newsArticle);


                }*/

                ArrayList<News> news = response.body().getNews();
                for( int i = 0; i < news.size(); i++){

                    String id = news.get(i).get_id();
                    String author = news.get(i).getAuthor();
                    String title = news.get(i).getTitle();
                    NewsArticle newsArticle = new NewsArticle(id, title, author, R.drawable.news1);
                    articles.add(newsArticle);


                }

                final NewsAdapter adapter = new NewsAdapter(fragmentBelongActivity, articles);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(fragmentBelongActivity);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgress.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                t.printStackTrace();
                Log.d("123", t.getMessage());
                Toast.makeText(getContext(),"failed",Toast.LENGTH_LONG).show();
            }

        });

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}