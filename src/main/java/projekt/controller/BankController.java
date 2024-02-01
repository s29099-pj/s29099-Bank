package projekt.controller;

import projekt.service.BankService;
import projekt.model.BankAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping("/register")
    public ResponseEntity<BankAccount> registerAccount(@RequestBody BankAccount bankAccount) {
        BankAccount account = bankService.registerAccount(
                bankAccount.getPesel(),
                bankAccount.getBalance(),
                bankAccount.getCurrency(),
                bankAccount.getOwnerFirstName(),
                bankAccount.getOwnerLastName()
        );
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account/{pesel}")
    public ResponseEntity<BankAccount> getAccountByPesel(@PathVariable String pesel) {
        BankAccount account = bankService.getAccountByPesel(pesel);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/accountsMoreThan/{threshold}")
    public ResponseEntity<List<BankAccount>> getAccountsByBalanceGreaterThan(@PathVariable BigDecimal threshold) {
        List<BankAccount> accounts = bankService.getAccountsByBalanceGreaterThan(threshold);
        return ResponseEntity.ok(accounts);
    }

}
