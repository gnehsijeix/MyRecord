package com.xjs.myrecords.javacollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author xjs
 *         date  2017/10/29
 *         description
 */

public class Test {
    /**
     * 队列
     */
    Queue queue;

    /**
     * 优先级队列
     */
    PriorityQueue priorityQueue;
    /**
     * 链表列表
     */
    LinkedList linkedList;

    /**
     * hash map
     */
    HashMap hashMap;


    private void testIterator() {
        Iterator itr = priorityQueue.iterator();
        while (itr.hasNext()) {
            itr.next();
        }
    }

}
