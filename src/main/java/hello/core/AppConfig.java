package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 프로젝트의 전반적인 설정을 설정 하는 곳
// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
// MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy
// MemberServiceImpl -> MemoryMemberServiceImpl
// OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy

// 리펙토링
// command + option + m
// MemoryMemberRepository -> memberRepository로 변경
// DisCount 메서드 만든 후 reuturn 값을 FixDiscountPolicy로 받기
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
        // 나는 MemberServiceImpl 인데 MemoryMemberRepository를 쓸꺼야
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
