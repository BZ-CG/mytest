package cn.edu.pzhu.mytest.leetcode.hot100;

/**
 * 单词搜索
 *
 * @author zhangcz
 * @since 20240530
 */
public class WordSearch {

    private boolean exist = false;

    private int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private int[][] visit = new int[10][10];

    public boolean exist(char[][] board, String word) {
        char start = word.toCharArray()[0];
        for (int i = 0; i < board.length && !exist; i++) {
            for (int j = 0; j < board[i].length && !exist; j++) {
                if (board[i][j] == start) {
                    visit[i][j] = 1;
                    dfs(i, j, 1, word, board);
                    visit[i][j] = 0;
                }
            }
        }

        return exist;
    }

    private void dfs(int x, int y, int num, String word, char[][] board) {
        if (num > word.length() - 1) {
            exist = true;
            return;
        }

        char dist = word.toCharArray()[num];
        for (int[] arr : dir) {
            int newX = x + arr[0];
            int newY = y + arr[1];

            if (newX < 0 || newX > board.length - 1 || newY < 0 || newY > board[0].length - 1) {
                continue;
            }

            if (dist != board[newX][newY]) {
                continue;
            }

            if (visit[newX][newY] != 0) {
                continue;
            }

            visit[newX][newY] = 1;
            dfs(newX, newY, num + 1, word, board);
            visit[newX][newY] = 0;
        }
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        //        char[][] board = new char[][] {
        //                {'A', 'B', 'C', 'E'},
        //                {'S', 'F', 'C', 'S'},
        //                {'A', 'D', 'E', 'E'}
        //        };
        //
        //        System.out.println(wordSearch.exist(board, "ASA"));

        char[][] board = new char[][] {
                { 'A' },
                { 'A' }
        };

        System.out.println(wordSearch.exist(board, "AAA"));
    }
}
