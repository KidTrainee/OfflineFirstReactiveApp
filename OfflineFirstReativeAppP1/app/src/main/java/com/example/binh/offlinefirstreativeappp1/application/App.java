package com.example.binh.offlinefirstreativeappp1.application;

import android.app.Application;

import com.example.binh.offlinefirstreativeappp1.dagger.component.AppComponent;
import com.example.binh.offlinefirstreativeappp1.dagger.component.DaggerAppComponent;
import com.example.binh.offlinefirstreativeappp1.dagger.module.AppModule;
import com.example.binh.offlinefirstreativeappp1.dagger.module.DataModule;

public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
