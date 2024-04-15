package cn.edu.pzhu.mytest.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结果工具类
 *
 * @author zhangcz
 * @since 20230627
 */
@UtilityClass
public class ResultUtils {

    public void printArr(int[] arr) {
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public void printArr(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    public void printListList(List<List<String>> list) {
        for (List<String> strings : list) {
            System.out.println(String.join(",", strings));
        }
    }

    public void printList(List<?> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
