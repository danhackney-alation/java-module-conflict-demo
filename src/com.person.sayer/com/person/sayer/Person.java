package com.person.sayer;

import info.modtest.face.ThingSayer;
import net.common.HairDescriber;

public class Person implements ThingSayer {
    @Override
    public String sayThing() {
        return "person saying (" + HairDescriber.curliness() + ")";
    }
}
