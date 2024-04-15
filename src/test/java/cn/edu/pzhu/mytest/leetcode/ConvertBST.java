package cn.edu.pzhu.mytest.leetcode;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

/**
 * ConvertBST
 *
 * @author zhangcz
 * @since 20220228
 */
public class ConvertBST {

    public int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode right = convertBST(root.right);
        sum += root.val;
        TreeNode newNode = new TreeNode(sum);
        newNode.left = convertBST(root.left);
        newNode.right = right;
        return newNode;
    }
}
