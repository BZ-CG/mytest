package cn.edu.pzhu.mytest.leetcode;

class MinStackWithLinked {

    private Node node;

    /**
     * initialize your data structure here.
     */
    public MinStackWithLinked() {

    }

    public void push(int val) {
        if (node == null) {
            node = new Node(val, val);
        } else {
            Node newNode = new Node(val, Math.min(node.minValue, val));
            newNode.prevNone = node;
            node = newNode;
        }
    }

    public void pop() {
        node = node.prevNone;
    }

    public int top() {
        return node.value;
    }

    public int getMin() {
        return node.minValue;
    }

    private class Node {
        int value;
        int minValue;
        Node prevNone;

        public Node(int value, int minValue) {
            this.value = value;
            this.minValue = minValue;
        }
    }
}