package com.example.expenseService.controller;


import com.example.expenseService.dto.ExpenseDto;
import com.example.expenseService.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")

public class ExpenseController {

   private final ExpenseService expenseService;

   @Autowired
    public ExpenseController(ExpenseService expenseService) {
       this.expenseService = expenseService;
   }

   @GetMapping("/expense/v1")
    public ResponseEntity<List<ExpenseDto>> getExpenses(@PathParam("user_id") @NonNull String userId) {
       try{
          List<ExpenseDto> expenses = expenseService.getExpenses(userId);
          return new ResponseEntity<>(expenses, HttpStatus.OK);
       }
       catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

       }
   }

    @PostMapping(path="/addExpense")
    public ResponseEntity<Boolean> addExpenses(@RequestHeader(value = "X-User-Id") @NonNull String userId, @RequestBody ExpenseDto expenseDto) {
        try {
            expenseDto.setUserId(userId);
            return new ResponseEntity<>(expenseService.CreateExpense(expenseDto), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);


        }
    }

}