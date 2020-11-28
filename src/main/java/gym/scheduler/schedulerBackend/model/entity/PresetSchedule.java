/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.entity;

import gym.scheduler.schedulerBackend.model.entity.Exercise;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrb
 */
@Entity
@Table(name = "preset_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PresetSchedule.findAll", query = "SELECT p FROM PresetSchedule p"),
    @NamedQuery(name = "PresetSchedule.findByScheduleId", query = "SELECT p FROM PresetSchedule p WHERE p.scheduleId = :scheduleId"),
    @NamedQuery(name = "PresetSchedule.findByPresetId", query = "SELECT p FROM PresetSchedule p WHERE p.presetId = :presetId")})
public class PresetSchedule implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "day_from_start")
    private int dayFromStart;
    @Basic(optional = false)
    @Column(name = "exercise_id")
    private int exerciseId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Column(name = "set_number")
    private Integer setNumber;
    @Column(name = "reps")
    private Integer reps;
    @Basic(optional = false)
    @Column(name = "preset_id")
    private int presetId;

    public PresetSchedule() {
    }

    public PresetSchedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public PresetSchedule(Integer scheduleId, int dayFromStart) {
        this.scheduleId = scheduleId;
        this.dayFromStart = dayFromStart;
    }

    public PresetSchedule(int dayFromStart, int exerciseId, int setNumber, int reps, int presetId) {
        this.dayFromStart = dayFromStart;
        this.exerciseId = exerciseId;
        this.setNumber = setNumber;
        this.reps = reps;
        this.presetId = presetId;
    }

    public PresetSchedule(int dayFromStart, int exerciseId, Integer scheduleId, Integer setNumber, Integer reps, int presetId) {
        this.dayFromStart = dayFromStart;
        this.exerciseId = exerciseId;
        this.scheduleId = scheduleId;
        this.setNumber = setNumber;
        this.reps = reps;
        this.presetId = presetId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDayFromStart() {
        return dayFromStart;
    }

    public void setDayFromStart(int dayFromStart) {
        this.dayFromStart = dayFromStart;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public int getPresetId() {
        return presetId;
    }

    public void setPresetId(int presetId) {
        this.presetId = presetId;
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
        if (!(object instanceof PresetSchedule)) {
            return false;
        }
        PresetSchedule other = (PresetSchedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.entity.PresetSchedule[ scheduleId=" + scheduleId + " ]";
    }

   

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
    
}
