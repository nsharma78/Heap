/*
 * Check if a given array represents min heap or not
 * Ex: {15,10,5,4,3,2} : true and {15,10,4,5,3,2}: false
 */

package main.java;

public class IsArrayMaxHeap {

    public static void main(String[] args) {
        int[] arr = {15,10,5,4,3,2};
        //int[] arr = {5,10,4,6,3,2};

        System.out.println("The given array is minimum heap: " + isArrayMaxHeap(arr));
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
