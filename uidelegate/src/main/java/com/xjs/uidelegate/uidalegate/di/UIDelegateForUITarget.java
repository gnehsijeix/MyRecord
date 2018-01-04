package com.xjs.uidelegate.uidalegate.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * create xjs
 * date  2017/8/8
 * description
 */
@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UIDelegateForUITarget {

}
