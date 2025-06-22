package kpo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import kpo.entities.InboxOrder;
import kpo.entities.PaymentResultOutbox;
import kpo.enums.PaymentStatus;
import kpo.exceptions.InsufficientFundsException;
import kpo.entities.BankAccountEntity;
import kpo.repositories.BankAccountRepository;
import kpo.repositories.InboxOrderRepository;
import kpo.repositories.PaymentResultOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final BankAccountRepository bankAccountRepository;
    private final ObjectMapper mapper = new ObjectMapper();
    private final InboxOrderRepository inboxRepository;
    private final PaymentResultOutboxRepository outboxRepository;

    public BankAccountEntity createAccount(Integer userId, Integer startingBalance) {
        if (bankAccountRepository.existsByUserId(userId)) {
            return getAccountByUserId(userId);
        }
        BankAccountEntity account = new BankAccountEntity(userId, startingBalance);
        bankAccountRepository.save(account);
        return account;
    }

    public BankAccountEntity getAccountByUserId(Integer userId) {
        return bankAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("No such BankAccount"));
    }

    public BankAccountEntity replenishAccount(Integer userId, Integer amount) {
        BankAccountEntity account = getAccountByUserId(userId);
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);
        return account;
    }

    public BankAccountEntity withdraw(Integer userId, Integer amount) {
        BankAccountEntity account = getAccountByUserId(userId);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
        return account;
    }

    @Scheduled(fixedDelay = 5000)
    public void checkInboxAndProcessOrder() {
        log.info("Проверка1");
        List<InboxOrder> orders = inboxRepository.findByProcessedFalse();
        log.info(orders.toString());
        orders.forEach(this::processOrder);
    }

    @Transactional
    void processOrder(InboxOrder order) {

        PaymentStatus paymentStatus;
        String payload;

        try {
            withdraw(order.getUserId(), order.getAmount());
            paymentStatus = PaymentStatus.COMPLETE;
        } catch (Exception e) {
            log.error("Error at processing order {}: {}", order.getId(), e.getMessage());
            paymentStatus = PaymentStatus.CANCELLED;
        }

        try {
            payload = mapper.writeValueAsString(Map.of(
                    "orderId", order.getOrderId(),
                    "status", paymentStatus
            ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        PaymentResultOutbox event = new PaymentResultOutbox(payload, false);
        order.setProcessed(true);

        log.info("Проверка 2 {}", paymentStatus);
        inboxRepository.save(order);
        outboxRepository.save(event);
    }
}


