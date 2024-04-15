package cn.edu.pzhu.mytest.huawei;

import cn.edu.pzhu.mytest.util.ResultUtils;

import java.util.Scanner;

/**
 * 数独
 *
 * @author zhangcz
 * @since 20230627
 */
public class SudoTest {

    private static int[][] result = null;

    public static void main(String[] args) {
        int[][] arr = new int[9][9];
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            String line = in.nextLine();
            String[] strings = line.split(" ");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(strings[j]);
            }
        }

        SudoTest huaWeiTest = new SudoTest();
        huaWeiTest.sudo(arr, 0, 0);
        ResultUtils.printArr(arr);
    }


    public boolean sudo(int[][] arr, int row, int column) {
        if (isOver(arr)) {
            result = copy(arr);
            return true;
        }
        for (int i = row; i < 9; i++) {
            for (int j = column; j < 9; j++) {
                if (arr[i][j] > 0) {
                    continue;
                }

                for (int z = 1; z <= 9; z++) {
                    arr[i][j] = z;
                    if (validRow(arr, i, z) && validColumn(arr, j, z) && validSudo(arr, i, j, z) && sudo(arr, i, j)) {
                        return true;
                    }
                    arr[i][j] = 0;
                }

                return false;
            }
        }

        return true;
    }

    private int[][] copy(int[][] arr) {
        int[][] result = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = arr[i][j];
            }
        }

        return result;
    }

    private boolean isOver(int[][] arr) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validRow(int[][] arr, int rowIndex, int value) {
        for (int i = 0; i < 9; i++) {
            if (arr[rowIndex][i] == value) {
                return false;
            }
        }

        return true;
    }

    private boolean validColumn(int[][] arr, int columnIndex, int value) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][columnIndex] == value) {
                return false;
            }
        }

        return true;
    }

    private boolean validSudo(int[][] arr, int rowIndex, int columnIndex, int value) {
        int row = rowIndex / 3;
        int column = columnIndex / 3;

        for (int i = row * 3; i < row * 3 + 3; i++) {
            for (int j = column * 3; j < column * 3 + 3; j++) {
                if (arr[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
