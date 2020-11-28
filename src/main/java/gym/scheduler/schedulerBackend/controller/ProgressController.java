/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.Payment;
import gym.scheduler.schedulerBackend.model.entity.Progress;
import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.model.repository.PaymentRepository;
import gym.scheduler.schedulerBackend.model.repository.ProgressRepository;
import gym.scheduler.schedulerBackend.model.repository.UserRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/progress")

public class ProgressController {

    @Autowired
    ProgressRepository progressRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all/{id}")
    public List<Progress> retrieveAllUserProgress(@PathVariable(name = "id") int id) {
        return progressRepository.findByUserId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateProgress(@PathVariable(name = "id") int id, @RequestBody Progress request) {

        Progress p = progressRepository.findByProgressId(id);
        p.setWeight(request.getWeight());
        p.setWeeklyCalories(request.getWeeklyCalories());

        Progress result = progressRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{progressid}")
                .buildAndExpand(result.getProgressId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "progress updated successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @PostMapping("/new")
    public ResponseEntity<?> addNewProgress(@RequestBody Progress request) {

        Progress p = new Progress(request.getWeeklyCalories(), request.getWeight(), request.getWeek(), request.getUserId());
        if (progressRepository.findByUserId(p.getUserId()).contains(p.getWeek())) {
            return new ResponseEntity(new ApiResponse(false, "progress for that week already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Progress result = progressRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/progress/{progressid}")
                .buildAndExpand(result.getProgressId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "progress created successfully"));
    }
}
