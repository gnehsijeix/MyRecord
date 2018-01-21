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

    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<>();
        // 子集合个数
        int max = 1 << set.size();
        for (int i = 0; i < max; i++) {
            int index = 0;
            int k = i;
            ArrayList<Integer> s = new ArrayList<Integer>();
            while (k > 0) {
                if ((k & 1) > 0) {
                    s.add(set.get(index));
                }
                k >>= 1;
                index++;
            }
            allSubsets.add(s);
        }
        return allSubsets;
    }
}
