package com.memosea.algorithms.sort;

/**
 * Created by mahui on 2017/6/11.
 * 选择排序（冒泡排序）
 * - 比较次数 N<sub>2</sub>/2
 * - 运行时间与输入无关
 * - 移动数据最少，与数组长度一致
 *
 */
public class Selection extends Sortor {

    public void sort(Comparable[] a){
        int len = a.length;
        for(int i = 0; i < len; i++){
            int minIdx = i;
            for(int j=i+1; j < len; j++){
                if(!less(a[minIdx],a[j])){
                    exchange(a,minIdx,j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Sortor sortor = new Selection();
        Integer [] testArray = new Integer[]{3, 2, 3};
        sortor.sort(testArray);
        assert isSorted(testArray);
        sortor.show(testArray);
    }
}
