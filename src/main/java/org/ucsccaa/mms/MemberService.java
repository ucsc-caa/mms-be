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

    public Optional<Member> addMember(Member member){
        return Optional.ofNullable(memberRepository.save(member));
    }

    public Optional<Member> updateMember(Member member, Long id){
        if(id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        Optional<Member> oldMember = memberRepository.findById(id);
        if(oldMember.isPresent()) {
            member.setId(id);
            return Optional.ofNullable(memberRepository.save(member));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Member> getMember(Long id) {
        if(id == null) {
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.findById(id);
    }

    public void deleteMember(Long id) {
        if(id == null) {
            throw new RuntimeException("invalid argument");
        }
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            memberRepository.deleteById(id);
        }
    }

    public Optional<Member> getMemberByEmail(String email) {
        if(email == null) {
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> getMemberByPhone(String phone) {
        if(phone == null) {
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.findByPhone(phone);
    }

    public Optional<Member> getMemberByWechat(String wechat) {
        if(wechat == null) {
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.findByWechat(wechat);
    }

    public Optional<Member> getMemberByStdId(String stdId) {
        if(stdId == null) {
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.findByStdId(stdId);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
