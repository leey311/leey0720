package PROJECT_1;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class SayingApp {
    public static void main(String[] args) {
        SayingAppRun sayingAppRun = new SayingAppRun();
        System.out.println("==명언 앱==");
        sayingAppRun.run();
    }
}

class SayingAppRun{
    Scanner scanner = new Scanner(System.in);
    SayingAppController sayingAppController = new SayingAppController();

    public void run() {

        while (true) {
            System.out.print("1. 등록 ㅣ 2. 목록 ㅣ 3. 삭제 ㅣ 4. 수정 ㅣ 5. 빌드 ㅣ 6. 종료 \n명령) ");
            String start = scanner.nextLine();
            switch (start) {
                case "1":
                    sayingAppController.regist(scanner);
                    break;
                case "2":
                    sayingAppController.list();
                    break;
                case "3":
                    sayingAppController.del(scanner);
                    break;
                case "4":
                    sayingAppController.mod(scanner);
                    break;
                default: return;
            }
        }
    }
}

class SayingAppController{
    private final Service service = new Service();
    private String author;
    private String say;

    private void qwe(Scanner scanner){
        System.out.print("작가) ");
        author = scanner.nextLine();

        System.out.print("명언) ");
        say = scanner.nextLine();
    }

    public void regist(Scanner scanner) {
        qwe(scanner);
        int id = service.registSaying(author, say);
        System.out.println(id + " 번 명언이 등록 되었습니다.");
    }

    public void list() {
        for (int i = 0; i<service.showList().size(); i++){
            System.out.println(service.showList().get(i).getId() + " / " + service.showList().get(i).getAuthor() + " / " + service.showList().get(i).getSay());
        }
    }

    public void del(Scanner scanner) {
        System.out.print("삭제) ");
        int del = scanner.nextInt();
        scanner.nextLine();

        if(service.checkSaying(del)){
            service.delSaying(del);
            System.out.println(del + " 번 명언을 삭제했습니다.");
        }else {
            System.out.println(del + " 번 명언은 존재하지 않습니다.");
        }
    }

    public void mod(Scanner scanner) {
        System.out.print("수정) ");
        int mod = scanner.nextInt();
        scanner.nextLine();

        if (service.checkSaying(mod)){
            qwe(scanner);
            service.modSaying(mod, author ,say);
        }else {
            System.out.println(mod + "번 명언은 존재하지 않습니다.");
        }
    }
}

class Service{
    Repository repository= new Repository();

    public int registSaying(String author, String say){
        Saying saying = new Saying();
        saying.setAuthor(author);
        saying.setSay(say);
        return repository.save(saying);
    }

    public List<Saying> showList() {
        return repository.findAll();
    }

    public boolean checkSaying(int i) {
        return repository.check(i);
    }

    public void delSaying(int del) {
        repository.delMap(del);
    }

    public void modSaying(int mod, String author, String say) {
        repository.modMap(mod, author ,say);
    }
}

class Repository{
    Map<Integer, Saying> map = new HashMap<>();
    int id = 0;

    int save(Saying saying) {
        saying.setId(++id);
        map.put(saying.getId(), saying);
        return saying.getId();
    }

    List<Saying> findAll(){
        return new ArrayList<>(map.values());
    }

    public void delMap(int del) {map.remove(del);}

    public boolean check(int i){
        if (map.containsKey(i)){
            return true;
        }else return false;
    }

    public void modMap(int mod, String author, String say) {
        Saying saying = map.get(mod);
        saying.setId(mod);
        saying.setAuthor(author);
        saying.setSay(say);
        map.put(mod, saying);
    }
}

@Setter
@Getter
class Saying{
    private int id;
    private String author;
    private String say;
}