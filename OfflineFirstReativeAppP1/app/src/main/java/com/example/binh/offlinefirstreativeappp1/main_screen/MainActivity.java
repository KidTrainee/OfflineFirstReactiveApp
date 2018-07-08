package com.example.binh.offlinefirstreativeappp1.main_screen;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.binh.offlinefirstreativeappp1.R;
import com.example.binh.offlinefirstreativeappp1.application.App;
import com.example.binh.offlinefirstreativeappp1.application.BasePresenter;
import com.example.binh.offlinefirstreativeappp1.data.AppRepository;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, SwipeRefreshLayout.OnRefreshListener {

    private MainActivityContract.Presenter mPresenter;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeContainer;

    private ArrayList<String> mPostTitleList;
    private ArrayAdapter mAdapter;

    @Inject
    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject dependency
        App.getAppComponent().inject(this);

        mListView = findViewById(R.id.my_list);
        mSwipeContainer = findViewById(R.id.swipeContainer);
        mSwipeContainer.setOnRefreshListener(this);
        mPostTitleList = new ArrayList<>();

        mPresenter = new MainActivityPresenter(mRepository, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showPosts(List<Post> posts) {
        for (Post post : posts) {
            this.mPostTitleList.add(post.getTitle());
        }

        // create the array adapter and set it to list view;
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mPostTitleList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error loading post", Toast.LENGTH_SHORT).show();
        disableRefreshing();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "Completed loading", Toast.LENGTH_SHORT).show();
        disableRefreshing();
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadPostFromRemoteDataStore();
    }

    private void disableRefreshing() {
        if (mSwipeContainer != null) {
            mSwipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeContainer.setRefreshing(false);
                }
            });
        }
    }
}
