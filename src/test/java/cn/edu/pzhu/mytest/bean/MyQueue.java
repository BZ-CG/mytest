package cn.edu.pzhu.mytest.bean;

import java.util.Stack;

class MyQueue {

    private Stack<Integer> stack;
    private Stack<Integer> temp;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack = new Stack<>();
        temp = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        int value = temp.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return value;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        int value = pop();
        push(value);
        return value;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }
}