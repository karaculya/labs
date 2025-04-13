package main.java.labs.patterns.behavioral;

import java.util.HashMap;
import java.util.Map;

public class CountIteratorStrategy implements Strategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = 0;
            for (int i : array) {
                if (i == num) count++;
            }
            map.put(num, count);
        }
        return map;
    }
}
