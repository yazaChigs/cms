package com.totalit.bloodbankstatement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.*;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
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
    @ApiOperation("saves quarantine stock  Details")
    public ResponseEntity<Map<String, Object>> saveQuarantinedStock( @RequestBody StockQuarantined stockQuarantined) throws JsonProcessingException {

//        ObjectMapper objectMapper = new ObjectMapper();
//        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockQuarantined));

        Branch branch = stockQuarantined.getBranch();
        Map<String, Object> response = new HashMap<>();
        try {
            if (stockQuarantined.getId()== null) {
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
                response.put("message", "added new stock quarantined Successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (stockQuarantined.getId() != null) {
                StockQuarantined quarantined = stockService.getByBranchAndActive(branch, Boolean.TRUE);
                if(quarantined != null) {
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
                    response.put("message", "updated stock quaratined Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
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
                    response.put("message", "added new stock quarantined Successfully");
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

    @PostMapping("/submit")
    @ApiOperation("uploads quarantine stock Details")
    public ResponseEntity<Map<String, Object>> submitQuarantinedStock( @RequestBody StockQuarantined stockQuarantined) throws JsonProcessingException {

        Branch branch = stockQuarantined.getBranch();
        Map<String, Object> response = new HashMap<>();
        try {
            if (stockQuarantined.getId()== null) {
                stockQuarantined.setActive(Boolean.FALSE);
                StockQuarantined stock = stockService.save(stockQuarantined);
                stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                    item.setStockQuarantined(stockQuarantined);
                    item.setActive(Boolean.FALSE);
                    stockIssuedToQuarantineService.save(item);
                });
                stockQuarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                    item.setStockQuarantined(stockQuarantined);
                    item.setActive(Boolean.FALSE);
                    stockReceivedFromQuarantineService.save(item);
                });

                response.put("stockQuarantined", stock);
                response.put("message", "added and submitted new stock quarantined Successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (stockQuarantined.getId() != null) {
                StockQuarantined quarantined = stockService.getByBranchAndActive(branch, Boolean.TRUE);
                if(quarantined != null) {
                    setUpObject(stockQuarantined,quarantined );
                    quarantined.setActive(Boolean.FALSE);
                    StockQuarantined stock = stockService.save(quarantined);
                    stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                        item.setStockQuarantined(stockQuarantined);
                        item.setActive(Boolean.FALSE);
                        stockIssuedToQuarantineService.save(item);
                    });
                    quarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                        item.setStockQuarantined(quarantined);
                        item.setActive(Boolean.FALSE);
                        stockReceivedFromQuarantineService.save(item);
                    });

                    response.put("stockQuarantined", stock);
                    response.put("message", "updated and submitted stock quarantined Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    stockQuarantined.setActive(Boolean.FALSE);
                    StockQuarantined stock = stockService.save(stockQuarantined);
                    stockQuarantined.getIssuedToQuarantines().stream().forEach(item -> {
                        item.setStockQuarantined(stockQuarantined);
                        item.setActive(Boolean.FALSE);
                        stockIssuedToQuarantineService.save(item);
                    });
                    stockQuarantined.getReceivedFromQuarantineds().stream().forEach(item ->{
                        stockReceivedFromQuarantineService.save(item);
                        item.setActive(Boolean.FALSE);
                        item.setStockQuarantined(stockQuarantined);
                    });

                    response.put("stockQuarantined", stock);
                    response.put("message", "added and submitted new stock quarantined Successfully");
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

    @PostMapping("/get-by-date")
    @ApiOperation("Returns all active company profiles")
    public StockQuarantined getByDate(@RequestBody StockQuarantined stockQuarantined) {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(stockQuarantined.getBranch());
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setBranches(branchList);
        searchDTO.setDate(stockQuarantined.getTodaysDate());
        return stockService.getQuarantineByDate(searchDTO, stockQuarantined.getBranch());
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