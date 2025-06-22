package kpo.repositories;

import kpo.entities.PaymentResultOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentResultOutboxRepository extends JpaRepository<PaymentResultOutbox, Integer> {
    List<PaymentResultOutbox> findBySentFalse();

}
