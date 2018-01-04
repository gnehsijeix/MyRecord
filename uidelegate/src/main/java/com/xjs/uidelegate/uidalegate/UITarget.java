package com.xjs.uidelegate.uidalegate;

import android.view.View;

/**
 * create xjs
 * date  2017/8/8
 * description  ui模块绑定者
 */

public interface UITarget<E> {

    View getViewContainer();

    E getExpand();
}
