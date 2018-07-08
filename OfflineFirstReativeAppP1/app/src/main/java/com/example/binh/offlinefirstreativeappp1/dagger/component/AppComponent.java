package com.example.binh.offlinefirstreativeappp1.dagger.component;

import com.example.binh.offlinefirstreativeappp1.dagger.module.AppModule;
import com.example.binh.offlinefirstreativeappp1.dagger.module.DataModule;
import com.example.binh.offlinefirstreativeappp1.data.remote.AppRemoteDataStore;
import com.example.binh.offlinefirstreativeappp1.main_screen.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject (MainActivity activity);
    void inject (AppRemoteDataStore appRemoteDataStore);
}
