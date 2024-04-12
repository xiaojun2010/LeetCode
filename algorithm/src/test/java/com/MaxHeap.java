package com;

import java.util.HashMap;

public class MaxHeap {

    int[] data;
    int size = 0;

    public MaxHeap(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    private int parent(int k) {
        return (k - 1) / 2;

    }

    private int leftChild(int k) {
        return 2 * k + 1;
    }

    private int rightChild(int k) {
        return 2 * k + 2;
    }

    public int getSize() {
        return size;
    }

    private void swap(int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    private void siftDown(int k) {
        while (leftChild(k) < getSize()) {
            int j = leftChild(k);
            if (j + 1 < getSize() && data[j + 1] > data[j]) {
                j = j + 1;
            }
            if (data[j] <= data[k]) {
                break;
            }
            swap(j, k);
            k = j;
        }
    }

    private void siftUp(int k) {
        while ((k - 1) / 2 >= 0 && data[(k - 1) / 2] < data[k]) {
            swap(k,(k-1)/2);
            k = (k-1)/2;
        }
    }

}
