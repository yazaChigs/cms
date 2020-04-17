/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.controller;

import com.yaza.cms.controller.admin.BranchController;
import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.Query;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.service.BranchService;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/query")
@Api(description = "Queries resource")
public class QueryController {

    public static final Logger logger = LoggerFactory.getLogger(BranchController.class);
    @Resource
    private QueryService service;
    @Resource
    private UserService userService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody Query q) {
        Map<String, Object> response= new HashMap<>();
        try {
            service.save(q);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Query Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @ApiOperation("Returns all active Queries")
    public List<Query> getAll() {
        return service.findByStatusAndActive("WAITING",Boolean.TRUE);
    }

    @GetMapping("/get-all-pending")
    @ApiOperation("Returns all active Queries")
    public List<Query> getAllPending() {
        return service.findByStatusAndActive("WAITING",Boolean.FALSE);
    }

    @GetMapping("/get-all-completed")
    @ApiOperation("Returns all complete Queries")
    public List<Query> getAllCompleted() {
        return service.findByStatus("RESOLVED");
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Query getItem(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        return service.get(id);
    }

}
