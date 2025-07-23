package kpo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox_order")
@Getter
@Setter
@NoArgsConstructor
public class OutboxOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private boolean sent;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public OutboxOrder(String payload, boolean sent) {
        this.payload = payload;
        this.sent = sent;
        this.createdAt = LocalDateTime.now();
    }
}