package com.totalit.bloodbankstatement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.*;
import com.totalit.bloodbankstatement.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


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
    public ResponseEntity<Map<String, Object>> saveCompanyPro( @RequestBody StockQuarantined stockQuarantined) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockQuarantined));

        Branch branch = stockQuarantined.getBranch();
        Map<String, Object> response = new HashMap<>();
        try {
            if (stockQuarantined.getId()== null) {
                System.err.println("***************");
                System.err.println("cant be using this one");
                System.err.println("***************");
                StockQuarantined stock = stockService.save(stockQuarantined);
                stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                    item.setStockQuarantined(stockQuarantined);
                    stockIssuedToQuarantineService.save(item);
                });
                stockQuarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                    item.setStockQuarantined(stockQuarantined);
                    stockReceivedFromQuarantineService.save(item);
                });

                response.put("stockQuarantined", stock);
                response.put("message", "added new stock available Successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (stockQuarantined.getId() != null) {
                StockQuarantined quarantined = stockService.getByBranchAndActive(branch, Boolean.TRUE);
                if(quarantined != null) {
                    System.err.println("***************");
                    System.err.println("using this one");
                    System.err.println("***************");
                    setUpObject(stockQuarantined,quarantined );
                    stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                        item.setStockQuarantined(stockQuarantined);
                        stockIssuedToQuarantineService.save(item);
                    });
                    quarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                        item.setStockQuarantined(quarantined);
                        stockReceivedFromQuarantineService.save(item);
                    });
                    StockQuarantined stock = stockService.save(quarantined);

                    response.put("stockQuarantined", stock);
                    response.put("message", "updated stock available Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    System.err.println("***************");
                    System.err.println("using this two");
                    System.err.println("***************");
                    stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                        item.setStockQuarantined(stockQuarantined);
                        stockIssuedToQuarantineService.save(item);
                    });
                    stockQuarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                        item.setStockQuarantined(stockQuarantined);
                        stockReceivedFromQuarantineService.save(item);
                    });
                    StockQuarantined stock = stockService.save(stockQuarantined);

                    response.put("stockQuarantined", stock);
                    response.put("message", "added new stock available Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }

        } catch (Exception ex) {
            System.err.println(ex);
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/get")
    @ApiOperation("Returns all active company profiles")
    public StockQuarantined getByBranchAndActive(@RequestParam("branchId") Long id) {

        Branch branch = service.get(id);
        List<StockIssuedToQuarantine> stockIssuedToQuarantines = new ArrayList<>();
        List<StockReceivedFromQuarantined> stockReceivedFromQuarantineds = new ArrayList<>();

        StockQuarantined stockQuarantined = stockService.getByBranchAndActive(branch , Boolean.TRUE);
        if (stockQuarantined ==null){
            return null;
        }
        if(stockQuarantined!=null && stockQuarantined.getStockIssuedToQuarantines()!=null) {
            stockQuarantined.getStockIssuedToQuarantines().stream().forEach(item -> {
                stockIssuedToQuarantines.add(item);
            });
        }
        if(stockQuarantined!=null && stockQuarantined.getStockReceivedFromQuarantineds()!=null) {
            stockQuarantined.getStockReceivedFromQuarantineds().stream().forEach(item -> {
                stockReceivedFromQuarantineds.add(item);
            });
        }
        stockQuarantined.setIssuedToQuarantines(stockIssuedToQuarantines);
        stockQuarantined.setReceivedFromQuarantineds(stockReceivedFromQuarantineds);

        return stockQuarantined;
    }

    @GetMapping("/get-by-active-branch")
    @ApiOperation("Returns all active company profiles")
    public StockQuarantined get(@PathVariable("branchId") Long id) {
        return stockService.get(id);
    }

    public void setUpObject(StockQuarantined old, StockQuarantined current) throws InvocationTargetException, IllegalAccessException {
        Date dateCreated = old.getDateCreated();
        User createdBy = old.getCreatedBy();
        BeanUtils.copyProperties(current, old);
        old.setDateCreated(dateCreated);
        old.setCreatedBy(createdBy);
    }

}