package org.ucsccaa.mms.controllers;

import org.springframework.web.bind.annotation.*;
import org.ucsccaa.mms.models.ServiceResponse;
import org.ucsccaa.mms.models.Status;
import org.ucsccaa.mms.services.MemberService;
import org.ucsccaa.mms.domains.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ServiceResponse<URI> addMember(@RequestBody Member member, HttpServletRequest req) throws URISyntaxException {
        Long id;
        try {
            id = memberService.addMember(member);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<>(new URI(req.getRequestURI() + id));
    }

    @PutMapping
    public ServiceResponse<Member> updateMember(@RequestBody Member member) throws URISyntaxException {
        Optional<Member> newMember;
        try {
            newMember = memberService.updateMember(member);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return newMember.isPresent() ? new ServiceResponse<>(newMember.get()) : new ServiceResponse<>(Status.NOT_FOUND, "MEMBER NOT FOUND");
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Object> deleteMember(@PathVariable Long id) {
        boolean delete;
        try {
           delete = memberService.deleteMember(id);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return delete ? new ServiceResponse<>() : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/id/{id}")
    public ServiceResponse<Optional<Member>> getMember(@PathVariable("id") Long id) {
        Optional<Member> member;
        try {
            member = memberService.getMember(id);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return member.isPresent() ? new ServiceResponse<Optional<Member>>(member) : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/email/{email}")
    public ServiceResponse<Optional<Member>> getMemberByEmail(@PathVariable("email") String email) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByEmail(email);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return member.isPresent() ? new ServiceResponse<Optional<Member>>(member) : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/phone/{phone}")
    public ServiceResponse<Optional<Member>> getMemberByPhone(@PathVariable("phone") String phone) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByPhone(phone);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return member.isPresent() ? new ServiceResponse<Optional<Member>>(member) : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/wechat/{wechat}")
    public ServiceResponse<Optional<Member>> getMemberByWechat(@PathVariable("wechat") String wechat) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByWechat(wechat);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return member.isPresent() ? new ServiceResponse<Optional<Member>>(member) : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/stdid/{stdid}")
    public ServiceResponse<Optional<Member>> getMemberByStdId(@PathVariable("stdid") String stdId) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByStdId(stdId);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return member.isPresent() ? new ServiceResponse<Optional<Member>>(member) : new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
    }

    @GetMapping("/")
    public ServiceResponse<List<Member>> getAll() {
        return new ServiceResponse<>(memberService.findAll());
    }
}
