package QuickSort;

// CSCC 13 || CCB || Quick Sort Implementation
// Montecillo, Porol

import java.io.*;
import java.util.*;

public class Quicksort {
    public static void main(String[] args) {
        // Read input data from a text file
        int[] unsortedList = readArrayFromFile("D:\\2023-2024 Miko's Folder\\GitHub Folder\\Montecillo-Porol-Coding-Activities\\Quick_Sort\\input.txt");
        int[] sortedList = new int[unsortedList.length];

        // Print original unsortedList
        System.out.println("Unsorted array:");
        printArray(unsortedList);

        // Call the Quicksort Algorithm to sort the unsortedList
        quicksort(unsortedList); // The quicksort method is called here.

        sortedList = unsortedList; // After quicksort, sortedList points to the sorted result.

        // Print the sorted Array
        System.out.println("\nArray sorted using Quick Sort:");
        printArray(sortedList);

        // Write the sorted array to an output text file
        writeArrayToFile("output.txt", unsortedList);
    }

    /**
     * Reads an array of integers from a text file.
     *
     * @param filename The name of the input file containing the integers, one per
     *                 line.
     * @return An array of integers read from the file.
     */
    private static int[] readArrayFromFile(String filename) {
        try {
            // Open the input file for reading.
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            reader.close();

            // Split the line by spaces to get individual elements
            String[] parts = line.split(" ");
            int[] array = new int[parts.length];

            // Convert string elements to integers and store them in the array
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }

            return array;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes an array of integers to a text file.
     *
     * @param filename The name of the output file where the array will be written.
     * @param array    The array of integers to be written to the file.
     */
    private static void writeArrayToFile(String filename, int[] array) {
        try {
            // Open the output file for writing.
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            // Iterate through the array and write each integer followed by a space to the
            // file.
            for (int num : array) {
                writer.write(Integer.toString(num) + " ");
            }

            // Close the file after writing the entire array.
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1); // Entry point for quicksort
    }

    private static void quicksort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }

        // Here, a right-most pivot is chosen.
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex); // Move pivot to the right-most position

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1); // Recursively sort the left partition
        quicksort(array, leftPointer + 1, highIndex); // Recursively sort the right partition
    }

    private static void printArray(int[] unsortedList) {
        for (int num : unsortedList) {
            System.out.print(num + " "); // Printing the array
        }
    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) {
            // Walk from the left until we find a number greater than the pivot, or hit the
            // right pointer.
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            // Walk from the right until we find a number less than the pivot, or hit the
            // left pointer.
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);
        }

        if (array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        } else {
            leftPointer = highIndex;
        }

        return leftPointer;
    }

    // Swapping the elements of the array
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}