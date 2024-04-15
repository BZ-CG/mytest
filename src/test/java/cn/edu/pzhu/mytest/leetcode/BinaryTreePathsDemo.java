package cn.edu.pzhu.mytest.leetcode;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * binaryTreePaths
 *
 * @author zhangcz
 * @since 20201028
 */
public class BinaryTreePathsDemo {

    public List<String> pathList = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> nodeValueList = new ArrayList<>();
        dfs(root, nodeValueList);
        return pathList;
    }


    public void dfs(TreeNode root, List<Integer> nodeValueList) {
        if (root == null) {
            return;
        }

        // 添加当前节点路径
        nodeValueList.add(root.val);
        // 叶子节点保存路径
        if (root.left == null && root.right == null) {
            String path = nodeValueList.stream().map(String::valueOf).collect(Collectors.joining("->"));
            pathList.add(path);
        }

        dfs(root.left, nodeValueList);
        dfs(root.right, nodeValueList);
        // 回溯
        nodeValueList.remove(nodeValueList.size() - 1);
    }


    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2, null, new TreeNode(5));
        TreeNode node1 = new TreeNode(1, node2, new TreeNode(3));

        BinaryTreePathsDemo demo = new BinaryTreePathsDemo();
        List<String> strings = demo.binaryTreePaths(node1);
        System.out.println(strings);
    }
}
