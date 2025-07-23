package kpo.repositories;

import kpo.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserId(Integer UserId);
}