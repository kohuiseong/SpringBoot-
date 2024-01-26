package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 프로젝트의 전반적인 설정을 설정 하는 곳
// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
// MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy
// MemberServiceImpl -> MemoryMemberServiceImpl
// OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy

// 리펙토링
// command + option + m
// MemoryMemberRepository -> memberRepository로 변경
// DisCount 메서드 만든 후 reuturn 값을 FixDiscountPolicy로 받기

// Appconfig 스프링 기반으로 개발
// @Bean이 등록 되면 자동으로 스프링 컨테이너로 등록 된다.
// ApplicationContext를 스프링 컨터이너라고 한다
// 스프링빈은  @Bean이 붙은 메서드명을 스프링 빈의 이름으로 사용 된다.
// 스프링 컨테이너는 @Configuration이 붙은 AppConfig 를 설정 정보로 사용한다. 여기서 @Bean 이라 적힌 메서드를 모두 호출 해서 스프링 컨테이너에 등록
// 기존에는 개발자가 직접 자바 코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고 스프링 컨테이너에서 스프링 빈을 찾아서 사용 하도록 변경 했다
@Configuration
public class AppConfig {

    // 싱글톤 호출
    // @Bean -> memberService -> new MemoryMemberRepository()
    // @Bean -> orderService -> new MemoryMemberRepository
    // 싱글톤은 객체가 하나만 생성이 되어야 하는데 AppConfig 클래스를 확인 하면 두 번 호출이 된다.
    // 1. call AppConfig.memberService
    // 2. call AppConfig.memberRepository
    // 3. call AppConfig.memberRepository
    // 4. call AppConfig.orderService
    // 5. call AppConfig.memberRepository
    // 결과는 총 3번 호출이 된다.
    // 스프링은 
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
        // 나는 MemberServiceImpl 인데 MemoryMemberRepository를 쓸꺼야
    }

    @Bean
   public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        // 구체화로 변경
        // FixDiscountPolicy -> RateDiscountPolicy로 변경
        return new RateDiscountPolicy();
    }
}
