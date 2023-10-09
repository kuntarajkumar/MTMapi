package com.api.mtm.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mtm.entitys.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
}
