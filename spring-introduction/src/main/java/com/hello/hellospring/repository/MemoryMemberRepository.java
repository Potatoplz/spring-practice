package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 회원 리포지토리 메모리 구현체
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository  {

    // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static Map<Long, Member> store = new HashMap<>(); // static은 instance와 관계없이 class 레벨에 붙는다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member; // 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null을 그대로 반환하는 대신 Optional로 감싸서 반환하면, 화면에서 처리할 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //맵을 루프돌면서 하나 찾아서 반환. 끝까지 돌렸는데 없으면 옵셔널에 null이 포함돼서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    
    // 저장은 Map으로 하고, 반환은 List로 함
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}