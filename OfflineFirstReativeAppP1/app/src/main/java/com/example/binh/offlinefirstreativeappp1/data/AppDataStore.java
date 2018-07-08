package com.example.binh.offlinefirstreativeappp1.data;


import com.example.binh.offlinefirstreativeappp1.data.model.Post;

import java.util.List;

import rx.Observable;

public interface AppDataStore {
    Observable<List<Post>> getPost();

}
