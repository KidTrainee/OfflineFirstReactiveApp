package com.example.binh.offlinefirstreativeappp1.main_screen;


import android.util.Log;

import com.example.binh.offlinefirstreativeappp1.data.AppRepository;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;
import com.example.binh.offlinefirstreativeappp1.data.remote.AppRemoteDataStore;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = MainActivityPresenter.class.getSimpleName();
    private Subscription mSubscription;
    private AppRepository mAppRepository;
    private MainActivityContract.View mView;

    public MainActivityPresenter(AppRepository appRepository,
                                 MainActivityContract.View view) {
        this.mAppRepository = appRepository;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPosts() {
        mSubscription = mAppRepository.getPost()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        mView.showPosts(posts);
                    }
                });
    }

    @Override
    public void loadPostFromRemoteDataStore() {
        new AppRemoteDataStore().getPost().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                        mView.showComplete();
                        loadPosts();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<Post> posts) {

                    }
                });
    }

    @Override
    public void subscribe() {
        loadPosts();
    }

    @Override
    public void unsubscribe() {
        // unsubscribe Rx subscription
        if (mSubscription!=null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
