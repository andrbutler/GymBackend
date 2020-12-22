/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import gym.scheduler.schedulerBackend.model.entity.Preset;
import gym.scheduler.schedulerBackend.model.repository.PresetRepository;
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
@RequestMapping("/api/presets")
public class PresetController {

    @Autowired
    PresetRepository presetRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Preset> retriveAllPresets() {
        return presetRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePreset(@PathVariable(name = "id") int id, @RequestBody Preset request) {

        Preset p = presetRepository.findByPresetId(id);
        
        if (presetRepository.findByPresetName(request.getPresetName()).isPresent() && !(p.getPresetName().equals(request.getPresetName()))) {
            return new ResponseEntity(new ApiResponse(false, "a preset with that name already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        p.setPresetName(request.getPresetName());

        Preset result = presetRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{presetname}")
                .buildAndExpand(result.getPresetName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "preset updated successfully"));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addPreset(@RequestBody Preset request) {
    if (presetRepository.findByPresetName(request.getPresetName()).isPresent()) {
            return new ResponseEntity(new ApiResponse(false, "a preset with that name already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Preset p = new Preset(request.getPresetName());

        Preset result = presetRepository.save(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/exercise_schedules/{presetname}")
                .buildAndExpand(result.getPresetName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "preset added successfully"));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public void deletePreset(@PathVariable(name = "id") int id) {
        
        presetRepository.deleteById(id);

    }
}
