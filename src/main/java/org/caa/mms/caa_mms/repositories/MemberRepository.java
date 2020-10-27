package org.caa.mms.caa_mms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.caa.mms.caa_mms.domains.Member;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    public Optional<Member> findById(Long id);
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByPhone(String phone);
    public Optional<Member> findByWechat(String wechat);
    public Optional<Member> findByStdId(String stdId);
}
