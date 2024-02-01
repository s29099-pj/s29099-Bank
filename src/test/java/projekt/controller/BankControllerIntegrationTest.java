package projekt.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import projekt.model.BankAccount;
import projekt.model.Currency;
import projekt.service.BankService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BankControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BankService bankService;


    @BeforeEach
    void setUp() {
        BankAccount account1 = new BankAccount("Michal", "Nowak", "12345678999", Currency.USD, BigDecimal.valueOf(1000));
        BankAccount account2 = new BankAccount("Marcin", "Kowalski", "12398745600", Currency.EUR, BigDecimal.valueOf(2000));

        bankService.registerAccount(account1.getPesel(), account1.getBalance(), account1.getCurrency(), account1.getOwnerFirstName(), account1.getOwnerLastName());
        bankService.registerAccount(account2.getPesel(), account2.getBalance(), account2.getCurrency(), account2.getOwnerFirstName(), account2.getOwnerLastName());
    }

    @Test
    void shouldRegisterAccount() {
        BankAccount account = new BankAccount("Michal", "Nowak", "12345678999", Currency.USD, BigDecimal.valueOf(1000));

        webTestClient.post()
                .uri("/bank/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(account)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BankAccount.class)
                .isEqualTo(account);
    }

    @Test
    void shouldGetAccountByPesel() {
        BankAccount account = new BankAccount("Michal", "Nowak", "12345678999", Currency.USD, BigDecimal.valueOf(1000));
        webTestClient.get()
                .uri("/bank/account/{pesel}", "12345678999")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BankAccount.class)
                .isEqualTo(account);
    }

    @Test
    void shouldGetAccountsByBalanceGreaterThan() {
        List<BankAccount> expectedAccounts = Arrays.asList(
                new BankAccount("Michal", "Nowak", "12345678999", Currency.USD, BigDecimal.valueOf(1000)),
                new BankAccount("Marcin", "Kowalski", "12398745600", Currency.EUR, BigDecimal.valueOf(2000))
        );

        List<BankAccount> actualAccounts = webTestClient.get()
                .uri("/bank/accountsMoreThan/{threshold}", 1000)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BankAccount.class)
                .returnResult().getResponseBody();


        assertEquals(expectedAccounts.size(), actualAccounts.size());
    }
}