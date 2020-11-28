/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.model.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andrb
 */
@Entity
@Table(name = "preset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preset.findAll", query = "SELECT p FROM Preset p"),
    @NamedQuery(name = "Preset.findByPresetId", query = "SELECT p FROM Preset p WHERE p.presetId = :presetId"),
    @NamedQuery(name = "Preset.findByPresetName", query = "SELECT p FROM Preset p WHERE p.presetName = :presetName")})
public class Preset implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "preset_name")
    private String presetName;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "preset_id")
    private Integer presetId;

    public Preset() {
    }

    public Preset(Integer presetId) {
        this.presetId = presetId;
    }

    public Preset(Integer presetId, String presetName) {
        this.presetId = presetId;
        this.presetName = presetName;
    }

    public Preset(String presetName) {
        this.presetName = presetName;
    }

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (presetId != null ? presetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preset)) {
            return false;
        }
        Preset other = (Preset) object;
        if ((this.presetId == null && other.presetId != null) || (this.presetId != null && !this.presetId.equals(other.presetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gym.scheduler.schedulerBackend.model.entity.Preset[ presetId=" + presetId + " ]";
    }


    
}
