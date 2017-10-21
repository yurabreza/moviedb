package com.moviedb.data.di;

import com.moviedb.data.api.MoviesDbService;
import com.moviedb.data.repository.MoviesRepositoryImpl;
import com.moviedb.domain.repository.MoviesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DataModule {

    @Provides
    MoviesRepository provideYahooStockRepository(MoviesDbService moviesDbService) {
        return new MoviesRepositoryImpl(moviesDbService);
    }
}

