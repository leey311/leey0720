package ThreadExample;

import java.awt.*;

public class Ex1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for(int i = 0; i<5; i++){
            toolkit.beep();
            try{Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }}}});
        thread.start();

        for (int i =0; i<5; i++){
            System.out.println("beep");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }
