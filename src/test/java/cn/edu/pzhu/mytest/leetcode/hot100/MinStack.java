package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.PriorityQueue;
import java.util.Stack;

class MinStack {

    private PriorityQueue<Integer> priorityQueue;

    private Stack<Integer> stack;

    public MinStack() {
        this.priorityQueue = new PriorityQueue<>();
        this.stack = new Stack<>();
    }

    public void push(int val) {
        priorityQueue.add(val);
        stack.push(val);
    }

    public void pop() {
        priorityQueue.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return priorityQueue.peek();
    }
}