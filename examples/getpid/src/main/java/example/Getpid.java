package example;

import com.kenai.jffi.Platform;
import jnr.invoke.*;

import java.lang.invoke.MethodHandle;

public class Getpid {
    static {
        System.setProperty("jnr.invoke.compile.dump", "true");
    }

    public static void main(String[] args) throws Throwable {

        Signature signature = Signature.getSignature(ResultType.primitive(NativeType.ULONG, long.class), new ParameterType[0]);

        Library libc = Library.open(Platform.getPlatform().mapLibraryName("c"), Library.LAZY | Library.LOCAL);

        MethodHandle mh = Native.getMethodHandle(signature, libc.getFunction("getpid"));
        System.out.println("pid = " + (long) mh.invokeExact());
    }
}
