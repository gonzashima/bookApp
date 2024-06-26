package com.example.bookApp.controllers;

import com.example.bookApp.dtos.MemberOutDto;
import com.example.bookApp.dtos.MemberPostDto;
import com.example.bookApp.dtos.MessageDto;
import com.example.bookApp.model.Member;
import com.example.bookApp.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberOutDto>> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberOutDto> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<MemberOutDto> createMember(@RequestBody @Valid MemberPostDto member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.deleteMemberById(id));
    }
}
