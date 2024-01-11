package hello.core.member;

public interface MemberRepository {

    // Member 회원 저장 메서드
    void save(Member member);

    // 회원 아이디로 찾는 메서드
    Member findById(Long memberId);
}
