package com.yaza.cms.report.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.dto.AdminStatsDTO;
import com.yaza.cms.domain.dto.StatsDTO;
import com.yaza.cms.repo.*;
import com.yaza.cms.report.api.GenericReportModel;
import com.yaza.cms.report.service.AdminStatsService;
import com.yaza.cms.report.service.StatsService;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminStatsServiceImpl implements AdminStatsService {

    @Resource
    private BranchService branchService;
    @Resource
    private QueryRepo queryRepo;
    @Resource
    private TaskRepo taskRepo;
    @Resource
    private UserRepo userRepo;
    @Resource
    private CategoryRepo categoryRepo;
    @Resource
    private MobileBankingRepo mobileBankingRepo;
    @Resource
    private TaskService taskService;
    @Resource
    private StatsService statsService;
    @Resource
    private UserRoleRepo roleRepo;

    @Override
    public List<GenericReportModel> getDefaultReport() {
        return null;
    }

    @Override
    public List<GenericReportModel> GetLastMonthStockReport() {
        return null;
    }

    @Override
    public AdminStatsDTO getInitialData() {

        AdminStatsDTO statsDTO = new AdminStatsDTO();
        List<Task> allTasks = taskRepo.findAll();
        List<User> users = userRepo.findByUserRoles(roleRepo.findByName("ROLE_E_BANKING"));
        List<String> userNames = new ArrayList<>();
        List<Integer> pendingList = new ArrayList<>();
        List<Integer> resolvedList = new ArrayList<>();
        List<Double> totalSpentTime = new ArrayList<>();
        List<Double> expectedTime = new ArrayList<>();
        List<Double> efficiency = new ArrayList<>();
        Double tempTime;
        Double tempWorkTime;

        statsDTO.setPending(taskRepo.findByStatus("PENDING").size());
        statsDTO.setResolved(taskRepo.findByStatus("RESOLVED").size());

        users.forEach(user -> {
            userNames.add(user.getDisplayName());
        });

        users.forEach(s -> {
           List<Task> userTasks = taskRepo.findByAssignee(s);
           userTasks.forEach(task -> {
           });
        });

        for (User user: users) {
            tempTime = 72.00;
            tempWorkTime = 0.00;
            pendingList.add(taskRepo.findByAssigneeAndStatus(user,"PENDING").size());
            resolvedList.add(taskRepo.findByAssigneeAndStatus(user,"RESOLVED").size());

            for (Task task: taskRepo.findByAssignee(user)) {
                if(task != null) {
                    tempTime += tempTime;
                    tempWorkTime += checkNull(task.getSpentTime());
                }
            }
            totalSpentTime.add(tempTime);
            expectedTime.add(tempWorkTime);
            efficiency.add(((double) Math.round(tempWorkTime / tempTime)));
        }

        statsDTO.setUserNames(userNames);
        statsDTO.setPendingList(pendingList);
        statsDTO.setResolvedList(resolvedList);
        statsDTO.setEfficiency(efficiency);
        statsDTO.setTotalSpentTime(totalSpentTime);
        statsDTO.setExpectedTime(expectedTime);

                ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(statsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return statsDTO;
    }

    public static Double checkNull ( Double input){
        if(input == null){
            input = 00.0;
        }
        return input;
    }
    public static Integer checkZero (Integer input){
        if(input == 0){
            input = 1;
        }
        return input;
    }
}
