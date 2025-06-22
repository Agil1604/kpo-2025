package kpo.repositories;

import kpo.entities.OutboxOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxOrderRepository extends JpaRepository<OutboxOrder, Integer> {
    List<OutboxOrder> findBySentFalse();
}
