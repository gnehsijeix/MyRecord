package com.xjs.uidelegate.uidalegate;


import com.gzdianrui.intelligentlock.di.NormalScope;

import dagger.Component;

/**
 * Created by xjs
 * on 2017/8/4
 * desc:
 */
@NormalScope
@Component(modules = UIHelperModule.class)
public interface UIHelperComponent {
    //TopBarUIDelegate provideTopBarImp();
}
