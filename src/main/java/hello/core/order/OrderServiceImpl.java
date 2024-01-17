package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.core.annotation.Order;

public class OrderServiceImpl implements OrderService {

    // 멤버 정보를 받아 오는 메서드
    private final MemberRepository memberRepository;

    // 할인 금액을 받아 오는 메서드(고정 할인 금액) - 추상화
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 고객 등급에 따른 할인 금액 메서드 - 구체화
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // FixDiscountPolicy, RateDiscountPolicy => DIP, OCP 위반 되지 않게 하기 위해서 코드 변경
    // OrderServiceImpl은 구체화, 추상화 클래스 모두 의존하기 때문에 DIP, OCP 위반
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        // 할인 정보로 넘기기
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
