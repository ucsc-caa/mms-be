package org.ucsccaa.mms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import org.ucsccaa.mms.Member;
import org.ucsccaa.mms.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private final Member expectedMember = new Member(1L,"test","test","test","test","test",
            "test","test","test","test","test","test","test","test",
            "test","test","test","test","test","test","test","test","test",true);

    private final List<Member> expectedMembers = new ArrayList(){{add(new Member(1L,"test","test","test",
            "test","test","test","test","test","test","test","test",
            "test","test","test","test","test","test","test","test",
            "test","test","test",true));}};

    @Before
    public void configure() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
        when(memberService.addMember(any())).thenReturn(expectedMember.getId());
    }

    @Test
    public void addMemberTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test\"}");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void addMemberTest_lackOfParam() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/members");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void updateMemberTest() throws Exception {
        when(memberService.updateMember(any())).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test\"}");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void updateMemberTest_NotFound() throws Exception {
        when(memberService.updateMember(any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test\"}");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMemberTest() throws Exception {
        when(memberService.getMember(1L)).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/id/1");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void getMemberTest_NotFound() throws Exception {
        when(memberService.getMember(1L)).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/id/1");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMemberByEmailTest() throws Exception {
        when(memberService.getMemberByEmail("test")).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/email/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void getMemberByEmailTest_NotFound() throws Exception {
        when(memberService.getMemberByEmail("test")).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/email/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMemberByPhoneTest() throws Exception {
        when(memberService.getMemberByPhone("test")).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/phone/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void getMemberByPhoneTest_NotFound() throws Exception {
        when(memberService.getMemberByPhone("test")).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/phone/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMemberByWechatTest() throws Exception {
        when(memberService.getMemberByWechat("test")).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/wechat/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void getMemberByWechatTest_NotFound() throws Exception {
        when(memberService.getMemberByWechat("test")).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/wechat/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMemberByStdIdTest() throws Exception {
        when(memberService.getMemberByStdId("test")).thenReturn(Optional.of(expectedMember));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/stdid/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedMember.getName()));
    }

    @Test
    public void getMemberByStdIdTest_NotFound() throws Exception {
        when(memberService.getMemberByStdId("test")).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/members/stdid/test");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteMemberTest() throws Exception {
        when(memberService.deleteMember(1L)).thenReturn(true);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .delete("/members/1");

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteMemberTest_NotFound() throws Exception {
        when(memberService.deleteMember(1L)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/members/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAllTest() throws Exception {
        when(memberService.findAll()).thenReturn(expectedMembers);
        mockMvc.perform(MockMvcRequestBuilders.get("/members/"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(expectedMembers.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(expectedMembers.get(0).getName()));

    }

}
