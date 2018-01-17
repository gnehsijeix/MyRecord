package com.xjs.myrecords;

import com.xjs.myrecords.utils.MathUtils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testJinzhi() {
        int i = 8;
        assertEquals("1000", Integer.toBinaryString(i));
    }

    /*@Test
    public void testYiwei() {
        int i = -8;
        assertEquals(4, i >>> 1);

    }*/
    @Test
    public void testCompora() {
        String a = "a";
        String b = "b";
        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void testRecursionGetCombination() {
        List<int[]> combination = MathUtils.recursionGetCombination(3);
        assertEquals("", combination.toString());
    }
}