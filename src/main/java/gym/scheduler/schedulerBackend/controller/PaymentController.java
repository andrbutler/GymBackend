/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import gym.scheduler.schedulerBackend.model.entity.Payment;
import gym.scheduler.schedulerBackend.model.repository.PaymentRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import java.net.URI;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author andrb
 */
@RestController
@RequestMapping("/api/payments")

public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all/{id}")
    public List<Payment> retrieveAllUserPayments(@PathVariable(name = "id") int id) {
        return paymentRepository.findByUserId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdatePayment(@PathVariable(name = "id") int id, @RequestBody Payment request) {

        Payment p = paymentRepository.findByPaymentId(id);

        p.setAmountDue(request.getAmountDue());
        p.setDueDate(request.getDueDate());
        p.setStatus(request.getStatus());
        p.setTransactionDate(request.getTransactionDate());

        Payment result = paymentRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{paymentid}")
                .buildAndExpand(result.getPaymentId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "payment updated successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addNewPayment(@RequestBody Payment request) {

        Payment p = new Payment(request.getAmountDue(), request.getStatus(), request.getDueDate(), request.getUserId());

        Payment result = paymentRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{paymentid}")
                .buildAndExpand(result.getPaymentId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "payment created successfully"));
    }

}
