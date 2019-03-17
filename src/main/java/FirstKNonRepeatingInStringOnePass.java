/*
 * For a giving string of alphabets, find first k non-repeating characters in single pass of string.
 * Example given str = ABCDBAGHCHFAC and k = 3, the output should be D G F
 */

package main.java;
import java.util.*;

public class FirstKNonRepeatingInStringOnePass {

    public static void main(String[] args) {
        String input = "ABCDBAGHCHFAC"; //"ABXCDBAGHCHFAC";
        int k = 3;
        System.out.println("The first k non-repeating characters in the given string are: " + firstKNonRepeating(input, k).toString());
    }

    static class Pair{
        int count, lastIndex;
        public Pair(int count, int lastIndex) {
            this.count = count;
            this.lastIndex = lastIndex;
        }
    }

    private static List<Character> firstKNonRepeating(String input, int k) {
        if (input == null || input.isEmpty())
            return Collections.emptyList();

        List<Character> result = new ArrayList<>(3);
        Map<Character, Pair> map = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!map.containsKey(c))
                map.put(c, new Pair(1, i));
            else {
                Pair pair = map.get(c);
                pair.count++;
                pair.lastIndex = i;
                map.put(c, pair);
            }
        }

        //create a priority q for k items
        //scan the map (max of 26) and insert index of K elements from map into priority queue where count = 1
        int heapSize = k;
        PriorityQueue<Integer> pq = new PriorityQueue<>(heapSize);

        for(Map.Entry<Character, Pair> entry : map.entrySet()) {
            int count = entry.getValue().count;
            int index = entry.getValue().lastIndex;

            if(count == 1) {
                if(--heapSize >= 0) {
                    pq.add(index);
                }
                else {
                    if(index < pq.peek()) {
                        pq.poll();
                        pq.add(index);
                    }
                }
            }
        }

        while(!pq.isEmpty()) {
            int i = pq.poll();
            result.add(input.charAt(i));
        }

        return result;
    }
}
