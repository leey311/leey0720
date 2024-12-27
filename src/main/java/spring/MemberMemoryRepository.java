package spring;

import java.util.*;

public class MemberMemoryRepository implements MemberRepository {
    private Map<Long, Member> store = new HashMap<>();
    private static Long num = 0L;

    @Override
    public Member save(Member mem) {
        mem.setId(++num);
        return store.put(mem.getId(), mem);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(mem -> mem.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
