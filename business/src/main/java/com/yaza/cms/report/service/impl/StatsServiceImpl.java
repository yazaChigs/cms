package com.yaza.cms.report.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaza.cms.domain.config.Admin.CardQueries;
import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.dto.StatsDTO;
import com.yaza.cms.domain.util.DateUtil;
import com.yaza.cms.repo.CardQueryRepo;
import com.yaza.cms.repo.CategoryRepo;
import com.yaza.cms.repo.MobileBankingRepo;
import com.yaza.cms.repo.QueryRepo;
import com.yaza.cms.report.api.GenericReportModel;
import com.yaza.cms.report.service.StatsService;
import com.yaza.cms.report.service.StockReportService;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    @Resource
    private BranchService branchService;
    @Resource
    private QueryRepo queryRepo;
    @Resource
    private CategoryRepo categoryRepo;
    @Resource
    private MobileBankingRepo mobileBankingRepo;
    @Resource
    private TaskService taskService;
    @Resource
    private StatsService statsService;

    @Override
    public List<GenericReportModel> getDefaultReport() {
        return null;
    }

    @Override
    public List<GenericReportModel> GetLastMonthStockReport() {
        return null;
    }

    @Override
    public StatsDTO getInitialData() {

        List<Query> allQueries = queryRepo.findAll();
        List<Query> cardQueries = new ArrayList<>();
        List<MobileBanking> mobileQueries = mobileBankingRepo.findAll();
        List<String> categoryNames = new ArrayList<>();
        List<String> mobileNames = new ArrayList<>();
        List<Integer> categoryNumbers = new ArrayList<>();
        List<Integer> mobileNumbers = new ArrayList<>();
        Integer numberOfCards = 0;

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setWaiting(queryRepo.findByStatus("WAITING").size());
        statsDTO.setPending(queryRepo.findByStatus("PENDING").size());
        statsDTO.setResolved(queryRepo.findByStatus("RESOLVED").size());


        categoryRepo.findAll().forEach(category -> {
            categoryNames.add(category.getName());
            categoryNumbers.add(queryRepo.findByCategory(category).size())
            ;
        });
        statsDTO.setMobileQueriesNames(categoryNames);
        statsDTO.setCardQueries(categoryNumbers);

                ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(statsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return statsDTO;
    }
}
