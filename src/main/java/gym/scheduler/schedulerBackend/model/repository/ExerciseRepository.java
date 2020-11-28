/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.Exercise;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author andrb
 */
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
    @Override
    List<Exercise> findAll();
    Exercise findByExerciseId(int exerciseId);
    Optional<Exercise> findByExerciseName(String exerciseName);
    List<Exercise> findByExerciseType(String exerciseType);
    List<Exercise> findByClassTime(Date classTime); 
}
