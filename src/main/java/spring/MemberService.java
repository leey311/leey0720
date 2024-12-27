package spring;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member mem){
        check(mem);
        memberRepository.save(mem);
        return mem.getId();
    }

    public List<Member> findMem(){return memberRepository.findAll();}

    public Optional<Member> findOne(Long memberId){return memberRepository.findById(memberId);}

    private void check(Member mem) {
        memberRepository.findByName(mem.getName()).ifPresent(
                mem1 ->{throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }
}


