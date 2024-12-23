package PROJECT_1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                    sayingAppController.show();
                    break;
                case "3":
                    sayingAppController.del(scanner);
                    break;
                case "4":
                    sayingAppController.modify(scanner);
                    break;
                case "5":
                    sayingAppController.bulid();
                    break;

                default: return;
            }
        }
    }
}

class SayingAppController{
    int i = 1;
    Service service = new Service();

    public void regist(Scanner scanner) {
        service.registservice(i, scanner);
        System.out.println(i++ + "번 명언이 등록 되었습니다.");
        System.out.println("파일 저장 완료");
    }

    public void show() {
        service.showservice();
    }

    public void del(Scanner scanner) {
        System.out.print("몇 번 명언을 삭제할까요?\n명령) ");
        int del = scanner.nextInt();
        scanner.nextLine();

        if (service.delservice(del)){
            System.out.println(del + "번 명언을 삭제했습니다");
            System.out.println("파일 삭제 완료");
        }else {
            System.out.println(del + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modify(Scanner scanner) {
        System.out.print("몇 번 명언을 수정할까요?\n명령) ");
        int mod = scanner.nextInt();
        scanner.nextLine();

        if (service.modifyservice(mod)){
            service.registservice(mod, scanner);
            System.out.println(mod + "번 명언을 수정했습니다");
            System.out.println("파일 수정 완료");
        }else {
            System.out.println(mod + "번 명언은 존재하지 않습니다.");
        }
    }

    public void bulid() {
        service.bulidservice();
        System.out.println("파일 갱신 완료");
    }
}

class Service{
    Saying saying;
    Repository repository;

    Service(){
        this.saying = new Saying();
        this.repository = new Repository();
    }

    public void registservice(int i, Scanner scanner) {
        System.out.print("작가) ");
        String name = scanner.nextLine();
        saying.setName(name);

        System.out.print("명언) ");
        String say = scanner.nextLine();
        saying.setSay(say);

        repository.sayingregist(i, saying);
    }

    public void showservice() {
        repository.sayingshow();

    }

    public boolean delservice(int del) {
        return repository.sayingdel(del);
    }

    public boolean modifyservice(int mod) {
        return repository.sayingmod(mod);
    }

    public void bulidservice() {
    }
}

class Repository{
    ArrayList<String> list = new ArrayList<>();
    private HashMap<Integer, String> map = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void sayingregist(int i, Saying saying){
        list.add(String.valueOf(i));
        list.add(saying.getName());
        list.add(saying.getSay());
        map.put(i, saying.toString());
        fileSave(i);
        saveLast(i);
    }

    public boolean sayingdel(int del){
        if (map.containsKey(del)){
            File file = new File( String.format("db/Saying/saing%s.json", del));
            if (file.exists()){file.delete();}
            map.remove(del);
            return true;
        }
        return false;
    }

    public boolean sayingmod(int mod){
        if (map.containsKey(mod)){
            fileSave(mod);
            return true;
        }
        return false;
    }


    public void sayingshow() {
        Stack<Map.Entry<Integer, String>> stack = new Stack<>();
        for(Map.Entry<Integer, String>entry : map.entrySet()){
            stack.push(entry);
        }
        
        while (!stack.isEmpty()){
            Map.Entry<Integer, String> entry = stack.pop();
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    private void fileSave(int key){
        String path = String.format("db/Saying/saing%s.json", key);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(path), key + map.get(key));
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveLast(int i){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/Saying/LastId.txt"))){
            writer.write(String.valueOf(i));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class Saying{
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String say;


    @Override
    public String toString(){
        return "/" + name + "/" +  say;
    }
}