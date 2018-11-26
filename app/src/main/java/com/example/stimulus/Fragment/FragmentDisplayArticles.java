package com.example.stimulus.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Random;

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
    private SearchView mSearchView;
    private String mine;
    private int[] textureArrayWin = {
            R.drawable.pattern1,
            R.drawable.pattern2,
            R.drawable.pattern3,
            R.drawable.pattern4,
            R.drawable.pattern5,
            R.drawable.pattern6,
            R.drawable.pattern7,
            R.drawable.pattern8,
            R.drawable.pattern9,
            R.drawable.pattern10,
    };

    public static FragmentDisplayArticles newInstance(String mine) {
        Bundle bundle = new Bundle();
        bundle.putString("mine", mine);


        FragmentDisplayArticles fragment = new FragmentDisplayArticles();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            mine = bundle.getString("mine");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
        mSearchView = (SearchView) retView.findViewById(R.id.action_search);
        readBundle(getArguments());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) item.getActionView();
        mSearchView.setQueryHint("Enter Article title");
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                mProgress.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("123", "zfhkjzflk");
                        searchAPICall(s);
                    }
                }).start();
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                return false;
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                NewsAdapter newsAdapter = new NewsAdapter(fragmentBelongActivity, articles);
                recyclerView.setAdapter(newsAdapter);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchAPICall(String search) {

        Call<Data> call = mAPIPost.getSearchResult("http://35.229.66.18:3000/news/search/" + search);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                ArrayList<NewsArticle> search = new ArrayList<>();
                ArrayList<News> news = response.body().getNews();
                Random random = new Random();
                for( int i = 0; i < news.size(); i++){
                    int r = random.nextInt(10);
                    String id = news.get(i).get_id();
                    String author = news.get(i).getAuthor();
                    String title = news.get(i).getTitle();
                    NewsArticle newsArticle = new NewsArticle(id, title, author, textureArrayWin[r], mine);
                    search.add(newsArticle);


                }

                final NewsAdapter adapter = new NewsAdapter(fragmentBelongActivity, search);
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

    public void makeAPICall() throws UnsupportedEncodingException {

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        //RedditApi redditAPI = retrofit.create(RedditApi.class);



        Call<Data> call = mAPIPost.getData("0x68643896723021f581ec1106da242f3441276134e1667d528fd2459d717af7fc5e5e60463bbc200386b6dcdce553f6be5e06167818d29678d62e4c59a1e454161c", "Qi4RMIiv3YHGBoKZxMWhjfMu0PoOB4vv", mine);
        if(call == null){
            Toast.makeText(fragmentBelongActivity,"No Articles to display", Toast.LENGTH_LONG).show();
        } else {
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
                    Random random = new Random();
                    ArrayList<News> news = response.body().getNews();
                    for (int i = 0; i < news.size(); i++) {
                        int r = random.nextInt(10);
                        String id = news.get(i).get_id();
                        String author = news.get(i).getAuthor();
                        String title = news.get(i).getTitle();
                        NewsArticle newsArticle = new NewsArticle(id, title, author, textureArrayWin[r], mine);
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
                    Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
                }

            });
        }

    }

}