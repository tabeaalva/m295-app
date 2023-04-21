package ch.ilv.m295.demoapp.department.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface MemberRepository extends JpaRepository<Member, Long> {
        List<Member> findByOrderByNameAsc();

}
