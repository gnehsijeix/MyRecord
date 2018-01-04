package com.xjs.uidelegate.uidalegate;

import com.gzdianrui.intelligentlock.uidalegate.interfaces.UIUnbinderManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjs
 * on 2017/8/7
 * desc: 一个view层所有unbinder 主要是为了解绑
 */

public class UIUnbinderManagerImp implements UIUnbinderManager {

    private List<UIDelegate.UIUnbinder> mUIunbinders;

    public UIUnbinderManagerImp() {
        mUIunbinders = new LinkedList<>();
    }


    @Override
    public void add(UIDelegate.UIUnbinder unbinder) {
        if (!mUIunbinders.contains(unbinder)) {
            mUIunbinders.add(unbinder);
        }
    }

    @Override
    public void remove(UIDelegate.UIUnbinder unbinder) {
        if (mUIunbinders.contains(unbinder)) {
            mUIunbinders.remove(unbinder);
        }
    }

    @Override
    public void removeAll() {
        for (UIDelegate.UIUnbinder uIunbinder : mUIunbinders) {
            if (uIunbinder != null) {
                uIunbinder.unbind();
            }
        }
        mUIunbinders.clear();
    }
}
