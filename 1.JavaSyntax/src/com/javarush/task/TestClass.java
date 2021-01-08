package com.javarush.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TestClass<T> {

    private T value1;
    private T value2;

    public void printValues() {
        System.out.println(value1);
        System.out.println(value2);
    }

    public static <T> TestClass<T> createAndAdd2Values(Object o1, Object o2) {
        TestClass<T> result = new TestClass<>();
        result.value1 = (T) o1;
        result.value2 = (T) o2;
        return result;
    }

    public static void main(String[] args) {
        Double d = 22.111;
        String s = "Test String";
        TestClass<Integer> test = createAndAdd2Values(d, s);
        test.printValues();
        SomeType<String> st = new SomeType();
        List<String> list = Arrays.asList("testMegaTest");
        st.test(list);
        Date yearStartTime = new Date();
        yearStartTime.setHours(0);
        yearStartTime.setMinutes(0);
        yearStartTime.setSeconds(0);

        yearStartTime.setDate(1);
        yearStartTime.setMonth(0);

    }

    public static class SomeType<T> {
        public <E> void test(Collection<E> collection) {
            for (E element : collection) {
                System.out.println(element);
            }
        }
        public void test(List<Integer> collection) {
            for (Integer element : collection) {
                System.out.println(element);
            }
        }
    }
}
