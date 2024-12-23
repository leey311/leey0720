package ThreadExample;

public class Ex2 {
    public static void main(String[] args) {
        Thread mainthread = Thread.currentThread();
        System.out.println(mainthread.getName());

        for (int i = 0; i<3; i++){
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    System.out.println(getName());
                }
            };
            thread1.setName("he" + i);
            thread1.start();
        }
    }
}
