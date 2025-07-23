package kpo.repositories;

import kpo.entities.InboxOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InboxOrderRepository extends JpaRepository<InboxOrder, Integer> {
    boolean existsByOrderId(Integer OrderId);
    List<InboxOrder> findByProcessedFalse();
}
