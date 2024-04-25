package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

/**
 * @author zhangcz
 * @since 20240425
 */
public class MaxPathSum {

    private int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        sum = Math.max(sum, left + right + root.val);
        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(-1);
        node1.left = node2;
        MaxPathSum maxPathSum = new MaxPathSum();
        System.out.println(maxPathSum.maxPathSum(node1));
    }

}
