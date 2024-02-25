package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;

public interface Userrepo extends JpaRepository<User, Integer>{

	
	@Query("select u from User u where u.email = ?1 and u.password = ?2")
	public User userLogin(String email, String Password);
	

}
