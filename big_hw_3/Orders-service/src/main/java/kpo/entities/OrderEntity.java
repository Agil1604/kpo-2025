package kpo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import kpo.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Positive
    @Column(nullable = false)
    private Integer amount;

    private String description;

    @Column(nullable = false)
    private OrderStatus status;

    public OrderEntity(Integer userId, Integer amount, OrderStatus status, String description) {
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }
}
