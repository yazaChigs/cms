/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totalit.bloodbankstatement.controller.admin;

import com.totalit.bloodbankstatement.domain.config.User;
import com.totalit.bloodbankstatement.domain.config.UserRole;
import com.totalit.bloodbankstatement.service.UserRoleService;
import com.totalit.bloodbankstatement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/user-role")
@Api(description = "User role resource")
public class UserRoleController {

    @Resource
    private UserRoleService service;
    @Resource
    private UserService userService;
//    @Resource
//    private ModuleService moduleService;
    
     @PostMapping("/save")
    @ApiOperation("Persists new facility object to the database")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Item created sucessfully")
        ,
        @ApiResponse(code = 500, message = "System error occurred saving item")
        ,
        @ApiResponse(code = 400, message = "Usually a validation error. The message will largely depend on the failed validations")
    })
    public ResponseEntity<Map<String, Object>> create(@RequestBody UserRole item) {
        Map<String, Object> response = validate(item);
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            UserRole role  = service.save(item);
            response.put("role", role);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Item created sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @ApiOperation("Returns all active user roles")
    public List<UserRole> getAll() {
        return service.getAll();
    }
    
    

    @GetMapping("/get-user-roles/{userId}")
    @ApiOperation("Returns all  user roles")
    public Set<UserRole> getUserRoles(@PathVariable("userId") Long id) {
        List<UserRole> userRoles = getAll();
        Set<UserRole> roles = new HashSet<>();
        User user = userService.get(id);
        userRoles.forEach(role -> {
            user.getUserRoles().forEach(r -> {
                r.setSelected(Boolean.TRUE);
                if (!Objects.equals(role.getId(), r.getId())) {
                    roles.add(role);
                }
                roles.add(r);
            });
        });
        return roles;
    }
    
//     @GetMapping("/get-module-roles/{moduleId}")
//    @ApiOperation("Returns all  user roles")
//    public Set<UserRole> getModuleRoles(@PathVariable("moduleId") Long id) {
//        List<UserRole> userRoles = getAll();
//        Set<UserRole> roles = new HashSet<>();
//         Module user = moduleService.get(id);
//        userRoles.forEach(role -> {
//            user.getUserRoles().forEach(r -> {
//                r.setSelected(Boolean.TRUE);
//                if (!Objects.equals(role.getId(), r.getId())) {
//                    roles.add(role);
//                }
//                roles.add(r);
//            });
//        });
//        return roles;
//    }
    
    @GetMapping("/get-user-roles")
    @ApiOperation("Returns logged in user's granted authorities")
    public List<String> getGrantedAuthorities(){
        List<String> roles = new ArrayList<>();
        userService.getCurrentUser().getUserRoles().forEach(role ->{
            roles.add(role.getName());
        });
        return roles;
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "Returns user role of id passed as parameter", response = UserRole.class)
    public UserRole getItem(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        return service.get(id);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Deactivate user role of id passed as parameter")
    @ApiResponses({
        @ApiResponse(code = 500, message = "System error occurred deleting item")
        ,
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

    private Map<String, Object> validate(UserRole item) {
        Map<String, Object> response = new HashMap<>();
        if (item.getName() == null) {
            response.put("name", "Field is required");
        }
        UserRole current = item;
        UserRole old = null;
        if (current.getId() != null) {
            old = service.get(current.getId());
        }
        if(current.getId()==null){
        if (service.checkDuplicate(current, old)) {
            response.put("name", "Item already exists");
        }
        }
        return response;
    }

    @PutMapping("/update")
    @ApiOperation("Updates existing user role object")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Item updated sucessfully")
        ,
        @ApiResponse(code = 500, message = "System error occurred saving item")
        ,
        @ApiResponse(code = 400, message = "Usually a validation error. The message will largely depend on the failed validations")
    })
    public ResponseEntity<Map<String, Object>> update(@RequestBody UserRole item) {
        Map<String, Object> response = validate(item);
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            UserRole old = service.get(item.getId());
            setUpObject(old, item);
            service.save(old);
        } catch (Exception ex) {
            response.put("message", "System error occurred saving item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Item updated sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void setUpObject(UserRole old, UserRole current) {
        Date dateCreated = old.getDateCreated();
        User createdBy = old.getCreatedBy();
        BeanUtils.copyProperties(current, old);
        old.setDateCreated(dateCreated);
        old.setCreatedBy(createdBy);
    }
}
