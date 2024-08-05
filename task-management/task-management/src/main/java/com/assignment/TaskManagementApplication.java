package com.assignment;

import com.assignment.constants.Role;
import com.assignment.entity.UserEntity;
import com.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class TaskManagementApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<UserEntity> user = userRepository.findUserByEmail("admin@gmail.com");
		if (user.isEmpty()) {
			UserEntity userEntity = new UserEntity();
			userEntity.setName("Super Admin");
			userEntity.setEmail("admin@gmail.com");
			userEntity.setRole(Role.ADMIN);
			userEntity.setPassword(passwordEncoder.encode("adminPwd"));
			userRepository.save(userEntity);
		}
	}
}
