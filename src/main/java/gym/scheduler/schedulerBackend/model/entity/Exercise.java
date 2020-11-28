/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andrb
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e"),
    @NamedQuery(name = "Exercise.findByExerciseId", query = "SELECT e FROM Exercise e WHERE e.exerciseId = :exerciseId"),
    @NamedQuery(name = "Exercise.findByExerciseType", query = "SELECT e FROM Exercise e WHERE e.exerciseType = :exerciseType"),
    @NamedQuery(name = "Exercise.findByClassTime", query = "SELECT e FROM Exercise e WHERE e.classTime = :classTime")})
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exercise_id")
    private Integer exerciseId;
    @Basic(optional = false)
    @Column(name = "exercise_name")
    private String exerciseName;
    @Column(name = "example_link")
    private String exampleLink;
    @Column(name = "exercise_type")
    private String exerciseType;
    @Column(name = "class_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date classTime;

    public Exercise() {
    }

    public Exercise(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Exercise(Integer exerciseId, String exerciseName) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
    }

    public Exercise(String exerciseName, String exampleLink, String exerciseType, Date classTime) {
        this.exerciseName = exerciseName;
        this.exampleLink = exampleLink;
        this.exerciseType = exerciseType;
        this.classTime = classTime;
    }
    
   

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExampleLink() {
        return exampleLink;
    }

    public void setExampleLink(String exampleLink) {
        this.exampleLink = exampleLink;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Date getClassTime() {
        return classTime;
    }

    public void setClassTime(Date classTime) {
        this.classTime = classTime;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exerciseId != null ? exerciseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.exerciseId == null && other.exerciseId != null) || (this.exerciseId != null && !this.exerciseId.equals(other.exerciseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.Exercise[ exerciseId=" + exerciseId + " ]";
    }
    
}
