package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangcz
 * @since 20240425
 */
public class Flatten {

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        TreeNode pre = null;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (pre != null) {
                pre.left = null;
                pre.right = current;
            }

            if (current.right != null) {
                queue.push(current.right);
            }

            if (current.left != null) {
                queue.push(current.left);
            }

            pre = current;
        }

    }
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);

        root.left = null;
        TreeNode pre = root;
        for (int i = 1; i < list.size(); i++) {
            pre.right = new TreeNode(list.get(i));
            pre = pre.right;
        }

    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node3, node4);

        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, null, node6);

        TreeNode node1 = new TreeNode(1, node2, node5);

        Flatten flatten = new Flatten();
        flatten.flatten2(node1);
        System.out.println();
    }

}
