/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.controller.admin;

import com.opencsv.CSVReader;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.User;
import com.totalit.bloodbankstatement.domain.config.UserRole;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/admin/branch")
@Api(description = "Company Profile resource")
public class BranchController {

    public static final Logger logger = LoggerFactory.getLogger(BranchController.class);
    @Resource
    private BranchService service;
    @Resource
    private UserService userService;
//    @Autowired
//    private StorageService storageService;
//    @Resource
//    private PatientService patientService;

    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCompanyPro(@RequestBody Branch branch) {

        Map<String, Object> response = validate(branch);
        if (!response.isEmpty()) {
            response.put("message", "Validation error?");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            service.save(branch);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Company Details Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-user-branch")
    @ApiOperation("Returns all active company profiles")
    public Branch getUserBranch() {
          return   userService.getCurrentUser().getBranch();
//        return service.getAll();
    }


    @GetMapping("/get-all")
    @ApiOperation("Returns all active company profiles")
    public List<Branch> getAll() {
        return service.getAll();
    }

    @GetMapping("/get-all-for-user")
    @ApiOperation("Returns all active company profiles")
    public List<Branch> getAllForUser(@RequestParam("branchId") Long branchId) {
        Branch branch = service.get(branchId);
        return service.getByUserBranch(branch);
    }
    
    @GetMapping("/get-item")
    @ApiOperation(value = "Returns company profile of id passed as parameter", response = UserRole.class)
    public Branch getItem(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        return service.get(id);
    }

//    @GetMapping("/profile-pic/{companyId}")
//    @ResponseBody
//    public ResponseEntity<org.springframework.core.io.Resource> getFile(@PathVariable("companyId") Long companyId) {
//          if(companyId != null) {
//              Company company = service.get(companyId);
//              String name = company.getFileName();
//              org.springframework.core.io.Resource file = storageService.loadFile(name);
//              if(file != null){
//              return ResponseEntity.ok()
//                      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                      .body(file);
//              }
//          }
//        return null;
//    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Deactivate company profile of id passed as parameter")
    @ApiResponses({
        @ApiResponse(code = 500, message = "System error occurred deleting item"),
        @ApiResponse(code = 200, message = "Item deleted sucessfully")
    })
    public ResponseEntity<Map<String, Object>> delete(@ApiParam(name = "id", value = "Id of object to be deleted") @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(service.get(id));
        } catch (Exception ex) {
            response.put("message", "System error occurred deleting item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Item deleted sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Updates existing user role object")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Item updated sucessfully"),
        @ApiResponse(code = 500, message = "System error occurred saving item"),
        @ApiResponse(code = 400, message = "Usually a validation error. The message will largely depend on the failed validations")
    })
    public ResponseEntity<Map<String, Object>> update(@RequestBody Branch item) {
        Map<String, Object> response = validate(item);
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Branch old = service.get(item.getId());
            setUpObject(old, item);
            service.save(old);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Item updated sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void setUpObject(Branch old, Branch current) {
        Date dateCreated = old.getDateCreated();
        User createdBy = old.getCreatedBy();
        BeanUtils.copyProperties(current, old);
        old.setDateCreated(dateCreated);
        old.setCreatedBy(createdBy);
    }

    private Map<String, Object> validate(Branch item) {
        Map<String, Object> response = new HashMap<>();
        if (item.getBranchName() == null) {
            response.put("name", "Field is required");
        }
        Branch current = item;
        Branch old = null;
        if (current.getId() != null) {
            old = service.get(current.getId());
        }
        if (service.checkDuplicate(current, old)) {
            response.put("name", "Item already exists");
        }
        return response;
    }

//    public static List<Patient> readFile(File file) {
//        List<Patient> list = new ArrayList<>();
//        try {
//            FileReader fileReader = new FileReader(file);
//            String currentLine[];
//            CSVReader csvReader = new CSVReader(fileReader);
//            while ((currentLine = csvReader.readNext()) != null) {
//                Patient patient = createFromStringArray(currentLine);
//                list.add(patient);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }

//    static Patient createFromStringArray(String[] input) throws ParseException {
//        Patient patient = new Patient();
//        patient.setPatientUUID(input[0]);
//        patient.setFirstName(input[1]);
//        patient.setLastName(input[2]);
//        patient.setNationalId(input[3]);
//        patient.setGender(Gender.valueOf(input[4]));
//
//        DateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
//                try {
//                    if (input[5]!=null && !input[5].equalsIgnoreCase("")) {
//                        String dateString = input[5];
//                        if (dateString.contains("-")) {
//                            dateString = dateString.replace("-", "/");
//                        }
//                        patient.setDob(format.parse(dateString));
//                    }
//                } catch (ParseException ex) {
//                    //ex.printStackTrace();
//                    format = new SimpleDateFormat("MM/dd/yy");
//                    try {
//                        patient.setDob(format.parse(input[5]));
//                    } catch (ParseException e) {
//                        //e.printStackTrace();
//                    }
//
//                }
//
//
//        ContactDetails contactDetails = new ContactDetails();
//        Address address = new Address();
//        Employer employer = new Employer();
//        employer.setName("");
//        NextOfKin nextOfKin = new NextOfKin();
//        nextOfKin.setFirstName("");
//        BillingOther billingOther = new BillingOther();
//        billingOther.setFirstName("");
//if(input[6].length()<20){
//        contactDetails.setPrimaryMobile(input[6]);
//}
//else{
//     contactDetails.setPrimaryMobile("");
//}
//        contactDetails.setEmail(input[7]);
//
//
//        patient.setContactDetails(contactDetails);
//        patient.setEmployer(employer);
//        patient.setNextOfKin(nextOfKin);
//        patient.setBillingOther(billingOther);
//        patient.setAddress(getAddress( input[8]));
//
//        return patient;
//    }
//
//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//
//    private Boolean validate(Patient item) {
//        Patient current = item;
//        Patient old = null;
//        if (current.getNationalId() != null && current.getId() == null) {
//            old = patientService.findByNationalId(item.getNationalId());
//        }
//        if (old != null) {
//            if (patientService.checkDuplicate(current, old)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public static Address getAddress(String address){
//        String[] a = address.trim().split("\\s+");
//        List<String> list = new ArrayList<>();
//        for(String s : a){
//            list.add(s);
//        }
//        int size = list.size();
//        if(size>5){
//        StringBuilder street = new StringBuilder();
//        street.append(list.get(0));
//        street.append(" ");
//        street.append(list.get(1));
//        street.append(" ");
//
//        Address a1 = new Address();
//        a1.setStreet(street.toString());
//        a1.setCity(list.get(size - 1));
//        a1.setSuburb(list.get(size-2));
//
//        return a1;
//        }
//        else{
//            if(address.length()<50){
//                Address address1 = new Address();
//                address1.setStreet(address);
//                return address1;
//            }
//        }
//
//        return null;
//
//    }
}
