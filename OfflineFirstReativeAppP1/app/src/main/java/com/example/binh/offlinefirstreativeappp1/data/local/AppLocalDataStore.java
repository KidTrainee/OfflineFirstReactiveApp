package com.example.binh.offlinefirstreativeappp1.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.binh.offlinefirstreativeappp1.data.AppDataStore;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;
import com.example.binh.offlinefirstreativeappp1.data.model.PostStorIOContentResolverDeleteResolver;
import com.example.binh.offlinefirstreativeappp1.data.model.PostStorIOContentResolverGetResolver;
import com.example.binh.offlinefirstreativeappp1.data.model.PostStorIOContentResolverPutResolver;
import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class AppLocalDataStore implements AppDataStore {
    private static final String TAG = AppLocalDataStore.class.getSimpleName();
    private Context mContext;

    private StorIOContentResolver mStorIOContentResolver;

    @Inject
    public AppLocalDataStore(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Post.class,
                        ContentResolverTypeMapping.builder()
                                .putResolver(new PostStorIOContentResolverPutResolver())
                                .getResolver(new PostStorIOContentResolverGetResolver())
                                .deleteResolver(new PostStorIOContentResolverDeleteResolver())
                                .build()
                ).build();
    }

    @Override
    public Observable<List<Post>> getPost() {
        Log.d(TAG, "getPost: loaded from local");
        return mStorIOContentResolver.get()
                .listOfObjects(Post.class)
                .withQuery(Query.builder()
                        .uri(DatabaseContract.PostTableContract.CONTENT_URI)
                        .build())
                .prepare()
                .asRxObservable();
    }

    public void savePostToDatabase(List posts) {
        mStorIOContentResolver.put().objects(posts).prepare().executeAsBlocking();
    }
}
