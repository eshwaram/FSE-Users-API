package com.projectmanager.microservices.usersservice.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.projectmanager.microservices.usersservice.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	Set<User> findAll();
}
