package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangcz
 * @since 20240425
 */
public class LowestCommonAncestor {

    private Map<Integer, TreeNode> parentMap = new HashMap<>();

    private Set<Integer> set = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);

        while (p != null) {
            set.add(p.val);
            p = parentMap.get(p.val);
        }

        while (q != null) {
            if (set.contains(q.val)) {
                return q;
            }
            q = parentMap.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs(root.left);
        }

        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
