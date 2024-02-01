package projekt.repository;

import org.junit.jupiter.api.Test;
import projekt.model.BankAccount;
import projekt.model.Currency;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankRepositoryTest {

    @Test
    void shouldAddNewAccountToRepository() {

        BankRepository bankRepository = new BankRepository();
        BankAccount bankAccount = new BankAccount("Michal", "Nowak", "12345678999", Currency.PLN, BigDecimal.valueOf(1000));


        bankRepository.addNew(bankAccount);


        List<BankAccount> allAccounts = bankRepository.getAll();
        assertNotNull(allAccounts);
        assertEquals(1, allAccounts.size());
        assertEquals(bankAccount, allAccounts.get(0));
    }

    @Test
    void shouldGetAccountByPesel() {

        BankRepository bankRepository = new BankRepository();
        BankAccount account1 = new BankAccount("Michal", "Nowak", "12345678999", Currency.PLN, BigDecimal.valueOf(1000));
        bankRepository.addNew(account1);


        BankAccount retrievedAccount = bankRepository.getByPesel("12345678999");


        assertNotNull(retrievedAccount);
        assertEquals(account1, retrievedAccount);
    }


    @Test
    void shouldGetByBalanceGreaterThanThreshold() {

        BankRepository bankRepository = new BankRepository();
        BankAccount account1 = new BankAccount("Michal", "Nowak", "12345678999", Currency.PLN, BigDecimal.valueOf(1000));
        BankAccount account2 = new BankAccount("Marcin", "Kowalski", "12398745600", Currency.USD, BigDecimal.valueOf(2000));
        bankRepository.addNew(account1);
        bankRepository.addNew(account2);


        List<BankAccount> accounts = bankRepository.getByBalanceGreaterThan(BigDecimal.valueOf(1000));


        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals(account2, accounts.get(0));
    }
}