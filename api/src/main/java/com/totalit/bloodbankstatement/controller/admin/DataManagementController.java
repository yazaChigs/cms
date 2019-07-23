package com.totalit.bloodbankstatement.controller.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.User;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.BranchDailyMinimalCapacityService;
import com.totalit.bloodbankstatement.service.admin.NoDaysRequiremetsService;
import com.totalit.bloodbankstatement.service.admin.UnadjustedDailyRequirementsService;
import com.totalit.bloodbankstatement.util.StringUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/admin/data")
@Api(description = "Data Management resource")
public class DataManagementController {

    public static final Logger logger = LoggerFactory.getLogger(DataManagementController.class);
    @Resource
    private BranchService service;
    @Resource
    private UnadjustedDailyRequirementsService unadjustedDailyRequirementsService;
    @Resource
    private NoDaysRequiremetsService noDaysRequiremetsService;
    @Resource
    private BranchDailyMinimalCapacityService branchDailyMinimalCapacityService;
    @Resource
    private UserService userService;

    @PostMapping("/save-no-days-requirements")
    @ApiOperation("Persists No Days Requirements Details")
    public ResponseEntity<Map<String, Object>> saveNoDaysRequirements( @RequestBody NoDaysRequiremets noDaysRequiremets) {
        Map<String, Object> response = new HashMap<>();
                noDaysRequiremetsService.save(noDaysRequiremets);
        response.put("message", "No Days Requirements saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-no-days-requirements")
    @ApiOperation("Returns all active No Days Requirements profiles")
    public NoDaysRequiremets getNoDaysRequirements() {
//        Branch brn = userService.getCurrentUser().getBranch();
//        if (brn==null){
//            return null;}
        return noDaysRequiremetsService.findFirstByOrderById();
    }

    @PostMapping("/save-ud-daily-requirements")
    @ApiOperation("Persists Unadjusted Daily Requirements Details")
    public ResponseEntity<Map<String, Object>> saveUnadjustedDailyRequirements( @RequestBody UnadjustedDailyRequirements unadjustedDailyRequirements) {
        Map<String, Object> response = new HashMap<>();
            unadjustedDailyRequirementsService.save(unadjustedDailyRequirements);
        response.put("message", "Unadjusted Daily Requirements saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-ud-daily-requirements")
    @ApiOperation("Returns all active Unadjusted Daily Requirements profiles")
    public UnadjustedDailyRequirements getUnadjustedDailyRequirements() {
//        Branch brn = userService.getCurrentUser().getBranch();
//        if (brn==null){
//            return null;}
        return unadjustedDailyRequirementsService.findFirstByOrderById();
    }

    @PostMapping("/save-bdmc")
    @ApiOperation("Persists Branch Daily Minimal Capacity Details")
    public ResponseEntity<Map<String, Object>> saveBranchDailyMinimalCapacity( @RequestBody BranchDailyMinimalCapacity branchDailyMinimalCapacity) {

        Map<String, Object> response = new HashMap<>();
                branchDailyMinimalCapacityService.save(branchDailyMinimalCapacity);
        response.put("message", "Branch Daily Minimal Capacity saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-bdmc")
    @ApiOperation("Returns all Branch Daily Minimal Capacity")
    public BranchDailyMinimalCapacity getBranchDailyMinimalCapacity() {
//        Branch brn = userService.getCurrentUser().getBranch();
//        if (brn==null){
//            return null;}
        return branchDailyMinimalCapacityService.findFirstByOrderById();
    }
    public void setUpObject(UnadjustedDailyRequirements old, UnadjustedDailyRequirements current) throws InvocationTargetException, IllegalAccessException {
        Date dateCreated = old.getDateCreated();
        User createdBy = old.getCreatedBy();
        BeanUtils.copyProperties(current, old);
        old.setDateCreated(dateCreated);
        old.setCreatedBy(createdBy);
    }

}
