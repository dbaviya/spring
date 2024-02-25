package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Userrepo;

@Repository
public class Userdao {
	@Autowired
	Userrepo uRepo;
	
	public User saveUser(User user)
	{
		return uRepo.save(user);
	}
	
	public User findUser(int userid)
	{
		Optional<User> opUser= uRepo.findById(userid);
		if(opUser.isPresent())
		{
			return opUser.get();
		}
		return null;
	}
	
	public User deleteUser(int userid)
	{
		User u = findUser(userid);
		uRepo.delete(u);
		return u;
		
	}
	
	public User updateUser(User user, int userid)
	{
		User exu= findUser(userid);
		
		if(exu!= null)
		{
			user.setUserid(userid);
			return uRepo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser()
	{
		
		List<User> user=uRepo.findAll();
		return user;

	}
	
	public User userLogin(String email, String password)
	{
		User u=uRepo.userLogin(email, password);
		return u;
	}
	 
   }



