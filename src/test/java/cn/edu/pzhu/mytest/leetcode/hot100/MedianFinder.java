package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {

    private PriorityQueue<Integer> min;

    private PriorityQueue<Integer> max;

    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (min.isEmpty() || num >= min.peek()) {
            min.add(num);
        } else {
            max.add(num);
        }

        if (max.size() - min.size() == 2) {
            min.add(max.poll());
        }

        if (min.size() - max.size() == 2) {
            max.add(min.poll());
        }
    }

    public double findMedian() {
        if (min.size() > max.size()) {
            return min.peek();
        } else if (min.size() < max.size()) {
            return max.peek();
        }

        return (min.peek() + max.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        medianFinder.addNum(-5);

        //        medianFinder.addNum(1);
        //        medianFinder.addNum(2);
        //        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());

    }

}