package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ 어노테이션이 붙은 것들을 자동으로 스프링 빈에 등록 해준다.
// () 안에 있는 것은 Configuration 클래스를 제외 하고 스프링 빈으로 등록 해주는 명령어
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
