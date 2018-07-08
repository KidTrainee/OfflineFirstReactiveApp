package com.example.binh.offlinefirstreativeappp1.data;


import com.example.binh.offlinefirstreativeappp1.data.local.AppLocalDataStore;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;
import com.example.binh.offlinefirstreativeappp1.data.remote.AppRemoteDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class AppRepository implements AppDataStore {

    private AppLocalDataStore mAppLocalDataStore;
    private AppRemoteDataStore mAppRemoteDataStore;

    @Inject
    public AppRepository(AppLocalDataStore appLocalDataStore,
                         AppRemoteDataStore appRemoteDataStore) {
        this.mAppLocalDataStore = appLocalDataStore;
        this.mAppRemoteDataStore = appRemoteDataStore;
    }

    @Override
    public Observable<List<Post>> getPost() {
        return Observable.concat(mAppLocalDataStore.getPost(), mAppRemoteDataStore.getPost())
                .first(new Func1<List<Post>, Boolean>() {
                    @Override
                    public Boolean call(List<Post> posts) {
                        return posts != null;
                    }
                });
    }
}
