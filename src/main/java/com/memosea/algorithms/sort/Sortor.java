package com.memosea.algorithms.sort;

/**
 * Created by mahui on 2017/6/11.
 */
public abstract class Sortor {
    public static boolean less(Comparable val1, Comparable val2){
        return val1.compareTo(val2) < 0;
    }
    public void exchange(Comparable[] a,int val1Idx,int val2Idx){
        Comparable tmp = a[val1Idx];
        a[val1Idx] = a[val2Idx];
        a[val2Idx] = tmp;
    }

    public void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(!less(a[i],a[i-1])) return false;
        }
        return true;
    }

    public abstract void sort(Comparable [] a);
}
