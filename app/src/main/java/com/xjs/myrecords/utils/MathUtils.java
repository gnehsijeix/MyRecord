package com.xjs.myrecords.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjs
 *         on 2018/1/17
 *         desc:
 */

public class MathUtils {

    public static List<int[]> recursionGetCombination(int position) {
        List<int[]> listList = new ArrayList<>();
        int[] singleSelfArray = new int[]{position};
        listList.add(singleSelfArray);
        if (position == 0) {
            return listList;
        }
        int[] fullIterator = new int[position];

        for (int i = position - 1; i >= 0; i--) {
            int[] iterator = new int[2];
            iterator[0] = i;
            iterator[1] = position;
            listList.add(iterator);
        }
        listList.add(fullIterator);
        listList.addAll(recursionGetCombination(position - 1));
        return listList;
    }
}
