package org.ucsccaa.mms;

import java.util.List;
import java.util.Optional;

import org.ucsccaa.mms.Member;
import org.ucsccaa.mms.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Long addMember(Member member) {
        return memberRepository.save(member).getId();
    }

    public Optional<Member> updateMember(Member member) {
        Long id = member.getId();
        Optional<Member> oldMember = memberRepository.findById(id);
        if (oldMember.isPresent()) {
            return Optional.of(memberRepository.save(member));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Member> getMember(Long id) {
        if (id == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        return memberRepository.findById(id);
    }

    public Boolean deleteMember(Long id) {
        if (id == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Member> getMemberByEmail(String email) {
        if (email == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> getMemberByPhone(String phone) {
        if (phone == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        return memberRepository.findByPhone(phone);
    }

    public Optional<Member> getMemberByWechat(String wechat) {
        if (wechat == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        return memberRepository.findByWechat(wechat);
    }

    public Optional<Member> getMemberByStdId(String stdId) {
        if (stdId == null) {
            throw new RuntimeException("argument cannot be NULL");
        }
        return memberRepository.findByStdId(stdId);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
