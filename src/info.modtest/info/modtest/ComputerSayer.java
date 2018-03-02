package info.modtest;

import net.common.HairDescriber;
import info.modtest.face.ThingSayer;

public class ComputerSayer implements ThingSayer {
    @Override
    public String sayThing() {
        return "computer saying (" + HairDescriber.computerHair() + ")";
    }
}
