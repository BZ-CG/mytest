package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

/**
 * diameterOfBinaryTree
 *
 * @author zhangcz
 * @since 20240425
 */
public class DiameterOfBinaryTree {

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        ans = Math.max(ans, left + right - 1);
        return Math.max(left, right) + 1;
    }

}
