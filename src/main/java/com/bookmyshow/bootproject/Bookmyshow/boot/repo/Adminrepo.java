package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
public interface Adminrepo extends JpaRepository<Admin ,Integer> {

	@Query("select a from Admin a where a.adminemail=?1 and a.adminpassword=?2")
	public Admin adminLogin(String adminemail, String adminpassword);
	
	
   }
