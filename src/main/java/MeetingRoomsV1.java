/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 */

package main.java;
import java.util.Arrays;

public class MeetingRoomsV1 {

    static class Interval implements Comparable<Interval>{
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start; this.end = end;
        }

        @Override
        public int compareTo(Interval i) {
            if(this.start > i.start) return 1;
            if(this.start < i.start) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return "[" + this.start + ", " + this.end + "]";
        }
    }

    private static boolean canAttendAllMeetings(Interval[] arr) { //O(n) O(1)
        if (arr == null || arr.length == 0)
            return true;

        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++) {
            if(i + 1 < arr.length) {
                if(arr[i].end > arr[i+1].start)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Interval[] arr = { new Interval(0,5), new Interval(5,10), new Interval(15,20)};
        //Interval[] arr = { new Interval(0,30), new Interval(5,10), new Interval(15,20)};
        for (Interval i : arr)
            System.out.println(i);
        System.out.println("Anyone can attend all meting with the list of meeting times: " + canAttendAllMeetings(arr));

    }
}
