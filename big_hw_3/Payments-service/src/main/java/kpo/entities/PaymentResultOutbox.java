package kpo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment_result_outbox")
@NoArgsConstructor
public class PaymentResultOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private boolean sent;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public PaymentResultOutbox(String payload, boolean sent) {
        this.payload = payload;
        this.sent = sent;
        this.createdAt = LocalDateTime.now();
    }
}
