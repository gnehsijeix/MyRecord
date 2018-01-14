package com.xjs.myrecords;

import java.util.Timer;

public class Builder_HomeActivityJ {

    private com.alibaba.android.arouter.facade.Postcard postcard;
    private int mustSetParamsCount = 4;
    public Builder_HomeActivityJ() {
        this.postcard = com.alibaba.android.arouter.launcher.ARouter.getInstance().build("com.xjs.code.home");

    }

    public Builder_HomeActivityJ mustSetProgress(int mprogress) {
        this.postcard.withInt("progress",mprogress);
        mustSetParamsCount--;
        return this;
    }

    public Builder_HomeActivityJ setTimer(Timer timer) {
        this.postcard.withObject("timer",timer);
        return this;
    }

    public Builder_HomeActivityJ mustSetMPassword(String mpassword) {
        this.postcard.withString("password",mpassword);
        mustSetParamsCount--;
        return this;
    }

    public com.alibaba.android.arouter.facade.Postcard generate() {
        if (mustSetParamsCount>0){
            throw new RuntimeException(String.format("has%1$s must param not set",mustSetParamsCount));
        }
        return postcard;
    }
}
