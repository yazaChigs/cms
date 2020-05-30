/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.controller.admin;

import com.yaza.cms.domain.config.Admin.TextMessaging;
import com.yaza.cms.domain.config.Query;
import com.yaza.cms.service.TaskService;
import com.yaza.cms.service.admin.TextMessagingService;
import com.yaza.cms.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tasu
 */
@CrossOrigin(origins = "*", maxAge = 200000)
@RestController
@RequestMapping("/api/sms")
@Api(description = "Queries resource")
public class  SmsController {

    public static final Logger logger = LoggerFactory.getLogger(SmsController.class);
    @Resource
    private TaskService service;
    @Resource
    private TextMessagingService txtService;
    @Resource
    private UserService userService;
    private String bulksms_ws;

    private URL url;
    private String strJson;
    private String ws_str;
    private String destinations;

    @PostMapping("/save-message-info")
    public ResponseEntity<Map<String, Object>> SaveTxtInfo(  @RequestBody TextMessaging textMessage) {
        Map<String, Object> response = new HashMap<>();
        String username = textMessage.getUsername();
        String webservicetoken = textMessage.getWebServiceToken();


        if (username==null || webservicetoken==null)
        {
            TextMessaging txt = txtService.save(textMessage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://portal.bulksmsweb.com/index.php?app=ws&u=" + username + "&h=" + webservicetoken + "&op=cr&to=0774077544&msg=test+only";
        ResponseEntity<String> status
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        Boolean saveStatus = status.getBody().startsWith("{\"status\":\"OK");
        if (saveStatus) {
            TextMessaging txt = txtService.save(textMessage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "please check your deatils");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-messaging")
    public TextMessaging getMessagingInfo() {
        TextMessaging info = txtService.findFirst();
        return info;
    }
    @GetMapping("/get-credit")
    public ResponseEntity<String> getCredit() {
        TextMessaging txt = txtService.findFirst();
        if (txt==null){
            return null;
        }
        String username = txt.getUsername();
        String webservicetoken = txt.getWebServiceToken();
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://portal.bulksmsweb.com/index.php?app=ws&u=" + username + "&h=" + webservicetoken + "&op=cr&to=0774077544&msg=test+only";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        return response;
    }

    @PostMapping("/send-text-to-one")
    public ResponseEntity<Map<String, Object>> sendOneSms(@RequestBody Query item, @RequestParam("patientId") Long patientId) {
        Map<String, Object> response = new HashMap<>();
        TextMessaging txt = txtService.findFirst();
        String username = txt.getUsername();
        String webservicetoken = txt.getWebServiceToken();
        String message = txt.getMessage();
        bulksms_ws = "http://portal.bulksmsweb.com/index.php?app=ws";
//        Appointment appointments = service.getByPatient(patientService.get(patientId), item.getFacility(), item.getPhysician(), item.getDate());

        String mobileNumber = item.getPhone();
        if (!mobileNumber.isEmpty()) {
            destinations = mobileNumber;
            try {
                ws_str = bulksms_ws + "&u=" + username + "&h=" + webservicetoken + "&op=pv";
                ws_str = ws_str + "&to=" + URLEncoder.encode(destinations, "UTF-8") + "&msg="
                        + URLEncoder.encode(String.format(message, item.getFullname()), "UTF-8");

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
                response.put("message", "message sent");
                //return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (MalformedURLException mue) {
                System.out.println("Ouch - a MalformedURLException happened.");
                mue.printStackTrace();
                response.put("message", "Ouch - a MalformedURLException happened.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            } catch (IOException ioe) {
                System.out.println("Oops- an IOException happened.");
                ioe.printStackTrace();
                response.put("message", "OOops- an IOException happened.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            message = "";
            destinations = "";
        } else {
//            System.err.println("**************************");
//            System.err.println(appointments.getPatient().getPrimaryMobile());
//            System.err.println("****************************");

            response.put("message", "patient has no phone number");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("message", "message sent");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
