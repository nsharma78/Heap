package main.java;
import java.util.*;

public class RunningMedian {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        double[] medians = getMedians(arr);
        System.out.println("The running medians are: " + Arrays.toString(medians));
    }

    private static double[] getMedians(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        double[] medians = new double[arr.length];
        PriorityQueue<Integer> lowers = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        PriorityQueue<Integer> highers = new PriorityQueue<>(); // Min Heap

        for(int i = 0; i < arr.length; i++) {
            addNumbers(arr[i], lowers, highers);
            rebalance(lowers, highers);
            medians[i] = getMedian(lowers, highers);
        }
        return medians;

    }

    private static void addNumbers(int i, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if(lowers.isEmpty() || i < lowers.peek())
            lowers.add(i);
        else
            highers.add(i);
    }

    private static void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> smallerHeap = (lowers.size() < highers.size()) ? lowers : highers;
        PriorityQueue<Integer> biggerHeap = (lowers.size() > highers.size()) ? lowers : highers;

        if(biggerHeap.size() - smallerHeap.size() >= 2) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    private static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> biggerHeap = (lowers.size() > highers.size()) ? lowers : highers;
        PriorityQueue<Integer> smallerHeap = (lowers.size() > highers.size()) ? highers : lowers;

        if(biggerHeap.size() == smallerHeap.size()) {
            return (biggerHeap.peek() + smallerHeap.peek())/2.0;
        }else {
            return biggerHeap.peek();
        }
    }
}
