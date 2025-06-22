package kpo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kpo.entities.OrderEntity;
import kpo.enums.OrderStatus;
import kpo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiver {
    private final ObjectMapper mapper = new ObjectMapper();
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "orders-result")
    @Transactional
    public void addOrder(String payload) throws JsonProcessingException {
        try {
            JsonNode node = mapper.readTree(payload);

            Integer id = node.get("orderId").asInt();
            OrderStatus status = OrderStatus.valueOf(node.get("status").asText());
            OrderEntity order = orderRepository.getReferenceById(id);
            order.setStatus(status);
            orderRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
