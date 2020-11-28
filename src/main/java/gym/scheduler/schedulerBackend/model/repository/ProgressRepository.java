/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.repository;

import gym.scheduler.schedulerBackend.model.entity.Progress;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author andrb
 */
public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    @Override
    List<Progress> findAll();
    Progress findByProgressId(int progressId);
    List<Progress> findByUserId(int userId);
    Optional<Progress> findByWeek(Date week);
}
