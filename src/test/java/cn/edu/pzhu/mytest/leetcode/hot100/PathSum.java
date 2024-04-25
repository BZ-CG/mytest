package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

/**
 * @author zhangcz
 * @since 20240425
 */
public class PathSum {

    private int count = 0;

    private int target = 0;

    private int current = 0;

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        preOrder(root);
        return count;
    }

    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        current = 0;
        getSum(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void getSum(TreeNode root) {
        if (root == null) {
            return;
        }

        current += root.val;
        if (current == target) {
            count++;
        }

        getSum(root.left);
        if (root.left != null) {
            current -= root.left.val;
        }

        getSum(root.right);
        if (root.right != null) {
            current -= root.right.val;
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node3, node4);

        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, null, node6);

        TreeNode node1 = new TreeNode(1, node2, node5);

        PathSum pathSum = new PathSum();
        System.out.println(pathSum.pathSum(node1, 6));
    }

}
