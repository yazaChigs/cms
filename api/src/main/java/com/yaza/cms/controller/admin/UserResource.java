/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaza.cms.controller.BaseResource;
import com.yaza.cms.domain.config.Branch;
import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.service.UserRoleService;
import com.yaza.cms.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author kanaz
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/user")
public class UserResource extends BaseResource {

    public static final Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService roleService;

    @PostMapping("/save")
    @ApiOperation("Persists new user  object to the database")
    public ResponseEntity<Map<String, Object>> saveUser( @RequestBody User user) throws JsonProcessingException {
        logger.info("Saving User {}");

        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
//        Company company = StringUtils.stringToCompanyObject(companyString);
//        user.setCompany(company);
//        System.err.println(user.getBranch());
//        if(user.getDateOfBirth() != null){
//        user.setDob(DateUtil.getDateFromStringApi(user.getDateOfBirth()));
//        }
        Map<String, Object> response = validate(user);
        if (!response.isEmpty()) {
            response.put("message", "Validation error?");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            /*Physician p = null;
            if(user.getPhysician()!=null){
                Physician physician = user.getPhysician();
                physician.setFirstName(user.getFirstName());
                physician.setLastName(user.getLastName());
                physician.setCompany(company);
              p = physicianService.save(physician);                               
            }
            if(p!=null){
                user.setPhysician(p);
            }*/
//            user.setWorkstation(null);
            userService.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    @ApiOperation("Change User Password")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestParam("id") Long id, @RequestParam("password") String password) {
        logger.info("Changing User Password{}");
        HashMap<String, Object> response = new HashMap<>();
        try {
            User user = userService.get(id);
            user.setPassword(password);
            User u = userService.changePassword(user);
            response.put("message", "Password Changed Successfully");
            response.put("user", u);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get-all")
    @ApiOperation("Returns User List")
    public ResponseEntity<?> getUserTypes() {
        logger.info("Retrieving User  List {}");
//        Company company = StringUtils.stringToCompanyObject(companyString);
        List<User> users = new ArrayList<>();
        for (User u : userService.getAll()) {
            u.getUserRoles().forEach(r -> r.setSelected(Boolean.TRUE));
            users.add(u);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-all-by-manager")
    @ApiOperation("Returns User List")
    public ResponseEntity<?> getUserByManager() {
        logger.info("Retrieving User  List for manager {}");
//        Company company = StringUtils.stringToCompanyObject(companyString);
        List<User> users = new ArrayList<>();
        Branch branch = userService.getCurrentUser().getBranch();
        UserRole role = roleService.getByName("ROLE_E_BANKING");
        users = userService.findByUserRoles(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/get-current-user")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/inactive")
    @ApiOperation("Returns All Inactive User Accounts")
    public ResponseEntity<?> getInactiveAccounts() {
        logger.info("Retrieving Inactive User Accounts{}");
//        Company company = StringUtils.stringToCompanyObject(companyString);
        return new ResponseEntity<>(userService.getAllInActive(), HttpStatus.OK);
    }

    @GetMapping("/activate/{userId}")
    @ApiOperation("Activate User Account")
    public ResponseEntity<Void> activateUserAccount(@PathVariable("userId") Long id) {
        logger.info("Activate User Account{}");
        User user = userService.get(id);
        user.setActive(Boolean.TRUE);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Deactivate User Account")
    public ResponseEntity<Map<String, Object>> deactivateUser(@ApiParam(name = "id", value = "Id of object to be deleted") @RequestParam("id") Long id) {
        logger.info("Deactivating User Account");
        Map<String, Object> response = new HashMap<>();
        try {
            userService.delete(userService.get(id));
        } catch (Exception ex) {
            response.put("message", "System error occurred deleting item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User Account Deactivated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Object> validate(User item) {
        Map<String, Object> response = new HashMap<>();
        User current = item;
        User old = null;
        if (current.getId() != null) {
            old = userService.get(current.getId());
        }
        if (item == null) {
            if (userService.checkDuplicate(current, old)) {
                response.put("userName", "Username Exists");
            }
        }
        return response;
    }
}
