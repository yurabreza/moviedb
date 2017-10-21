package com.moviedb.app.di;


import com.moviedb.app.MainApp;
import com.moviedb.data.executor.JobExecutor;
import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.presentation.UIThread;

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

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}

