package myTestPackage;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        String[] stringLists = new String[1];  //  (1)
//
//        Object[] objects = stringLists;  //  (3)
//        objects[0] = 1;  //  (4)
//        String s = stringLists[0];  //  (5)

//        Box<String> stringBox = new Box<>();
//        Box rawBox = stringBox;
//        rawBox.set(8);  // warning: unchecked invocation to set(T)
        SomeType st = new SomeType();
        List<String> list = Arrays.asList("test");
        st.test(list);
        List<Number> list1 = new ArrayList<>();
        list1.add( 5);
        System.out.println(list1.get(0).getClass());
        Number[] integers = new Number[5];
        integers[0] = 55;
        System.out.println(integers[0].getClass());
        System.out.println();
        System.out.println(60 & 7);
    }

//    public static class Box<T> {
//        public void set(T t) { /* ... */ }
//        // ...
//    }

    public static class SomeType<T> {
        public <E> void test(Collection<E> collection) {
            for (E element : collection) {
                System.out.println(element);
                System.out.println("coll list");
            }
        }
//        public void test(List<Integer> collection) {
//            for (Integer element : collection) {
//                System.out.println(element);
//                System.out.println("list<integer>");
//            }
//        }
    }
}