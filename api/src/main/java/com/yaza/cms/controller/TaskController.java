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
import com.yaza.cms.domain.config.Admin.TextMessaging;
import com.yaza.cms.pdf.TaskPdf;
import com.yaza.cms.service.CategoryService;
import com.yaza.cms.service.QueryService;
import com.yaza.cms.service.TaskService;
import com.yaza.cms.service.UserService;
import com.yaza.cms.service.admin.TextMessagingService;
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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
    private TextMessagingService messagingService;
    @Resource
    private UserService userService;
    private String bulksms_ws;

    private URL url;
    private String strJson;
    private String ws_str;
    private String destinations;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody Task q) {
        Map<String, Object> response= new HashMap<>();
        try {
            service.save(q);
        if (q.getQuery()!= null && q.getQuery().getStatus().equals("WAITING")) {
            q.getQuery().setActive(Boolean.FALSE);
            q.getQuery().setStatus("PENDING");
            q.getQuery().setActionTaken(q.getActionTaken());
            queryService.save(q.getQuery());
        }
        if (q.getQuery()!= null && q.getStatus().equals("RESOLVED")) {
            q.getQuery().setStatus("RESOLVED");
            q.getQuery().setActionTaken(q.getActionTaken());
            queryService.save(q.getQuery());
            if(sendOneSms(q.getQuery())) {
            response.put("text","text message sent");
            }
            else {
                response.put("text","saved but error sending text message ");
            }
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

    @GetMapping("/is-there-complete")
    @ApiOperation("Returns all active Tasks for user")
    public Integer isThereCOmplete() {
        User currentUser =userService.getCurrentUser();
        Integer numberOfCopmplete = 0;
        numberOfCopmplete = service.findByCreatedByAndStatusAndActive(currentUser,"RESOLVED", Boolean.TRUE).size();
        if (numberOfCopmplete >0) {
            service.findByCreatedByAndStatusAndActive(currentUser,"RESOLVED", Boolean.TRUE).forEach(task ->
            {task.setActive(Boolean.FALSE);
            service.save(task);});
            return  numberOfCopmplete;
        }
        else {
            return null;
        }
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

//    @PostMapping("/send-text-to-one")
    public Boolean sendOneSms( Query item) {
        Map<String, Object> response = new HashMap<>();
        TextMessaging txt = messagingService.findFirst();
        String username = txt.getUsername();
        String webservicetoken = txt.getWebServiceToken();
//        String message = txt.getMessage();
        String message = "Dear %s, Your query of $ %s, Stan Number %s, %s has been resolved. %s";
        bulksms_ws = "http://portal.bulksmsweb.com/index.php?app=ws";
//        Appointment appointments = service.getByPatient(patientService.get(patientId), item.getFacility(), item.getPhysician(), item.getDate());

        String mobileNumber = item.getPhone();
        if (!mobileNumber.isEmpty()) {
            destinations = mobileNumber;
            try {
                ws_str = bulksms_ws + "&u=" + username + "&h=" + webservicetoken + "&op=pv";
                ws_str = ws_str + "&to=" + URLEncoder.encode(destinations, "UTF-8") + "&msg="
                        + URLEncoder.encode(String.format(message, item.getFullname(),item.getAmount(),item.getStanNo(),item.getCategory(),item.getActionTaken()), "UTF-8");

                url = new URL(ws_str);
                // Sttep 3: Open a URLConnection to the url.  //
                URLConnection conn = url.openConnection();         // throws an IOException
                // Step 4:
                // Convert the InputStreamReader to a Buffered Reader.      //
                // Buffering the stream makes the reading faster; the readLinevv  vvvvvvvvvvvvv        vv() method of the BufferedReader makes the reading easier.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                // Step 5:
                // Store the whole output in a variable. The output will be JSON string variable.
                String inputLine;
                strJson = "";
                while ((inputLine = br.readLine()) != null) {
                    strJson += inputLine;
                }
                br.close();

                //Variable strJson holds the reponse from the Webservice
                System.out.println(strJson);
                System.err.println("message sent");
                response.put("message", "message sent");
                //return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (MalformedURLException mue) {
                System.out.println("Ouch - a MalformedURLException happened.");
                mue.printStackTrace();
                response.put("message", "Ouch - a MalformedURLException happened.");
                return false;

            } catch (IOException ioe) {
                System.out.println("Oops- an IOException happened.");
                ioe.printStackTrace();
                response.put("message", "OOops- an IOException happened.");
                return false;
            }

            message = "";
            destinations = "";
        } else {
            response.put("message", "patient has no phone number");
            System.err.println("patient has no phone number");
            return false;
        }

        response.put("message", "message sent");
        return true;
    }
}
