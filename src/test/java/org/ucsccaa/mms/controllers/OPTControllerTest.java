package org.ucsccaa.mms.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.ucsccaa.mms.MembershipManagementSystemApplication;
import org.ucsccaa.mms.domains.OPT;
import org.ucsccaa.mms.services.OPTService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MembershipManagementSystemApplication.class)
public class OPTControllerTest {
    protected MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private OPTService optService;
    @InjectMocks
    private OPTController optController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(optController).build();
    }

    @Test
    public void createOPTTest() throws Exception {
        OPT optExpect = new OPT();
        optExpect.setId((long) 1);
        optExpect.setStatus("STATUS");
        optExpect.setCardNumber("TEST-CARD-ID");

        when(optService.createOPT(any())).thenReturn(optExpect.getId());
        String json = objectMapper.writeValueAsString(optExpect);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/OPTs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(result.getResponse().getHeader("Location"), "http://localhost/OPTs/" + optExpect.getId().toString());
    }

    @Test
    public void createEmptyBodyTest() throws Exception {
        doThrow(new IllegalArgumentException("OPT can't be NULL")).when(optService).createOPT(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/OPTs")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getOPTByIDTest() throws Exception {
        OPT optExpect = new OPT();
        optExpect.setId((long) 1);
        optExpect.setStatus("STATUS");
        optExpect.setCardNumber("TEST-CARD-ID");
        when(optService.findOPTByID(any())).thenReturn(Optional.of(optExpect));
        mockMvc.perform(MockMvcRequestBuilders.get("/OPTs/" + optExpect.getId().toString()))
                .andExpect(ResultMatcher.matchAll(MockMvcResultMatchers.jsonPath("$.cardNumber").value(optExpect.getCardNumber()),
                        MockMvcResultMatchers.jsonPath("$.id").value(optExpect.getId()),
                        MockMvcResultMatchers.jsonPath("$.status").value(optExpect.getStatus()),
                        MockMvcResultMatchers.status().isOk()));
    }

    @Test
    public void getOPTByIDNoFoundTest() throws Exception {
        when(optService.findOPTByID(any())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/OPTs/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateOPTByIDTest() throws Exception {
        OPT optExpect = new OPT();
        optExpect.setId((long) 1);
        optExpect.setStatus("STATUS");
        optExpect.setEndDate(LocalDateTime.now());
        optExpect.setCardNumber("TEST-CARD-ID");
        String json = objectMapper.writeValueAsString(optExpect);
        System.out.println(json);
        when(optService.updateOPTByID(any())).thenReturn(Optional.of(optExpect));
        mockMvc.perform(MockMvcRequestBuilders.put("/OPTs/" + optExpect.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(ResultMatcher.matchAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.status").value("STATUS"),
                        MockMvcResultMatchers.jsonPath("$.cardNumber").value("TEST-CARD-ID")
                ));
    }

    @Test
    public void updateOPTNotFoundTest() throws Exception {
        OPT optExpect = new OPT();
        optExpect.setId((long) 1);
        optExpect.setStatus("STATUS");
        optExpect.setEndDate(LocalDateTime.now());
        optExpect.setCardNumber("TEST-CARD-ID");
        String json = objectMapper.writeValueAsString(optExpect);
        System.out.println(json);
        when(optService.updateOPTByID(any())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.put("/OPTs/1").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void deleteByIDTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/OPTs/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteByIDNotFoundTest() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(optService).deleteOPT(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/OPTs/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
