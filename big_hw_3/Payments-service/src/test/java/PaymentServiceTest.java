import kpo.entities.BankAccountEntity;
import kpo.entities.InboxOrder;
import kpo.exceptions.InsufficientFundsException;
import kpo.repositories.BankAccountRepository;
import kpo.repositories.InboxOrderRepository;
import kpo.repositories.PaymentResultOutboxRepository;
import kpo.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private InboxOrderRepository inboxRepository;
    @Mock
    private PaymentResultOutboxRepository outboxRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void createAccount_newAccount_savesAndReturns() {
        when(bankAccountRepository.existsByUserId(1)).thenReturn(false);
        when(bankAccountRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        BankAccountEntity account = paymentService.createAccount(1, 100);

        assertNotNull(account);
        assertEquals(1, account.getUserId());
        assertEquals(100, account.getBalance());
    }

    @Test
    void createAccount_existingAccount_returnsExisting() {
        BankAccountEntity existing = new BankAccountEntity(1, 200);
        when(bankAccountRepository.existsByUserId(1)).thenReturn(true);
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.of(existing));

        BankAccountEntity result = paymentService.createAccount(1, 100);

        assertSame(existing, result);
    }

    @Test
    void getAccountByUserId_exists_returnsAccount() {
        BankAccountEntity account = new BankAccountEntity(1, 100);
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.of(account));

        BankAccountEntity result = paymentService.getAccountByUserId(1);

        assertSame(account, result);
    }

    @Test
    void getAccountByUserId_notExists_throwsException() {
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> paymentService.getAccountByUserId(1));
    }

    @Test
    void replenishAccount_valid_updatesBalance() {
        BankAccountEntity account = new BankAccountEntity(1, 100);
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.of(account));
        when(bankAccountRepository.save(account)).thenReturn(account);

        BankAccountEntity result = paymentService.replenishAccount(1, 50);

        assertEquals(150, result.getBalance());
    }

    @Test
    void withdraw_sufficientFunds_updatesBalance() {
        BankAccountEntity account = new BankAccountEntity(1, 100);
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.of(account));
        when(bankAccountRepository.save(account)).thenReturn(account);

        BankAccountEntity result = paymentService.withdraw(1, 50);

        assertEquals(50, result.getBalance());
    }

    @Test
    void withdraw_insufficientFunds_throwsException() {
        BankAccountEntity account = new BankAccountEntity(1, 30);
        when(bankAccountRepository.findByUserId(1)).thenReturn(Optional.of(account));

        assertThrows(InsufficientFundsException.class, () -> paymentService.withdraw(1, 50));
    }
}