package com.projectmanager.microservices.usersservice.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.microservices.usersservice.entities.User;
import com.projectmanager.microservices.usersservice.entities.UserData;
import com.projectmanager.microservices.usersservice.entities.Users;
import com.projectmanager.microservices.usersservice.repositories.UsersRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//@EnableEurekaClient
public class UsersController {

	@Autowired
	UsersRepository repo;

	@Autowired
	Environment env;

	@PostMapping("/adduser")
	public void addUser(@RequestBody UserData input) {
		User user = new User();
		BeanUtils.copyProperties(input, user);
		repo.save(user);
	}

	@GetMapping("/getusers")
	public Users retrieveUser() {
		Set<User> dbData = repo.findAll();
		Users result = new Users();
		result.setPort(env.getProperty("local.server.port"));

		for (User data : dbData) {
			UserData user = new UserData();
			user.setUserID(String.valueOf(data.getUserID()));
			user.setEmpID(data.getEmpID());
			user.setfName(data.getfName());
			user.setlName(data.getlName());
			result.addUsers(user);
		}
		return result;
	}

	@DeleteMapping("/deleteuser/{uid}")
	public void deleteUser(@PathVariable String uid) {
		repo.deleteById(Long.parseLong(uid));
	}

	@PutMapping("/updateuser/{uid}")
	public void updateUser(@RequestBody User input, @PathVariable String uid) {
		Optional<User> dbData = repo.findById(Long.parseLong(uid));
		User existUser = new User();
		if (dbData.isPresent()) {
			existUser = dbData.get();
		}
		existUser.setfName(input.getfName());
		existUser.setlName(input.getlName());
		existUser.setEmpID(input.getEmpID());
		repo.save(existUser);
	}
	
	@GetMapping("/getuser/{uid}")
	public User getTask(@PathVariable long uid) {
		Optional<User> dbData = repo.findById(uid);
		User existUser = new User();
		if (dbData.isPresent()) {
			existUser = dbData.get();
		}
		return existUser;
	}

}
