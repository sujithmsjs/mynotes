package com.suji.coll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NewMain {

    public static void main(String[] args) {

        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // To boxed array
        IntStream is = Arrays.stream(data);
        Stream<Integer> s = is.boxed();
        Integer[] a = s.toArray(Integer[]::new);

        // To boxed list
        List<Integer> you = Arrays.stream(data).boxed().collect(Collectors.toList());
        List<Integer> like = IntStream.of(data).boxed().collect(Collectors.toList());

        System.out.println(you);

    }

}
