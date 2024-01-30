package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다
 * 단지 DiscountPolicy 인터페이스만 의존한다
 * OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지는 알 수 없다
 * OrderServiceImpl의 생성자를 통해서 어떤 구현객체을 주입할지는 오직 외부 Appconfig 에서 결정 한다.
 * OrderServiceImpl은 이제부터 실행에만 집중 하면 된다
 */
@Component
public class OrderServiceImpl implements OrderService {

    // 멤버 정보를 받아 오는 메서드
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 할인 금액을 받아 오는 메서드(고정 할인 금액) - 추상화
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 고객 등급에 따른 할인 금액 메서드 - 구체화
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // FixDiscountPolicy, RateDiscountPolicy => DIP, OCP 위반 되지 않게 하기 위해서 코드 변경
    // OrderServiceImpl은 구체화, 추상화 클래스 모두 의존하기 때문에 DIP, OCP 위반


    // 수정자 주입
//     private MemberRepository memberRepository;
//     private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//    필드 주입
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;

    // @Autowired // 생성자가 하나 일 경우 @Autowired를 생략 해도 된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        // 할인 정보로 넘기기
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new hello.core.order.Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
