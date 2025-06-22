package kpo.kafka;

import kpo.entities.PaymentResultOutbox;
import kpo.repositories.PaymentResultOutboxRepository;
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
    private final PaymentResultOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void processSingleEvent(PaymentResultOutbox event) {
        try {
            kafkaTemplate.send("orders-result", event.getPayload()).get(5, TimeUnit.SECONDS);
            event.setSent(true);
            outboxRepository.save(event);
        } catch (Exception e) {
            log.error("Failed to process event ID: {}", event.getId(), e);
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void sendOutboxEvents() {
        List<PaymentResultOutbox> events = outboxRepository.findBySentFalse();
        events.forEach(this::processSingleEvent);
    }
}
