package com.yaza.cms.report.service;

import com.yaza.cms.domain.dto.AdminStatsDTO;
import com.yaza.cms.domain.dto.StatsDTO;
import com.yaza.cms.report.api.GenericReportModel;

public interface AdminStatsService extends GenericReportService<GenericReportModel> {

    AdminStatsDTO getInitialData();
}
