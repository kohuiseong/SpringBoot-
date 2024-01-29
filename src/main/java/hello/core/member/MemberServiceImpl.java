package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 구현체가 하나만 있으면 관례상 ServiceImpl 사용
 DIP 위반
 MemberServiceImpl은 추상화 MemberServiceImpl, 구체화 MemberService 두 개다 의존 하기 떄문에 좋지 않은 개발 이다
 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존 하지 않는다
 * 단지 MemberRepository 인터페이스만 의존한다
 * MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지는 알 수 없다
 * MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입 할지는 오직 외부 에서 결정 된다.
 * MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중
 * 객체의 생성과 연결은 AppConfig가 담당
 * DIP 완성 -> MemoryServiceImpl 은 MemberRepository인 추상에만 의존 하면 된다. 이제 구체 클래스를 몰라도 된다.
 * 관심사의 분리 -> 객체를 생성 하고 연결하는 역할과 실행하는 역할이 명확히 분리 되었다
 */
@Component
public class MemberServiceImpl implements MemberService{

    // 추상화 에만 의존 한다
    private final MemberRepository memberRepository;

    @Autowired // = ac.getBean(MemberRepository.class), ComponentScan 을 사용하면 자동으로 빈으로 등록 되는데 의존 관계는 주입이 되지 않으므로 Autowired 사용
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
