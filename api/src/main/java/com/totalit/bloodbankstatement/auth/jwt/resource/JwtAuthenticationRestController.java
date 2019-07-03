package com.totalit.bloodbankstatement.auth.jwt.resource;

import com.totalit.bloodbankstatement.auth.jwt.JwtTokenUtil;
import com.totalit.bloodbankstatement.domain.config.User;
import com.totalit.bloodbankstatement.domain.config.UserRole;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.UserRoleService;
import com.totalit.bloodbankstatement.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class JwtAuthenticationRestController {
    
    public static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationRestController.class);

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
        
       @Resource
       private UserService userService;
       
       @Resource
       private UserRoleService roleService;
       
         @Resource
       private BranchService companyService;
//         @Resource
//         private ModuleService moduleService;
//
//       @Autowired
//       private SwitchService switchService;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException{
                try{
		boolean successAuth = authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());		
                User user;
                if(successAuth){
                 String token = createToken(authenticationRequest);
                   user = userService.findByUserName(authenticationRequest.getUserName());
                   return ResponseEntity.ok(new AuthDetail(token, user));
                }
               else{
                   return null;
//                    user = switchService.checkCloudUser(authenticationRequest);//Rest Template to check if user Exist on Switch
//                    if (user != null && user.getCompany() != null) {
//                        saveRoles(user.getUserRoles()); //Save Roles To Cloud Database
//                        Company com = null;
//                            Company company = companyService.findByCustomerId(user.getCompany().getCustomerId());
//                            if (company == null) {// If Company Is Null then It is Saved to Cloud Database
//                                Set<Module> modules = user.getCompany().getModules(); // Modules From Switch
//                                Set<Module> modul = new HashSet<>();
//                                user.getCompany().setModules(null);
//                                for(Module module : modules){
//                                    Module m = moduleService.findByUuid(module.getUuid());
//                                    if(m != null){
//                                        modul.add(m);
//                                    }
//                                    else{//Save Module To Cloud Database since it does not exist in the database
//                                       module.setId(null);
//                                       Module mo = moduleService.save(module);
//                                       modul.add(mo);
//                                    }
//                                }
//                                user.getCompany().setId(null);
//                                user.getCompany().setModules(modul);
//                                logger.info("\n\nSaving Company: {}");
//                                com = companyService.save(user.getCompany());
//                            }
//                        //Save User From Switch to Cloud
//                        User cloudUser= userService.findByUserName(authenticationRequest.getUserName());
//                        if(cloudUser == null){//If Cloud User Is Null then, user from switch is saved to cloud Database
//
//                            user.setUserRoles(null);
//                            user.setId(null);
//                            user.setPassword(authenticationRequest.getPassword());
//                            user.setUserRoles(getSuperAdminRole());
//                            user.setCompany(com);
//                            logger.info("\n\nSaving User: {}");
//                            cloudUser = userService.save(user);
//                        }
//                       String token = createToken(authenticationRequest);
//                       logger.info("\n\nToken Response: {}", token);
//                       return ResponseEntity.ok(new AuthDetail(token, cloudUser));
//                    }
//
//                    else if(user != null && user.getCompany() == null){
//
//                        saveRoles(user.getUserRoles()); //Save Roles To Cloud Database
//                        User authUser= userService.findByUserName(authenticationRequest.getUserName());
//                        if(authUser == null){
//                            user.setUserRoles(null);
//                            user.setId(null);
//                            user.setUserRoles(getGlobalRole());
//                            logger.info("\n\nSaving User: {}", user);
//                            authUser = userService.save(user);
//                        }
//                       String token = createToken(authenticationRequest);
//                       logger.info("\n\nToken Response: {}", token);
//                       return ResponseEntity.ok(new AuthDetail(token, authUser));
//                    }
//                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                }
                catch(Exception e){
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

	}
        
        public void saveRoles(Set<UserRole> roles){
            for(UserRole role: roles){
               UserRole userRole =  roleService.findByUuid(role.getUuid());
               if(userRole == null){
                   role.setId(null);
                   roleService.save(role);
               }
            }
        }
        
        public String createToken(JwtTokenRequest authenticationRequest){
            authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
            final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        }
        public Set<UserRole> getSuperAdminRole(){
            Set<UserRole> roles = new HashSet<>();
            UserRole role = roleService.getByName("ROLE_SUPER_ADMIN");
            if(role != null){
                roles.add(role);
            }
            return roles;
        }
        public Set<UserRole> getGlobalRole(){
            Set<UserRole> roles = new HashSet<>();
            UserRole role = roleService.getByName("ROLE_GLOBAL");
            if(role != null){
                roles.add(role);
            }
            return roles;
        }

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		//String username = jwtTokenUtil.getUsernameFromToken(token);
		//JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private boolean authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                        logger.error("Credentials Validated Successfully");
                        return true;
		} catch (DisabledException e) {
			// throw new AuthenticationException("USER_DISABLED", e);
                        logger.error("USER_DISABLED", e);
                        return false;
		} catch (BadCredentialsException e) {
			// throw new AuthenticationException("INVALID_CREDENTIALS", e);
                        logger.error("INVALID_CREDENTIALS", e);
                        return false;
		}
	}
        
            @GetMapping("/user")
    @ApiOperation("Returns Current User in Session")
    public ResponseEntity<User> currentUser() {
        System.err.println("Current User {}");
        return ResponseEntity.ok(userService.getCurrentUser());
    }
        
}
