package hello.world;

import io.github.classgraph.ClassGraph;

public class NativeImageInit {
    static {
        new ClassGraph()
                .disableModuleScanning() // added for GraalVM
                // .disableDirScanning() // added for GraalVM
                // .disableNestedJarScanning() // added for GraalVM
                // .disableRuntimeInvisibleAnnotations() // added for GraalVM
                .addClassLoader(ClassLoader.getSystemClassLoader())
                .enableAllInfo()
                .acceptPackages("hello.world")
                .scan();
    }
}
