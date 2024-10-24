package cn.edu.pzhu.mytest.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRUCache {

    public Map<Integer, Integer> map;
    public LinkedList<Integer> queue;

    private int maxLength;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        queue = new LinkedList<>();
        maxLength = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);
        if (value == null) {
            return -1;
        }

        queue.remove((Integer) key);
        queue.addFirst(key);
        return value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            queue.remove((Integer) key);
            queue.addFirst(key);
            return;
        }

        if (queue.size() == maxLength) {
            Integer removeLast = queue.removeLast();
            map.remove(removeLast);
        }

        map.put(key, value);
        queue.addFirst(key);
    }
}