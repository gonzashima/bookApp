package com.example.bookApp.services;

import com.example.bookApp.dtos.LoanDto;
import com.example.bookApp.mappers.LoanMapper;
import com.example.bookApp.model.Loan;
import com.example.bookApp.model.Member;
import com.example.bookApp.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final MemberService memberService;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper, MemberService memberService) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.memberService = memberService;
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Loan createLoan(LoanDto loanDto) {
        Optional<Member> optional = memberService.getMemberByEmailAndPassword(loanDto.member().email(), loanDto.member().password());
        if (optional.isEmpty())
            throw new IllegalStateException("Member does not exist");

        Loan loan = loanMapper.apply(loanDto);
        loan.setMember(optional.get());

        return loanRepository.save(loan);
    }

}
