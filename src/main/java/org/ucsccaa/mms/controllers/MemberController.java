package org.ucsccaa.mms.controllers;

import org.springframework.web.bind.annotation.*;
import org.ucsccaa.mms.models.ServiceResponse;
import org.ucsccaa.mms.models.Status;
import org.ucsccaa.mms.services.MemberService;
import org.ucsccaa.mms.domains.Member;

import org.springframework.beans.factory.annotation.Autowired;

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
            return new ServiceResponse<>(new URI(req.getRequestURI() + "/" + id));
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @PutMapping
    public ServiceResponse<Member> updateMember(@RequestBody Member member) throws URISyntaxException {
        Optional<Member> newMember;
        try {
            newMember = memberService.updateMember(member);
            if (!newMember.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "MEMBER NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<>(newMember.get());
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Object> deleteMember(@PathVariable Long id) {
        boolean delete;
        try {
           delete = memberService.deleteMember(id);
           if (!delete) {
               return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
           }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<>();
    }

    @GetMapping("/id/{id}")
    public ServiceResponse<Member> getMember(@PathVariable("id") Long id) {
        Optional<Member> member;
        try {
            member = memberService.getMember(id);
            if (!member.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<Member>(member.get());
    }

    @GetMapping("/email/{email}")
    public ServiceResponse<Member> getMemberByEmail(@PathVariable("email") String email) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByEmail(email);
            if (!member.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<Member>(member.get());
    }

    @GetMapping("/phone/{phone}")
    public ServiceResponse<Member> getMemberByPhone(@PathVariable("phone") String phone) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByPhone(phone);
            if (!member.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<Member>(member.get());
    }

    @GetMapping("/wechat/{wechat}")
    public ServiceResponse<Member> getMemberByWechat(@PathVariable("wechat") String wechat) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByWechat(wechat);
            if (!member.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<Member>(member.get());
    }

    @GetMapping("/stdid/{stdid}")
    public ServiceResponse<Member> getMemberByStdId(@PathVariable("stdid") String stdId) {
        Optional<Member> member;
        try {
            member = memberService.getMemberByStdId(stdId);
            if (!member.isPresent()) {
                return new ServiceResponse<>(Status.NOT_FOUND, "ID NOT FOUND");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
        return new ServiceResponse<Member>(member.get());
    }

    @GetMapping("/")
    public ServiceResponse<List<Member>> getAll() {
        return new ServiceResponse<>(memberService.findAll());
    }
}
