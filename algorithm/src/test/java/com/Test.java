package com;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] list = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] res = Arrays.copyOfRange(list, 0, 10);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        Arrays.copyOfRange(new int[]{123,34}, 0, 10);
    }
}
