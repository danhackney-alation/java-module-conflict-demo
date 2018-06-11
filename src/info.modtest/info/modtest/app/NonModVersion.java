package info.modtest.app;

import java.util.ServiceLoader;
import net.common.HairDescriber;
import info.modtest.face.ThingSayer;

/**
 * A version which does not use module loading. If run with all jars in the
 * classpath, it will fail with a {@code NoSuchMethodError} in the call to
 * {@code sayThing()} for either {@code com.person.sayer.Person} or {@code
 * org.dog.sayer.Dog}.
 */
public class NonModVersion {
    private static final String TEMPLATE =
        "ThingSayer impl: %s, module name: %s\n\tSomething says: %s";


    public static void main(String[] args) {
        System.out.println("Starting (" + HairDescriber.computerHair() + ")");
        ServiceLoader<ThingSayer> sl = ServiceLoader.load(ThingSayer.class);
        sl.stream()
            .map(p ->
                 String.format(
                               TEMPLATE,
                               p.type(),
                               p.type().getModule().getName(),
                               p.get().sayThing()))
            .forEach(System.out::println);

        System.out.println("After modloading (" + HairDescriber.computerHair() + ")");
    }
}
