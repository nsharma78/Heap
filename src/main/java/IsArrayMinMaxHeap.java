/*
 * Check if a given array represents min heap or not
 * Ex: {2,3,4,5,10,15} : true and {2,10,4,5,3,15}: false
 */

package main.java;

public class IsArrayMinMaxHeap {

    public static void main(String[] args) {
        //int[] arr = {2,3,4,5,10,15};
        int[] arr = {2,10,4,5,3,15};

        System.out.println("The given array is minimum heap: " + isArrayMinHeap(arr));

        int[] arr1 = {15,10,5,4,3,2};
        //int[] arr1 = {5,10,4,6,3,2};

        System.out.println("The given array is minimum heap: " + isArrayMaxHeap(arr1));
    }

    private static boolean isArrayMinHeap(int[] arr) { //O(n) O(1)
        if (arr == null || arr.length < 2)
            return true;

        for(int i = 0; i < (arr.length - 2)/2; i++) {
            if( (arr[i] > arr[2*i + 1]) ||
                    ((2 * i + 2 != arr.length) && (arr[i] > arr[2*i + 2])))
                return false;
        }
        return true;
    }

    private static boolean isArrayMaxHeap(int[] arr) { //O(n) O(1)
        if (arr == null || arr.length < 2)
            return true;

        for(int i = 0; i < (arr.length - 2)/2; i++) {
            if( (arr[i] < arr[2*i + 1]) ||
                    ((2 * i + 2 != arr.length) && (arr[i] < arr[2*i + 2])))
                return false;
        }
        return true;
    }
}
