package com.moviedb.presentation.app;

import android.app.Application;

import com.moviedb.presentation.app.di.DependencyGraph;

import io.realm.Realm;


public class MainApp extends Application {

    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);

        Realm.init(this);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
