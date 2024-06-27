package com.example.bookApp.mappers;

import com.example.bookApp.dtos.MemberPostDto;
import com.example.bookApp.model.Member;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MemberPostMapper implements Function<MemberPostDto, Member> {

    @Override
    public Member apply(MemberPostDto memberPostDto) {
        return new Member(memberPostDto.name(), memberPostDto.email(), memberPostDto.password());
    }
}
