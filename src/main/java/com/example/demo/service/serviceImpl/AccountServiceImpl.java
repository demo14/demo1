package com.example.demo.service.serviceImpl;

import com.example.demo.dto.AccountDetail;
import com.example.demo.dto.Balance;
import com.example.demo.dto.TransferBetweenAccounts;
import com.example.demo.entity.Account;
import com.example.demo.exceptions.AccountNotFound;
import com.example.demo.mappers.Mapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void create(AccountDetail account) {
        Account accountIn = Mapper.toAccount(account);
        accountRepository.save(accountIn);
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFound("Account with id " + id + " not found"));
        accountRepository.delete(account);
    }

    @Override
    @Transactional
    public void transfer(TransferBetweenAccounts transfer) {
        Account accountFor = accountRepository.findAccountByAccountNumber(transfer.getAccountFrom());
        Account accountTo = accountRepository.findAccountByAccountNumber(transfer.getAccountTo());
        if (transfer.getAmount().intValue() <= 0) {
            throw new RuntimeException("invalid amount");
        }
        if (accountFor.getBalance().compareTo(transfer.getAmount()) >= 0) {
            accountFor.setBalance(accountFor.getBalance().subtract(transfer.getAmount()));
            accountRepository.save(accountFor);
            accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
            accountRepository.save(accountTo);
        } else {
            throw new RuntimeException("Insufficient funds");
        }
    }

    @Override
    public void addMoney(Balance balance) {
        Account account = accountRepository.findAccountByAccountNumber(balance.getAccountNumber());
        if (balance.getAmount().intValue() <= 0) {
            throw new RuntimeException("invalid amount");
        }
        account.setBalance(account.getBalance().add(balance.getAmount()));
        accountRepository.save(account);
    }

    @Override
    public void cashOutMoney(Balance balance) {
        Account account = accountRepository.findAccountByAccountNumber(balance.getAccountNumber());
        if (balance.getAmount().intValue() <= 0) {
            throw new RuntimeException("invalid amount");
        }
        account.setBalance(account.getBalance().subtract(balance.getAmount()));
        accountRepository.save(account);
    }

    @Override
    public void addBalance(Balance balance) {
        Account account = accountRepository.findAccountByAccountNumber(balance.getAccountNumber());
        if (balance.getAmount().intValue() <= 0) {
            throw new RuntimeException("invalid amount");
        }
        account.setBalance(balance.getAmount());

        accountRepository.save(account);
    }
}
