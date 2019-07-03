package com.totalit.bloodbankstatement.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.controller.admin.BranchController;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.UserService;
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
@RequestMapping("/api/available-stock")
@Api(description = "Stock available resource")
public class StockAvailableController {

    public static final Logger logger = LoggerFactory.getLogger(StockAvailableController.class);
    @Resource
    private BranchService service;
    @Resource
    private UserService userService;
    @Resource
    private StockAvailableService stockService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody StockAvailable stockAvailable) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
          System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockAvailable));

        Map<String, Object> response = new HashMap<>();
        try {
            stockService.save(stockAvailable);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "available stock saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    @ApiOperation("Returns all active company profiles")
    public StockAvailable getByBranchAndActive(@RequestParam("branchId") Long id) {

        Branch branch = service.get(id);
        return stockService.getByBranchAndActive(branch , Boolean.TRUE);
    }

}
