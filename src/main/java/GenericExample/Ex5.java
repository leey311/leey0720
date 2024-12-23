package GenericExample;

public class Ex5 {
    static void re1(App<?> app){
        System.out.println(app.t.getClass().getSimpleName() + "regist1");
    }

    public static void main(String[] args) {
        re1(new App<Person>(new Person()));
    }
}
class App<T>{
    T t;
    App(T t){
        this.t =t;
    }
}

class Person{}
class Worker extends  Person{}
class Student extends  Person{}
class Mstdent extends Student{}
class Hstdent extends Student{}

