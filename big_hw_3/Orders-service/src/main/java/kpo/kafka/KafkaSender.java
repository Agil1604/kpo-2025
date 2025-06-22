package kpo.kafka;

import kpo.entities.OutboxOrder;
import kpo.repositories.OutboxOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final OutboxOrderRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void processSingleEvent(OutboxOrder event) {
        try {
            kafkaTemplate.send("orders", event.getPayload()).get(5, TimeUnit.SECONDS);
            event.setSent(true);
            outboxRepository.save(event);
        } catch (Exception e) {
            log.error("Failed to process event ID: {}", event.getId(), e);
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void sendOutboxEvents() {
        List<OutboxOrder> events = outboxRepository.findBySentFalse();
        events.forEach(this::processSingleEvent);
    }
}
