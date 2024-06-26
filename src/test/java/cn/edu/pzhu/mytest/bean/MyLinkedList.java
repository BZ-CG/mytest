package cn.edu.pzhu.mytest.bean;

public class MyLinkedList {

    private Node head;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = new Node();
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) {
            return -1;
        }

        int currentIndex = 0;
        Node node = head.next;
        while (node != null) {
            if (currentIndex == index) {
                return node.val;
            }

            currentIndex++;
            node = node.next;
        }

        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node();
        node.val = val;
        node.next = head.next;
        head.next = node;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        Node newNode = new Node();
        newNode.val = val;

        node.next = newNode;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
            return;
        }

        Node newNode = new Node();
        newNode.val = val;

        int currentIndex = 0;
        Node node = head.next;
        Node preNode = head;
        while (node != null) {
            if (currentIndex == index) {
                newNode.next = node;
                preNode.next = newNode;
                return;
            }

            currentIndex++;
            preNode = node;
            node = node.next;
        }

        if (currentIndex == index) {
            addAtTail(val);
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        int currentIndex = 0;
        Node node = head.next;
        Node preNode = head;
        while (node != null) {
            if (currentIndex == index) {
                preNode.next = node.next;
                return;
            }

            currentIndex++;
            preNode = node;
            node = node.next;
        }
    }


    class Node {
        int val;
        Node next;
    }

}