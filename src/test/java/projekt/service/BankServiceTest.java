package projekt.service;

import org.junit.jupiter.api.Test;
import projekt.model.BankAccount;
import projekt.model.Currency;
import projekt.repository.BankRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    @Test
    void shouldRegisterAccount() {

        BankRepository mockRepository = new BankRepository();
        BankService bankService = new BankService(mockRepository);


        BankAccount registeredAccount = bankService.registerAccount(
                "12345678999",
                BigDecimal.valueOf(1000),
                Currency.PLN,
                "Michal",
                "Nowak"
        );


        assertNotNull(registeredAccount);
        assertEquals("Michal", registeredAccount.getOwnerFirstName());
        assertEquals("Nowak", registeredAccount.getOwnerLastName());
        assertEquals("12345678999", registeredAccount.getPesel());
        assertEquals(BigDecimal.valueOf(1000), registeredAccount.getBalance());
        assertEquals(Currency.PLN, registeredAccount.getCurrency());

    }
    @Test
    void shouldGetAccountByPesel() {

        BankRepository mockRepository = new BankRepository();
        BankService bankService = new BankService(mockRepository);


        BankAccount testAccount = new BankAccount
                ("Michal", "Nowak", "12345678999", Currency.EUR, BigDecimal.valueOf(500));
        mockRepository.addNew(testAccount);


        BankAccount retrievedAccount = bankService.getAccountByPesel("12345678999");


        assertNotNull(retrievedAccount);
        assertEquals("Michal", retrievedAccount.getOwnerFirstName());
        assertEquals("Nowak", retrievedAccount.getOwnerLastName());
        assertEquals("12345678999", retrievedAccount.getPesel());
        assertEquals(BigDecimal.valueOf(500), retrievedAccount.getBalance());
        assertEquals(Currency.EUR, retrievedAccount.getCurrency());
    }

}