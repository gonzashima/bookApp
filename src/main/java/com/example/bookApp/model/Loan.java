package com.example.bookApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column(name = "start_date", columnDefinition = "DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Column(name = "expiration_date", columnDefinition = "DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Loan() {
    }

    public Loan(Long id, LocalDate startDate, LocalDate expirationDate, Member member) {
        this.id = id;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.member = member;
    }

    public Loan(LocalDate startDate, LocalDate expirationDate, Member member) {
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
