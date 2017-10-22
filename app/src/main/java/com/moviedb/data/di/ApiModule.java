package com.moviedb.data.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.moviedb.BuildConfig;
import com.moviedb.data.ApiKeyInterceptor;
import com.moviedb.data.api.MoviesDbService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@Singleton
public class ApiModule {

    @Provides
    Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    // FIXME: 20.10.17 provide both interceptors
    @Provides
    OkHttpClient provideOkHttpClient(Interceptor interceptor1) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor1)
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    MoviesDbService provideApiService(Retrofit retrofit) {
        return retrofit.create(MoviesDbService.class);
    }
}