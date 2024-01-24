package hello.core.singleton;

public class SingletoneService {

    // static 이 있어서 컨테이너에 하나만 올라가게 된다.
    private static final SingletoneService instance = new SingletoneService();

    public static SingletoneService getInstance() {
        return instance;
    }

    private SingletoneService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
