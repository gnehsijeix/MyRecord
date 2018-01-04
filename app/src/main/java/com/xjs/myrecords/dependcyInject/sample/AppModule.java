package com.xjs.myrecords.dependcyInject.sample;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xjs.myrecords.dependcyInject.sample.annotations.AppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * create xjs
 * date  2017/7/9
 * description
 */
@Module
public class AppModule {

    private Context mContext;
    public AppModule (Context context) {
        this.mContext = context;
    }
    @AppScope
    @Provides
    Context provideContext() {
        return mContext;
    }
    @AppScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }


}
