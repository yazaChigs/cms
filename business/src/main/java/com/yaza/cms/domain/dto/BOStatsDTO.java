package com.yaza.cms.domain.dto;

import java.io.Serializable;
import java.util.List;

public class BOStatsDTO implements Serializable {

    private Integer resolved;
    private Integer pending;


    public Integer getResolved() {
        return resolved;
    }

    public void setResolved(Integer resolved) {
        this.resolved = resolved;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }
}
