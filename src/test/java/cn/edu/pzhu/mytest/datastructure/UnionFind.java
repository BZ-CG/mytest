package cn.edu.pzhu.mytest.datastructure;

/**
 * 并查集
 *
 * @author zhangcz
 * @since 20240125
 */
public class UnionFind {

    /**
     * 连通分量数
     */
    private int count;

    /**
     * 父结点指针
     */
    private int[] parent;

    /**
     * 记录数的大小
     */
    private int[] size;

    /**
     * 初始化
     *
     * @param n 节点数量
     */
    public UnionFind(int n) {
        // 最开始都不相连通，所以连通数量等于总节点数
        this.count = n;

        // 父节点指针指向自己
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }


    /**
     * 将 q 和 q 连接
     *
     * @param p 节点
     * @param q 节点
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // 合并
        parent[rootQ] = rootP;

        // 每次合并都会减少一个连通量
        count--;
    }

    /**
     * 返回节点的根节点
     *
     * @param x 节点
     * @return 根节点
     */
    private int find(int x) {
        if (parent[x] != x) {
            // 这里递归非常的巧妙，递归的终点等于根节点，在遍历过程中将每个节点的parent都指向了根节点
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    /**
     * 判断 q 和 p 是否连通
     *
     * @param p 节点
     * @param q 节点
     * @return true: 连通, false: 不连通
     */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }


    /**
     * 返回图中有多少连通分量
     *
     * @return 图中连通分量数
     */
    public int count() {
        return count;
    }
}
