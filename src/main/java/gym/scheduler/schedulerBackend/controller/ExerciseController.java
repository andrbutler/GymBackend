/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.Address;
import gym.scheduler.schedulerBackend.model.entity.Exercise;
import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.model.repository.ExerciseRepository;
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
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all")
    public List<Exercise> retrieveAllExercises() {
	return exerciseRepository.findAll();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/live")
    public List<Exercise> retrieveAllLiveClasses() {
	return exerciseRepository.findByExerciseType("LIVE");
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public Exercise retrieveById(@PathVariable(name = "id") int id) {
        return exerciseRepository.findByExerciseId(id);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addExercise(@RequestBody Exercise request) {

         if(exerciseRepository.findByExerciseName(request.getExerciseName()).isPresent() &&
                 !(request.getExerciseType().equals("LIVE"))) {
            return new ResponseEntity(new ApiResponse(false, "exercise " + request.getExerciseName() +  " already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Exercise e = new Exercise();
        e.setExerciseName(request.getExerciseName());
        e.setExampleLink(request.getExampleLink());
        e.setExerciseType(request.getExerciseType());
        e.setClassTime(request.getClassTime());

        
        Exercise result = exerciseRepository.save(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercises/{exercisename}")
                .buildAndExpand(result.getExerciseName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise added successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateExercise(@PathVariable(name = "id") int id, @RequestBody Exercise request) {

        Exercise e = exerciseRepository.findByExerciseId(id);
        
         if(exerciseRepository.findByExerciseName(request.getExerciseName()).isPresent() &&
                 !(request.getExerciseType().equals("LIVE")) &&
                 !(request.getExerciseName().equals(e.getExerciseName()))) {
            return new ResponseEntity(new ApiResponse(false, "exercise " + request.getExerciseName() +  " already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        e.setExerciseName(request.getExerciseName());
        e.setExampleLink(request.getExampleLink());
        e.setExerciseType(request.getExerciseType());
        e.setClassTime(request.getClassTime());


        
        Exercise result = exerciseRepository.save(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercises/{exercisename}")
                .buildAndExpand(result.getExerciseName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise updated successfully"));
    }
}
