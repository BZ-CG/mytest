package cn.edu.pzhu.mytest.huawei;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] findArr = new int[501];

        int index = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (findArr[a] == 0) {
                arr[index++] = a;
                findArr[a] = 1;
            }
        }

        bubbleSort(arr, 0, index);
        for (int i = 0; i < index; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void bubbleSort(int[] arr, int left, int right) {
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j < right; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}