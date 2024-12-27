package spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemberMemoryRepository memberMemoryRepository;

    @BeforeEach
    void beforeach(){
        memberMemoryRepository = new MemberMemoryRepository();
        memberService = new MemberService(memberMemoryRepository);
    }

    @AfterEach
    void aftereach(){
        memberMemoryRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void 회원리스트() {
        //given
        Member member = new Member();
        member.setName("hello");
        memberService.join(member);
        //when

        //then
        assertThat(memberService.findMem().size()).isEqualTo(1);
    }

    @Test
    void findOne() {
    }
}