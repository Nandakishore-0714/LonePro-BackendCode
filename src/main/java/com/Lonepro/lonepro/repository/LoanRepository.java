package com.Lonepro.lonepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Lonepro.lonepro.entity.Loan;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);
}