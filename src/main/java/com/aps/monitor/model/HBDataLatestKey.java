package com.aps.monitor.model;

import java.util.Date;

public class HBDataLatestKey {
    private Date dataTime;

    private String nodeMn;

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getNodeMn() {
        return nodeMn;
    }

    public void setNodeMn(String nodeMn) {
        this.nodeMn = nodeMn == null ? null : nodeMn.trim();
    }
}