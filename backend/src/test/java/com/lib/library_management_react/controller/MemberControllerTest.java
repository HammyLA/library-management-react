package com.lib.library_management_react.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lib.library_management_react.model.Member;
import com.lib.library_management_react.service.MemberService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private Member member;

    @BeforeEach
    public void setup() {
        member = new Member("John", "Doe", "john.doe@example.com");
        member.setMemberid(1);
    }

    @Test
    public void testFindAll() throws Exception {
        when(memberService.getAllMembers()).thenReturn(Arrays.asList(member));

        mockMvc.perform(get("/api/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberid").value(member.getMemberid()))
                .andExpect(jsonPath("$[0].firstName").value(member.getFirstName()));
    }

    @Test
    public void testFindById() throws Exception {
        when(memberService.getById(1)).thenReturn(member);

        mockMvc.perform(get("/api/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberid").value(member.getMemberid()))
                .andExpect(jsonPath("$.firstName").value(member.getFirstName()));
    }

    @Test
    public void testPostMember() throws Exception {
        when(memberService.createMember(any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testDeleteMember() throws Exception {
        when(memberService.hasRented(1)).thenReturn(false);

        mockMvc.perform(delete("/api/members/1"))
                .andExpect(status().isOk());
        verify(memberService, times(1)).deleteMember(1);
    }

    @Test
    public void testDeleteMemberWithRental() throws Exception {
        when(memberService.hasRented(1)).thenReturn(true);

        mockMvc.perform(delete("/api/members/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Member deletion failed: Member null has outstanding rental"));
    }

    @Test
    public void testFindByName() throws Exception {
        when(memberService.getByName("John")).thenReturn(Arrays.asList(member));

        mockMvc.perform(get("/api/members/search").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }
}
