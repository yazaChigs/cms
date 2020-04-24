/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.controller;

import com.yaza.cms.controller.admin.BranchController;
import com.yaza.cms.domain.config.Admin.CardQueries;
import com.yaza.cms.domain.config.Admin.MobileBanking;
import com.yaza.cms.domain.config.Category;
import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.service.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/category")
@Api(description = "Queries resource")
public class CategoryController {

    public static final Logger logger = LoggerFactory.getLogger(BranchController.class);
    @Resource
    private CategoryService service;
    @Resource
    private UserService userService;
    @Resource
    private CardQueryService cardQueryService;
    @Resource
    private MobileBankngService mobileBankngService;


    @PostMapping("/save")
    @ApiOperation("Persists Company Details")
    public ResponseEntity<Map<String, Object>> saveCard(@RequestBody Category q) {
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
    public List<Category> getAllCard() {
        return service.getAll();
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public Category getItemMobile(@ApiParam(name = "id", value = "Id used to fetch the object") @RequestParam("id") Long id) {
        return service.get(id);
    }

    @GetMapping("/get-all-by-type")
    @ApiOperation(value = "Returns query of id passed as parameter", response = UserRole.class)
    public List<Category> getCategoriesByType(@ApiParam(name = "type", value = "Id used to fetch the object") @RequestParam("type") String type) {
        return service.findByQueryType(type);
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "Deactivate company profile of id passed as parameter")
    @ApiResponses({
            @ApiResponse(code = 500, message = "System error occurred deleting item"),
            @ApiResponse(code = 200, message = "Item deleted sucessfully")
    })
    public ResponseEntity<Map<String, Object>> deleteMobile(@ApiParam(name = "id", value = "Id of object to be deleted") @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            mobileBankngService.delete(mobileBankngService.get(id));
        } catch (Exception ex) {
            response.put("message", "System error occurred deleting item");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Item deleted sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
