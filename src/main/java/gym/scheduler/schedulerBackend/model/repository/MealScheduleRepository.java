/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.MealSchedule;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrb
 */
public interface MealScheduleRepository extends JpaRepository<MealSchedule, Integer> {
      @Override
    List<MealSchedule> findAll();
    List<MealSchedule> findByUserId(int userId);
    MealSchedule findByScheduleId(int scheduleId);
    List<MealSchedule> findByMealDate(Date mealDate); 
    
}
