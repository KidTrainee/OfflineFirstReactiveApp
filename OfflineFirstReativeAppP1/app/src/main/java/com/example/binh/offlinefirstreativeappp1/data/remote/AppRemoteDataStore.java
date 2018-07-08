package com.example.binh.offlinefirstreativeappp1.data.remote;

import android.util.Log;

import com.example.binh.offlinefirstreativeappp1.application.App;
import com.example.binh.offlinefirstreativeappp1.data.AppDataStore;
import com.example.binh.offlinefirstreativeappp1.data.local.AppLocalDataStore;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Action1;

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    Retrofit retrofit;

    @Inject
    AppLocalDataStore appLocalDataStore;

    public AppRemoteDataStore() {
        App.getAppComponent().inject(this);
    }



    @Override
    public Observable<List<Post>> getPost() {
        Log.d("REMOTE","Loaded from remote");

        return retrofit.create(PostService.class).getPostList().doOnNext(new Action1<List<Post>>() {
            @Override
            public void call(List<Post> posts) {
                appLocalDataStore.savePostToDatabase(posts);
            }
        });

    }

    private interface PostService {
        @GET("/posts")
        Observable<List<Post>> getPostList();
    }

}
