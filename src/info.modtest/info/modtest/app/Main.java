package info.modtest.app;

import info.modtest.face.ThingSayer;
import java.util.ServiceLoader;
import java.nio.file.Paths;
import java.lang.module.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import net.common.HairDescriber;
import java.util.stream.Stream;


public class Main {

    private static final String TEMPLATE =
        "ThingSayer impl: %s, module name: %s, mod version: %s\n\tSomething says: %s";

    public static ModuleLayer loadModuleLayer() {
        ModuleLayer thisLayer = Main.class.getModule().getLayer();
        List<ModuleLayer> layers = new ArrayList<>();
        //        layers.add(thisLayer);
        List<Configuration> configs = new ArrayList<>();
        //        configs.add(thisLayer.configuration());

        ModuleLayer parent = ModuleLayer.boot();
        parent = thisLayer;
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        scl = Main.class.getClassLoader();
        for (File jar : Paths.get("third_party")
                 .toFile()
                 .listFiles((dir, name) -> name.endsWith(".jar"))) {
            ModuleFinder finder = ModuleFinder.of(jar.toPath());

            Configuration cf = parent
                .configuration().resolveAndBind(finder, ModuleFinder.of(), Set.of());

            configs.add(cf);
            layers.add(parent.defineModulesWithOneLoader(cf, scl));
        }

        Configuration parentConfig =
            Configuration.resolve(ModuleFinder.of(), configs, ModuleFinder.of(), Set.of());
        return ModuleLayer.defineModulesWithManyLoaders(parentConfig, layers, scl).layer();

    }

    public static void main(String[] args) {
        System.out.println("Starting (" + HairDescriber.computerHair() + ")");
        ServiceLoader<ThingSayer> sl = ServiceLoader.load(loadModuleLayer(), ThingSayer.class);
        sl.stream()
            .map(p ->
                 String.format(
                               TEMPLATE,
                               p.type(),
                               p.type().getModule().getName(),
                               p.type().getModule().getDescriptor().toNameAndVersion(),
                               p.get().sayThing()))
            .forEach(System.out::println);

        System.out.println("After modloading (" + HairDescriber.computerHair() + ")");
    }
}
