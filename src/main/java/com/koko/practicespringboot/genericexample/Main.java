package com.koko.practicespringboot.genericexample;

import java.util.*;

class Student {
    String name;
}

public class Main {
    public static void main(String[] args) {
        List<String> ss = new ArrayList<>();
        ss.add("Zilla");
        ss.add("apple");
        System.out.println(ss);

        List<String> sa = new LinkedList<>();
        sa.add("Zilla");
        sa.add("apple");
        System.out.println(sa);
    }
}
