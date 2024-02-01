package hello.core.autowired;

import hello.core.member.Member;
//import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean = " + noBean1);
        }

        // null 호출 -> 자동 주입 할 대상이 없으면 null 이 입력된다.
//        @Autowired
//        public void setNoBean2(@Nullable Member noBean2) {
//            System.out.println("noBean = " + noBean2);
//        }

        // Optional.empty 호출 -> 자동 주입 할 대상이 없으면
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean = " + noBean3);
        }
    }
}
