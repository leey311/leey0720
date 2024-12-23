package GenericExample;

public class Ex2 {
    public static void main(String[] args) {
        Pro<Car, Integer> pro = new Pro<>();

        Car car2 = new Car();

        pro.setKind(new Car());
        pro.setModel(100);

        Car car1 = pro.getKind();
        int a = pro.getModel();

        System.out.println(car1);
        System.out.println(car2);

    }

}


class Pro<T, S>{
    T kind;
    S model;

    T getKind(){return kind;}
    S getModel(){return model;}

    void setKind(T kind){this.kind = kind;}
    void setModel(S model){this.model = model;}
}

class Car{}