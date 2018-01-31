# Java conflicting module test

This is a test to see how Java 9 deals with modules that define conflicting
packages. The idea is that `info.modtest.app.Main` will try loading different
service implementations from automatic modules from packages in `third_party`.
However, `info.modtest`, `com.person.sayer`, and `org.dog.sayer` all provide
different (and incompatible) implementations of `net.common.HairDescriber`, so
this implementation checks whether they can all be loaded and run successfully.
