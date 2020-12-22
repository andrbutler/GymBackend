/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.Exercise;
import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import gym.scheduler.schedulerBackend.model.entity.PresetSchedule;
import gym.scheduler.schedulerBackend.model.repository.ExerciseScheduleRepository;
import gym.scheduler.schedulerBackend.model.repository.PresetScheduleRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import gym.scheduler.schedulerBackend.payload.PresetScheduleRequest;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
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
@RequestMapping("/api/exercise_schedules")
public class ExcerciseScheduleController {

    @Autowired
    ExerciseScheduleRepository exerciseScheduleRepository;
    
    @Autowired
    PresetScheduleRepository presetScheduleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all/{id}")
    public List<ExerciseSchedule> retrieveAllUserExerciseSchedules(@PathVariable(name = "id") int id) {
        return exerciseScheduleRepository.findByUserId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateExerciseSchedule(@PathVariable(name = "id") int id, @RequestBody ExerciseSchedule request) {

        ExerciseSchedule e = exerciseScheduleRepository.findByScheduleId(id);

        e.setExerciseDate(request.getExerciseDate());
        e.setReps(request.getReps());
        e.setSetNumber(request.getSetNumber());

        ExerciseSchedule result = exerciseScheduleRepository.save(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise schedule updated successfully"));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/update/feedback/{id}")
    public ResponseEntity<?> UpdateExerciseDifficulty(@PathVariable(name = "id") int id, @RequestBody ExerciseSchedule request) {

        ExerciseSchedule e = exerciseScheduleRepository.findByScheduleId(id);

        e.setDifficultyFeedback(request.getDifficultyFeedback());

        ExerciseSchedule result = exerciseScheduleRepository.save(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise schedule updated successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addNewExerciseSchedule(@RequestBody ExerciseSchedule request) {

        ExerciseSchedule e = new ExerciseSchedule(request.getExerciseDate(), request.getSetNumber(), 
                request.getExerciseId(), request.getUserId(), request.getReps());

        ExerciseSchedule result = exerciseScheduleRepository.save(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise scheduled successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groupUpdate")
    public ResponseEntity<?> groupUpdate(@RequestBody ExerciseSchedule[] request) {
        
        
        for(ExerciseSchedule e : request){
        if (e.getScheduleId() == null){
            ExerciseSchedule newSchedule = new ExerciseSchedule(e.getExerciseDate(), e.getSetNumber(), 
                e.getExerciseId(), e.getUserId(), e.getReps());
           exerciseScheduleRepository.save(newSchedule);
        } else {
        ExerciseSchedule updateSchedule = exerciseScheduleRepository.findByScheduleId(e.getScheduleId());

        updateSchedule.setExerciseDate(e.getExerciseDate());
        updateSchedule.setReps(e.getReps());
        updateSchedule.setSetNumber(e.getSetNumber());
        exerciseScheduleRepository.save(updateSchedule);
        }
        
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{userid}")
                .buildAndExpand(request[0].getUserId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise scheduled successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/newFromPreset")
    public ResponseEntity<?> addNewExerciseScheduleFromPreset(@RequestBody PresetScheduleRequest request) {
        Calendar c =  Calendar.getInstance();
       
        List<PresetSchedule> presetSchedules = presetScheduleRepository.findByPresetId(request.getPresetId());
        
        for(PresetSchedule p : presetSchedules){
        c.setTime(request.getStartDate());
        c.add(Calendar.DATE, p.getDayFromStart());
        Date d = c.getTime();
        ExerciseSchedule e = new ExerciseSchedule(d, p.getSetNumber(), 
                p.getExerciseId(), request.getUserId(), p.getReps());

        exerciseScheduleRepository.save(e);
        
        
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{presetid}")
                .buildAndExpand(request.getPresetId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise scheduled successfully"));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public void deleteExerciseSchedule(@PathVariable(name = "id") int id) {

        exerciseScheduleRepository.deleteById(id);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groupDelete")
    public void groupDelete(@RequestBody int[] request){
        for (int id : request) {
           exerciseScheduleRepository.deleteById(id); 
        }
    }
}
