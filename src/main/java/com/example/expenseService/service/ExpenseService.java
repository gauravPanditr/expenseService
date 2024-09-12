package com.example.expenseService.service;


import com.example.expenseService.dto.ExpenseDto;
import com.example.expenseService.enitites.Expense;
import com.example.expenseService.repositry.ExpenseRepositary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ExpenseService {


    private ExpenseRepositary expenseRepositary;

    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepositary expenseRepositary) {
        this.expenseRepositary = expenseRepositary;
    }

    public boolean CreateExpense(ExpenseDto expenseDto) {
        setCurrency(expenseDto);
        try{
            expenseRepositary.save(objectMapper.convertValue(expenseDto, Expense.class));
            return true;

        }
        catch(Exception e){
          return false;
        }
    }

    public void setCurrency(ExpenseDto expenseDto){
        if(Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("inr");
        }

    }

    public boolean UpdateExpense(ExpenseDto expenseDto){
        Optional<Expense>optionalExpense=expenseRepositary.findByUserIdAndExternalId(expenseDto.getUserId(),expenseDto.getExternalId());

        if(optionalExpense.isEmpty()){
             return false;

        }
        Expense expense = optionalExpense.get();
        expense.setAmount(expenseDto.getAmount());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())?expenseDto.getMerchant():expense.getMerchant());
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())?expenseDto.getMerchant():expense.getCurrency());
        expenseRepositary.save(expense);
        return true;

    }


    public List<ExpenseDto> getExpenses(String userId){
        List<Expense> expenseOpt = expenseRepositary.findByUserId(userId);
        return objectMapper.convertValue(expenseOpt, new TypeReference<List<ExpenseDto>>() {});
    }



}
