package main.java.labs.patterns.behavioral;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountStreamStrategy implements Strategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toMap(
                        Function.identity(),
                        e -> 1,
                        Integer::sum
                ));
    }
}
