package com.example.expenseSrivce.repositry;

import com.example.expenseSrivce.enitites.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepositary extends CrudRepository<Expense,Long> {

    List<Expense> findByUserId(Long userId);

    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime, Timestamp endTime);


    Optional<Expense>findByUserIdAndExpenseId(Long userId, Long expenseId);




}
