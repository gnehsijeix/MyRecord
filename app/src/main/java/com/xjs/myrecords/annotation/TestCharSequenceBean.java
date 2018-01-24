package com.xjs.myrecords.annotation;

/**
 * @author xjs
 *         on  2018/1/24
 *         desc:
 */

public class TestCharSequenceBean implements CharSequence {

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
