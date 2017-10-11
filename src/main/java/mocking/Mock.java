package mocking;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mock {


    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> clazz) {

        Class<?>[] classes = new Class<?>[] {clazz};

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), classes, (proxy, method, args) -> {
            System.out.println("In Proxy");
            return null;
        });
    }

    public static <T> T verify(T mock) {
        return mock;
    }
}
