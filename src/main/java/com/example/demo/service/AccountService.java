package com.example.demo.service;

import com.example.demo.dto.AccountDetail;
import com.example.demo.dto.Balance;
import com.example.demo.dto.TransferBetweenAccounts;

public interface AccountService {
    void create(AccountDetail account);

    void delete(Long id);

    void transfer(TransferBetweenAccounts transfer);

    void addMoney(Balance balance);

    void cashOutMoney(Balance balance);

    void addBalance(Balance balance);
}
