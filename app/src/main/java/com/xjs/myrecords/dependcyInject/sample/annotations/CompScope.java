package com.xjs.myrecords.dependcyInject.sample.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * create xjs
 * date  2017/7/9
 * description : 定义component 唯一
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CompScope {
}
