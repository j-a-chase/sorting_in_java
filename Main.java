// Name: James A. Chase
// File: Main.java
// Date: 24 February 2024

// imports
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        menu1();
        System.out.print("> ");

        Scanner input = new Scanner(System.in);

        int n;
        try {
            n = input.nextInt();
        } catch (InputMismatchException e) {
            n = 100000;
            input.next();
        }
        if (n < 0) n *= -1;

        int[] test_arr = generateArray(n);

        menu2();
        System.out.print("> ");

        int response = -1;
        long startTime, total;
        startTime = total = 0;
        while (response == -1) {
            try {
                response = input.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
                input.next();
            }

            switch(response) {
                case 1:
                    startTime = System.nanoTime();
                    bubbleSort(test_arr, n);
                    total = System.nanoTime() - startTime;
                    break;
                case 2:
                    startTime = System.nanoTime();
                    insertionSort(test_arr, n);
                    total = System.nanoTime() - startTime;
                    break;
                case 3:
                    startTime = System.nanoTime();
                    mergeSort(test_arr, 0, n-1);
                    total = System.nanoTime() - startTime;
                    break;
                case 4:
                    startTime = System.nanoTime();
                    quickSort(test_arr, 0, n-1);
                    total = System.nanoTime() - startTime;
                    break;
                default:
                    System.out.println("Error: Invalid input");
                    response = -1;

                    System.out.print("> ");
                    break;
            }
        }
        input.close();
        System.out.println("Algorithm performance: " + (total / 1000000) + " milliseconds.");
    }

    static void menu1() {
        System.out.println("#################################################");
        System.out.println("===== Welcome to Sorting Algorithms in Java =====");
        System.out.println("#################################################");
        System.out.println();
        System.out.println("#################################################");
        System.out.println("===== Enter desired size of array:          =====");
        System.out.println("===== Size for good comparisons: 100000     =====");
        System.out.println("#################################################");
    }

    static void menu2() {
        System.out.println();
        System.out.println("#################################################");
        System.out.println("===== Select an algorithm:                  =====");
        System.out.println("=====                                       =====");
        System.out.println("===== [1] Bubble Sort                       =====");
        System.out.println("===== [2] Insertion Sort                    =====");
        System.out.println("===== [3] Merge Sort                        =====");
        System.out.println("===== [4] Quick Sort                        =====");
        System.out.println("#################################################");
    }

    static int[] generateArray(int n) {
        return new Random().ints(n, 0, n).toArray();
    }

    static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    static void bubbleSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    static void insertionSort(int[] arr, int n) {
        int highest, highest_index;
        int end = n-1;
        while (end > 0) {
            highest = arr[0];
            highest_index = 0;

            for (int i = 0; i < end-1; i++) {
                if (arr[i] > highest) {
                    highest = arr[i];
                    highest_index = i;
                }
            }

            if (highest > arr[end]) {
                int temp = highest;
                arr[highest_index] = arr[end];
                arr[end] = temp;
            }
            end--;
        }
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);

            merge(arr, l, m, r);
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
        int ln = m - l + 1;
        int rn = r - m;

        int[] leftSide = new int[ln];
        int[] rightSide = new int[rn];

        for (int i = 0; i < ln; i++) {
            leftSide[i] = arr[l + i];
        }
        for (int i = 0; i < rn; i++) {
            rightSide[i] = arr[m + 1 + i];
        }

        int i = 0, j = 0, k = l;
        while (i < ln && j < rn) {
            if (leftSide[i] <= rightSide[j]) {
                arr[k] = leftSide[i];
                i++;
            } else {
                arr[k] = rightSide[j];
                j++;
            }
            k++;
        }

        while (i < ln) {
            arr[k] = leftSide[i];
            i++;
            k++;
        }

        while (j < rn) {
            arr[k] = rightSide[j];
            j++;
            k++;
        }
    }

    static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int p = partition(arr, l, r);

            quickSort(arr, l, p-1);
            quickSort(arr, p+1, r);
        }
    }

    static int partition(int[] arr, int l, int r) {
        int piv = arr[r];
        int i = l - 1;
        
        for (int j = l; j < r; j++) {
            if (arr[j] <= piv) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[r];
        arr[r] = temp;

        return i + 1;
    }
}
