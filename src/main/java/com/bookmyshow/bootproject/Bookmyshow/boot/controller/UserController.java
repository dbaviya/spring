package com.bookmyshow.bootproject.Bookmyshow.boot.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;
import com.bookmyshow.bootproject.Bookmyshow.boot.dto.Userdto;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.UserService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService uService;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Userdto>> saveUser(@Valid @RequestBody User user,BindingResult result)
	{
		return uService.saveUser(user);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<Userdto>> findUser(@RequestParam int userid)
	{
		return uService.findUser(userid);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<Userdto>> delelteUser(@RequestParam int userid,@RequestParam String email,@RequestParam String password)
	{
		return uService.deleteUser(userid, email, password);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Userdto>> updateUser(@RequestBody User user,@RequestParam int userid,@RequestParam String email,@RequestParam String password)
	{
		return uService.updateUser(user, userid, email, password);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<Userdto>>> findAllUsers()
	{
		return uService.findAllUser();
	}
}