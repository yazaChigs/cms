/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.auth;

/**
 *
 * @author kanaz
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/token")
//@Api(description = "Authentication Token.  Json Web Token(JWT)")
public class AuthenticationResource {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Resource
//    private UserService userService;
//
//    @RequestMapping(value = "/generate", method = RequestMethod.POST)
//    @ApiOperation("Returns Session Token for the requested resource. Returns Error Code 401 for unauthorized access if user is not found")
//    public ResponseEntity<?> register(@ApiParam("Login user information(Username and Passord for validation) ") @RequestBody User loginUser) throws AuthenticationException {
//        System.err.println("Requesting Token.............");
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginUser.getUserName(),
//                        loginUser.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        final User user = userService.findByUserName(loginUser.getUserName());
//        final String token = jwtTokenUtil.generateToken(user);
//        return ResponseEntity.ok(new AuthToken(token, user.getCompany()));
//    }
//
//    @GetMapping("/user")
//    @ApiOperation("Returns Current User in Session")
//    public ResponseEntity<User> currentUser() {
//        System.err.println("Current User {}");
//        return ResponseEntity.ok(userService.getCurrentUser());
//    }
//
//    @GetMapping("/test")
//    @ApiOperation("Test Api")
//    public ResponseEntity<Void> test() {
//        System.err.println("Test {}");
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
