package com.example.demo.controller;

import com.example.demo.dto.AccountDetail;
import com.example.demo.dto.Balance;
import com.example.demo.dto.TransferBetweenAccounts;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v")
public class AccountManagementController {

    private final AccountService accountService;

    @ApiOperation(value = "Открыть счет")
    @PostMapping("/create")
    public ResponseEntity<AccountDetail> createAccount(@RequestBody AccountDetail account) {
        accountService.create(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Закрыть счет")
    @DeleteMapping( "/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id){
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    @ApiOperation(value = "Установить баланс счета")
    @PutMapping("/setBalance")
    public ResponseEntity<Balance> setBalance(@RequestBody Balance balance){
        accountService.addBalance(balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Пополнение счета")
    @PutMapping("/payment")
    public ResponseEntity<Balance> cashIn(@RequestBody Balance balance){
        accountService.addMoney(balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Списание с счета")
    @PutMapping("/cashOut")
    public ResponseEntity<Balance> cashOut(@RequestBody Balance balance){
        accountService.cashOutMoney(balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Перевод между счетами")
	@PutMapping("/transfer")
    public ResponseEntity<TransferBetweenAccounts> transferMoney(@RequestBody TransferBetweenAccounts transfer){
        accountService.transfer(transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
