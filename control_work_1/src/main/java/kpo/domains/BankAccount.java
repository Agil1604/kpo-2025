package kpo.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Доменный класс, представляющий банковский счёт.
 */
@Getter
@ToString
@AllArgsConstructor
public class BankAccount {
    final private Integer id;

    @Setter
    private String name;

    private Integer balance;

    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть положительной");
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0 || amount > balance) throw new IllegalArgumentException("Сумма должна быть положительной и не должна превышать баланс");
        balance -= amount;
    }
}