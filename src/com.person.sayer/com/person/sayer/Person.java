package com.person.sayer;

import info.modtest.face.ThingSayer;

public class Person implements ThingSayer {
    @Override
    public String sayThing() {
        return "person saying";
    }
}
