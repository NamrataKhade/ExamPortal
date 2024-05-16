

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Starting Code .......");
		
//		User user = new User();
//		user.setFirstName("Namrata");
//		user.setLastName("Karad");
//		user.setUsername("namrata123");
//		user.setPassword("namrata123");
//		user.setEmail("namrata@gmail.com");
//		user.setProfile("default.png");
//		
//		Role role1=new Role();
//		role1.setRoleId(11L);
//		role1.setRoleName("ADMIN"); 
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		
//		UserRole userRole=new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
	}

}
