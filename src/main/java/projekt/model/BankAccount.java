package projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class BankAccount {
    private String ownerFirstName;
    private String ownerLastName;
    private String pesel; // identyfikator?
    private Currency currency;
    private BigDecimal balance;


}
