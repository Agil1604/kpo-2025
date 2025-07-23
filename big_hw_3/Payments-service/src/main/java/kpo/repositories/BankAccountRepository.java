package kpo.repositories;

import kpo.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    Optional<BankAccountEntity> findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}