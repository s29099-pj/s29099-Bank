package projekt.repository;


import org.springframework.stereotype.Repository;
import projekt.model.BankAccount;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

@Repository
public class BankRepository {
    private List<BankAccount> bankAccounts = new ArrayList<>();


    public void addNew(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }
    public BankAccount getByPesel(String pesel) {
        return bankAccounts.stream()
                .filter(account  -> account.getPesel().equals(pesel))
                .findFirst()
                .orElse(null);
    }
    public List<BankAccount> getByBalanceGreaterThan(BigDecimal threshold) {
        return bankAccounts.stream()
                .filter(account -> account.getBalance().compareTo(threshold) > 0)
                .collect(Collectors.toList());
    }
    public List<BankAccount> getAll() {
        return bankAccounts;
    }


}
