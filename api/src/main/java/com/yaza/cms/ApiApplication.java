package com.yaza.cms;

import com.yaza.cms.domain.config.User;
import com.yaza.cms.domain.config.UserRole;
import com.yaza.cms.service.UserRoleService;
import com.yaza.cms.service.UserService;
import com.yaza.cms.service.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer implements CommandLineRunner{

	@Resource
	StorageService storageService;
	@Autowired
	private UserRoleService roleService;
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		storageService.init();
		roleScript();
		UserRole role = new UserRole();
		UserRole ur = null;
		role.setActive(Boolean.TRUE);
		role.setName("ROLE_GLOBAL");
		role.setDescription("Global role");
		UserRole userRole = roleService.getByName(role.getName());
		if(userRole==null){
			ur = roleService.save(role);
		}else{
			ur = userRole;
		}
		Set<UserRole> roles = new HashSet<>();
		roles.add(ur);
		User user = new User();
		user.setFirstName("Malvin");
		user.setLastName("Maponde");
		user.setActive(Boolean.TRUE);
		//user.setAddress("17057 Zengeza 4 Chitungwiza");

		user.setUserName("malvin@gmail.com");
		user.setPassword("Pass1234");
		user.setUserRoles(roles);
		User u = userService.findByUserName(user.getUserName());
		if(u == null){
			userService.save(user);
		}

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(ApiApplication.class);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	private void roleScript(){
		UserRole role1 = new UserRole();
		role1.setActive(Boolean.TRUE);
		role1.setName("ROLE_MANAGER");
		role1.setDescription("manager  role");
		UserRole userRole1 = roleService.getByName(role1.getName());
		if(userRole1==null){
			roleService.save(role1);
		}
		UserRole role2 = new UserRole();
		role2.setActive(Boolean.TRUE);
		role2.setName("ROLE_E_BANKING");
		role2.setDescription("e-banking  role");
		UserRole userRole2 = roleService.getByName(role2.getName());
		if(userRole2==null){
			roleService.save(role2);
		}
		UserRole role3 = new UserRole();
		role3.setActive(Boolean.TRUE);
		role3.setName("ROLE_BRANCH_OFFICER");
		role3.setDescription("branch officer role");
		UserRole userRole3 = roleService.getByName(role3.getName());
		if(userRole3==null){
			roleService.save(role3);
		}
	}
}
