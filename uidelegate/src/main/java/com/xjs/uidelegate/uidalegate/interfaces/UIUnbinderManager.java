package com.xjs.uidelegate.uidalegate.interfaces;

import com.gzdianrui.intelligentlock.uidalegate.UIDelegate;

/**
 * create xjs
 * date  2017/8/8
 * description
 */

public interface UIUnbinderManager {

    void add(UIDelegate.UIUnbinder unbinder);

    void remove(UIDelegate.UIUnbinder unbinder);

    void removeAll();

}
