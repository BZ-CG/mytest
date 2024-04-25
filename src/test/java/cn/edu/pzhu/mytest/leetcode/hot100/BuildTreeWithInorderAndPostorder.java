package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcz
 * @since 20240425
 */
public class BuildTreeWithInorderAndPostorder {

    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] postorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int[] inorder, int postLeft, int postRight, int inLeft, int inRight) {
        if (postLeft > postRight) {
            return null;
        }

        int rootValue = postorder[postRight];
        TreeNode root = new TreeNode(rootValue);
        int rootInorderIndex = inorderIndexMap.get(rootValue);
        int leftCount = rootInorderIndex - inLeft;

        root.left = build(postorder, inorder, postLeft, postLeft + leftCount - 1, inLeft, rootInorderIndex - 1);
        root.right = build(postorder, inorder, postLeft + leftCount, postRight - 1, rootInorderIndex + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        BuildTreeWithInorderAndPostorder build = new BuildTreeWithInorderAndPostorder();

        int[] post = new int[] { 9, 15, 7, 20, 3 };
        int[] in = new int[] { 9, 3, 15, 20, 7 };

        TreeNode root = build.buildTree(post, in);
        System.out.println(root);
    }
}
