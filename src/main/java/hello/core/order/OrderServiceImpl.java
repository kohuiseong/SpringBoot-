package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.core.annotation.Order;

public class OrderServiceImpl implements OrderService {

    // 멤버 정보를 받아 오는 메서드
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 할인 금액을 받아 오는 메서드
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        // 할인 정보로 넘기기
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
