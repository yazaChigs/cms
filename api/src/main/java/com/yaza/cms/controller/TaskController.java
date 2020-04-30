/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.controller;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.yaza.cms.controller.admin.BranchController;
import com.yaza.cms.domain.config.*;
import com.yaza.cms.pdf.TaskPdf;
import com.yaza.cms.service.CategoryService;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.TaskService;
import com.yaza.cms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/task")
@Api(description = "Queries resource")
public class TaskController {

    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Resource
    private TaskService service;
    @Resource
    private QueryService queryService;
    @Resource
    private UserService userService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody Task q) {
        Map<String, Object> response= new HashMap<>();
        try {
            service.save(q);
        if (q.getQuery()!= null && q.getQuery().getStatus().equals("WAITING")) {
            q.getQuery().setActive(Boolean.FALSE);
            q.getQuery().setStatus("PENDING");
            queryService.save(q.getQuery());
        }
        if (q.getQuery()!= null && q.getStatus().equals("RESOLVED")) {
            q.getQuery().setStatus("RESOLVED");
            queryService.save(q.getQuery());
        }


        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Task Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> updateTask(@RequestBody Task q) {
        Map<String, Object> response= new HashMap<>();
        try {
            service.save(q);

        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Task updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @ApiOperation("Returns all active Tasks")
    public List<Task> getAll() {
        User currentUser = userService.getCurrentUser();
        return service.findByAssigneeNotAndStatusNot(currentUser, "RESOLVED");
    }

    @GetMapping("/get-overdue")
    @ApiOperation("Returns all active Tasks")
    public List<Task> getOverdueTasks() {
        User currentUser = userService.getCurrentUser();
        List<Task> allTasks = service.findByStatus("PENDING");
        List<Task> overDurTasks = new ArrayList<>();
        LocalDate todaysDate = LocalDate.now();
        allTasks.forEach(task -> {
            long diffDays = ChronoUnit.DAYS.between(LocalDate.parse(task.getDateCreated().toString()), todaysDate);
            System.err.println("****************");
            System.err.println(diffDays);
            System.err.println("****************");
            if (diffDays>3) {
                overDurTasks.add(task);
            }
        });

        return overDurTasks;
    }

    @GetMapping("/get-my-tasks")
    @ApiOperation("Returns all active Tasks for user")
    public List<Task> getMyTasks() {
        User currentUser =userService.getCurrentUser();
        return service.findByAssigneeAndStatusNot(currentUser, "RESOLVED");
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Task getItem(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        return service.get(id);
    }

    @GetMapping("/get-by-query")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Task getItemByQuery(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        Query query = queryService.get(id);
        return service.findByQuery(query);
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPatientStatement() throws IOException, DocumentException, WriterException {
        User currentUser =userService.getCurrentUser();
        List<Task> tasks = service.findByAssigneeAndStatusNot(currentUser, "RESOLVED");
        TaskPdf pdf = new TaskPdf();
        ByteArrayInputStream bis = pdf.generatePdf(tasks);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=accountstatement.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
}
