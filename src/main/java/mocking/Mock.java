package mocking;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mock {

    static class Call {
        Method method;
        Object[] args;

        Call(Method method, Object[] args) {
            this.method = method;
            this.args = args;
        }
    }

    private static Map<Object, Call> mockInteractions = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> clazz) {

        Class<?>[] classes = new Class<?>[] {clazz};

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), classes, (proxy, method, args) -> {
            if(method.getName().equals("hashCode")) {
                return System.identityHashCode(proxy);
            }

            mockInteractions.put(proxy, new Call(method, args));
            return null;
        });
    }

    public static <T> T verify(T mock) {
        System.out.println(mockInteractions);
        return mock;
    }
}
