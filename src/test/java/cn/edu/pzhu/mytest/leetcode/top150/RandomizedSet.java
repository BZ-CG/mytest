package cn.edu.pzhu.mytest.leetcode.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author zhangcz
 * @since 20240910
 */
public class RandomizedSet {

    private Map<Integer, Integer> map;
    private List<Long> list;

    private Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        if (contains) {
            return false;
        }
        list.add((long) val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        boolean contains = map.containsKey(val);
        if (contains) {
            int index = map.remove(val);
            list.set(index, Long.MAX_VALUE);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int size = list.size();
        int index = random.nextInt(size);
        Long aLong = list.get(index);
        while (aLong == Long.MAX_VALUE) {
            index = random.nextInt(size);
            aLong = list.get(index);
        }

        return Math.toIntExact(aLong);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

//        System.out.println(randomizedSet.insert(1));
//        System.out.println(randomizedSet.remove(2));
//        System.out.println(randomizedSet.insert(2));
//        System.out.println(randomizedSet.getRandom());
//        System.out.println(randomizedSet.remove(1));
//        System.out.println(randomizedSet.insert(2));
//        System.out.println(randomizedSet.getRandom());

        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());

    }


}
