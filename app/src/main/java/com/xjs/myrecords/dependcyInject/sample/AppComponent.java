package com.xjs.myrecords.dependcyInject.sample;

import android.content.Context;

import com.google.gson.Gson;
import com.xjs.myrecords.dependcyInject.sample.annotations.AppScope;

import dagger.Component;

/**
 * create xjs
 * date  2017/7/9
 * description
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    Context provideApplicationContext();

    Gson provideGson();


}
