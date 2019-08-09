package com.totalit.bloodbankstatement.report.service;

import java.io.Serializable;
import java.util.List;

public interface GenericReportService<T extends Serializable> {
    public List<T> getDefaultReport();
}
