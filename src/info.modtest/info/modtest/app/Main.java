package info.modtest.app;

import info.modtest.face.ThingSayer;
import java.util.ServiceLoader;
import java.nio.file.Paths;
import java.lang.module.*;
import java.util.Set;
import net.common.HairDescriber;


public class Main {
    public static ModuleLayer loadModuleLayer() {
        ModuleFinder finder = ModuleFinder.of(Paths.get("third_party"));

        ModuleLayer parent = ModuleLayer.boot();

        Configuration cf = parent
            .configuration().resolveAndBind(finder, ModuleFinder.of(), Set.of());

        ClassLoader scl = ClassLoader.getSystemClassLoader();

        return parent.defineModulesWithOneLoader(cf, scl);

    }

    public static void main(String[] args) {
        System.out.println("Starting (" + HairDescriber.computerHair() + ")");
        ServiceLoader<ThingSayer> sl = ServiceLoader.load(loadModuleLayer(), ThingSayer.class);
        for (ThingSayer ts : sl) {
            System.out.println("Something says: " + ts.sayThing());
        }
        System.out.println("After modloading (" + HairDescriber.computerHair() + ")");
    }
}
