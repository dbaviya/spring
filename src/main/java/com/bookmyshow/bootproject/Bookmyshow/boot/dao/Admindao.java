package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Adminrepo;

@Repository
public class Admindao {
  @Autowired
  Adminrepo aRepo;
  public Admin saveAdmin(Admin admin)
	{
		return aRepo.save(admin);
  
  }
  public Admin findadmin(int adminId) {
	  Optional<Admin>oadmin= aRepo.findById(adminId);
	if(oadmin.isPresent()) {
		return oadmin.get();
	}
	  return null;
	  
  }
  public Admin deleteAdmin(int adminId) {
	  Admin admin=findadmin(adminId);
	  return admin;
  }
  public Admin updateadmin(Admin admin, int adminId) {
	  Admin exAdmin = findadmin(adminId);
	  if(exAdmin != null){
		  exAdmin.setAdminId(adminId);
		  return aRepo.save(admin);
		  }
	  return null;

  }
  public List<Admin> findAllAdmin()
	{
		List<Admin> admin = aRepo.findAll();
		return admin;
	}
  public Admin adminLogin(String adminemail,String adminpassword) {
		
		return aRepo.adminLogin(adminemail, adminpassword);
	}
  
}
