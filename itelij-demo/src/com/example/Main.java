package com.example;

import com.example.models.Person;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello human");
        Person p = new Person("jeffrey", "ye");
        System.out.println(p.getFirstName());
    }
}
