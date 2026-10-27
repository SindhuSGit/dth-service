package com.sample;

public class Calculator {
    private static int add(int a, int b) {
        return a + b;
    }

        private static int subtract(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
       int result = add(5, 5);
       System.out.println(result);
        
    }
    
}
