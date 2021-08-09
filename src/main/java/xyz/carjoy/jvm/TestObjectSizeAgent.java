package xyz.carjoy.jvm;

import java.lang.instrument.Instrumentation;

public class TestObjectSizeAgent {
    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation _inst){
        inst = _inst;
    }

    public static long sizeOf (Object o) {
        return inst.getObjectSize(0);
    }


}
