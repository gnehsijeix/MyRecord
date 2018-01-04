package com.xjs.myrecords.dependcyInject.sample;

import android.content.Context;
import android.util.Log;

import com.xjs.myrecords.dependcyInject.sample.annotations.CompScope;
import com.xjs.myrecords.dependcyInject.sample.annotations.ProviderPersonFormContext;
import com.xjs.myrecords.dependcyInject.sample.annotations.ProviderPersonFromName;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * create xjs
 * date  2017/7/9
 * description
 */
@Module
public class ActivityModule {
    private static final String TAG = "ActivityModule";

    public ActivityModule(){
        Log.d(TAG, "ActivityModule: constructor");
    }
    @ProviderPersonFormContext
    @Provides
    Person providePerson(Context context) {
        return new Person(context);
    }
    @ProviderPersonFromName
    @Provides
    Person providePerson2(String name) {
        return new Person(name);
    }
    @Provides
    String providePersonName() {
        return new Random().nextInt(10)+"拉";
    }

    @CompScope
    @Provides
    SingletonObject provideSingletonObject(){
        return new SingletonObject("tag");
    }

}

