package com.xjs.uidelegate.uidalegate.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by xjs
 * on 2017/8/4
 * desc:
 */
@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UIDelegateForFragment {

}
