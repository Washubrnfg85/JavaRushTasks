package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods interfaceWithMethods;

    public CustomInvocationHandler (SomeInterfaceWithMethods interfaceWithMethodsImpl) {
        this.interfaceWithMethods = interfaceWithMethodsImpl;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        method.invoke(interfaceWithMethods, args);
        System.out.println(method.getName() + " out");
        return method.invoke(interfaceWithMethods, args);
    }
}
