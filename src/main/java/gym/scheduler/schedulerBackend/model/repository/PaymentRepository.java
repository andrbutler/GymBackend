/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.Payment;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrb
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Override
    List<Payment> findAll();
    List<Payment> findByUserId(int userId);
    Payment findByPaymentId(int paymentId);
    Optional<Payment> findByDueDate(Date dueDate); 
    Optional<Payment> findByTransactionDate(Date transactionDate); 
}
