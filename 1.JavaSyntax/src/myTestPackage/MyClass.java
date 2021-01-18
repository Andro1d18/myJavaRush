package myTestPackage;

public class MyClass {

    public static void main(String[] args) {

        Frog frog = new Frog("квакушка");

        Thread thread1 = new Thread(frog);
        thread1.setName("1");
        thread1.start();

        Thread thread2 = new Thread(frog);
        thread2.setName("2");
        thread2.start();
    }

    static class Frog implements Runnable {
        String name;

        public Frog(String name) {
            this.name = name;
        }
        @Override
        synchronized public void run() {

            for (int i = 0; i < 10; i++) {
                System.out.println("Лягушка " + name + " из болота №" + Thread.currentThread().getName() + " говорит КВАК" + i);


                try {
                    notify();//"освобождаем" ранее заблокированный поток
                    wait();//"блокируем" текущий поток
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
