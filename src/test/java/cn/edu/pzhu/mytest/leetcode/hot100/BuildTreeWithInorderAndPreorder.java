package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcz
 * @since 20240425
 */
public class BuildTreeWithInorderAndPreorder {

    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootInorderIndex = inorderIndexMap.get(preorder[preLeft]);
        int leftCount = rootInorderIndex - inLeft;

        root.left = build(preorder, inorder, preLeft + 1, preLeft + leftCount, inLeft, rootInorderIndex - 1);
        root.right = build(preorder, inorder, preLeft + leftCount + 1, preRight, rootInorderIndex + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        BuildTreeWithInorderAndPreorder build = new BuildTreeWithInorderAndPreorder();

        int[] pre = new int[] { 3, 9, 20, 15, 7 };
        int[] in = new int[] { 9, 3, 15, 20, 7 };

        TreeNode root = build.buildTree(pre, in);
        System.out.println(root);
    }
}
