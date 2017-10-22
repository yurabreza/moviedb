package com.moviedb.presentation.app;

import android.app.Application;

import com.moviedb.presentation.app.di.DependencyGraph;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApp extends Application {

    public static final String DATABASE = "database";
    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DATABASE)
                .schemaVersion(3)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
