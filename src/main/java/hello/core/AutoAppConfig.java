package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ 어노테이션이 붙은 것들을 자동으로 스프링 빈에 등록 해준다.
// () 안에 있는 것은 Configuration 클래스를 제외 하고 스프링 빈으로 등록 해주는 명령어
// 권장 하는 방법
// 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
@ComponentScan (
        basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치를 지정, 이 패키지를 포함해서 하위 클래스 까지 찾아준다.hello.core -> member 위치 지정 할 수 있다.
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
