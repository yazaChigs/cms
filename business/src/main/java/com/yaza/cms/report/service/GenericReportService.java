package com.yaza.cms.report.service;

import java.io.Serializable;
import java.util.List;

public interface GenericReportService<T extends Serializable> {
    public List<T> getDefaultReport();

    public List<T> GetLastMonthStockReport();
}
