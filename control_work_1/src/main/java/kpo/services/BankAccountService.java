package kpo.services;

import kpo.domains.BankAccount;
import kpo.exceptions.EntityNotFoundException;
import kpo.factories.DomainFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final Map<Integer, BankAccount> accounts = new HashMap<>();
    private final DomainFactory factory;

    public BankAccount createAccount(String name, Integer balance) {
        BankAccount account = factory.createAccount(name, balance);
        accounts.put(account.getId(), account);
        return account;
    }

    public BankAccount updateAccountName(Integer id, String newName) {
        BankAccount account = getAccount(id);
        account.setName(newName);
        return account;
    }

    public BankAccount getAccount(Integer id) {
        return Optional.ofNullable(accounts.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    public BankAccount deleteAccount(Integer id) {
        BankAccount removedAccount = accounts.remove(id);
        if (removedAccount == null) {
            throw new EntityNotFoundException("Account not found");
        }
        return removedAccount;
    }
}