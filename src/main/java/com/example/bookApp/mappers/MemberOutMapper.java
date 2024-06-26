package com.example.bookApp.mappers;

import com.example.bookApp.dtos.MemberOutDto;
import com.example.bookApp.model.Member;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MemberOutMapper implements Function<Member, MemberOutDto> {

    @Override
    public MemberOutDto apply(Member member) {
        return new MemberOutDto(member.getId(), member.getName(), member.getEmail());
    }
}
