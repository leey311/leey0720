package GenericExample;

import lombok.Getter;
import lombok.Setter;

public class Ex4 {
    static <A> Fox<A> foxing(A a){
        Fox<A> fox = new Fox<>();
        fox.setT(a);
        return fox;
    }

    public static void main(String[] args) {
        Fox<Integer> fox1 = foxing(100);
        System.out.println(fox1.getT());
    }

}

class Fox<T>{
    @Setter
    @Getter
    private T t;
}