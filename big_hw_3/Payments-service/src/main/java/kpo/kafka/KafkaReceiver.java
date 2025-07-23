package kpo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kpo.entities.InboxOrder;
import kpo.repositories.InboxOrderRepository;
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
    private final InboxOrderRepository inboxRepository;

    @KafkaListener(topics = "orders")
    @Transactional
    public void addOrder(String payload) throws JsonProcessingException {
        try {
            JsonNode node = mapper.readTree(payload);

            InboxOrder order = new InboxOrder(
                    node.get("orderId").asInt(),
                    node.get("userId").asInt(),
                    node.get("amount").asInt(),
                    false
            );

            if (inboxRepository.existsByOrderId(order.getOrderId())) {
                return;
            }
            order.setProcessed(false);
            inboxRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
