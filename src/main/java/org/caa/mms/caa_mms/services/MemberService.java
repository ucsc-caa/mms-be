package org.caa.mms.caa_mms.services;

import java.util.List;
import java.util.Optional;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.repositories.MemberRepository;
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
        Optional<Member> oldMember = memberRepository.findById(id);
        if(oldMember.isPresent()){
            member.setId(id);
            return Optional.ofNullable(memberRepository.save(member));
        }
        else{
            return Optional.empty();
        }
    }

    public Optional<Member> getMember(Long id){
        return memberRepository.findById(id);
    }

    public boolean deleteMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            memberRepository.delete(member.get());
            return true;
        }
        else{
            return false;
        }
    }
    public Optional<Member> getMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }
    public Optional<Member> getMemberByPhone(String phone){
        return memberRepository.findByPhone(phone);
    }
    public Optional<Member> getMemberByWechat(String wechat){
        return memberRepository.findByWechat(wechat);
    }
    public Optional<Member> getMemberByStdId(String stdId){
        return memberRepository.findByStdId(stdId);
    }
    public List<Member> getAll(){
        return memberRepository.findAll();
    }
}
