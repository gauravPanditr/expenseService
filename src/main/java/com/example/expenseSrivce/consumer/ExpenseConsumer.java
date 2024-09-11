package com.example.expenseSrivce.consumer;

import com.example.expenseSrivce.service.ExpenseService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer {

  private ExpenseService expenseService;

  @Autowired
    ExpenseConsumer(ExpenseService expenseService) {
      this.expenseService = expenseService;
  }




}
