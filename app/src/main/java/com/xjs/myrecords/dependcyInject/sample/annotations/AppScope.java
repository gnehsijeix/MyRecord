package com.xjs.myrecords.dependcyInject.sample.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * create xjs
 * date  2017/7/9
 * description ： 定义用作为全局生命周期
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface AppScope {
}
