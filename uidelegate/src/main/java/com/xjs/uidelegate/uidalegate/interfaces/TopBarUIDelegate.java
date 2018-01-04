package com.xjs.uidelegate.uidalegate.interfaces;

import com.gzdianrui.intelligentlock.uidalegate.UIDelegate;

/**
 * Created by xjs
 * on 2017/8/4
 * desc: topbar 的接口
 */

public interface TopBarUIDelegate extends UIDelegate {

    TopBarUIDelegate setTitle(CharSequence text);

    TopBarUIDelegate setBackButtonRes(int res);

    TopBarUIDelegate setBackgroundColor(int color);

    TopBarUIDelegate setBackgroundRes(int res);

    TopBarUIDelegate setTitileColor(int color);
}
