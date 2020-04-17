package com.yaza.cms.controller.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.dto.SearchDTO;
import com.yaza.cms.domain.dto.StockInfoDTO;
import com.yaza.cms.report.api.GenericReportModel;
import com.yaza.cms.report.service.StockReportService;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/dashboard")
@Api(description = "Dashboard Information")
public class DashboardController {

    public static final Logger logger = LoggerFactory.getLogger(BranchController.class);
    @Resource
    private BranchService service;
    @Resource
    private UserService userService;
    @Resource
    private StockReportService stockReportService;

//    @PostMapping("/get-for-selected-branches")
//    @ApiOperation("Returns info  for selected branches")
//    public StockInfoDTO getForSelectedBranches(@RequestBody List<Branch> branches ) throws JsonProcessingException {
//
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setBranches(branches);
//       return stockAvailableService.getResult(searchDTO);
//
//       // return searchDTO;
//    }

//    @PostMapping("/get-for-selected-branches-by-date")
//    @ApiOperation("Returns info by date for selected branches")
//    public StockInfoDTO getForSelectedBranchesByDate(@RequestBody SearchDTO dto) {
//        return stockAvailableService.getResult(dto);
//    }

    @GetMapping("/get-report")
    public List<GenericReportModel> getReport() {
        return stockReportService.getDefaultReport();
    }

    @GetMapping("/get-last-month-report")
    public List<GenericReportModel> getLastMonthStockReportForBranch() {
        return stockReportService.GetLastMonthStockReport();
    }
}
