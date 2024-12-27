package spring;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member mem);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
