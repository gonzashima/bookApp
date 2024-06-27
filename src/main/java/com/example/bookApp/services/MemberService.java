package com.example.bookApp.services;

import com.example.bookApp.dtos.MemberOutDto;
import com.example.bookApp.dtos.MemberPostDto;
import com.example.bookApp.dtos.MessageDto;
import com.example.bookApp.mappers.MemberOutMapper;
import com.example.bookApp.mappers.MemberPostMapper;
import com.example.bookApp.model.Member;
import com.example.bookApp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberPostMapper memberPostMapper;
    private final MemberOutMapper memberOutMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberPostMapper memberPostMapper, MemberOutMapper memberOutMapper) {
        this.memberPostMapper = memberPostMapper;
        this.memberOutMapper = memberOutMapper;
        this.memberRepository = memberRepository;
    }

    public List<MemberOutDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(memberOutMapper).collect(Collectors.toList());
    }

    public MemberOutDto getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty())
            throw new IllegalStateException("Member does not exist");

        return memberOutMapper.apply(member.get());
    }

    public MemberOutDto createMember(MemberPostDto memberPostDto) {
        Member parsedMember = memberPostMapper.apply(memberPostDto);
        memberRepository.save(parsedMember);

        return memberOutMapper.apply(parsedMember);
    }

    public MemberOutDto updateMember(Long id, MemberPostDto memberPostDto) {
        Optional<Member> optional = memberRepository.findById(id);

        if(optional.isEmpty())
            throw new IllegalStateException("Member not found");

        Member member = optional.get();
        member.setEmail(memberPostDto.email());
        member.setName(memberPostDto.name());
        member.setPassword(memberPostDto.password());

        return memberOutMapper.apply(memberRepository.saveAndFlush(member));
    }

    public MessageDto deleteMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty())
            throw new IllegalStateException("Member does not exist");

        memberRepository.deleteById(id);
        return new MessageDto("User with id: " + id + " deleted successfully");
    }

}
