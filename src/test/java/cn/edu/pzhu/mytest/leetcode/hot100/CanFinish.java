package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhangcz
 * @since 20240425
 */
public class CanFinish {

    /**
     * 思路：拓扑排序(BFS)
     * 1、记录节点的后驱节点，邻接表
     * 2、先解决入度为0的节点，加入队列
     * 3、如果后驱节点的所有前驱节点都完成，则当前节点的入度为0，加入队列
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度记录
        int[] indegreeArr = new int[numCourses];
        // 邻接表
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            // prerequisite[0] 依赖 prerequisite[1]，prerequisite[0] 入度+1
            indegreeArr[prerequisite[0]]++;
            // prerequisite[1] 完成后可完成 prerequisite[0]，构建邻接表
            adjacencyList.get(prerequisite[1]).add(prerequisite[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegreeArr[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            numCourses--;
            // pre 结束后，起邻接表的节点入度-1
            for (Integer adjacency : adjacencyList.get(pre)) {
                indegreeArr[adjacency]--;
                // 如果减为0，则加入队列
                if (indegreeArr[adjacency] == 0) {
                    queue.add(adjacency);
                }
            }
        }

        return numCourses == 0;
    }
}
