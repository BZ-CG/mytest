package cn.edu.pzhu.mytest.bean;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 树结构工具类
 *
 * @author zhangcz
 * @since 20201028
 */
@UtilityClass
public class TreeNodeUtils {


    /**
     * 构建树
     *
     * @param childrenList 子节点列表
     * @param root         根节点
     * @return 树的根节点
     */
    public TreeNode build(List<TreeNode> childrenList, TreeNode root) {
        setChildrenList(root, childrenList);
        return root;

    }

    /**
     * 为根节点set子节点
     *
     * @param root         根节点
     * @param childrenList 子节点列表
     */
    private void setChildrenList(TreeNode root, List<TreeNode> childrenList) {
        if (CollectionUtils.isEmpty(childrenList)) {
            return;
        }

        List<TreeNode> list = new ArrayList<>(childrenList.size());
        childrenList.stream()
                .filter(node -> node.getParentId().equals(root.getId()))
                .forEach(node -> {
                    node.setParentNode(root);
                    setChildrenList(node, childrenList);
                    list.add(node);
                });

        root.setChildrenList(list);
    }


    /**
     * 从当前节点遍历树
     *
     * @param root   当前根节点
     * @param action 回调函数
     */
    public void traverse(TreeNode root, Consumer<Integer> action) {
        if (root == null) {
            return;
        }

        if (CollectionUtils.isEmpty(root.getChildrenList())) {
            return;
        }

        for (TreeNode treeNode : root.getChildrenList()) {
            action.accept(treeNode.getId());
            traverse(treeNode, action);
        }
    }

    /**
     * 从指定节点反向遍历到根节点
     *
     * @param treeNode 当前节点
     * @param action   回调函数
     */
    public void traverseWithReverse(TreeNode treeNode, Consumer<Integer> action) {
        if (treeNode == null) {
            return;
        }

        action.accept(treeNode.getId());
        traverseWithReverse(treeNode.getParentNode(), action);
    }

    /**
     * 从当前节点及其子树中寻找指定id的节点
     *
     * @param treeNode 当前节点
     * @param id       待寻找节点id
     * @return 待寻找的节点
     */
    public TreeNode find(TreeNode treeNode, Integer id) {
        if (treeNode == null) {
            return null;
        }

        if (id.equals(treeNode.getId())) {
            return treeNode;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            List<TreeNode> childrenList = pop.getChildrenList();
            if (!CollectionUtils.isEmpty(childrenList)) {
                for (TreeNode node : childrenList) {
                    if (node.getId().equals(id)) {
                        return node;
                    }

                    stack.push(node);
                }
            }

        }

        return null;
    }
}
