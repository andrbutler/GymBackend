/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrb
 */
@Entity
@Table(name = "exercise_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExerciseSchedule.findAll", query = "SELECT e FROM ExerciseSchedule e"),
    @NamedQuery(name = "ExerciseSchedule.findByScheduleId", query = "SELECT e FROM ExerciseSchedule e WHERE e.scheduleId = :scheduleId"),
    @NamedQuery(name = "ExerciseSchedule.findByExerciseDate", query = "SELECT e FROM ExerciseSchedule e WHERE e.exerciseDate = :exerciseDate"),
    @NamedQuery(name = "ExerciseSchedule.findByUserId", query = "SELECT e FROM ExerciseSchedule e WHERE e.userId = :userId")
})
public class ExerciseSchedule implements Serializable {

    @Column(name = "reps")
    private int reps;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Basic(optional = false)
    @Column(name = "exercise_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exerciseDate;
    @Column(name = "set_number")
    private int setNumber;
    @Column(name = "difficulty_feedback")
    private int difficultyFeedback;
    @Basic(optional = false)
    @Column(name = "exercise_id")
    private int exerciseId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public ExerciseSchedule() {
    }

    public ExerciseSchedule(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public ExerciseSchedule(Integer scheduleId, Date exerciseDate) {
        this.scheduleId = scheduleId;
        this.exerciseDate = exerciseDate;
    }

    public ExerciseSchedule(Date exerciseDate, int setNumber, int exerciseId, int userId, int reps) {
        this.exerciseDate = exerciseDate;
        this.setNumber = setNumber;
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.reps = reps;
    }

    public ExerciseSchedule(Date exerciseDate, int difficultyFeedback, int exerciseId, int userId) {
        this.exerciseDate = exerciseDate;
        this.difficultyFeedback = difficultyFeedback;
        this.exerciseId = exerciseId;
        this.userId = userId;
    }
    
   
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDifficultyFeedback() {
        return difficultyFeedback;
    }

    public void setDifficultyFeedback(int difficultyFeedback) {
        this.difficultyFeedback = difficultyFeedback;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExerciseSchedule)) {
            return false;
        }
        ExerciseSchedule other = (ExerciseSchedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.ExerciseSchedule[ scheduleId=" + scheduleId + " ]";
    }

}