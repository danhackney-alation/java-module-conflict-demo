package com.alation.modtest.app;

import com.alation.modtest.face.ThingSayer;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting");
        ServiceLoader<ThingSayer> sl = ServiceLoader.load(ThingSayer.class);
        for (ThingSayer ts : sl) {
            System.out.println("Something says: " + ts.sayThing());
        }
        System.out.println("After modloading");
    }
}
