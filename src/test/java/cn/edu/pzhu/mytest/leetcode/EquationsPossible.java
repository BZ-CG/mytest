package cn.edu.pzhu.mytest.leetcode;

/**
 * 等式判断
 *
 * @author zhangcz
 * @since 20240125
 */
public class EquationsPossible {
    private int[] parent;

    public EquationsPossible() {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
    }

    public boolean equationsPossible(String[] equations) {
        for (String equation : equations) {
            if (equation.contains("==")) {
                char[] chars = equation.toCharArray();
                union(chars[0], chars[3]);
            }
        }

        for (String equation : equations) {
            if (equation.contains("!=")) {
                char[] chars = equation.toCharArray();
                if (connected(chars[0], chars[3])) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean connected(char p, char q) {
        int rootP = find(p - 'a');
        int rootQ = find(q - 'a');
        return rootP == rootQ;
    }

    public void union(char p, char q) {
        int rootP = find(p - 'a');
        int rootQ = find(q - 'a');

        if (rootP == rootQ) {
            return;
        }

        parent[rootP] = rootQ;
    }


    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


}
