package kpo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank_account")
@NoArgsConstructor
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer userId;

    @Positive
    @Column(nullable = false)
    private Integer balance;

    public BankAccountEntity(Integer userId, Integer balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
