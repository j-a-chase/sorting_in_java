// Name: James A. Chase
// File: Main.java
// Date: 24 February 2024

// imports
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        menu1();
        System.out.print("> ");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println();

        int[] test_arr = generateArray(n);

        menu2();
        System.out.print("> ");

        int response = -1;
        long startTime, total;
        startTime = total = 0;
        while (response == -1) {
            response = input.nextInt();

            switch(response) {
                case 1:
                    startTime = System.nanoTime();
                    bubbleSort(test_arr, n);
                    total = System.nanoTime() - startTime;
                    break;
                case 2:
                    // function
                    break;
                case 3:
                    // function
                    break;
                case 4:
                    // function
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
        System.out.println("#################################################");
    }

    static void menu2() {
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
}
