package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangcz
 * @since 20240425
 */
public class Flatten {

    private TreeNode dummy = new TreeNode(0);

    private TreeNode pre = dummy;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);

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

}
