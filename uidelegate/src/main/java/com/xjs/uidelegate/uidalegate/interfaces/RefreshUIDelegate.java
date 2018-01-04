package com.xjs.uidelegate.uidalegate.interfaces;

import android.view.View;

import com.xjs.uidelegate.uidalegate.UIDelegate;

/**
 * Created by xjs
 * on 2017/8/4
 * desc:
 */

public interface RefreshUIDelegate extends UIDelegate {

    void addHandler(RefreshHandler handler);

    void beginRefresh();

    void beginLoadmore();

    void refreshOrLoadmoreComplete();

    interface RefreshHandler {

        boolean onRefreshBegin(View container);

        boolean onLoadMoreBegin(View container);
    }

}
