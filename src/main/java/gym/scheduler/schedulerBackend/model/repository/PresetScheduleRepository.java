/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.PresetSchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author andrb
 */
public interface PresetScheduleRepository extends CrudRepository<PresetSchedule, Integer> {
     @Override
    List<PresetSchedule> findAll();
    PresetSchedule findByScheduleId(int scheduleId);
    List<PresetSchedule> findByPresetId(int presetId);
}
