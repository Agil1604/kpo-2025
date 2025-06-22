package kpo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import kpo.entities.OrderEntity;
import kpo.entities.OutboxOrder;
import kpo.enums.OrderStatus;
import kpo.repositories.OrderRepository;
import kpo.repositories.OutboxOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OutboxOrderRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Transactional
    public OrderEntity createOrder(Integer userId, Integer amount, String description) {
        OrderEntity order = new OrderEntity(userId, amount, OrderStatus.NEW, description);
        orderRepository.save(order);

        try {
            String payload = mapper.writeValueAsString(Map.of(
                    "orderId", order.getId(),
                    "userId", order.getUserId(),
                    "amount", order.getAmount()
            ));
            OutboxOrder outbox = new OutboxOrder(payload, false);
            outboxRepository.save(outbox);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to create outbox event", e);
        }

        return order;
    }

    public OrderEntity getOrder(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("No such Order"));
    }

    public List<OrderEntity> getOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
