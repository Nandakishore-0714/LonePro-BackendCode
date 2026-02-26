package com.Lonepro.lonepro.Controller;

import com.Lonepro.lonepro.entity.Loan;
import com.Lonepro.lonepro.entity.User;
import com.Lonepro.lonepro.repository.LoanRepository;
import com.Lonepro.lonepro.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin("*")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    // Request Loan
    @PostMapping("/{userId}")
    public Loan requestLoan(@PathVariable Long userId,
                            @RequestBody Loan loan) {

        loan.setStatus("PENDING");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        loan.setUser(user);

        return loanRepository.save(loan);
    }

    // Get All Loans (Admin)
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Approve Loan
    @PutMapping("/approve/{loanId}")
    public Loan approveLoan(@PathVariable Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus("APPROVED");

        return loanRepository.save(loan);
    }

    // Reject Loan
    @PutMapping("/reject/{loanId}")
    public Loan rejectLoan(@PathVariable Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus("REJECTED");

        return loanRepository.save(loan);
    }

    // Get Loans By User
    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        return loanRepository.findByUserId(userId);
    }
}