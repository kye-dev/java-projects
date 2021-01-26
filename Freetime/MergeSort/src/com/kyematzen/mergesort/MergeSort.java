package com.kyematzen.mergesort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr4 = {12, 4, 2, 8};

        printArray(arr4); // before change for array
        System.out.println("Original: " + "0, " + (arr4.length - 1));
        mergeSort(arr4, 0, arr4.length - 1);
        System.out.println("done");
		printArray(arr4);

    }

    public static void printArray(int[] a) {
        for (int elt : a) {
            System.out.print(elt + "  ");
        }
        System.out.println();
    }

    public static void mergeSort(int[] a, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int middle = (leftIndex + rightIndex) / 2;

        System.out.println("31 (Left: " + leftIndex + ", Right: " + rightIndex + ", Middle: " + middle + ")");

        mergeSort(a, leftIndex, middle);

        System.out.println("35 (Left: " + leftIndex + ", Right: " + rightIndex + ", Middle: " + middle + ")");

        mergeSort(a, middle + 1, rightIndex);

        merge(a, leftIndex, rightIndex);
        //printArray(a);

    }

    public static void merge(int[] a, int left, int right) {
        int[] temp = new int[right - left + 1];

        if (left >= right) return;

        int mid = (left + right) / 2;

        int i = 0;//fills temp array
        int leftIndex = left;

        // 1. 0

        int rightIndex = mid + 1;

        while ((leftIndex <= mid) && (rightIndex <= right)) {
            if (a[leftIndex] <= a[rightIndex]) {
                temp[i] = a[leftIndex];
                leftIndex++;
            } else {
                temp[i] = a[rightIndex];
                rightIndex++;
            }
            i++;
        }

        while (leftIndex <= mid) {
            temp[i] = a[leftIndex];
            leftIndex++;
            i++;
        }

        while (rightIndex <= right) {
            temp[i] = a[rightIndex];
            rightIndex++;
            i++;
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i - left];
        }
    }
}