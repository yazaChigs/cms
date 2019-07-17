package com.totalit.bloodbankstatement.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.controller.admin.BranchController;
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
    @Resource
    private StockReceivedFromAvailableService stockReceivedFromAvailableService;
    @Resource
    private StockIssuedToAvailableService stockIssuedToAvailableService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveAvailableStock(@RequestBody StockAvailable stockAvailable) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//          System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockAvailable));

          Branch branch = stockAvailable.getBranch();

//        Map<String, Object> response =validate(stockAvailable);
        Map<String, Object> response = new HashMap<>();
        try {
            if (stockAvailable.getId()== null) {
                StockAvailable stock = stockService.save(stockAvailable);
                stockAvailable.getIssuedToAvailable().stream().forEach(item -> {
                    item.setStockAvailable(stockAvailable);
                    stockIssuedToAvailableService.save(item);
                });
                stockAvailable.getReceivedFromAvailable().stream().forEach(item ->{
                    item.setStockAvailable(stockAvailable);
                    stockReceivedFromAvailableService.save(item);
                });

                response.put("stockAvailable", stock);
                response.put("message", "added new stock available Successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (stockAvailable.getId() != null) {
                StockAvailable stockAvailable1 = stockService.getByBranchAndActive(branch, Boolean.TRUE);
                if (stockAvailable1 != null) {
                    setUpObject(stockAvailable, stockAvailable1);
                    StockAvailable stock = stockService.save(stockAvailable1);
                    stockAvailable1.getIssuedToAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable1);
                        stockIssuedToAvailableService.save(item);
                    });
                    stockAvailable1.getReceivedFromAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable1);
                        stockReceivedFromAvailableService.save(item);
                    });

                    response.put("stockAvailable", stock);
                    response.put("message", "updated stock available Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    stockAvailable.getIssuedToAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable);
                        stockIssuedToAvailableService.save(item);
                    });
                    stockAvailable.getReceivedFromAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable);
                        stockReceivedFromAvailableService.save(item);
                    });
                    StockAvailable stock = stockService.save(stockAvailable);

                    response.put("stockAvailable", stock);
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

    @PostMapping("/submit")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> submitAvailableStock(@RequestBody StockAvailable stockAvailable) throws JsonProcessingException {

        Branch branch = stockAvailable.getBranch();

//        Map<String, Object> response =validate(stockAvailable);
        Map<String, Object> response = new HashMap<>();
        try {
            if (stockAvailable.getId()== null) {
                stockAvailable.setActive(Boolean.FALSE);
                StockAvailable stock = stockService.save(stockAvailable);
                stockAvailable.getIssuedToAvailable().stream().forEach(item -> {
                    item.setStockAvailable(stockAvailable);
                    item.setActive(Boolean.FALSE);
                    stockIssuedToAvailableService.save(item);
                });
                stockAvailable.getReceivedFromAvailable().stream().forEach(item ->{
                    item.setStockAvailable(stockAvailable);
                    item.setActive(Boolean.FALSE);
                    stockReceivedFromAvailableService.save(item);
                });

//                response.put("stockAvailable", stock);
                response.put("message", "added and submitted new stock available Successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if (stockAvailable.getId() != null) {
                StockAvailable stockAvailable1 = stockService.getByBranchAndActive(branch, Boolean.TRUE);
                if (stockAvailable1 != null) {
                    setUpObject(stockAvailable, stockAvailable1);
                    stockAvailable1.setActive(Boolean.FALSE);
                    StockAvailable stock = stockService.save(stockAvailable1);
                    stockAvailable1.getIssuedToAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable1);
                        item.setActive(Boolean.FALSE);
                        stockIssuedToAvailableService.save(item);
                    });
                    stockAvailable1.getReceivedFromAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable1);
                        item.setActive(Boolean.FALSE);
                        stockReceivedFromAvailableService.save(item);
                    });

//                    response.put("stockAvailable", stock);
                    response.put("message", "updated and submitted stock available Successfully");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    stockAvailable.setActive(Boolean.FALSE);
                    StockAvailable stock = stockService.save(stockAvailable);
                    stockAvailable.getIssuedToAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable);
                        item.setActive(Boolean.FALSE);
                        stockIssuedToAvailableService.save(item);
                    });
                    stockAvailable.getReceivedFromAvailable().stream().forEach(item -> {
                        item.setStockAvailable(stockAvailable);
                        item.setActive(Boolean.FALSE);
                        stockReceivedFromAvailableService.save(item);
                    });

//                    response.put("stockAvailable", stock);
                    response.put("message", "added and submitted new stock available Successfully");
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
    public StockAvailable getByBranchAndActive(@RequestParam("branchId") Long id) throws JsonProcessingException {

        Branch branch = service.get(id);
        List<StockIssuedToAvailable> stockIssuedToAvailable = new ArrayList<>();
        List<StockReceivedFromAvailable> stockReceivedFromAvailable = new ArrayList<>();

        StockAvailable stockAvailable = stockService.getByBranchAndActive(branch , Boolean.TRUE);

        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockAvailable));

        if (stockAvailable ==null){
            return null;
        }
        if(stockAvailable!=null && stockAvailable.getStockIssuedToAvailable()!=null) {
            stockAvailable.getStockIssuedToAvailable().stream().forEach(item -> {
                stockIssuedToAvailable.add(item);
            });
        }
        if(stockAvailable!=null && stockAvailable.getStockReceivedFromAvailable()!=null) {
            System.err.println("trying to get stock reeived");
            stockAvailable.getStockReceivedFromAvailable().stream().forEach(item -> {
                stockReceivedFromAvailable.add(item);
            });
        }
        stockAvailable.setIssuedToAvailable(stockIssuedToAvailable);
        stockAvailable.setReceivedFromAvailable(stockReceivedFromAvailable);

        return stockAvailable;
    }

    private Map<String, Object> validate(StockAvailable item) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> response = new HashMap<>();

        StockAvailable current = item;
        StockAvailable old = null;

        if (current.getId() != null){
            old = stockService.get(current.getId());
        }
        if(current.getId()==null){
            if (stockService.checkDuplicate(current, old)) {
                setUpObject(old, current);
            }
        }
        return response;
    }

    public void setUpObject(StockAvailable old, StockAvailable current) throws InvocationTargetException, IllegalAccessException {
        Date dateCreated = old.getDateCreated();
        User createdBy = old.getCreatedBy();
        BeanUtils.copyProperties(current, old);
        old.setDateCreated(dateCreated);
        old.setCreatedBy(createdBy);
    }

}
