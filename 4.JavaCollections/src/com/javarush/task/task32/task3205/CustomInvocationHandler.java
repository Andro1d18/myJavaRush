package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class CustomInvocationHandler implements InvocationHandler {

    SomeInterfaceWithMethods someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods siwm){
        this.someInterfaceWithMethods = siwm;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " in");
        Object o = method.invoke(someInterfaceWithMethods, args); //Жесть какая то - как так результат выполения метода записывается в o?..
        System.out.println(method.getName() + " out");

        return o;
    }
}
