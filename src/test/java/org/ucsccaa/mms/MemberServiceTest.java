package org.ucsccaa.mms;

import org.junit.Before;
import org.ucsccaa.mms.Member;
import org.ucsccaa.mms.MemberRepository;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;
    private final Member expectedMember = new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test");
    private final List<Member> expectedMembers = new ArrayList(){{add(new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"));}};

    @Before
    public void configure() {
        when(memberRepository.save(eq(expectedMember))).thenReturn(expectedMember);
        when(memberRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedMember));
        when(memberRepository.findByEmail("test")).thenReturn(java.util.Optional.of(expectedMember));
        when(memberRepository.findByPhone("test")).thenReturn(java.util.Optional.of(expectedMember));
        when(memberRepository.findByStdId("test")).thenReturn(java.util.Optional.of(expectedMember));
        when(memberRepository.findByWechat("test")).thenReturn(java.util.Optional.of(expectedMember));
        when(memberRepository.findAll()).thenReturn(expectedMembers);
    }

    public MemberService getMemberService() {
        return memberService;
    }

    @Test
    public void testAddMember() {
        Long id = memberService.addMember(new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test")).get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMember_exception() {
        when(memberRepository.save(eq(expectedMember))).thenThrow(new RuntimeException());
        memberService.addMember(new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"));
    }

    @Test
    public void testUpdateMember() {
        Long expectedId = memberService.addMember(expectedMember).get().getId();
        Long id = memberService.updateMember(expectedMember, 1L).get().getId();
        Assert.assertEquals(expectedId,id);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateMember_invalidArgument() {
        memberService.updateMember(new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"), null);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateMember_exception() {
        when(memberRepository.save(eq(expectedMember))).thenThrow(new RuntimeException());
        memberService.updateMember(new Member(1L,"test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test","test"), 1L);
    }

    @Test
    public void testGetMember() {
        Long id = memberService.getMember(1L).get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMember_invalidArgument() {
        memberService.getMember(null);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMember_exception() {
        when(memberRepository.findById(1L)).thenThrow(new RuntimeException());
        memberService.getMember(1L);
    }

    @Test
    public void testGetMemberByEmail() {
        Long id = memberService.getMemberByEmail("test").get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByEmail_invalidArgument() {
        memberService.getMemberByEmail(null);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByEmail_exception() {
        when(memberRepository.findByEmail("test")).thenThrow(new RuntimeException());
        memberService.getMemberByEmail("test");
    }

    @Test
    public void testGetMemberByPhone() {
        Long id = memberService.getMemberByPhone("test").get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByPhone_invalidArgument() {
        memberService.getMemberByPhone(null);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByPhone_exception() {
        when(memberRepository.findByPhone("test")).thenThrow(new RuntimeException());
        memberService.getMemberByPhone("test");
    }

    @Test
    public void testGetMemberByWechat() {
        Long id = memberService.getMemberByWechat("test").get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByWechat_invalidArgument() {
        memberService.getMemberByWechat(null);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByWechat_exception() {
        when(memberRepository.findByWechat("test")).thenThrow(new RuntimeException());
        memberService.getMemberByWechat("test");
    }

    @Test
    public void testGetMemberByStdId() {
        Long id = memberService.getMemberByStdId("test").get().getId();
        Assert.assertEquals(expectedMember.getId(), id);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByStdId_invalidArgument() {
        memberService.getMemberByStdId(null);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMemberByStdId_exception() {
        when(memberRepository.findByStdId("test")).thenThrow(new RuntimeException());
        memberService.getMemberByStdId("test");
    }

    @Test
    public void testFindAll() {
        List<Member> actualMembers = memberService.findAll();
        Assert.assertEquals(expectedMembers.get(0).getId(), actualMembers.get(0).getId());
    }

    @Test(expected = RuntimeException.class)
    public void testFindAll_exception() {
        when(memberRepository.findAll()).thenThrow(new RuntimeException());
        memberService.findAll();
    }

    @Test
    public void testDeleteMember() {
        memberService.deleteMember(1L);
        verify(memberRepository, times(1)).deleteById(1L);
    }
}
