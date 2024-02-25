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

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Seat;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.SeatService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("Seat")
public class SeatController {
     @Autowired
     SeatService sService;
     
     @PostMapping("save")
     public ResponseEntity<ResponseStructure<Seat>> saveSeats(@Valid @RequestBody Seat seats,BindingResult result ,@RequestParam String adminemail,@RequestParam String adminpassword)
 	{
 		return sService.saveSeats(seats, adminemail, adminpassword);
 	}
 	
 	@GetMapping("find")
 	public ResponseEntity<ResponseStructure<Seat>> findSeats(int seatid)
 	{
 		return sService.findSeats(seatid);
 	}
 	
 	@DeleteMapping("delete")
 	public ResponseEntity<ResponseStructure<Seat>> delelteSeats(@RequestParam int seatid ,@RequestParam String adminemail,@RequestParam String adminpassword)
 	{
 		return sService.deleteSeats(seatid, adminemail, adminpassword);
 	}
 	
 	@PutMapping("update")
 	public ResponseEntity<ResponseStructure<Seat>> updateSeats(@RequestBody Seat seats, @RequestParam int seatid ,@RequestParam String adminemail,@RequestParam String adminpassword)
 	{
 		return sService.updateSeats(seats, seatid, adminemail, adminpassword);
 	}
 	
 	@GetMapping("findAll")
 	public ResponseEntity<ResponseStructure<List<Seat>>> findAllSeats()
 	{
 		return sService.findAllSeats();
 	}
 }