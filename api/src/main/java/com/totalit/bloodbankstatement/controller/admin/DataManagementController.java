package com.totalit.bloodbankstatement.controller.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.BranchDailyMinimalCapacityService;
import com.totalit.bloodbankstatement.service.admin.NoDaysRequiremetsService;
import com.totalit.bloodbankstatement.service.admin.UnadjustedDailyRequirementsService;
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
    public ResponseEntity<Map<String, Object>> saveNoDaysRequirements(@RequestHeader(value = "Branch") String branchString, @RequestBody NoDaysRequiremets noDaysRequiremets) {
        Branch branch = StringUtils.stringToCompanyObject(branchString);


        Map<String, Object> response = new HashMap<>();
        try {
            noDaysRequiremets.setBranch(branch);


            noDaysRequiremets.setBranch(branch);
            if(noDaysRequiremets.getId() != null)
            {
                noDaysRequiremetsService.save(noDaysRequiremets);

            }
            else
            {
                Branch branch1 = userService.getCurrentUser().getBranch();
                NoDaysRequiremets noDaysRequiremets1 = noDaysRequiremetsService.getByBranch(branch1);


                if(noDaysRequiremets1 != null)
                {

                    noDaysRequiremets.setId(noDaysRequiremets1.getId());
                    noDaysRequiremets.setVersion(noDaysRequiremets1.getVersion());
                    noDaysRequiremets.setCreatedBy(noDaysRequiremets1.getCreatedBy());
                    noDaysRequiremets.setCreatedById(noDaysRequiremets1.getCreatedById());
                    noDaysRequiremets.setActive(noDaysRequiremets1.getActive());
                    noDaysRequiremets.setCreatedByName(noDaysRequiremets1.getCreatedByName());
                    noDaysRequiremets.setDateCreated(noDaysRequiremets1.getDateCreated());
                    noDaysRequiremets.setDateModified(noDaysRequiremets1.getDateModified());
                    noDaysRequiremets.setModifiedBy(noDaysRequiremets1.getModifiedBy());
                    noDaysRequiremets.setDeleted(noDaysRequiremets1.getDeleted());

                    noDaysRequiremetsService.save(noDaysRequiremets);

                }
                else{
                    noDaysRequiremetsService.save(noDaysRequiremets);
                }
            }

        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "No Days Requirements saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-no-days-requirements")
    @ApiOperation("Returns all active No Days Requirements profiles")
    public NoDaysRequiremets getNoDaysRequirements() {
        Branch brn = userService.getCurrentUser().getBranch();
        if (brn==null){
            return null;}
        return noDaysRequiremetsService.getByBranch(brn);
    }

    @PostMapping("/save-ud-daily-requirements")
    @ApiOperation("Persists Unadjusted Daily Requirements Details")
    public ResponseEntity<Map<String, Object>> saveUnadjustedDailyRequirements(@RequestHeader(value = "Branch") String branchString, @RequestBody UnadjustedDailyRequirements unadjustedDailyRequirements) {

        Branch branch = StringUtils.stringToCompanyObject(branchString);
        Map<String, Object> response = new HashMap<>();
        try {
            unadjustedDailyRequirements.setBranch(branch);
            if(unadjustedDailyRequirements.getId() != null)
            {
                unadjustedDailyRequirementsService.save(unadjustedDailyRequirements);

            }
            else
            {
                Branch branch1 = userService.getCurrentUser().getBranch();
                UnadjustedDailyRequirements unadjustedDailyRequirements1 = unadjustedDailyRequirementsService.getByBranch(branch1);


                if(unadjustedDailyRequirements1 != null)
                {

                    System.err.println("Object Found");
                    unadjustedDailyRequirements.setId(unadjustedDailyRequirements1.getId());
                    unadjustedDailyRequirements.setVersion(unadjustedDailyRequirements1.getVersion());
                    unadjustedDailyRequirements.setCreatedBy(unadjustedDailyRequirements1.getCreatedBy());
                    unadjustedDailyRequirements.setCreatedById(unadjustedDailyRequirements1.getCreatedById());
                    unadjustedDailyRequirements.setActive(unadjustedDailyRequirements1.getActive());
                    unadjustedDailyRequirements.setCreatedByName(unadjustedDailyRequirements1.getCreatedByName());
                    unadjustedDailyRequirements.setDateCreated(unadjustedDailyRequirements1.getDateCreated());
                    unadjustedDailyRequirements.setDateModified(unadjustedDailyRequirements1.getDateModified());
                    unadjustedDailyRequirements.setModifiedBy(unadjustedDailyRequirements1.getModifiedBy());
                    unadjustedDailyRequirements.setDeleted(unadjustedDailyRequirements1.getDeleted());

                    unadjustedDailyRequirementsService.save(unadjustedDailyRequirements);

                }
                else{
                    System.err.println("Object Not Found");
                    unadjustedDailyRequirementsService.save(unadjustedDailyRequirements);
                }
            }
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Unadjusted Daily Requirements saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-ud-daily-requirements")
    @ApiOperation("Returns all active Unadjusted Daily Requirements profiles")
    public UnadjustedDailyRequirements getUnadjustedDailyRequirements() {
        Branch brn = userService.getCurrentUser().getBranch();
        if (brn==null){
            return null;}
        return unadjustedDailyRequirementsService.getByBranch(brn);
    }

    @PostMapping("/save-bdmc")
    @ApiOperation("Persists Branch Daily Minimal Capacity Details")
    public ResponseEntity<Map<String, Object>> saveBranchDailyMinimalCapacity(@RequestHeader(value = "Branch") String branchString, @RequestBody BranchDailyMinimalCapacity branchDailyMinimalCapacity) {

        Branch branch = StringUtils.stringToCompanyObject(branchString);
        Map<String, Object> response = new HashMap<>();
        try {
            branchDailyMinimalCapacity.setBranch(branch);


            if(branchDailyMinimalCapacity.getId() != null)
            {
                branchDailyMinimalCapacityService.save(branchDailyMinimalCapacity);

            }
            else
            {
                Branch branch1 = userService.getCurrentUser().getBranch();
                BranchDailyMinimalCapacity branchDailyMinimalCapacity1 = branchDailyMinimalCapacityService.getByBranch(branch1);


                if(branchDailyMinimalCapacity1 != null)
                {

                    branchDailyMinimalCapacity.setId(branchDailyMinimalCapacity1.getId());
                    branchDailyMinimalCapacity.setVersion(branchDailyMinimalCapacity1.getVersion());
                    branchDailyMinimalCapacity.setCreatedBy(branchDailyMinimalCapacity1.getCreatedBy());
                    branchDailyMinimalCapacity.setCreatedById(branchDailyMinimalCapacity1.getCreatedById());
                    branchDailyMinimalCapacity.setActive(branchDailyMinimalCapacity1.getActive());
                    branchDailyMinimalCapacity.setCreatedByName(branchDailyMinimalCapacity1.getCreatedByName());
                    branchDailyMinimalCapacity.setDateCreated(branchDailyMinimalCapacity1.getDateCreated());
                    branchDailyMinimalCapacity.setDateModified(branchDailyMinimalCapacity1.getDateModified());
                    branchDailyMinimalCapacity.setModifiedBy(branchDailyMinimalCapacity1.getModifiedBy());
                    branchDailyMinimalCapacity.setDeleted(branchDailyMinimalCapacity1.getDeleted());

                    branchDailyMinimalCapacityService.save(branchDailyMinimalCapacity);

                }
                else{
                    branchDailyMinimalCapacityService.save(branchDailyMinimalCapacity);
                }
            }


        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Branch Daily Minimal Capacity saved Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-bdmc")
    @ApiOperation("Returns all Branch Daily Minimal Capacity")
    public BranchDailyMinimalCapacity getBranchDailyMinimalCapacity() {
        Branch brn = userService.getCurrentUser().getBranch();
        if (brn==null){
            return null;}
        return branchDailyMinimalCapacityService.getByBranch(brn);
    }

}
