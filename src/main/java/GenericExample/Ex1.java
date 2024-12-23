package GenericExample;

public class Ex1 {
    public static void main(String[] args) {

        Box<String> box1 = new Box<>();
        box1.content = "hello";
        System.out.println(box1.content);

        Box<Integer> box2 = new Box<>();
        box2.content = 100;
        System.out.println(box2.content);

    }
}


class Box<T>{
    T content;
}