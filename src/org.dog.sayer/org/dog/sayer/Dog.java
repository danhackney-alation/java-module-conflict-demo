package org.dog.sayer;

import info.modtest.face.ThingSayer;
import net.common.HairDescriber;

public class Dog implements ThingSayer {
    public String sayThing() {
        return "woof (" + HairDescriber.fluffiness() + ")";
    }
}
