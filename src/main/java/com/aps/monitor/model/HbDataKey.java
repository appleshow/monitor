package com.aps.monitor.model;

import java.util.Date;

public class HbDataKey {
    private Date dataTime;

    private String dataType;

    private String nodeMn;

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getNodeMn() {
        return nodeMn;
    }

    public void setNodeMn(String nodeMn) {
        this.nodeMn = nodeMn == null ? null : nodeMn.trim();
    }
}