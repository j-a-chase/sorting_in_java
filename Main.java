// Name: James A. Chase
// File: Main.java
// Date: 24 February 2024

// imports
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // print out first menu for input
        menu1();
        System.out.print("> ");

        // create Scanner for user input
        Scanner input = new Scanner(System.in);

        // store user value in n
        int n;
        try {
            n = input.nextInt();
        } catch (InputMismatchException e) {
            // set n to 10^5 if invalue input is given
            n = 100000;
            input.next();
        }
        // handle if they input a negative number
        if (n < 0) n *= -1;

        // create test array with n number of random numbers
        int[] test_arr = generateArray(n);

        // print menu for sorting algorithm options
        menu2();
        System.out.print("> ");

        // initialize variable for user input
        int response = -1;

        // initialize values for time tracking
        long startTime, total;
        startTime = total = 0;
        
        // handle user inputting an invalid number or other input
        while (response == -1) {
            try {
                response = input.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
                input.next();
            }

            // handle user responses
            switch(response) {
                // bubble sort
                case 1:
                    startTime = System.nanoTime();
                    bubbleSort(test_arr, n);
                    total = System.nanoTime() - startTime;
                    break;
                // insertion sort
                case 2:
                    startTime = System.nanoTime();
                    insertionSort(test_arr, n);
                    total = System.nanoTime() - startTime;
                    break;
                // merge sort
                case 3:
                    startTime = System.nanoTime();
                    mergeSort(test_arr, 0, n-1);
                    total = System.nanoTime() - startTime;
                    break;
                // quick sort
                case 4:
                    startTime = System.nanoTime();
                    quickSort(test_arr, 0, n-1);
                    total = System.nanoTime() - startTime;
                    break;
                // invalid input
                default:
                    System.out.println("Error: Invalid input");
                    response = -1;
                    System.out.print("> ");
                    break;
            }
        }
        // close input buffer
        input.close();

        // print results
        System.out.println("Algorithm performance: " + (total / 1000000) + " milliseconds.");
    }

    // prints first menu
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

    // prints second menu
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

    // generates an array of random numbers in the range of n
    static int[] generateArray(int n) {
        return new Random().ints(n, 0, n).toArray();
    }

    // prints out an array's values (mainly for debugging)
    static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    // uses the bubble sort algorithm to sort the array
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

    // uses the insertion sort algorithm to sort the array
    static void insertionSort(int[] arr, int n) {
        // keeps track of highest vlaue and index
        int highest, highest_index;

        // pointer to the end of the array
        int end = n-1;

        // loop through from back to front
        while (end > 0) {
            // begin with highest at the beginning
            highest = arr[0];
            highest_index = 0;

            // loop through front to end-1
            for (int i = 0; i < end-1; i++) {
                // update highest
                if (arr[i] > highest) {
                    highest = arr[i];
                    highest_index = i;
                }
            }

            // compare highest with end value
            if (highest > arr[end]) {
                // swap
                int temp = highest;
                arr[highest_index] = arr[end];
                arr[end] = temp;
            }

            // decrement end
            end--;
        }
    }

    // Recursive function that sorts the array using merge sort
    static void mergeSort(int[] arr, int l, int r) {
        // if the left pointer is less than the right pointer
        if (l < r) {
            // find middle
            int m = (l + r) / 2;

            // merge sort left side
            mergeSort(arr, l, m);

            // merge sort right side
            mergeSort(arr, m+1, r);

            // merge halves together
            merge(arr, l, m, r);
        }
    }

    // helper function for merge sort, combines split arrays
    static void merge(int[] arr, int l, int m, int r) {
        // find left anad right lengths
        int ln = m - l + 1;
        int rn = r - m;

        // define left and right side array sizes
        int[] leftSide = new int[ln];
        int[] rightSide = new int[rn];

        // populate left and right side arrays
        for (int i = 0; i < ln; i++) {
            leftSide[i] = arr[l + i];
        }
        for (int i = 0; i < rn; i++) {
            rightSide[i] = arr[m + 1 + i];
        }

        // update array whilst sorting left and right sides
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

        // add remaining left side numbers if there are numbers remaining
        while (i < ln) {
            arr[k] = leftSide[i];
            i++;
            k++;
        }

        // add remaining right side numbers if there are numbers remaining
        while (j < rn) {
            arr[k] = rightSide[j];
            j++;
            k++;
        }
    }

    // sorts array using quick sort algorithm
    static void quickSort(int[] arr, int l, int r) {
        // if left pointer is less than right pointer
        if (l < r) {
            // find the partition value
            int p = partition(arr, l, r);

            // quick sort halves based on the partition
            quickSort(arr, l, p-1);
            quickSort(arr, p+1, r);
        }
    }

    // helper function for quick sort, finds the partition
    static int partition(int[] arr, int l, int r) {
        // calculate initial pivot value
        int piv = arr[r];
        int i = l - 1;
        
        // loop through portion of array
        for (int j = l; j < r; j++) {
            if (arr[j] <= piv) {
                i++;

                // swap values less than pivot
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap last value with righthand value
        int temp = arr[i+1];
        arr[i+1] = arr[r];
        arr[r] = temp;

        // return index for new split
        return i + 1;
    }
}
