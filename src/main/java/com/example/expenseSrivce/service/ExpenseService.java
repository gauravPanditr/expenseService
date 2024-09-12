package com.example.expenseSrivce.service;


import com.example.expenseSrivce.repositry.ExpenseRepositary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ExpenseService {


    private ExpenseRepositary expenseRepositary;

    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepositary expenseRepositary) {
        this.expenseRepositary = expenseRepositary;
    }





}
