package com.example.binh.offlinefirstreativeappp1.main_screen;

import com.example.binh.offlinefirstreativeappp1.application.BasePresenter;
import com.example.binh.offlinefirstreativeappp1.application.BaseView;
import com.example.binh.offlinefirstreativeappp1.data.model.Post;

import java.util.List;

public interface MainActivityContract {
    interface Presenter extends BasePresenter{
        void loadPosts();

        void loadPostFromRemoteDataStore();

    }
    interface View extends BaseView<Presenter> {

        void showError(String message);

        void showComplete();

        void showPosts(List<Post> posts);
    }
}
