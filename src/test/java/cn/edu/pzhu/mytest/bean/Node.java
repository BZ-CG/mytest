package cn.edu.pzhu.mytest.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Node implements Serializable {
    public int val;
    public List<Node> children;

    public Node() {
        System.out.println("invoke public Node()");
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public Object readResolve() {
        return new Node();
    }
}