package com.koko.practicespringboot.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//          First Input >> Array: [4,3 ,1 ,2,6,9,7,8,5]       sum: 8  Output: [0,2]
//          Second Input >> Array: [4 ,3 ,2 a,6 b,9,7,8,5]        sum: 8  Output: [2,3]
// O(N*N)
public class Makro {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 3, 1, 2, 6, 9, 7, 8, 5}, 8)));
    }

    public static int[] solution(int[] input, int inputSum) {
        if (input.length < 1) {
            return new int[0];
        }

        int pointerA = 0;
        int pointerB = 1;
        int sum = 0;

        while (pointerA < input.length) {
            // array out of bound
            sum += input[pointerA] + input[pointerB];

            if (sum == inputSum) {
                return new int[]{pointerA, pointerB};
            } else if (sum < inputSum) {
                pointerB++;
            } else {
                sum = 0;
                pointerB = pointerA++;
            }
        }
        return new int[0];
    }
}
