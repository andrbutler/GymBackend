/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.payload;

import java.util.Date;

/**
 *
 * @author andrb
 */
public class PresetScheduleRequest {
    private Date startDate;
    private int userId;
    private int presetId;

    public PresetScheduleRequest(Date startDate, int presetId, int userId) {
       this.presetId = presetId;
        this.startDate = startDate;
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPresetId() {
        return presetId;
    }

    public void setPresetId(int presetId) {
        this.presetId = presetId;
    }
    
}
