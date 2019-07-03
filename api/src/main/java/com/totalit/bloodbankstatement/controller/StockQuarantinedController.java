package com.totalit.bloodbankstatement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.controller.admin.BranchController;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.service.*;
import com.totalit.bloodbankstatement.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/quarantined-stock")
@Api(description = "Stock quarantine resource")
public class StockQuarantinedController {

    public static final Logger logger = LoggerFactory.getLogger(StockQuarantinedController.class);
    @Resource
    private BranchService service;
    @Resource
    private UserService userService;
    @Resource
    private StockQuarantinedService stockService;
    @Resource
    private StockIssuedToQuarantineService stockIssuedToQuarantineService;
    @Resource
    private StockReceivedFromQuarantineService stockReceivedFromQuarantineService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestHeader(value = "Branch") String branchString, @RequestBody StockQuarantined stockQuarantined) {
        System.err.println("****************");
        System.err.println("**SAVING**");
        System.err.println("****************");
        Branch branch = StringUtils.stringToCompanyObject(branchString);
        Map<String, Object> response = new HashMap<>();
        try {
            stockQuarantined.setBranch(branch);

//            ObjectMapper objectMapper = new ObjectMapper();
//            System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockQuarantined));


            StockQuarantined st = stockService.save(stockQuarantined);
            stockQuarantined.getStockIssuedToQuarantines().forEach(complaint -> {
                complaint.setStockQuarantined(st);
                stockIssuedToQuarantineService.save(complaint);
            });
            stockQuarantined.getStockReceivedFromQuarantineds().forEach(complaint -> {
                complaint.setStockQuarantined(st);
                stockReceivedFromQuarantineService.save(complaint);
            });
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "available stock saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/get")
    @ApiOperation("Returns all active company profiles")
    public StockQuarantined getByBranchAndActive(@RequestParam("branchId") Long id) {

        Branch branch = service.get(id);
        return stockService.getByBranchAndActive(branch , Boolean.TRUE);
    }

    @GetMapping("/get-by-active-branch")
    @ApiOperation("Returns all active company profiles")
    public StockQuarantined get(@PathVariable("branchId") Long id) {
        return stockService.get(id);
    }

}