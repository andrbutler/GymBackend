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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrb
 */
@Entity
@Table(name = "progress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Progress.findAll", query = "SELECT p FROM Progress p"),
    @NamedQuery(name = "Progress.findByProgressId", query = "SELECT p FROM Progress p WHERE p.progressId = :progressId"),
    @NamedQuery(name = "Progress.findByWeeklyCalories", query = "SELECT p FROM Progress p WHERE p.weeklyCalories = :weeklyCalories"),
    @NamedQuery(name = "Progress.findByWeight", query = "SELECT p FROM Progress p WHERE p.weight = :weight"),
    @NamedQuery(name = "Progress.findByWeek", query = "SELECT p FROM Progress p WHERE p.week = :week"),
    @NamedQuery(name = "Progress.findByUserId", query = "SELECT p FROM Progress p WHERE p.userId = :userId")})
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Integer progressId;
    @Column(name = "weekly_calories")
    private Integer weeklyCalories;
    @Column(name = "weight")
    private Integer weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "week")
    @Temporal(TemporalType.DATE)
    private Date week;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public Progress() {
    }

    public Progress(Integer progressId) {
        this.progressId = progressId;
    }

    public Progress(Integer progreessId, Date week) {
        this.progressId = progressId;
        this.week = week;
    }

    public Progress(Integer weeklyCalories, Integer weight, Date week, int userId) {
        this.weeklyCalories = weeklyCalories;
        this.weight = weight;
        this.week = week;
        this.userId = userId;
    }

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgreessId(Integer progreessId) {
        this.progressId = progreessId;
    }

    public Integer getWeeklyCalories() {
        return weeklyCalories;
    }

    public void setWeeklyCalories(Integer weeklyCalories) {
        this.weeklyCalories = weeklyCalories;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getWeek() {
        return week;
    }

    public void setWeek(Date week) {
        this.week = week;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progressId != null ? progressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.progressId == null && other.progressId != null) || (this.progressId != null && !this.progressId.equals(other.progressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.entity.Progress[ progreessId=" + progressId + " ]";
    }
    
}
