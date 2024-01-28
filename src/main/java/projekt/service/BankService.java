package projekt.service;

import lombok.AllArgsConstructor;
import projekt.model.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import projekt.repository.BankRepository;
import projekt.model.BankAccount;


@Service
@AllArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public BankAccount registerAccount(String pesel, BigDecimal initialBalance, Currency currency, String ownerFirstName, String ownerLastName) {
        if (!isValidPesel(pesel)) {
            throw new IllegalArgumentException("Invalid PESEL");
        }
        BankAccount account = new BankAccount(ownerFirstName, ownerLastName, pesel, currency, initialBalance);
        bankRepository.addNew(account);
        return account;
    }

    public BankAccount getAccountByPesel(String pesel) {
        return bankRepository.getByPesel(pesel);
    }

    public List<BankAccount> getAccountsByBalanceGreaterThan(BigDecimal threshold) {
        return bankRepository.getByBalanceGreaterThan(threshold);
    }

    private boolean isValidPesel(String pesel) {

        return pesel != null && pesel.length() == 11;
    }


}
