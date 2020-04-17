package com.yaza.cms.report.api;

import java.io.Serializable;
import java.util.List;

public class GenericReportModel implements Serializable {

    private List<String> row;

    public GenericReportModel() {

    }

    public GenericReportModel(List<String> row) {
        this.row = row;
    }

    public List<String> getRow() {
        return row;
    }

    public void setRow(List<String> row) {
        this.row = row;
    }
}
