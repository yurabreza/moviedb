package com.moviedb.app;

import android.app.Application;

import com.moviedb.app.di.DependencyGraph;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;


public class MainApp extends Application {

    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
