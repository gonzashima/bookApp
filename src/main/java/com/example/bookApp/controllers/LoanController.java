package com.example.bookApp.controllers;

import com.example.bookApp.dtos.LoanDto;
import com.example.bookApp.model.Loan;
import com.example.bookApp.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getLoans() {
        return ResponseEntity.ok(loanService.getLoans());
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody @Valid LoanDto loanDto) {
        return ResponseEntity.ok(loanService.createLoan(loanDto));
    }
}
