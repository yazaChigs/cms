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
import com.yaza.cms.domain.dto.BOStatsDTO;
import com.yaza.cms.domain.util.FileInfo;
import com.yaza.cms.pdf.TaskPdf;
import com.yaza.cms.service.*;
import com.yaza.cms.service.impl.StorageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public static final String PATIENT_PATH = "query";
    @Resource
    private QueryService service;
    @Resource
    private TaskService tService;
    @Resource
    private UserService userService;
    @Resource
    private StorageService storageService;
    @Resource
    private UserRoleService roleService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody Query q) {

        Map<String, Object> response= new HashMap<>();
        if (service.findByStanNoAndAccountNumberAndAmount(q.getStanNo(), q.getAccountNumber(), q.getAmount())!=null
                && service.findByStanNo(q.getStanNo())!=null)
        { response.put("message", "Query already exists");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);}
        Query result = new Query();
        Task task = new Task();
        Integer numberOfTasks = 0;
        User userToBeAssigned = new User();
        UserRole role = roleService.getByName("ROLE_E_BANKING");
        try {
            if (q!= null && q.getStatus().equals("WAITING")) {
//                q.setActive(Boolean.FALSE);
                q.setStatus("PENDING");
                result = service.save(q);
            }
            task.setQuery(q);
            task.setPriority(q.getPriority());
            task.setStatus("WAITING");

            for(User user:userService.findByUserRoles(role)) {
                Integer taskSize = tService.findByAssigneeAndStatusNot(user,"RESOLVED").size();
                if(taskSize<=numberOfTasks) {
                    numberOfTasks = taskSize;
                    userToBeAssigned = user;
                }
            }
            task.setAssignee(userToBeAssigned);
            tService.save(task);

        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(result != null) {
            response.put("data",result);
        }
        response.put("message", "Query Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get-all")
    @ApiOperation("Returns all active Queries")
    public List<Query> getAll() {
        return service.getAll();
    }

    @GetMapping("/get-all-pending")
    @ApiOperation("Returns all active Queries")
    public List<Query> getAllPending() {

        return service.findByStatusAndActive("PENDING",Boolean.TRUE);
    }

    @GetMapping("/get-all-overdue")
    @ApiOperation("Returns all active Queries")
    public List<Query> getAllOverDue() throws ParseException {
        List<Query> overdueQueries = new ArrayList<>();
        LocalDate todaysDate = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(todaysDate.toString());
        service.findByStatusAndActive("PENDING",Boolean.TRUE).forEach(query -> {
            long diff = (date1.getTime() - query.getDateCreated().getTime())/(24 * 60 * 60 * 1000);
            if(diff>3) {
                overdueQueries.add(query);
            }
        });

        return overdueQueries;
    }

    @GetMapping("/get-all-completed")
    @ApiOperation("Returns all complete Queries")
    public List<Query> getAllCompleted() {
        return service.findByStatus("RESOLVED");
    }

    @GetMapping("/get-by-user")
    @ApiOperation("Returns all Queries for user")
    public List<Query> getByUser() {
        return service.findByCreatedBy(userService.getCurrentUser());
    }

    @GetMapping("/get-user-stats")
    @ApiOperation("Returns all Queries for user")
    public BOStatsDTO getUserStats() {
        BOStatsDTO boStatsDTO = new BOStatsDTO();
        boStatsDTO.setPending(service.findByStatusAndActive("PENDING", Boolean.TRUE).size());
        boStatsDTO.setResolved(service.findByStatusAndActive("RESOLVED", Boolean.TRUE).size());
        return boStatsDTO;
    }

    @GetMapping("/get-by-stan")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Query getBystan(@RequestParam("id") String id) {
        return service.findByStanNo(id);
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Query getItem(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        Query q = service.get(id);
        String path = "cms" + File.separatorChar + PATIENT_PATH + File.separatorChar;
        List<FileInfo> fileInfoList = new ArrayList<>();
        FileInfo fileInfo = new FileInfo();
        String file = new String();
        if(q.getFileName()!=null) {
            file = q.getFileName();
            fileInfo.setFileName(file);
            fileInfo.setPath(path.concat(file));

            fileInfoList.add(fileInfo);

            q.setFileInfos(fileInfoList);
        }


        return q;
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ApiOperation("Upload File For Patient Radiology Order")
    public ResponseEntity<Map<String, Object>> uploadResult(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
//        log.info("Upload File For Lab Order");
        Map<String, Object> response = new HashMap<>();
//        PatientRadiologyOrder order = patientRadiologyOrderService.get(id);
        Query order = service.get(id);
        String fileName = saveFile(file, order);
        StringBuilder sb = new StringBuilder();
        if (order.getFileName() == null || order.getFileName().equals("")) {
            order.setFileName(fileName);
        } else {
            sb.append(order.getFileName()).append(",").append(fileName);
            order.setFileName(sb.toString());
        }
        Query laboratoryOrder =   service.save(order);
        laboratoryOrder.setFileInfos(getFileInfos(laboratoryOrder));
        response.put("data", laboratoryOrder);
        response.put("message", "Results Uploaded Successfully");

        String subject = "Notification of Uploaded Radilogy Test Results";

//        emailService.sendBasicMail(userEmail, subject);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/file")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> getFile(@RequestParam("filePath") String name) throws IOException {
        org.springframework.core.io.Resource file = storageService.loadFile(name);
        FileDataSource fds = new FileDataSource(file.getFile());
        String[] fileType = fds.getContentType().split("/");
     /*   if (fileType[1].equalsIgnoreCase("octet-stream") || fileType[1].equalsIgnoreCase("pdf")) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"").contentType(MediaType.APPLICATION_PDF)
                    .body(file);
        }*/
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    public String saveFile(MultipartFile file, Query order) {
        String[] fileFrags = file.getOriginalFilename().split("\\.");
        String extension = fileFrags[fileFrags.length - 1]; //get file extension
        String[] filenames = new String[0];
        if (order.getFileName() != null) {
            filenames = order.getFileName().split(",");
        }
        int numberOfFile = filenames.length + 1;
//        String orderName = order.getRadiologyName() + "-" + order.getRadiologyOrder().getName();
//        String ordername = orderName.replaceAll("\\s+", "").toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append(Calendar.getInstance().get(Calendar.YEAR)).append(Calendar.getInstance().get(Calendar.MONTH)).append(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).append("-");//current year
        sb.append(numberOfFile).append("-");
        sb.append(".").append(extension);
        String fileName = sb.toString().toLowerCase();
        String dir =   "cms" + File.separatorChar + PATIENT_PATH;
        Path path = storageService.createNewDirectory(dir);
        storageService.storeFile(file, path, fileName);
        return fileName;
    }

    public List<FileInfo> getFileInfos(Query order){
        String path = "cms" + File.separatorChar + PATIENT_PATH;
        List<FileInfo> fileInfos = new ArrayList<>();
        String[] files = new String[0];
        if (order.getFileName() != null) {
            files = order.getFileName().split(",");
        }
        for (String file : files) {
            FileInfo info = new FileInfo();
            info.setFileName(file);
            info.setPath(path.concat(file));
            fileInfos.add(info);
        }
        return fileInfos;
    }

//    public boolean checkDublicate(Query query) {
//        service.
//    }





}
