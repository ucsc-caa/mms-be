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
    private MemberRepository repo;

    public Optional<Member> addMember(Member member){
        return Optional.ofNullable(repo.save(member));
    }
    public Optional<Member> getMember(Long id){
        return repo.findById(id);
    }

    public boolean deleteMember(Long id){
        Optional<Member> member = repo.findById(id);
        if(member.isPresent()){
            repo.delete(member.get());
            return true;
        }
        else{
            return false;
        }
    }
    public Optional<Member> getMemberByEmail(String email){
        return repo.findByEmail(email);
    }
    public Optional<Member> getMemberByPhone(String phone){
        return repo.findByPhone(phone);
    }
    public Optional<Member> getMemberByWechat(String wechat){
        return repo.findByWechat(wechat);
    }
    public Optional<Member> getMemberByStdId(String stdId){
        return repo.findByStdId(stdId);
    }
    public List<Member> getAll(){
        return repo.findAll();
    }
}
