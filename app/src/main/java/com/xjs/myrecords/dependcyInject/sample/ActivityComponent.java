package com.xjs.myrecords.dependcyInject.sample;

import android.app.Activity;

import com.xjs.myrecords.dependcyInject.sample.annotations.AppScope;
import com.xjs.myrecords.dependcyInject.sample.annotations.CompScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * create xjs
 * date  2017/7/9
 * description
 */
@CompScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
