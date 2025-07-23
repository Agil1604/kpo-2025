package kpo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inbox_order")
@NoArgsConstructor
public class InboxOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    @Setter
    @Column(nullable = false)
    private boolean processed = false;

    public InboxOrder(Integer orderId, Integer userId, Integer amount, boolean processed) {
        this.orderId = orderId;
        this.amount = amount;
        this.userId = userId;
        this.processed = processed;
        this.receivedAt = LocalDateTime.now();
    }
}
