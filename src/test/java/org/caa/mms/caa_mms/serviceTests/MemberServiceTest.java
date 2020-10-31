package org.caa.mms.caa_mms.serviceTests;

import java.util.List;
import java.util.Optional;

import org.caa.mms.caa_mms.domains.Member;
import org.caa.mms.caa_mms.services.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService service;

    @Test
    public void addMemberTest(){
        Member mockMember = new Member();
        mockMember.setStatus("mock");
        Member receivedMember = service.addMember(mockMember).get();
        Assert.assertEquals(receivedMember.getStatus(),mockMember.getStatus());
    }

    @Test
    public void deleteMemberTest(){
        Member mockMember = new Member();
        mockMember.setStatus("mock");
        long id = service.addMember(mockMember).get().getId();
        if(service.deleteMember(id)){
            Assert.assertFalse(service.getMember(id).isPresent());
        }
        else{
            Assert.assertEquals(1,0);
        }
    }

    @Test
    public void getMemberByEmailTest(){
        Member mockMember = new Member();
        mockMember.setEmail("mock");
        service.addMember(mockMember);
        Member receivedMember = service.getMemberByEmail("mock").get();
        Assert.assertEquals(receivedMember.getEmail(), mockMember.getEmail());
    }

    @Test
    public void getMemberByPhoneTest(){
        Member mockMember = new Member();
        mockMember.setPhone("mock");
        service.addMember(mockMember);
        Member receivedMember = service.getMemberByPhone("mock").get();
        Assert.assertEquals(receivedMember.getPhone(), mockMember.getPhone());
    }

    @Test
    public void getMemberByWechatTest(){
        Member mockMember = new Member();
        mockMember.setWechat("mock");
        service.addMember(mockMember);
        Member receivedMember = service.getMemberByWechat("mock").get();
        Assert.assertEquals(receivedMember.getWechat(), mockMember.getWechat());
    }

    @Test
    public void getMemberByStdIdTest(){
        Member mockMember = new Member();
        mockMember.setStdId("mock");
        service.addMember(mockMember);
        Member receivedMember = service.getMemberByStdId("mock").get();
        Assert.assertEquals(receivedMember.getStdId(), mockMember.getStdId());
    }
}
