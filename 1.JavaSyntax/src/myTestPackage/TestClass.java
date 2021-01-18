package myTestPackage;

import java.util.*;

public class TestClass<T> {

    private T value1;
    private T value2;

    public void printValues() {
        System.out.println(value1);
        System.out.println(value2);
    }

    public static void testMethod(){
        SomeStaticInnerClass someStaticInnerClass = new SomeStaticInnerClass();

    }

    public static <T> TestClass<T> createAndAdd2Values(Object o1, Object o2) {

        TestClass<Integer> testClass = new TestClass<>();
        TestClass<Integer>.SomeType<Integer> someType = testClass.new SomeType<Integer>();

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
       // SomeType<String> st = new SomeType();
        List<String> list = Arrays.asList("testMegaTest", "best");
        //st.test(list);
        Date yearStartTime = new Date();
        yearStartTime.setHours(0);
        yearStartTime.setMinutes(0);
        yearStartTime.setSeconds(0);

        yearStartTime.setDate(1);
        yearStartTime.setMonth(0);

//        List<String> list2  = new ArrayList<>();
//        final List<String> list3 = list2;
//        list3.add("");
//        System.out.println();
//        list3 = new ArrayList<>();
//        list2 = new ArrayList<>();
        Collections.sort(list, Collections.reverseOrder());
        TestClass.SomeType someType = new TestClass().new SomeType();
        System.out.println(someType.testPrivateInt);
        System.out.println(someType.testPrivateInt);
    }

    public class SomeType<T> {
        private int testPrivateInt = 100;
       // private  int testPrivateInt = 100;

        public <E> void test(Collection<E> collection) {
            for (E element : collection) {
                System.out.println(element);
                System.out.println(value1);
            }
        }
        public void test(List<Integer> collection) {
            for (Integer element : collection) {
                System.out.println(element);
            }
        }
    }

    public static class SomeStaticInnerClass{

    }
}
