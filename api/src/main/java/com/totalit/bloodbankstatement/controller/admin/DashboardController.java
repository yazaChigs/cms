package com.totalit.bloodbankstatement.controller.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
import com.totalit.bloodbankstatement.domain.dto.StockInfoDTO;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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
    private StockAvailableService stockAvailableService;

    @PostMapping("/get-for-selected-branches")
    @ApiOperation("Returns info  for selected branches")
    public StockInfoDTO getForSelectedBranches(@RequestBody List<Branch> branches ) throws JsonProcessingException {

        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setBranches(branches);
       return stockAvailableService.getResult(searchDTO);

       // return searchDTO;
    }

    @PostMapping("/get-for-selected-branches-by-date")
    @ApiOperation("Returns info by date for selected branches")
    public StockInfoDTO getForSelectedBranchesByDate(@RequestBody SearchDTO dto) {

        return stockAvailableService.getResult(dto);

        // return null;
    }
}
