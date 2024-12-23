package ThreadExample;

public class sdg {
    public static void main(String[] args) {
        a a = new a();
        System.out.println(a.a);
        System.out.println(a);

        a b = a;

        System.out.println(b.a);
        System.out.println(b);

        a c = new a();

        System.out.println(c);

    }
}


class a{
    int a = 10;
}