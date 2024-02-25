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
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.dto.Admindto;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.AdminService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
	@RequestMapping("Admin")
	public class AdminController {
	
	
		@Autowired
		AdminService aService;
		
		@PostMapping("save")
		
		public  ResponseEntity<ResponseStructure<Admindto>> saveAdmin(@Valid @RequestBody Admin admin, BindingResult result)
		{
			return aService.saveAdmin(admin);
		}
		
		@GetMapping("find")
		public ResponseEntity<ResponseStructure<Admindto>> findAdmin(@RequestParam int adminId)
		{
			return aService.findAdmin(adminId);
		}
		
		@DeleteMapping("delete")
		public ResponseEntity<ResponseStructure<Admindto>> deleteAdmin(@RequestParam int adminId,@RequestParam String adminemail,@RequestParam String adminpassword)
		{
			return aService.deleteAdmin(adminId, adminemail , adminpassword);
		}
		@PutMapping("update")
		public ResponseEntity<ResponseStructure<Admindto>> updateAdmin(@RequestBody Admin admin,@RequestParam int adminId,@RequestParam String adminemail,@RequestParam String adminpassword)
		{
			return aService.updateAdmin(admin, adminId, adminemail, adminpassword);
		}
		
		@GetMapping("findAll")
		public ResponseEntity<ResponseStructure<List<Admindto>>> findAllAdmin()
		{
			return aService.findAllAdmin();
		}
		
		@PutMapping("assignTheatre")
		public ResponseEntity<ResponseStructure<Admindto>> assignTheatre(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.assignTheatreToAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		@PutMapping("deleteTheatre")
		public ResponseEntity<ResponseStructure<Admin>> deleteTheatre(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.deleteTheatreFromAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		@PutMapping("removeTheatre")
		public ResponseEntity<ResponseStructure<Admin>> removeTheatre(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.removeTheatreFromAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		@PutMapping("assignUser")
		public ResponseEntity<ResponseStructure<Admindto>> assignUser(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.assignTheatreToAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		@PutMapping("deleteUser")
		public ResponseEntity<ResponseStructure<Admin>> deleteUser(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.deleteUserFromAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		@PutMapping("removeUser")
		public ResponseEntity<ResponseStructure<Admin>> removeUser(@RequestParam int adminId,@RequestParam int TheatreId,@RequestParam String adminemail,@RequestParam String adminPassword)
		{
			return aService.removeUserFromAdmin(adminId, TheatreId, adminemail, adminPassword);
		}
		
}

		