package com.example.expenseService.repositry;

import com.example.expenseService.enitites.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepositary extends CrudRepository<Expense,Long> {

    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime, Timestamp endTime);


    Optional<Expense>findByUserIdAndExternalId(String userId, String expenseId);




}
