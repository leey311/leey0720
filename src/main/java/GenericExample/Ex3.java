package GenericExample;

public class Ex3 {
    public static void main(String[] args) {
        HomeAgency homeAgency = new HomeAgency();
        Home home = homeAgency.rent();
        home.turn();

        HorseAgency horseAgency = new HorseAgency();
        Horse horse = horseAgency.rent();
        horse.run();
    }
}


interface Rent<T> {
    T rent();
}

class Home {
    void turn(){
        System.out.println("turn on");
    }
}

class Horse{
    void run(){
        System.out.println("run");
    }
}

class HomeAgency implements  Rent<Home>{
    @Override
    public Home rent(){
        return new Home();
    }
}

class HorseAgency implements Rent<Horse>{
    @Override
    public Horse rent(){
        return new Horse();
    }
}