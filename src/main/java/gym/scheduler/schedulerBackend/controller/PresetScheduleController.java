/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import gym.scheduler.schedulerBackend.model.entity.PresetSchedule;
import gym.scheduler.schedulerBackend.model.repository.ExerciseScheduleRepository;
import gym.scheduler.schedulerBackend.model.repository.PresetScheduleRepository;
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
@RequestMapping("/api/preset_schedules")
public class PresetScheduleController {

    @Autowired
    PresetScheduleRepository presetScheduleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all/{id}")
    public List<PresetSchedule> retrieveAllPresetExerciseSchedulesByPreset(@PathVariable(name = "id") int id) {
        return presetScheduleRepository.findByPresetId(id);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allschedules")
    public List<PresetSchedule> retrieveAllPresetExerciseSchedules() {
        return presetScheduleRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePresetSchedule(@PathVariable(name = "id") int id, @RequestBody PresetSchedule request) {

        PresetSchedule p = presetScheduleRepository.findByScheduleId(id);

        p.setDayFromStart(request.getDayFromStart());
        p.setReps(request.getReps());
        p.setSetNumber(request.getSetNumber());

        PresetSchedule result = presetScheduleRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/preset_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "preset schedule updated successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groupUpdate")
    public ResponseEntity<?> groupUpdate(@RequestBody PresetSchedule[] request) {

        for (PresetSchedule p : request) {
            if (p.getScheduleId() == null) {
                PresetSchedule newSchedule = new PresetSchedule(p.getDayFromStart(), p.getExerciseId(),
                        p.getSetNumber(), p.getReps(), p.getPresetId());;
                presetScheduleRepository.save(newSchedule);
            } else {
                PresetSchedule updateSchedule = presetScheduleRepository.findByScheduleId(p.getScheduleId());
                updateSchedule.setDayFromStart(p.getDayFromStart());
                updateSchedule.setReps(p.getReps());
                updateSchedule.setSetNumber(p.getSetNumber());

                presetScheduleRepository.save(updateSchedule);
            }

        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{presetid}")
                .buildAndExpand(request[1].getPresetId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "exercise scheduled successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addNewPresetSchedule(@RequestBody PresetSchedule request) {

        PresetSchedule p = new PresetSchedule(request.getDayFromStart(), request.getExerciseId(),
                request.getSetNumber(), request.getReps(), request.getPresetId());

        PresetSchedule result = presetScheduleRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/preset_schedules/{scheduleid}")
                .buildAndExpand(result.getScheduleId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "preset schedule created successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public void deletePresetSchedule(@PathVariable(name = "id") int id) {

        presetScheduleRepository.deleteById(id);

    }
}
