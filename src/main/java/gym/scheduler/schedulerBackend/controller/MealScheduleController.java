/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import gym.scheduler.schedulerBackend.model.entity.MealSchedule;
import gym.scheduler.schedulerBackend.model.repository.ExerciseScheduleRepository;
import gym.scheduler.schedulerBackend.model.repository.MealScheduleRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import java.net.URI;
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
@RequestMapping("/api/meal_schedules")
public class MealScheduleController {

    @Autowired
    MealScheduleRepository mealScheduleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all/{id}")
    public List<MealSchedule> retrieveAllUserMealSchedules(@PathVariable(name = "id") int id) {
        return mealScheduleRepository.findByUserId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateMealSchedule(@PathVariable(name = "id") int id, @RequestBody MealSchedule request) {

        MealSchedule m = mealScheduleRepository.findByScheduleId(id);

        m.setMealDate(request.getMealDate());
        m.setMealType(request.getMealType());

        MealSchedule result = mealScheduleRepository.save(m);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "meal schedule updated successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groupUpdate")
    public ResponseEntity<?> groupUpdate(@RequestBody MealSchedule[] request) {

        for (MealSchedule m : request) {
            if (m.getScheduleId() == null) {
                MealSchedule newSchedule = new MealSchedule(m.getMealDate(), m.getMealType(),
                        m.getMealId(), m.getUserId());
                mealScheduleRepository.save(newSchedule);
            } else {
                MealSchedule updateSchedule = mealScheduleRepository.findByScheduleId(m.getScheduleId());
                updateSchedule.setMealDate(m.getMealDate());
                updateSchedule.setMealType(m.getMealType());

                mealScheduleRepository.save(updateSchedule);
            }

        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{mealid}")
                .buildAndExpand(request[0].getMealId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise scheduled successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addNewMealSchedule(@RequestBody MealSchedule request) {

        MealSchedule m = new MealSchedule(request.getMealDate(), request.getMealType(),
                request.getMealId(), request.getUserId());

        MealSchedule result = mealScheduleRepository.save(m);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/meal_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "meal scheduled successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public void deleteMealSchedule(@PathVariable(name = "id") int id) {

        mealScheduleRepository.deleteById(id);

    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groupDelete")
    public void groupDelete(@RequestBody int[] request){
        for (int id : request) {
           mealScheduleRepository.deleteById(id); 
        }
    }
}
