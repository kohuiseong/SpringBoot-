package hello.core.member;

// 구현체가 하나만 있으면 관례상 ServiceImpl 사용
// DIP 위반
// MemberServiceImpl은 추상화 MemberServiceImpl, 구체화 MemberService 두 개다 의존 하기 떄문에 좋지 않은 개발 이다
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
