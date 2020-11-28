/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.ExerciseSchedule;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrb
 */
public interface ExerciseScheduleRepository extends JpaRepository<ExerciseSchedule, Integer> {
    @Override
    List<ExerciseSchedule> findAll();
    List<ExerciseSchedule> findByUserId(int userId);
    ExerciseSchedule findByScheduleId(int scheduleId);
    List<ExerciseSchedule> findByExerciseDate(Date exerciseDate); 
}
