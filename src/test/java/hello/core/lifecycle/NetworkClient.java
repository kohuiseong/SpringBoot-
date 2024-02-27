package hello.core.lifecycle;

// javax로 시작 하면 다른 컨테이너를 쓰더라도 그대로 적용 된다.
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url : "  + url);
        connect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 연결 끊어짐
    public void disconnect() {
        System.out.println("close : " + url);
    }


    // 의존관계 주입이 끝나면 호출 한다
    /*PostConstruct, PreDestory
    최신 스프링에서 가장 권장하는 방법
    애노테이션 하마나 붙이면 되므로 매우 편리하다
    패키지에서 잘 보면 java.annotation.PostConstruct이다. 스프링에 종속적인 기술이 아니라 JSR-250 라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서 동작한다.
    컴포넌트 스캔과 잘 어울린다.
    유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용하자
     */

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 메세지 호출");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
