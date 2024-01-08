package com.koko.practicespringboot.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeJavaStream {
    public static void main(String[] args) {
        String s = "i love booboomyoheinthu";
        String[] split = s.replace(" ", "").split("");

        Map<String, List<String>> collect = Arrays.stream(split)
                .collect(Collectors.groupingBy(s1 -> s1));
        System.out.println(collect);

        // count
        Map<String, Long> collect1 = Arrays.stream(split)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect1);

        // find duplicate values
        List<String> dupChars = Arrays.stream(split)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(dupChars);

        // find not duplicated values
        List<String> notDup = Arrays.stream(split)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(notDup);

        String firstUniqueElement = Arrays.stream(split)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse("notfound");
        System.out.println(firstUniqueElement);

        int[] numbers = {5, 9, 11, 8, 2, 21, 1};

        Integer integers = Arrays.stream(numbers).boxed()
                .sorted()
                .skip(1)
                .findFirst().orElse(0);

        System.out.println(integers);

        String[] strArray = {"java", "boot", "springboot", "microservices", "kubernetes + springboot"};

        String longestStr = Arrays.stream(strArray)
                .reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
                .orElse("not found");
        System.out.println(longestStr);

        List<String> strings = Arrays.stream(numbers)
                .boxed()
                .map(n -> n + "")
                .filter(f -> f.startsWith("1"))
                .toList();
        System.out.println(strings);

        String join = String.join("-", strArray);
        System.out.println(join);

        IntStream.rangeClosed(0, 100)
                .filter(i -> i % 2 == 0)
                .skip(1)
                .limit(10)
                .forEach(i -> System.out.println("range closed: " + i));
    }
}
