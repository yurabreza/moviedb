package com.moviedb.app.di;


import com.moviedb.app.MainApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MainApp mMainApp;

    public AppModule(MainApp mainApp) {
        mMainApp = mainApp;
    }

    @Provides
    @Singleton
    MainApp provideMainApp() {
        return mMainApp;
    }
}

