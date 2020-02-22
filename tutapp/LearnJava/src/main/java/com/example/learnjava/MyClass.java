package com.example.learnjava;

public class MyClass {

    //This is entry point
    public static void main(String[] args) {
        String word = "WORDS O MORE";

        if (word.contains("OR")) {
            System.out.println("Here");
        }
        String[] arr = word.split(" ");
        System.out.println(arr[1]);

    }
}

