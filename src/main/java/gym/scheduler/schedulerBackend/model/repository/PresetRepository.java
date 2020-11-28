/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.Preset;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrb
 */
public interface PresetRepository extends JpaRepository<Preset, Integer> {
     @Override
    List<Preset> findAll();
    Preset findByPresetId(int presetId);
    Optional<Preset> findByPresetName(String presetName);
}
