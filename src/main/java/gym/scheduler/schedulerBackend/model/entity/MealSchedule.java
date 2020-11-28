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
@Table(name = "meal_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MealSchedule.findAll", query = "SELECT m FROM MealSchedule m"),
    @NamedQuery(name = "MealSchedule.findByScheduleId", query = "SELECT m FROM MealSchedule m WHERE m.scheduleId = :scheduleId"),
    @NamedQuery(name = "MealSchedule.findByMealDate", query = "SELECT m FROM MealSchedule m WHERE m.mealDate = :mealDate"),
    @NamedQuery(name = "MealSchedule.findByUserId", query = "SELECT m FROM MealSchedule m WHERE m.userId = :userId")})
public class MealSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Basic(optional = false)
    @Column(name = "meal_date")
    @Temporal(TemporalType.DATE)
    private Date mealDate;
    @Basic(optional = false)
    @Column(name = "meal_type")
    private String mealType;
     @Basic(optional = false)
    @Column(name = "meal_id")
    private int mealId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public MealSchedule() {
    }

    public MealSchedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public MealSchedule(Integer scheduleId, Date mealDate, String mealType) {
        this.scheduleId = scheduleId;
        this.mealDate = mealDate;
        this.mealType = mealType;
    }

    public MealSchedule(Date mealDate, String mealType, int mealId, int userId) {
        this.mealDate = mealDate;
        this.mealType = mealType;
        this.mealId = mealId;
        this.userId = userId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
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
        if (!(object instanceof MealSchedule)) {
            return false;
        }
        MealSchedule other = (MealSchedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.MealSchedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
