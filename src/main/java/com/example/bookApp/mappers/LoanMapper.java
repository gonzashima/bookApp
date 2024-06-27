package com.example.bookApp.mappers;

import com.example.bookApp.dtos.LoanDto;
import com.example.bookApp.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LoanMapper implements Function<LoanDto, Loan> {

    private final MemberPostMapper memberPostMapper;

    @Autowired
    public LoanMapper(MemberPostMapper memberPostMapper) {
        this.memberPostMapper = memberPostMapper;
    }

    @Override
    public Loan apply(LoanDto loanDto) {
        return new Loan(loanDto.startDate(), loanDto.expirationDate(), memberPostMapper.apply(loanDto.member()));
    }
}
