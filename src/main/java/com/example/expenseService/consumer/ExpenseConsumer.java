package com.example.expenseService.consumer;

import com.example.expenseService.dto.ExpenseDto;
import com.example.expenseService.service.ExpenseService;
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

  @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
  public void listen(ExpenseDto eventData) {
    try{
      // Todo: Make it transactional, and check if duplicate event (Handle idempotency)
      expenseService.CreateExpense(eventData);
    }catch(Exception ex){
      ex.printStackTrace();
      System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
    }
  }


}
